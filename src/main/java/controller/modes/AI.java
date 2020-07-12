package controller.modes;

import controller.BoardController;
import controller.Controller;
import gui.GameFrame;
import gui.myComponents.MyCardButton;
import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.Target;
import models.Character;
import models.board.InfoPassive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AI extends BoardController {

    private final int heroPowerUses = 0;

    public AI(){
    }

    @Override
    protected void setPlayers() {
      chooseFriendAsMain();
      chooseEnemyAsMain();
      enemyPlayer.setInfoPassive(InfoPassive.getRandomPassives(1).get(0));
      setUpPassives(enemyPlayer);
    }

    @Override
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
            mapper.endTurnPlay();
        }
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
                mapper.updateHands();
                mapper.updateFields();
            }
            else chooseAffordableCard();
    }

    private void attackHero(Minion minion){
        if((!tauntExist(1))&&minion.canAttack()){
            attack(minion,getFriendlyPlayer().getPlayersChoosedHero());
        }
    }

    private void allAttackHero(){
        for (Minion minion : getFriendlyPlayer().getFieldCardsInGame())attackHero(minion);
    }

    private void useHeroPower(){
        if(heroPowerUses<1){
            playHeroPower();
            if(enemyPlayer.getPlayersChoosedHero().getHeroPower().isHasTarget()){
                if(getEnemyFieldCards().size()!=0)playTargetetPower(getEnemyFieldCards().get(0).getId());
                if(getFriendlyFieldCards().size()!=0)playTargetetPower(getFriendlyFieldCards().get(0).getId());
            }
        }
    }

//    private boolean attackMinion(Minion minion){
//        for (Minion minion1 : getFriendlyPlayer().getFieldCardsInGame()){
//            if((!tauntExist(1))||minion1.hasTaunt())attack(minion,minion1);
//        }
//    }

    private void rest(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private boolean checkIfHeroCanDie(){
        if(tauntExist(1))return false;
        int maxAttack = 0;
        for (Minion minion : getEnemyFieldCards()){
            if(minion.canAttack()){
                maxAttack+=minion.getAttack();
            }
        }
        return maxAttack >= getFriendlyPlayer().getPlayersChoosedHero().getHP();
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
            if(target==Target.FRIENDLY_HERO)targets.add(enemyPlayer.getPlayersChoosedHero());
            if(target==Target.FRIENDLY_HERO)targets.add(friendlyPlayer.getPlayersChoosedHero());
        }
        Collections.shuffle(targets);
        if(targets.size()!=0) return targets.get(0);
        else return null;
    }

    private void AIAttack(){
        for (Minion minion : getEnemyFieldCards()) {
            if (minion.canAttack() && getFriendlyFieldCards().size() != 0) {
                for (Minion minion1 : getFriendlyFieldCards()) {
                    if ((!tauntExist(1)) || minion1.hasTaunt()) {
                        attack(minion, minion1);
                        AIAttack();
                        mapper.updateFields();
                    }
                }
            }
        }
    }

    private boolean AICanAttack(){
        if(getFriendlyFieldCards().size()!=0) {
            for (Minion minion : getFriendlyFieldCards()) {
                if (minion.canAttack()) return true;
            }
        }
        return false;
    }

    @Override
    public void syncEnemyFieldComponents(ArrayList<MyCardButton> fieldComponents) {
        removeDeadOnes(getEnemyFieldCards(),fieldComponents);
    }


}
