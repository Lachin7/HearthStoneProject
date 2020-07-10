package controller.actionVisitors;

import controller.BoardController;
import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.minions.*;
import models.Cards.spells.*;
import models.Cards.weapons.HeadhuntersHatchet;
import models.Cards.weapons.LightsJustice;
import models.Cards.weapons.SerratedTooth;
import models.Character;

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
        //target should be a minion
        boardController.changeMinion((Minion) target,4,4);
        boardController.giveMinionTaunt((Minion) target);
        boardController.giveMinionDivineShield((Minion) target);
    }

    @Override
    public void visitBookOfSpecters(BookOfSpecters bookOfSpecters, Character target, BoardController boardController) {
        boardController.draw(3);
        //discard the spells
    }

    @Override
    public void visitPolymorph(Polymorph polymorph, Character target, BoardController boardController) {
        //target should be a minion
        boardController.transformMinion((Minion) target,1,1);
    }

    @Override
    public void visitFriendlySmith(FriendlySmith friendlySmith, Character target, BoardController boardController) {
        //TODO
    }

    @Override
    public void visitLearnDarconic(LearnDraconic learnDraconic, Character target, BoardController boardController) {

    }

    @Override
    public void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target, BoardController boardController) {

    }

    @Override
    public void visitBeamingSidekick(BeamingSidekick beamingSidekick, Character target, BoardController boardController) {
        //battle cry // minion target
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
        // target : enemy is it only  the minion?
        boardController.freeze((Minion) target);
    }

    @Override
    public void visitFungalBruiser(FungalBruiser fungalBruiser, Character target, BoardController boardController) {

    }

    @Override
    public void visitGoblinBomb(GoblinBomb goblinBomb, Character target, BoardController boardController) {

    }

    @Override
    public void visitHighPriestAmet(HighPriestAmet highPriestAmet, Character target, BoardController boardController) {

    }

    @Override
    public void visitLocust(Locust locust, Character target, BoardController boardController) {

    }

    @Override
    public void visitLostSpirit(LostSpirit lostSpirit, Character target, BoardController boardController) {

    }

    @Override
    public void visitMagmaRager(MagmaRager magmaRager, Character target, BoardController boardController) {

    }

    @Override
    public void visitMurlocRaider(MurlocRaider murlocRaider, Character target, BoardController boardController) {

    }

    @Override
    public void visitRatcatcher(Ratcatcher ratcatcher, Character target, BoardController boardController) {
        //target is a friendly minion
        boardController.changeMinion(ratcatcher,((Minion)target).getHP(),((Minion)target).getAttack());
        boardController.transformMinion((Minion) target,0,0);
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
        boardController.getCurrentPlayer().getFieldCardsInGame().add((Minion) target);
        boardController.getCurrentPlayer().getDeckCardsInGame().add((Card) target);
        // for animation you can generate a add method in controller for any of these three lists
    }

    @Override
    public void visitScavengingShivarra(ScavengingShivarra scavengingShivarra, Character target, BoardController boardController) {
// todo
    }

    @Override
    public void visitSecurityRover(SecurityRover securityRover, Character target, BoardController boardController) {

    }

    @Override
    public void visitStarscryer(Starscryer starscryer, Character target, BoardController boardController) {

    }

    @Override
    public void visitSwampKingDred(SwampKingDred swampKingDred, Character target, BoardController boardController) {

    }

    @Override
    public void visitTombWarden(TombWarden tombWarden, Character target, BoardController boardController) {
        boardController.getCurrentPlayer().getFieldCardsInGame().add(tombWarden);
        // be aware of loop
    }

    @Override
    public void visitCurioCollector(CurioCollector curioCollector, Character target, BoardController boardController) {

    }

    @Override
    public void visitDreadScale(DreadScale dreadScale, Character target, BoardController boardController) {

    }

    @Override
    public void visitMalygossExplosion(MalygossExplosion malygossExplosion, Character target, BoardController boardController) {

    }

    @Override
    public void visitMalygossNova(MalygossNova malygossNova, Character target, BoardController boardController) {
        for (Card card : boardController.getOpponentPlayer().getFieldCardsInGame()){
            boardController.changeMinion((Minion) card,-2,0);
        }
    }

    @Override
    public void visitScrapShot(ScrapShot scrapShot, Character target, BoardController boardController) {

    }

    @Override
    public void visitPsycheSplit(PsycheSplit psycheSplit, Character target, BoardController boardController) {
        boardController.changeMinion((Minion) target,1,2);
//        boardController.getCurrentPlayer().getFieldCardsInGame().add(psycheSplit);
    }

    @Override
    public void visitHeadhuntersHatchet(HeadhuntersHatchet headhuntersHatchet, Character target, BoardController boardController) {

    }

    @Override
    public void visitLightsJustice(LightsJustice lightsJustice, Character target, BoardController boardController) {

    }

    @Override
    public void visitSerratedTooth(SerratedTooth serratedTooth, Character target, BoardController boardController) {

    }
}
