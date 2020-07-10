package controller.actionVisitors.card;

import controller.BoardController;
import controller.modes.DeckReader;
import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.Spell;
import models.Cards.minions.*;
import models.Cards.spells.*;
import models.Cards.spells.questAndReward.LearnDraconic;
import models.Cards.spells.questAndReward.StrengthInNumbers;
import models.Cards.weapons.FieryWarAxe;
import models.Cards.weapons.LightsJustice;
import models.Cards.weapons.SerratedTooth;
import models.Character;

import java.util.Random;

public class InitialMoveCardVisitor implements CardVisitor {


    @Override
    public void visitSprint(Sprint sprint, Character target, BoardController boardController) {
        boardController.draw(4);
    }

    @Override
    public void visitSwarmOfLocusts(SwarmOfLocusts swarmOfLocusts, Character target, BoardController boardController) {
        boardController.summon("Locust",7);
    }

    @Override
    public void visitPharaohsBlessing(PharaohsBlessing pharaohsBlessing, Character target, BoardController boardController) {
        boardController.changeMinion((Minion) target,4,4);
        boardController.giveMinionTaunt((Minion) target);
        boardController.giveMinionDivineShield((Minion) target);
    }

    @Override
    public void visitBookOfSpecters(BookOfSpecters bookOfSpecters, Character target, BoardController boardController) {
        boardController.drawDiscard(3, Card.type.SPELL);
    }

    @Override
    public void visitPolymorph(Polymorph polymorph, Character target, BoardController boardController) {
        boardController.transformMinion((Minion) target,1,1);
        ((Minion) target).setHasTaunt(false);
        ((Minion) target).setHasDivineShield(false);
    }

    @Override
    public void visitFriendlySmith(FriendlySmith friendlySmith, Character target, BoardController boardController) {
        boardController.discover(Card.type.WEAPON);
    }

    @Override
    public void visitLearnDraconic(LearnDraconic learnDraconic, Character target, BoardController boardController) {
        boardController.makeQuestReward(8, Card.type.SPELL,"MagmaRager","LearnDraconic");
    }

    @Override
    public void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target, BoardController boardController) {
       if(boardController instanceof DeckReader) boardController.makeQuestReward(10, Card.type.MINION,"SecurityRover","StrengthInNumbers");
       else {
           while (true) {
               int rand = new Random().nextInt(boardController.getCurrentPlayer().getDeckCardsInGame().size());
               Card card = boardController.getCurrentPlayer().getDeckCardsInGame().get(rand);
               if (card instanceof Minion) boardController.makeQuestReward(10, Card.type.MINION, card.getName() , "StrengthInNumbers");
               break;
           }
       }
    }

    @Override
    public void visitBeamingSidekick(BeamingSidekick beamingSidekick, Character target, BoardController boardController) {
        boardController.changeMinion((Minion) target,2,0);
    }

    @Override
    public void visitBonechewerVanguard(BonechewerVanguard bonechewerVanguard, Character target, BoardController boardController) {
    }

    @Override
    public void visitConchguardWarlord(ConchguardWarlord conchguardWarlord, Character target, BoardController boardController) {

    }

    @Override
    public void visitDragonrider(Dragonrider dragonrider, Character target, BoardController boardController) {

    }

    @Override
    public void visitFrozenShadoweaver(FrozenShadoweaver frozenShadoweaver, Character target, BoardController boardController) {
    }

    @Override
    public void visitFungalBruiser(FungalBruiser fungalBruiser, Character target, BoardController boardController) {

    }

    @Override
    public void visitGoblinBomb(GoblinBomb goblinBomb, Character target, BoardController boardController) {
        boardController.getOpponentPlayer().getPlayersChoosedHero().setHP(boardController.getOpponentPlayer().getPlayersChoosedHero().getHP()-2);
    }

    @Override
    public void visitHighPriestAmet(HighPriestAmet highPriestAmet, Character target, BoardController boardController) {
        //todo make a visitor
    }

    @Override
    public void visitLocust(Locust locust, Character target, BoardController boardController) {

    }

    @Override
    public void visitLostSpirit(LostSpirit lostSpirit, Character target, BoardController boardController) {
        for(Minion minion : boardController.getCurrentPlayer().getFieldCardsInGame())boardController.changeMinion(minion,0,1);
    }

    @Override
    public void visitMagmaRager(MagmaRager magmaRager, Character target, BoardController boardController) {

    }

    @Override
    public void visitMurlocRaider(MurlocRaider murlocRaider, Character target, BoardController boardController) {

    }

    @Override
    public void visitRatcatcher(Ratcatcher ratcatcher, Character target, BoardController boardController) {

   }

    @Override
    public void visitRocketAugmerchant(RocketAugmerchant rocketAugmerchant, Character target, BoardController boardController) {
        // any friendly or enemy minion
        boardController.changeMinion((Minion) target,-1,0);
        boardController.giveMinionRush((Minion) target);
    }

    @Override
    public void visitSathrovarr(Sathrovarr sathrovarr, Character target, BoardController boardController) {
        boardController.getCurrentPlayer().getHandsCards().add((Card) target);
        boardController.summon(((Minion) target).getName(),1);
        boardController.getCurrentPlayer().getDeckCardsInGame().add((Card) target);
        // for animation you can generate a add method in controller for any of these three lists
    }

    @Override
    public void visitScavengingShivarra(ScavengingShivarra scavengingShivarra, Character target, BoardController boardController) {
//        //deal 6 damges among other minions , can do this better
//       if(boardController.getFriendlyFieldCards().size()!=0)boardController.changeMinion(boardController.getFriendlyFieldCards().get(0),-3,0);
//       if(boardController.getEnemyFieldCards().size()!=0)boardController.changeMinion(boardController.getEnemyFieldCards().get(0),-3,0);
        for(Minion minion : boardController.getOpponentPlayer().getFieldCardsInGame()) boardController.changeMinion(minion,-1,0);
    }

    @Override
    public void visitSecurityRover(SecurityRover securityRover, Character target, BoardController boardController) {

    }

    @Override
    public void visitStarscryer(Starscryer starscryer, Character target, BoardController boardController) {
        boardController.draw(Card.type.SPELL);
    }

    @Override
    public void visitSwampKingDred(SwampKingDred swampKingDred, Character target, BoardController boardController) {
        //todo
    }

    @Override
    public void visitTombWarden(TombWarden tombWarden, Character target, BoardController boardController) {
        boardController.summon("TombWarden",1);
    }

    @Override
    public void visitCurioCollector(CurioCollector curioCollector, Character target, BoardController boardController) {

    }

    @Override
    public void visitDreadScale(DreadScale dreadScale, Character target, BoardController boardController) {

    }
    @Override
    public void visitLifedrinker(Lifedrinker lifedrinker, Character target, BoardController boardController){
        boardController.changeHero(boardController.getOpponentPlayer().getPlayersChoosedHero(),-3,0);
        boardController.changeHero(boardController.getCurrentPlayer().getPlayersChoosedHero(),3,0);
    }

    @Override
    public void visitScrapDeadlyShot(ScrapDeadlyShot scrapDeadlyShot, Character target, BoardController boardController) {
       for(Minion minion : boardController.getOpponentPlayer().getFieldCardsInGame()) boardController.changeMinion(minion,-1,0);
    }

    @Override
    public void visitPsycheSplit(PsycheSplit psycheSplit, Character target, BoardController boardController) {
        boardController.changeMinion((Minion) target,1,2);
        boardController.summon(((Minion) target).getName(),1);
    }

    @Override
    public void visitFieryWarAxe(FieryWarAxe fieryWarAxe, Character target, BoardController boardController) {

    }

    @Override
    public void visitLightsJustice(LightsJustice lightsJustice, Character target, BoardController boardController) {

    }

    @Override
    public void visitSerratedTooth(SerratedTooth serratedTooth, Character target, BoardController boardController) {

    }

    @Override
    public void visitSheep(Sheep sheep, Character target, BoardController boardController) {

    }
}
