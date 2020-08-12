package server.controller.Board.modes;

import lombok.SneakyThrows;
import request_response.response.EndTurn;
import request_response.response.GoToPanel;
import request_response.response.Message;
import server.models.board.Side;
import request_response.request.UpdateFieldCards;
import request_response.request.UpdateHandCards;
import server.ClientHandler;
import server.controller.Board.BoardController;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Cards.Target;
import server.models.Character;
import server.models.board.InfoPassive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class AI extends BoardController {

    private final int heroPowerUses = 0;

    public AI(ClientHandler clientHandler) {
        super(clientHandler);
    }

    @Override
    public void setPlayers() {
      chooseMainAsFriend();
      chooseMainAsEnemy();
      enemyPlayer.setInfoPassive(InfoPassive.getRandomPassives(1).get(0));
      setUpPassives(enemyPlayer);
    }

    @Override
    public void endTurn() {
        restartThread();
        for (Card card : getCurrentPlayer().getFieldCardsInGame()) card.accept(endTurnCardVisitor, null, this);
        getCurrentPlayer().getInfoPassive().accept(endTurnPassiveVisitor, getCurrentPlayer(), this);
        changeManaForTurn(friendlyPlayer);
        changeManaForTurn(enemyPlayer);
        switchTimes++;
        playAI();
        for (Minion minion : getCurrentPlayer().getFieldCardsInGame()) minion.setCanAttack(true);
        turnDraw();
        clientHandler.sendResponse("EndTurn",new EndTurn());
    }

    @Override
    public void checkGameFinished() {
        if(friendlyPlayer.getChoosedHero().getHP()<=0){
            clientHandler.sendResponse("Message",new Message("you lost :(("));
            clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
        }
        if(enemyPlayer.getChoosedHero().getHP()<=0){
            clientHandler.sendResponse("Message",new Message("you won!!"));
            clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
        }
    }

    protected void playAI() {
        if(switchTimes%2==1){
            while (canAffordAny()||AICanAttack()){
                if(checkIfHeroCanDie())allAttackHero();
                rest();
                if(canAffordAny()) chooseAffordableCard();
                rest();
                if (AICanAttack())AIAttack();
                rest();
            }
        }
    }

    @Override
    public boolean getAllowance(Side side) {
        return side==Side.FRIENDLY && switchTimes%2==0;
    }

    @Override
    public void exitPlay(boolean you) {
        if (!gameFinished)playerController.makePlayerLoser(clientHandler.getMainPlayer());
    }

    @Override
    public boolean getCardBackVisible(Side side) {
        return side==Side.ENEMY;
    }

    private void chooseAffordableCard(){
            int rand = random.nextInt(enemyPlayer.getHandsCards().size());
            Card card = enemyPlayer.getHandsCards().get(rand);
            if (enemyPlayer.getCurrentMana() >= card.getManaCost()) {
                initialPlay(card);
                if (card.getHasInitialMoveTarget()) {
                    if(selectTarget(card)!=null)card.accept(initialMoveVisitor, selectTarget(card), this);
                    checkIfMinionsAreDead(friendlyPlayer);
                }
                updateHands();
                updateFields();
            }
            else chooseAffordableCard();
    }


    private void attackHero(Minion minion){
        if((!tauntExist())&&minion.canAttack()) attack(minion,getFriendlyPlayer().getChoosedHero());
    }

    private void allAttackHero(){
        for (Minion minion : getFriendlyPlayer().getFieldCardsInGame())attackHero(minion);
    }

    private void useHeroPower(){
        if(heroPowerUses<1){
            playHeroPower();
            if(enemyPlayer.getChoosedHero().getHeroPower().isHasTarget()){
                if(getEnemyFieldCards().size()!=0) playTargetedPower(getEnemyFieldCards().get(0).getId());
                if(getFriendlyFieldCards().size()!=0) playTargetedPower(getFriendlyFieldCards().get(0).getId());
            }
        }
    }

    @SneakyThrows
    private void rest(){
        Thread.sleep(1000);
    }


    private boolean checkIfHeroCanDie(){
        if(tauntExist())return false;
        int maxAttack = 0;
        for (Minion minion : getEnemyFieldCards()) if(minion.canAttack()) maxAttack+=minion.getAttack();
        return maxAttack >= getFriendlyPlayer().getChoosedHero().getHP();
    }

    private boolean canAffordAny(){
        for(Card card : enemyPlayer.getHandsCards()) {
            if (card.getManaCost() <= enemyPlayer.getCurrentMana()){
                return true;
            }
        }
        return false;
    }

    private Character selectTarget(Card card){
        ArrayList<Character> targets = new ArrayList<>();
        for (Target target : card.getTargets()){
            if(target==Target.FRIENDLY_MINION)targets.addAll(enemyPlayer.getFieldCardsInGame());
            if(target==Target.ENEMY_MINION)targets.addAll(friendlyPlayer.getFieldCardsInGame());
            if(target==Target.FRIENDLY_HERO)targets.add(enemyPlayer.getChoosedHero());
            if(target==Target.FRIENDLY_HERO)targets.add(friendlyPlayer.getChoosedHero());
        }
        Collections.shuffle(targets);
        if(targets.size()!=0) return targets.get(0);
        else return null;
    }

    private void AIAttack(){
        for (Minion minion : getEnemyFieldCards()) {
            if (minion.canAttack() && getFriendlyFieldCards().size() != 0) {
                for (Minion minion1 : getFriendlyFieldCards()) {
                    if ((!tauntExist()) || minion1.hasTaunt()) {
                        attack(minion, minion1);
                        AIAttack();
                        updateFields();
                    }
                }
            }
        }
    }
    private boolean AICanAttack(){
        if(getFriendlyFieldCards().size()!=0) for (Minion minion : getFriendlyFieldCards()) if (minion.canAttack()) return true;
        return false;
    }

    private void updateFields(){
        LinkedList<Long> arrayList = new LinkedList<>();
        for (Card card : friendlyPlayer.getFieldCardsInGame())arrayList.add(card.getId());
        new UpdateFieldCards(Side.FRIENDLY,arrayList).execute(clientHandler);
        arrayList = new LinkedList<>();
        for (Card card : enemyPlayer.getFieldCardsInGame())arrayList.add(card.getId());
        new UpdateFieldCards(Side.ENEMY,arrayList).execute(clientHandler);
    }

    private void updateHands() {
        new UpdateHandCards(Side.FRIENDLY).execute(clientHandler);
        new UpdateHandCards(Side.ENEMY).execute(clientHandler);
    }



//    @Override
//    public void syncEnemyFieldComponents(ArrayList<MyCardButton> fieldComponents) {
//        removeDeadOnes(getEnemyFieldCards(),fieldComponents);
//    }


}
