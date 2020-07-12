package controller.actionVisitors.card;

import controller.BoardController;
import models.Cards.Minion;
import models.Cards.minions.*;
import models.Cards.spells.*;
import models.Cards.spells.questAndReward.LearnDraconic;
import models.Cards.spells.questAndReward.StrengthInNumbers;
import models.Cards.weapons.FieryWarAxe;
import models.Cards.weapons.LightsJustice;
import models.Cards.weapons.SerratedTooth;
import models.Character;

public class SummonCardVisitor implements CardVisitor{
    @Override
    public void visitSprint(Sprint sprint, Character target, BoardController boardController) {

    }

    @Override
    public void visitSwarmOfLocusts(SwarmOfLocusts swarmOfLocusts, Character target, BoardController boardController) {

    }

    @Override
    public void visitPharaohsBlessing(PharaohsBlessing pharaohsBlessing, Character target, BoardController boardController) {

    }

    @Override
    public void visitBookOfSpecters(BookOfSpecters bookOfSpecters, Character target, BoardController boardController) {

    }

    @Override
    public void visitPolymorph(Polymorph polymorph, Character target, BoardController boardController) {

    }

    @Override
    public void visitFriendlySmith(FriendlySmith friendlySmith, Character target, BoardController boardController) {

    }

    @Override
    public void visitLearnDraconic(LearnDraconic learnDraconic, Character target, BoardController boardController) {

    }

    @Override
    public void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target, BoardController boardController) {

    }

    @Override
    public void visitBeamingSidekick(BeamingSidekick beamingSidekick, Character target, BoardController boardController) {

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

    }

    @Override
    public void visitHighPriestAmet(HighPriestAmet highPriestAmet, Character target, BoardController boardController) {
        boardController.transformMinion((Minion) target,highPriestAmet.getHP(),target.getAttack());
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

    }

    @Override
    public void visitRocketAugmerchant(RocketAugmerchant rocketAugmerchant, Character target, BoardController boardController) {

    }

    @Override
    public void visitSathrovarr(Sathrovarr sathrovarr, Character target, BoardController boardController) {

    }

    @Override
    public void visitScavengingShivarra(ScavengingShivarra scavengingShivarra, Character target, BoardController boardController) {

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

    }

    @Override
    public void visitCurioCollector(CurioCollector curioCollector, Character target, BoardController boardController) {

    }

    @Override
    public void visitDreadScale(DreadScale dreadScale, Character target, BoardController boardController) {

    }

    @Override
    public void visitLifedrinker(Lifedrinker lifedrinker, Character target, BoardController boardController) {

    }

    @Override
    public void visitScrapDeadlyShot(ScrapDeadlyShot scrapDeadlyShot, Character target, BoardController boardController) {

    }

    @Override
    public void visitPsycheSplit(PsycheSplit psycheSplit, Character target, BoardController boardController) {

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
