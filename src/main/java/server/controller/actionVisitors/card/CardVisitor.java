package server.controller.actionVisitors.card;

import server.controller.Board.BoardController;
import server.models.Cards.minions.*;
import server.models.Cards.spells.*;
import server.models.Cards.spells.questAndReward.LearnDraconic;
import server.models.Cards.spells.questAndReward.StrengthInNumbers;
import server.models.Cards.weapons.FieryWarAxe;
import server.models.Cards.weapons.LightsJustice;
import server.models.Cards.weapons.SerratedTooth;
import server.models.Character;

public interface CardVisitor {
    void visitSprint(Sprint sprint, Character target, BoardController boardController);
    void visitSwarmOfLocusts(SwarmOfLocusts swarmOfLocusts, Character target, BoardController boardController);
    void visitPharaohsBlessing(PharaohsBlessing pharaohsBlessing, Character target, BoardController boardController);
    void visitBookOfSpecters(BookOfSpecters bookOfSpecters, Character target, BoardController boardController);
    void visitPolymorph(Polymorph polymorph, Character target, BoardController boardController);
    void visitFriendlySmith(FriendlySmith friendlySmith, Character target, BoardController boardController);
    void visitLearnDraconic(LearnDraconic learnDraconic, Character target, BoardController boardController);
    void visitStrengthInNumbers(StrengthInNumbers strengthInNumbers, Character target, BoardController boardController);
    void visitBeamingSidekick(BeamingSidekick beamingSidekick, Character target, BoardController boardController);
    void visitBonechewerVanguard(BonechewerVanguard bonechewerVanguard, Character target, BoardController boardController);
    void visitConchguardWarlord(ConchguardWarlord conchguardWarlord, Character target, BoardController boardController);
    void visitDragonrider(Dragonrider dragonrider, Character target, BoardController boardController);
    void visitFrozenShadoweaver(FrozenShadoweaver frozenShadoweaver, Character target, BoardController boardController);
    void visitFungalBruiser(FungalBruiser fungalBruiser, Character target, BoardController boardController);
    void visitGoblinBomb(GoblinBomb goblinBomb, Character target, BoardController boardController);
    void visitHighPriestAmet(HighPriestAmet highPriestAmet, Character target, BoardController boardController);
    void visitLocust(Locust locust, Character target, BoardController boardController);
    void visitLostSpirit(LostSpirit lostSpirit, Character target, BoardController boardController);
    void visitMagmaRager(MagmaRager magmaRager, Character target, BoardController boardController);
    void visitMurlocRaider(MurlocRaider murlocRaider, Character target, BoardController boardController);
    void visitRatcatcher(Ratcatcher ratcatcher, Character target, BoardController boardController);
    void visitRocketAugmerchant(RocketAugmerchant rocketAugmerchant, Character target, BoardController boardController);
    void visitSathrovarr(Sathrovarr sathrovarr, Character target, BoardController boardController);
    void visitScavengingShivarra(ScavengingShivarra scavengingShivarra, Character target, BoardController boardController);
    void visitSecurityRover(SecurityRover securityRover, Character target, BoardController boardController);
    void visitStarscryer(Starscryer starscryer, Character target, BoardController boardController);
    void visitSwampKingDred(SwampKingDred swampKingDred, Character target, BoardController boardController);
    void visitTombWarden(TombWarden tombWarden, Character target, BoardController boardController);
    void visitCurioCollector(CurioCollector curioCollector, Character target, BoardController boardController);
    void visitDreadScale(DreadScale dreadScale, Character target, BoardController boardController);
    void visitLifedrinker(Lifedrinker lifedrinker, Character target, BoardController boardController);
    void visitScrapDeadlyShot(ScrapDeadlyShot scrapDeadlyShot, Character target, BoardController boardController);
    void visitPsycheSplit(PsycheSplit psycheSplit, Character target, BoardController boardController);
    void visitFieryWarAxe(FieryWarAxe fieryWarAxe, Character target, BoardController boardController);
    void visitLightsJustice(LightsJustice lightsJustice, Character target, BoardController boardController);
    void visitSerratedTooth(SerratedTooth serratedTooth, Character target, BoardController boardController);
    void visitSheep(Sheep sheep, Character target, BoardController boardController);

}
