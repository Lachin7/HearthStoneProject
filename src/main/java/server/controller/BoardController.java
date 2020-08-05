package server.controller;

import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import models.board.Side;
import request_response.response.SetDiscoverPanel;
import server.ClientHandler;
import server.controller.actionVisitors.card.*;
import server.controller.actionVisitors.heroPower.*;
import server.controller.actionVisitors.passive.*;
import server.controller.util.QuestRewardMaker;

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

    @Getter
    private File eventsLog;
    private FileWriter fileWriter;
    @Getter
    @Setter
    protected Random random;
    @Getter
    protected CardController cardController;
    @Getter
    @Setter
    protected int Mana, switchTimes = 0,turnCardDrawNum = 1;
    protected MyAudioPlayer audioPlayer;
    protected InitialMoveCardVisitor initialMoveVisitor;
    protected InitialSetupsPassiveVisitor initialSetupsPassiveVisitor;
    protected InitialSetUpHeroPowerVisitor initialSetUpHeroPowerVisitor;
    protected EnemyPlaysCardVisitor enemyPlaysCardVisitor;
    protected SummonCardVisitor summonCardVisitor;
    protected EndTurnCardVisitor endTurnCardVisitor;
    protected EndTurnPassiveVisitor endTurnPassiveVisitor;
    protected EnemyPlayesHeroPowerVisitor enemyPlayesHeroPowerVisitor;
    protected DrawACardVisitor drawACardVisitor;
    protected TakesDamageVisitor takesDamageVisitor;
    @Getter
    protected Player friendlyPlayer, enemyPlayer;
    @Getter
    protected ArrayList<QuestRewardMaker> friendlyQuestRewards = new ArrayList<>(),questRewardMakers = new ArrayList<>(), enemyQuestRewards = new ArrayList<>();
    protected ClientHandler clientHandler;

    public BoardController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        random = new Random();
        cardController = new CardController(clientHandler);
        makeEvents();
        audioPlayer = MyAudioPlayer.getInstance();
        initVisitors();

        setPlayers();
        initialDeckToHand(friendlyPlayer);
        initialDeckToHand(enemyPlayer);
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

    protected void chooseMainAsFriend() {
        friendlyPlayer = clientHandler.getMainPlayer();
        resetPlayer(friendlyPlayer);
    }

    protected void resetPlayer(Player player) {
        player.setCurrentMana(0);
        player.setHandsCards(new ArrayList<>());
        player.setFieldCardsInGame(new ArrayList<>());
        player.setDeckCardsInGame(new ArrayList<>());
        for (Card card : player.getDeck().getCards())
            player.getDeckCardsInGame().add(cardController.createCard(card.getName()));
    }

    protected void chooseMainAsEnemy() {
        enemyPlayer = new Player();
        enemyPlayer.setDeckCardsInGame(new ArrayList<>());
        for (Card card : friendlyPlayer.getDeckCardsInGame())
            enemyPlayer.getDeckCardsInGame().add(cardController.createCard(card.getName()));
//            enemyPlayer.setDeckCardsInGame(new ArrayList<Card>(Arrays.asList(creatCardFromjson("SwarmOfLocusts"),creatCardFromjson("SwarmOfLocusts"),creatCardFromjson("PharaohsBlessing"),creatCardFromjson("BookOfSpecters"),creatCardFromjson("DreadScale"),creatCardFromjson("Starscryer"),creatCardFromjson("FungalBruiser"),creatCardFromjson("BeamingSidekick"),creatCardFromjson("SerratedTooth"),creatCardFromjson("BonechewerVanguard"),creatCardFromjson("CurioCollector"),creatCardFromjson("Sathrovarr"),creatCardFromjson("ScrapDeadlyShot"),creatCardFromjson("FriendlySmith"),creatCardFromjson("LostSpirit"),creatCardFromjson("HighPriestAmet"),creatCardFromjson("DreadScale"),creatCardFromjson("LearnDraconic"),creatCardFromjson("ScrapDeadlyShot"),creatCardFromjson("Starscryer"),creatCardFromjson("FrozenShadoweaver"),creatCardFromjson("CurioCollector"))));
        enemyPlayer.setChoosedHero(new Mage());
        enemyPlayer.setName("enemy");
    }

    protected abstract void setPlayers();

    public void initialGameSetUps(Player player) {
        setUpSpecialPowers(player);
        setUpPassives(player);
    }

    protected void setUpPassives(Player player) {
        player.getInfoPassive().accept(initialSetupsPassiveVisitor, player, this);
    }

    protected void initialDeckToHand(Player player) {
        moveRandomCards(player.getDeckCardsInGame(), player.getHandsCards(), 3, true);
    }

    protected boolean isANewGame() {
        return (clientHandler.getMainPlayer().getMakeNewGame() == null || clientHandler.getMainPlayer().getMakeNewGame());
    }

    public void getFirstChoices(ArrayList<String> replacedCard, Player player) {
        for (String name : replacedCard) {
            Card card = cardController.createCard(name);
            player.getHandsCards().remove(card);
            if (player == friendlyPlayer) draw();
            if (player == enemyPlayer) {
                switchTimes++;
                draw();
                switchTimes++;
            }
            player.getDeckCardsInGame().add(card);
        }
    }

    protected void setUpSpecialPowers(Player player) {
        String name = player.getChoosedHero().getName();
        if (name.equals("Mage")) {
            for (Card card : player.getDeckCardsInGame()) {
                if (card instanceof Spell) {
                    card.setManaCost(card.getManaCost() - 2);
                    if (card.getManaCost() < 0) card.setManaCost(0);
                }
            }
        } else if (name.equals("Rogue")) {
            for (Card card : player.getDeckCardsInGame()) {
                if (card.getHeroClass() != Card.HeroClass.NEUTRAL && card.getHeroClass() != Card.HeroClass.ROGUE) {
                    card.setManaCost(card.getManaCost() - 2);
                    if (card.getManaCost() < 0) card.setManaCost(0);
                }
            }
        } else if (name.equals("Warlock")) player.getChoosedHero().setHP(35);
        else if (name.equals("Hunter")) {
            for (Card card : player.getDeckCardsInGame())
                if (card instanceof Minion) ((Minion) card).setCanAttack(true);
        } else if (name.equals("Priest")) {
            for (Card card : player.getDeckCardsInGame()) {
                if (card instanceof Spell && ((Spell) card).getCanRestore())
                    ((Spell) card).setRestoreAmount(((Spell) card).getRestoreAmount() * 2);
            }
        }
    }

    private void moveRandomCards(List<Card> source, List<Card> target, int num, boolean deleteFromOriginal) {
        for (int times = 0; times < num; times++) {
            int i = random.nextInt(source.size());
            target.add(source.get(i));
            if (deleteFromOriginal) source.remove(i);
        }
    }

    public void changeCharacter(Character character, int hpToAdd, int attackToAdd) {
        if (character != null) {
            if (character instanceof  Minion) changeMinion((Minion) character,hpToAdd,attackToAdd);
            if (character instanceof Hero) changeHero((Hero) character,hpToAdd,attackToAdd);
        }
    }

    public void makeEvents() {
        eventsLog = new File("./src/main/java/logs/events/eventsLog");
        try {
            fileWriter = new FileWriter(eventsLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logGameEvent(String message) {
        try {
            fileWriter.append(message);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * methods for drawing a card from deck to hand
     */

    protected Card shuffleAndGetCard() {
        Collections.shuffle(getCurrentPlayer().getDeckCardsInGame());
        return getCurrentPlayer().getDeckCardsInGame().get(0);
    }

    private boolean canDraw() {
        if (getCurrentPlayer().getDeckCardsInGame().size() == 0) {
            JOptionPane.showMessageDialog(null, "you don't have any cards in your deck\n your hero will lose a health:(");
            getCurrentPlayer().getChoosedHero().setHP(getCurrentPlayer().getChoosedHero().getHP() - 1);
            return false;
        } else if (getCurrentPlayer().getHandsCards().size() == getHandCardsLimit()) {
            JOptionPane.showMessageDialog(null, "maximum number of hand cards for a play is 12 , unfortunately you lost the coming card");
            return false;
        }
        return true;
    }

     protected int getHandCardsLimit(){
        return 12;
     }

    public void draw() {
        if (canDraw()) draw(shuffleAndGetCard());
    }

    public void draw(Card.type type) {
        if (canDraw()) {
            for (Card card : getCurrentPlayer().getDeckCardsInGame())
                if (card.getType() == type) {
                    draw(card);
                    return;
                }
        }
    }

    public void drawDiscard(int num, Card.type type) {
        for (int i = 0; i < num; i++) {
            Card card = shuffleAndGetCard();
            draw(card);
            if (card.getType() == type) getCurrentPlayer().getHandsCards().remove(card);
        }
    }

    private void draw(Card card) {
        getCurrentPlayer().getHandsCards().add(card);
        getCurrentPlayer().getDeckCardsInGame().remove(card);
        for (Minion minion : getCurrentPlayer().getFieldCardsInGame()) minion.accept(drawACardVisitor, null, this);
        logGameEvent("draw card " + getCurrentPlayer().getDeckCardsInGame().get(0).getName() + " from deck to hand cards\n");
        getPlayerLogger().log(Level.INFO, "draw card - PlayPanel\n");
    }

    private void turnDraw() {
        for (int i = 0; i < turnCardDrawNum; i++) draw();
    }

    public void draw(int times) {
        for (int i = 0; i < times; i++) {
            draw();
        }
    }

    /**
     * play methods
     */
    public void initialPlay(Card card) {
        applyManaCostForCard(card);
        getCurrentPlayer().getHandsCards().remove(card);
        if (card instanceof Minion) playMinion((Minion) card);
        if (card instanceof Spell) playSpell(card);
        if (card instanceof Weapon) playWeapon(card);
        checkIfMinionsAreDead(friendlyPlayer);
        checkIfMinionsAreDead(enemyPlayer);
        if (!card.getHasInitialMoveTarget()) card.accept(initialMoveVisitor, null, this);
        logGameEvent("Player " + getCurrentPlayer().getID() + " played a " + card.getType() + " card : " + card.getName() + "\n");
        getPlayerLogger().log(Level.INFO, "played card - PlayPanel");
    }

    public void playTarget(Card card, long id) {
        Character target = null;
        if (id != -1) target = getCharacterOfTarget(id);
        card.accept(initialMoveVisitor, target, this);
        checkIfMinionsAreDead(friendlyPlayer);
        checkIfMinionsAreDead(enemyPlayer);
        logGameEvent("Player " + getCurrentPlayer().getID() + "-  selected the target : " + target.getName() + " for :  " + card.getName());
        getPlayerLogger().log(Level.INFO, "-  selected the target : \"+ target.getName()+ \" for :  \"+ card.getName()");
    }

    private void playWeapon(Card card) {
        getCurrentPlayer().getChoosedHero().setWeapon((Weapon) card);
    }

    private void playSpell(Card card) {
        audioPlayer.playQuick("spell.wav");
    }

    private void playMinion(Minion card) {
        audioPlayer.playQuick("Whip Crack-SoundBible.com-330576409.wav");
        Card newCard = cardController.createCard(card.getName());
        newCard.setId(card.getId());
        getCurrentPlayer().getFieldCardsInGame().add((Minion) newCard);
        for (Minion minion : getOpponentPlayer().getFieldCardsInGame())
            minion.accept(enemyPlaysCardVisitor, card, this);
        getOpponentPlayer().getChoosedHero().getHeroPower().accept(enemyPlayesHeroPowerVisitor, card, this);
    }

    public void applyManaCostForCard(Card card) {
        getCurrentPlayer().setCurrentMana(getCurrentPlayer().getCurrentMana() - card.getManaCost());
        for (QuestRewardMaker questRewardMaker : questRewardMakers)
            questRewardMaker.cardIsDrawn(card.getManaCost(), card.getType());
    }

    public void applyManaCost(int cost) {
        getCurrentPlayer().setCurrentMana(getCurrentPlayer().getCurrentMana() - cost);
    }


    public void makeQuestReward(int manaToSpend, Card.type type, String cardToSummon, String name) {
        QuestRewardMaker qrm = new QuestRewardMaker(manaToSpend, type, cardToSummon, this, name);
        questRewardMakers.add(qrm);
        if (switchTimes % 2 == 0) friendlyQuestRewards.add(qrm);
        else enemyQuestRewards.add(qrm);
    }

    public void discover(Card.type Type) {
        ArrayList<Card> cards = new ArrayList<>();
        moveRandomCards(cardController.getTypeCards(Type), cards, 3, false);
        clientHandler.sendResponse("SetDiscoverPanel",new SetDiscoverPanel(new Pair<>(cards.get(0).getId(),cards.get(0).getName()),new Pair<Long,String>(cards.get(1).getId(),cards.get(1).getName()),new Pair<Long,String>(cards.get(2).getId(),cards.get(2).getName())));
    }

    public void restoreHp(Character character) {
        character.setHP(character.getMaxHp());
    }

    public void attack(Character attacker, Character target) {
        if (attacker != null && target != null) {
            if (target instanceof Minion && ((Minion) target).hasDivineShield())
                ((Minion) target).setHasDivineShield(false);
            else changeCharacter(target,-attacker.getHP(),0);
            changeCharacter(attacker,attacker.getHP()-target.getAttack(),0);
            if (attacker instanceof Minion) ((Minion) attacker).setCanAttack(false);
            logGameEvent("Character : " + attacker.getName() + " attacked character : " + target.getName());
            checkIfMinionIsDead(friendlyPlayer, (Minion) target);
        }
    }

    public synchronized void endTurn() {
        for (Card card : getCurrentPlayer().getFieldCardsInGame()) card.accept(endTurnCardVisitor, null, this);
        getCurrentPlayer().getInfoPassive().accept(endTurnPassiveVisitor, getCurrentPlayer(), this);
         changeManaForTurn();
        switchTimes++;
        playAI();
        for (Minion minion : getCurrentPlayer().getFieldCardsInGame()) minion.setCanAttack(true);
        turnDraw();
    }

    protected void changeManaForTurn(){
        if (Mana < getManaLimit()) Mana++;
        if (Mana + getCurrentPlayer().getInitialMana() <= getManaLimit())
            getCurrentPlayer().setCurrentMana(Mana + getCurrentPlayer().getInitialMana());
    }

    protected int getManaLimit(){
        return 10;
    }

    protected void playAI() {
    }

    protected void checkIfMinionsAreDead(Player player) {
        player.getFieldCardsInGame().removeIf(minion -> minion.getHP() <= 0);
    }

    public void checkIfMinionIsDead(Player player, Minion minion) {
        if (player.getFieldCardsInGame().contains(minion) && minion.getHP() <= 0)
            player.getFieldCardsInGame().remove(minion);
    }

    public Character getCharacterOfTarget(long name) {
//        if(name.equals("hero1")) return getCurrentPlayer().getPlayersChoosedHero();
//        if(name.equals("hero2")) return getOpponentPlayer().getPlayersChoosedHero();
        return getTheCardWithID(name);
    }

    public Minion getTheCardWithID(long Id) {
        for (Minion minion : getCurrentPlayer().getFieldCardsInGame()) if (minion.getId() == Id) return minion;
        for (Minion minion : getOpponentPlayer().getFieldCardsInGame()) if (minion.getId() == Id) return minion;
        return null;
    }

    public void summon(String cardName, int number) {
        for (int i = 0; i < number; i++) {
            if (getCurrentPlayer().getFieldCardsInGame().size() < 7) {
                Minion card = (Minion) cardController.createCard(cardName);
                getCurrentPlayer().getFieldCardsInGame().add(card);
                for (Minion minion : getCurrentPlayer().getFieldCardsInGame())
                    minion.accept(summonCardVisitor, card, this);
                logGameEvent("Player " + getCurrentPlayer().getID() + "-  summoned the card : " + cardName);
                getPlayerLogger().log(Level.INFO, "-  summoned the card : " + cardName);
            }
        }
    }

    public void changeMinion(Minion minion, int hpToAdd, int attackToAdd) {
        if (hpToAdd < 0 && minion.hasDivineShield()) minion.setHasDivineShield(false);
        else {
            minion.setHP(minion.getHP() + hpToAdd);
            if (hpToAdd < 0) {
                minion.accept(takesDamageVisitor, null, this);
                audioPlayer.playQuick("attack.wav");
            }
        }
        minion.setAttack(minion.getAttack() + attackToAdd);
    }

    private void syncButtonsWithCards(List<Minion> cards , List<Long> buttons){
        for (Minion card : cards)if (!buttons.contains(card.getId())) buttons.add(card.getId());
    }

    public void syncFriendlyFieldComponents(ArrayList<Long> buttons) {
        syncButtonsWithCards( getFriendlyFieldCards(),buttons);
        getFriendlyPlayer().setFieldCardsInGame(syncCardsWithButtons(getFriendlyFieldCards(),buttons));
    }

    public void syncEnemyFieldComponents(ArrayList<Long> buttons) {
        syncButtonsWithCards(getEnemyFieldCards(),buttons);
        getEnemyPlayer().setFieldCardsInGame(syncCardsWithButtons(getEnemyFieldCards(),buttons));
    }

    private ArrayList<Minion> syncTheOrder(ArrayList<Minion> minions, ArrayList<MyCardButton> buttons) {
        ArrayList<Minion> result = new ArrayList<>();
        for (MyCardButton myCardButton : buttons) {
            for (Minion minion : minions) {
                if (minion.getId() == (myCardButton.getId()) && minion.getHP() > 0) {
                    result.add(minion);
                    break;
                }
            }
        }
        return result;
    }

    private ArrayList<Minion> syncCardsWithButtons(List<Minion> minions, List<Long> buttons) {
        ArrayList<Minion> result = new ArrayList<>();
        for (Long id : buttons) {
            for (Minion minion : minions) {
                if (minion.getId() == id && minion.getHP() > 0) {
                    result.add(minion);
                    break;
                }
            }
        }
        return result;
    }

    private boolean cardIdExist(ArrayList<Minion> cards, MyCardButton button) {
        checkIfMinionsAreDead(friendlyPlayer);
        checkIfMinionsAreDead(enemyPlayer);
        for (Card card : cards) if (card.getId() == button.getId()) return true;
        return false;
    }

    protected void removeDeadOnes(ArrayList<Minion> cards, ArrayList<MyCardButton> buttons) {
        buttons.removeIf(button -> !cardIdExist(cards, button));
    }

    public void transformMinion(Minion minion, int hp, int attack) {
        minion.setHP(hp);
        minion.setAttack(attack);
    }

    public void changeHero(Hero hero, int hpToAdd, int attackToAdd) {
        hero.setHP(hero.getHP() + hpToAdd);
        hero.setAttack(hero.getAttack() + attackToAdd);
    }

    public void giveMinionTaunt(Minion minion) {
        minion.setHasTaunt(true);
    }

    public void giveMinionDivineShield(Minion minion) {
        minion.setHasDivineShield(true);
    }

    public void giveMinionRush(Minion target) {
        target.setRush(true);
    }

    public boolean tauntExist() {
        if (switchTimes % 2 == 0) return checkTaunt(enemyPlayer);
        else return checkTaunt(friendlyPlayer);
    }

    private boolean checkTaunt(Player player) {
        for (Minion minion : player.getFieldCardsInGame()) if (minion.hasTaunt()) return true;
        return false;
    }

    public void playHeroPower() {
        if ((!getCurrentPlayer().getChoosedHero().getHeroPower().isHasTarget()) && hasManaForPower())
            getCurrentPlayer().getChoosedHero().getHeroPower().accept(initialSetUpHeroPowerVisitor, null, this);
    }

    public void playTargetetPower(long id) {
        getCurrentPlayer().getChoosedHero().getHeroPower().accept(initialSetUpHeroPowerVisitor, getTheCardWithID(id), this);
    }

    public boolean hasManaForPower() {
        return getCurrentPlayer().getChoosedHero().getHeroPower().getMana() <= getCurrentPlayer().getCurrentMana();
    }

    public Player getCurrentPlayer() {
        if (switchTimes % 2 == 0) return friendlyPlayer;
        return enemyPlayer;
    }

    public Player getOpponentPlayer() {
        if (getCurrentPlayer().getID() == (friendlyPlayer.getID())) return enemyPlayer;
        else return friendlyPlayer;
    }

    public Logger getPlayerLogger() {
        return clientHandler.getPlayerController().getPlayerLOGGER();
    }

    public List<Minion> getFriendlyFieldCards() {
        return getFriendlyPlayer().getFieldCardsInGame();
    }

    public List<Minion> getEnemyFieldCards() {
        return getEnemyPlayer().getFieldCardsInGame();
    }

    public void setDiscovery(String name) {
        getCurrentPlayer().getChoosedHero().setWeapon((Weapon) cardController.createCard(name));
    }

//    public boolean enemyHasTaunt() {
//        ArrayList<Minion> cards = getOpponentPlayer().getFieldCardsInGame();
//        for (Card card : cards) if (((Minion) card).hasTaunt()) return true;
//        return false;
//    }

    public void checkGameOver() {
        //todo
        if (enemyPlayer.getChoosedHero().getHP() <= 0) {
            clientHandler.getMainPlayer().getDeck().setAllGamesPlayed(clientHandler.getMainPlayer().getDeck().getAllGamesPlayed() + 1);
            clientHandler.getMainPlayer().getDeck().setWinGamesPlayed(clientHandler.getMainPlayer().getDeck().getWinGamesPlayed() + 1);
        }
        if (friendlyPlayer.getChoosedHero().getHP() <= 0) {
           clientHandler.getMainPlayer().getDeck().setAllGamesPlayed(clientHandler.getMainPlayer().getDeck().getAllGamesPlayed() + 1);
        }
    }

    private void transformWeapon(Weapon weapon, int durability, int attack) {
        weapon.setAttack(attack);
        weapon.setDurability(durability);
    }

    public void setEnemyPlayer(Player enemyPlayer) {
        this.enemyPlayer = enemyPlayer;
    }

    public abstract boolean getAllowance(Side side);

    public void addSwitch() {
        switchTimes++;
    }

    public Side getCurrentSide() {
        if (switchTimes%2==0)return Side.FRIENDLY;
        return Side.ENEMY;
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
//        int request_response.response = JOptionPane.showOptionDialog(null,passives.get(0).getName() + " : " + passives.get(0).getExplanation()+
//                        "\n" + passives.get(1).getName() + " : " + passives.get(1).getExplanation()+ "\n" +passives.get(2).getName() + " : " + passives.get(2).getExplanation(),
//                 getCurrentPlayer().getPlayerName()+ " : choose Info Passive", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, InfoPassives, InfoPassives[0]);
//        getCurrentPlayer().setInfoPassive(passives.get(request_response.response));
//        getPlayerLogger().log(Level.INFO,"passive "+ passives.get(request_response.response)+" is selected - PlayPanel");
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
