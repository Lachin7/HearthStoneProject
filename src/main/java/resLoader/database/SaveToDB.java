package resLoader.database;

import server.models.Cards.minions.*;
import server.models.Cards.spells.*;
import server.models.Cards.spells.questAndReward.LearnDraconic;
import server.models.Cards.spells.questAndReward.StrengthInNumbers;
import server.models.Cards.weapons.FieryWarAxe;
import server.models.Cards.weapons.LightsJustice;
import server.models.Cards.weapons.SerratedTooth;
import server.models.Heroes.*;
import server.models.Player;

public class SaveToDB {
    private static DataBase dataBase = new DataBase();

    private static void saveHeroes(){
        dataBase.save(new Mage());
        dataBase.save(new Rogue());
        dataBase.save(new Warlock());
        dataBase.save(new Neutral());
        dataBase.save(new Hunter());
        dataBase.save(new Priest());
    }

    private static void saveCards(){
        dataBase.save(new BeamingSidekick());
        dataBase.save(new BonechewerVanguard());
        dataBase.save(new ConchguardWarlord());
        dataBase.save(new CurioCollector());
        dataBase.save(new Dragonrider());
        dataBase.save(new DreadScale());
        dataBase.save(new FrozenShadoweaver());
        dataBase.save(new FungalBruiser());
        dataBase.save(new GoblinBomb());
        dataBase.save(new HighPriestAmet());
        dataBase.save(new Lifedrinker());
        dataBase.save(new Locust());
        dataBase.save(new LostSpirit());
        dataBase.save(new MagmaRager());
        dataBase.save(new MurlocRaider());
        dataBase.save(new Ratcatcher());
        dataBase.save(new RocketAugmerchant());
        dataBase.save(new Sathrovarr());
        dataBase.save(new ScavengingShivarra());
        dataBase.save(new SecurityRover());
        dataBase.save(new Sheep());
        dataBase.save(new Starscryer());
        dataBase.save(new SwampKingDred());
        dataBase.save(new TombWarden());
        dataBase.save(new LearnDraconic());
        dataBase.save(new StrengthInNumbers());
        dataBase.save(new BookOfSpecters());
        dataBase.save(new FriendlySmith());
        dataBase.save(new Polymorph());
        dataBase.save(new PsycheSplit());
        dataBase.save(new ScrapDeadlyShot());
        dataBase.save(new Sprint());
        dataBase.save(new SwarmOfLocusts());
        dataBase.save(new FieryWarAxe());
        dataBase.save(new LightsJustice());
        dataBase.save(new SerratedTooth());
    }

    public static void main(String[] args) {
        saveCards();
        saveHeroes();
        dataBase.getSessionFactory().close();
    }

}
