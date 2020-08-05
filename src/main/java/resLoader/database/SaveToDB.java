package resLoader.database;

import models.Cards.minions.*;
import models.Cards.spells.*;
import models.Cards.spells.questAndReward.LearnDraconic;
import models.Cards.spells.questAndReward.StrengthInNumbers;
import models.Cards.weapons.FieryWarAxe;
import models.Cards.weapons.LightsJustice;
import models.Cards.weapons.SerratedTooth;
import models.Heroes.*;
import models.Player;

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
        dataBase.save(new RiverCrocolisk());
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
        dataBase.save(new MalygossExplosion());
        dataBase.save(new MalygossNova());
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
