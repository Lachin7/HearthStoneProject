package controller;

import com.google.gson.annotations.Expose;
import controller.actionVisitors.card.*;
import controller.actionVisitors.heroPower.*;
import controller.actionVisitors.passive.*;
import controller.util.Mapper;
import controller.util.QuestRewardMaker;

import gui.myComponents.MyCardButton;
import models.Character;
import resLoader.MyAudioPlayer;
import models.Cards.*;
import models.Heroes.*;
import models.Player;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public abstract class BoardController {

    @Expose private File eventsLog;
    @Expose private FileWriter fileWriter;
    @Expose protected Random random ;
    @Expose protected CardController  cardController;
    @Expose protected int Mana , switchTimes=0;
    @Expose protected MyAudioPlayer audioPlayer;
    @Expose protected InitialMoveCardVisitor initialMoveVisitor;
    @Expose protected InitialSetupsPassiveVisitor initialSetupsPassiveVisitor;
    @Expose protected InitialSetUpHeroPowerVisitor initialSetUpHeroPowerVisitor;
    @Expose protected EnemyPlaysCardVisitor enemyPlaysCardVisitor;
    @Expose protected SummonCardVisitor summonCardVisitor;
    @Expose protected EndTurnCardVisitor endTurnCardVisitor;
    @Expose protected EndTurnPassiveVisitor endTurnPassiveVisitor;
    @Expose protected EnemyPlayesHeroPowerVisitor enemyPlayesHeroPowerVisitor;
    @Expose protected DrawACardVisitor drawACardVisitor;
    @Expose protected TakesDamageVisitor takesDamageVisitor;
    @Expose protected Player friendlyPlayer, enemyPlayer ;
    @Expose protected Mapper mapper;
    @Expose protected ArrayList<QuestRewardMaker> friendlyQuestRewards = new ArrayList<>(), enemyQuestRewards = new ArrayList<>();
    @Expose protected ArrayList<QuestRewardMaker> questRewardMakers = new ArrayList<>();
    @Expose private int  turnCardDrawNum = 1;


    public BoardController() {
        random = new Random();
        cardController = new CardController();
        makeEvents();
        mapper = Mapper.getInstance();

        audioPlayer = MyAudioPlayer.getInstance();
        initVisitors();

        setPlayers();
        initialDeckToHand(friendlyPlayer);
        initialDeckToHand(enemyPlayer);
//        setCurrentPlayer(friendlyPlayer);
    }

    private void initVisitors() {
        initialMoveVisitor = new InitialMoveCardVisitor();
        initialSetupsPassiveVisitor = new InitialSetupsPassiveVisitor();
        initialSetUpHeroPowerVisitor = new InitialSetUpHeroPowerVisitor();
        endTurnCardVisitor = new EndTurnCardVisitor();
        endTurnPassiveVisitor = new EndTurnPassiveVisitor();
        drawACardVisitor = new DrawACardVisitor();
        takesDamageVisitor = new TakesDamageVisitor();
        summonCardVisitor = new SummonCardVisitor();
        enemyPlaysCardVisitor = new EnemyPlaysCardVisitor();
        enemyPlayesHeroPowerVisitor = new EnemyPlayesHeroPowerVisitor();
    }

    protected void chooseFriendAsMain(){
        friendlyPlayer = Controller.getInstance().getMainPlayer();
        friendlyPlayer.setCurrentMana(0);
        friendlyPlayer.setHandsCards(new ArrayList<>());
        friendlyPlayer.setFieldCardsInGame(new ArrayList<>());
        friendlyPlayer.setDeckCardsInGame(new ArrayList<>());
        for(Card card : friendlyPlayer.getPlayersDeck().getCards()) friendlyPlayer.getDeckCardsInGame().add(cardController.createCard(card.getName()));
    }

    protected void chooseEnemyAsMain(){
        enemyPlayer = new Player();
        enemyPlayer.setPlayersDeckCards(new ArrayList<>());
        for(Card card : friendlyPlayer.getDeckCardsInGame())enemyPlayer.getDeckCardsInGame().add(cardController.createCard(card.getName()));
//            enemyPlayer.setDeckCardsInGame(new ArrayList<Card>(Arrays.asList(creatCardFromjson("SwarmOfLocusts"),creatCardFromjson("SwarmOfLocusts"),creatCardFromjson("PharaohsBlessing"),creatCardFromjson("BookOfSpecters"),creatCardFromjson("DreadScale"),creatCardFromjson("Starscryer"),creatCardFromjson("FungalBruiser"),creatCardFromjson("BeamingSidekick"),creatCardFromjson("SerratedTooth"),creatCardFromjson("BonechewerVanguard"),creatCardFromjson("CurioCollector"),creatCardFromjson("Sathrovarr"),creatCardFromjson("ScrapDeadlyShot"),creatCardFromjson("FriendlySmith"),creatCardFromjson("LostSpirit"),creatCardFromjson("HighPriestAmet"),creatCardFromjson("DreadScale"),creatCardFromjson("LearnDraconic"),creatCardFromjson("ScrapDeadlyShot"),creatCardFromjson("Starscryer"),creatCardFromjson("FrozenShadoweaver"),creatCardFromjson("CurioCollector"))));
        enemyPlayer.setPlayersChoosedHero(new Mage());
        enemyPlayer.setPlayerName("aaa");
    }

    protected abstract void setPlayers();

    public void initialGameSetUps(Player player){
        setUpSpecialPowers(player);
        setUpPassives(player);
    }

    protected void setUpPassives(Player player){
        player.getInfoPassive().accept(initialSetupsPassiveVisitor,player,this);
    }

    protected void initialDeckToHand(Player player){
        moveRandomCards(player.getDeckCardsInGame(),player.getHandsCards(),3,true);
    }

    protected boolean isANewGame(){
        return (Controller.getInstance().getMainPlayer().getMakeNewGame()==null || Controller.getInstance().getMainPlayer().getMakeNewGame());
    }

    public void getFirstChoices(ArrayList<String> replacedCard, Player player) {
        for(String name : replacedCard){
            Card card = cardController.createCard(name);
            player.getHandsCards().remove(card);
            if(player==friendlyPlayer)draw();
            if(player==enemyPlayer){
                switchTimes++;
                draw();
                switchTimes++;
            }
            player.getDeckCardsInGame().add(card);
        }
    }

    protected void setUpSpecialPowers(Player player){
       String name = player.getPlayersChoosedHero().getName();
       if(name.equals("Mage")){
           for (Card card : player.getDeckCardsInGame()) {
               if (card instanceof Spell) {
                   card.setManaCost(card.getManaCost()-2);
                   if (card.getManaCost() < 0) card.setManaCost(0);
               }
           }
       }
       else if(name.equals("Rogue")){
           for(Card card : player.getDeckCardsInGame()){
               if(card.getHeroClass()!= Card.HeroClass.NEUTRAL && card.getHeroClass()!= Card.HeroClass.ROGUE){
                   card.setManaCost(card.getManaCost()-2);
                   if (card.getManaCost() < 0) card.setManaCost(0);
               }
           }
       }
       else if(name.equals("Warlock")) player.getPlayersChoosedHero().setHP(35);
       else if(name.equals("Hunter")){
           for(Card card : player.getDeckCardsInGame()) if(card instanceof Minion) ((Minion) card).setCanAttack(true);
       }
       else if (name.equals("Priest")){
           for (Card card : player.getDeckCardsInGame()){
               if(card instanceof Spell && ((Spell) card).getCanRestore()) ((Spell) card).setRestoreAmount(((Spell) card).getRestoreAmount()*2);
           }
       }
    }

    private void moveRandomCards(ArrayList<Card> source , ArrayList<Card> target, int num, boolean deleteFromOriginal){
        for (int times = 0; times < num; times++) {
            int i = random.nextInt(source.size());
            target.add(source.get(i));
            if(deleteFromOriginal)source.remove(i);
        }
    }

    public void changeCharacter(Character character, int hpToAdd, int attackToAdd){
       if(character!=null) character.setHP(character.getHP()+hpToAdd);
    }

    public void makeEvents(){
        eventsLog = new File("./src/main/java/logs/events/eventsLog");
        try {
            fileWriter = new FileWriter(eventsLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logGameEvent(String message){
        try {
            fileWriter.append(message);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** methods for drawing a card from deck to hand */

    protected Card shuffleAndGetCard(){
        Collections.shuffle(getCurrentPlayer().getDeckCardsInGame());
        return getCurrentPlayer().getDeckCardsInGame().get(0);
    }

    private boolean canDraw(){
        if(getCurrentPlayer().getDeckCardsInGame().size()==0) {
            JOptionPane.showMessageDialog(null,"you don't have any cards in your deck\n your hero will lose a health:(");
            getCurrentPlayer().getPlayersChoosedHero().setHP(getCurrentPlayer().getPlayersChoosedHero().getHP()-1);
            return false;
        }
        else if(getCurrentPlayer().getHandsCards().size()==12) {
            JOptionPane.showMessageDialog(null,"maximum number of hand cards for a play is 12 , unfortunately you lost the coming card");
            return false;
        }
        return true;
    }

    public void draw() {
        if(canDraw()) draw(shuffleAndGetCard());
    }

    public void draw(Card.type type){
        if(canDraw()){
            for(Card card : getCurrentPlayer().getDeckCardsInGame()) if(card.getType()==type){
                draw(card);
                return;
            }
        }
    }

    public void drawDiscard(int num,Card.type type){
        for (int i = 0; i <num ; i++) {
            Card card = shuffleAndGetCard();
            draw(card);
            if(card.getType()==type) getCurrentPlayer().getHandsCards().remove(card);
        }
    }

    private void draw(Card card){
        getCurrentPlayer().getHandsCards().add(card);
        getCurrentPlayer().getDeckCardsInGame().remove(card);
        for(Minion minion: getCurrentPlayer().getFieldCardsInGame()) minion.accept(drawACardVisitor,null,this);
        logGameEvent("draw card " + getCurrentPlayer().getDeckCardsInGame().get(0).getName() + " from deck to hand cards\n");
        getPlayerLogger().log(Level.INFO, "draw card - PlayPanel\n");
    }

    private void turnDraw(){
        for (int i = 0; i < turnCardDrawNum ; i++) draw();
    }

    public void draw(int times) {
        for (int i = 0; i < times; i++) {
            draw();
        }
    }

    /** play methods */
    public void initialPlay(Card card){
        applyManaCostForCard(card);
        getCurrentPlayer().getHandsCards().remove(card);
        if(card instanceof Minion) playMinion((Minion) card);
        if(card instanceof Spell) playSpell(card);
        if(card instanceof Weapon) playWeapon(card);
        checkIfMinionsAreDead(friendlyPlayer);
        checkIfMinionsAreDead(enemyPlayer);
        if(!card.getHasInitialMoveTarget())card.accept(initialMoveVisitor,null,this);
        logGameEvent("Player " + getCurrentPlayer().getPlayerID()+ " played a "+ card.getType() +  " card : "+card.getName() +"\n");
        getPlayerLogger().log(Level.INFO,"played card - PlayPanel");
    }

    public void playTarget(Card card, long id){
        Character target = null;
        if(id!=-1)  target= getCharacterOfTarget(id);
        card.accept(initialMoveVisitor,target,this);
        checkIfMinionsAreDead(friendlyPlayer);
        checkIfMinionsAreDead(enemyPlayer);
        logGameEvent("Player " + getCurrentPlayer().getPlayerID()+"-  selected the target : "+ target.getName()+ " for :  "+ card.getName());
        getPlayerLogger().log(Level.INFO,"-  selected the target : \"+ target.getName()+ \" for :  \"+ card.getName()");
    }

    private void playWeapon(Card card){
        getCurrentPlayer().getPlayersChoosedHero().setWeapon((Weapon)card);
    }

    private void playSpell(Card card) {
        audioPlayer.playQuick("spell.wav");
    }

    private void playMinion(Minion card){
        audioPlayer.playQuick("Whip Crack-SoundBible.com-330576409.wav");
        Card newCard = cardController.createCard(card.getName());
        newCard.setId(card.getId());
        getCurrentPlayer().getFieldCardsInGame().add((Minion) newCard);
        for(Minion minion : getOpponentPlayer().getFieldCardsInGame())minion.accept(enemyPlaysCardVisitor,card,this);
        getOpponentPlayer().getPlayersChoosedHero().getHeroPower().accept(enemyPlayesHeroPowerVisitor,card,this);
    }


    public void applyManaCostForCard(Card card){
        getCurrentPlayer().setCurrentMana(getCurrentPlayer().getCurrentMana()-card.getManaCost());
        for(QuestRewardMaker questRewardMaker: questRewardMakers) questRewardMaker.cardIsDrawn(card.getManaCost(),card.getType());
    }

    public void applyManaCost(int cost){
        getCurrentPlayer().setCurrentMana(getCurrentPlayer().getCurrentMana()-cost);
    }


    public void makeQuestReward(int manaToSpend, Card.type type, String cardToSummon, String name){
        QuestRewardMaker qrm = new QuestRewardMaker(manaToSpend,type,cardToSummon,this,name);
        questRewardMakers.add(qrm);
        if(switchTimes%2==0)friendlyQuestRewards.add(qrm);
        else enemyQuestRewards.add(qrm);
    }

    public void discover(Card.type Type) {
        ArrayList<Card> cards = new ArrayList<>();
        moveRandomCards(cardController.getTypeCards(Type),cards,3,false);
        mapper.setDiscoverPanel(cards.get(0).getName(),cards.get(1).getName(),cards.get(2).getName());
    }

    public void restoreHp(Character character){
        character.setHP(character.getMaxHp());
    }

    public void attack(Character attacker , Character target){
        if(attacker!=null&&target!=null) {
            if (target instanceof Minion && ((Minion)target).hasDivineShield()) ((Minion)target).setHasDivineShield(false);
            else target.setHP(target.getHP()-attacker.getAttack());
            attacker.setHP(attacker.getHP()-target.getAttack());
            if(attacker instanceof Minion)((Minion)attacker).setCanAttack(false);
            logGameEvent("Character : "+ attacker.getName()+ " attacked character : "+ target.getName());
        }
    }

    public synchronized void endTurn() {
        for(Card card : getCurrentPlayer().getFieldCardsInGame())card.accept(endTurnCardVisitor,null,this);
        getCurrentPlayer().getInfoPassive().accept(endTurnPassiveVisitor,getCurrentPlayer(),this);
        if(Mana < 10)Mana++;
        if(Mana+getCurrentPlayer().getInitialMana()<=10)getCurrentPlayer().setCurrentMana(Mana+getCurrentPlayer().getInitialMana());
//        friendlyPlayer.setCurrentMana(10);
//        enemyPlayer.setCurrentMana(10);
        switchTimes++;
        playAI();
        for(Minion minion : getCurrentPlayer().getFieldCardsInGame())minion.setCanAttack(true);
        turnDraw();
    }

    protected void playAI(){}

    protected void checkIfMinionsAreDead(Player player){
        player.getFieldCardsInGame().removeIf(minion -> minion.getHP() <= 0);
    }

    public void checkIfMinionIsDead(Player player ,Minion minion){
        if(player.getFieldCardsInGame().contains(minion)&&minion.getHP()<=0)player.getFieldCardsInGame().remove(minion);
    }

    public Character getCharacterOfTarget(long name) {
//        if(name.equals("hero1")) return getCurrentPlayer().getPlayersChoosedHero();
//        if(name.equals("hero2")) return getOpponentPlayer().getPlayersChoosedHero();
         return getTheCardWithID(name);
    }

    private Minion getTheCardWithID(long Id) {
        for(Minion minion : getCurrentPlayer().getFieldCardsInGame()) if(minion.getId()==Id) return minion;
        for(Minion minion : getOpponentPlayer().getFieldCardsInGame()) if(minion.getId()==Id) return minion;
        return null;
    }

    public void summon(String cardName, int number) {
        for (int i = 0; i < number ; i++) {
            if(getCurrentPlayer().getFieldCardsInGame().size()<7) {
                Minion card = (Minion) cardController.createCard(cardName);
                getCurrentPlayer().getFieldCardsInGame().add(card);
                mapper.addToPlayField(card,switchTimes);
                for(Minion minion: getCurrentPlayer().getFieldCardsInGame())minion.accept(summonCardVisitor,card,this);
                logGameEvent("Player " + getCurrentPlayer().getPlayerID()+"-  summoned the card : "+ cardName);
                getPlayerLogger().log(Level.INFO,"-  summoned the card : "+ cardName);
            }
        }
    }

    public void changeMinion(Minion minion, int hpToAdd, int attackToAdd){
        if(hpToAdd<0&&minion.hasDivineShield())minion.setHasDivineShield(false);
        else {
            minion.setHP(minion.getHP() + hpToAdd);
            if (hpToAdd < 0) {
                minion.accept(takesDamageVisitor, null, this);
                audioPlayer.playQuick("attack.wav");
            }
        }
        minion.setAttack(minion.getAttack() + attackToAdd);
    }

    public void syncFriendlyFieldComponents(ArrayList<MyCardButton> fieldComponents) {
        removeDeadOnes(getFriendlyFieldCards(),fieldComponents);
        getFriendlyPlayer().setFieldCardsInGame(syncTheOrder(getFriendlyFieldCards(),fieldComponents));
    }

    public void syncEnemyFieldComponents(ArrayList<MyCardButton> fieldComponents) {
        removeDeadOnes(getEnemyFieldCards(),fieldComponents);
        getEnemyPlayer().setFieldCardsInGame(syncTheOrder(getEnemyFieldCards(),fieldComponents));
    }

    private ArrayList<Minion> syncTheOrder(ArrayList<Minion> minions,ArrayList<MyCardButton> buttons ){
        ArrayList<Minion> result = new ArrayList<>();
        for(MyCardButton myCardButton : buttons){
            for(Minion minion : minions){
                if(minion.getId()==(myCardButton.getId()) && minion.getHP()>0){
                    result.add(minion);
                    break;
                }
            }
        }
        return result;
    }

    private boolean cardIdExist(ArrayList<Minion> cards, MyCardButton button){
        checkIfMinionsAreDead(friendlyPlayer);
        checkIfMinionsAreDead(enemyPlayer);
        for(Card card : cards) {
            if (card.getId() == button.getId()) return true;
        }
        return false;
    }

    protected void removeDeadOnes(ArrayList<Minion> cards, ArrayList<MyCardButton> buttons){
        buttons.removeIf(button -> !cardIdExist(cards, button));
    }

    public void transformMinion(Minion minion, int hp,int attack){
        minion.setHP(hp);
        minion.setAttack(attack);
    }

    public void changeHero(Hero hero, int hpToAdd, int attackToAdd){
        hero.setHP(hero.getHP()+hpToAdd);
        hero.setAttack(hero.getAttack()+attackToAdd);
    }

    public void giveMinionTaunt(Minion minion){
        minion.setHasTaunt(true);
    }

    public void giveMinionDivineShield(Minion minion){
        minion.setHasDivineShield(true);
    }

    public void giveMinionRush(Minion target){
        target.setRush(true);
    }

    public boolean tauntExist(int turn){
        if(turn%2==0)return checkTaunt(enemyPlayer);
        else return checkTaunt(friendlyPlayer);
    }

    private boolean checkTaunt(Player player){
        for(Minion minion : player.getFieldCardsInGame()) if(minion.hasTaunt())return true;
        return false;
    }

    public File getEventsLog() {
        return eventsLog;
    }

    public void setTurnCardDrawNum(int turnCardDrawNum) {
        this.turnCardDrawNum = turnCardDrawNum;
    }

    public void playHeroPower() {
      if((!getCurrentPlayer().getPlayersChoosedHero().getHeroPower().isHasTarget()) && hasManaForPower())
          getCurrentPlayer().getPlayersChoosedHero().getHeroPower().accept(initialSetUpHeroPowerVisitor,null,this);
    }

    public void playTargetetPower(long id){
        getCurrentPlayer().getPlayersChoosedHero().getHeroPower().accept(initialSetUpHeroPowerVisitor,getTheCardWithID(id),this);
    }

    public boolean hasManaForPower(){
       return getCurrentPlayer().getPlayersChoosedHero().getHeroPower().getMana() <= getCurrentPlayer().getCurrentMana();
    }

    public Player getCurrentPlayer() {
        if(switchTimes%2==0)return friendlyPlayer;
        return enemyPlayer;
    }

    public Player getFriendlyPlayer() {
        return friendlyPlayer;
    }
    public Player getEnemyPlayer() {
        return enemyPlayer;
    }
    public Player getOpponentPlayer() {
        if (getCurrentPlayer().getPlayerID()==(friendlyPlayer.getPlayerID())) return enemyPlayer;
        else return friendlyPlayer;
    }

    public Logger getPlayerLogger(){
        return Controller.getInstance().getPlayerController().getPlayerLOGGER();
    }

    public ArrayList<Card> getFriendlyHandCards(){
        return getFriendlyPlayer().getHandsCards();
    }
    public ArrayList<Card> getEnemyHandCards(){
        return getEnemyPlayer().getHandsCards();
    }
    public ArrayList<Minion> getFriendlyFieldCards(){
        return getFriendlyPlayer().getFieldCardsInGame();
    }
    public ArrayList<Minion> getEnemyFieldCards(){
        return getEnemyPlayer().getFieldCardsInGame();
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public CardController getCardController() {
        return cardController;
    }
    public int getMana() {
        return Mana;
    }

    public void setMana(int mana) {
        Mana = mana;
    }

    public ArrayList<QuestRewardMaker> getFriendlyQuestRewards() {
        return friendlyQuestRewards;
    }

    public ArrayList<QuestRewardMaker> getQuestRewardMakers() {
        return questRewardMakers;
    }

    public void setDiscovery(String name) {
        getCurrentPlayer().getPlayersChoosedHero().setWeapon((Weapon) cardController.createCard(name));
    }

    public ArrayList<QuestRewardMaker> getEnemyQuestRewards() {
        return enemyQuestRewards;
    }

    public boolean enemyHasTaunt(){
        ArrayList<Minion> cards = getOpponentPlayer().getFieldCardsInGame();
        for(Card card : cards) if(((Minion)card).hasTaunt()) return true;
        return false;
    }

    public void checkGameOver(){
        if(enemyPlayer.getPlayersChoosedHero().getHP()<=0){
            Controller.getInstance().getMainPlayer().getPlayersDeck().setAllGamesPlayed(Controller.getInstance().getMainPlayer().getPlayersDeck().getAllGamesPlayed()+1);
            Controller.getInstance().getMainPlayer().getPlayersDeck().setWinGamesPlayed(Controller.getInstance().getMainPlayer().getPlayersDeck().getWinGamesPlayed()+1);
        }
        if(friendlyPlayer.getPlayersChoosedHero().getHP()<=0){
            Controller.getInstance().getMainPlayer().getPlayersDeck().setAllGamesPlayed(Controller.getInstance().getMainPlayer().getPlayersDeck().getAllGamesPlayed()+1);
        }
    }

    private void transformWeapon(Weapon weapon, int durability, int attack){
        weapon.setAttack(attack);
        weapon.setDurability(durability);
    }



    //    private void selectFirstCards() {
//        firstDrawCards = new MyCardButton[3];
//        JList list = new JList(firstDrawCards);
//        //TODO the quest and reward
//        for(int i = 0; i<3; i++){
//            firstDrawCards[i] = new MyCardButton( getCurrentPlayer().getHandsCards().get(i).getName(),50,null);
//        }
//        JOptionPane.showMessageDialog(null, list, "you can select any to discard it", JOptionPane.PLAIN_MESSAGE);
//        for ( Object object : list.getSelectedValuesList()) {
//            Card card = cardController.creatCard(((JButton) object).getName());
//            getCurrentPlayer().getHandsCards().remove(card);
//            draw();
//            getCurrentPlayer().getDeckCardsInGame().add(card);
//        }
//    }


//    private void selectPassives(){
//        passives = InfoPassive.getRandomPassives(3);
//        InfoPassives = new String[]{passives.get(0).getName(),passives.get(1).getName(),passives.get(2).getName()};
//        int response = JOptionPane.showOptionDialog(null,passives.get(0).getName() + " : " + passives.get(0).getExplanation()+
//                        "\n" + passives.get(1).getName() + " : " + passives.get(1).getExplanation()+ "\n" +passives.get(2).getName() + " : " + passives.get(2).getExplanation(),
//                 getCurrentPlayer().getPlayerName()+ " : choose Info Passive", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, InfoPassives, InfoPassives[0]);
//        getCurrentPlayer().setInfoPassive(passives.get(response));
//        getPlayerLogger().log(Level.INFO,"passive "+ passives.get(response)+" is selected - PlayPanel");
//    }



    //    public ArrayList<Card> getPlayersHandCards(){
//        return Controller.getInstance().getCurrentPlayer().getHandsCards();
//    }
//    public ArrayList<Card> getPlayerFieldCards() {
//        return Controller.getInstance().getCurrentPlayer().getFieldCardsInGame();
//    }
//    public ArrayList<Card> getPlayersDeckCards(){
//        return Controller.getInstance().getCurrentPlayer().getDeckCardsInGame();
//    }

    //    private Card[] discoverType(Card.type type){
//        Card[] result = new Card[3];
//        int i = 0;
//        for (Card card: cardController.getTypeCards(type)) {
//            if(i==3) break;
//            result[i] = card;
//            i++;
//        }
//        return result;
//    }

}
