package JSON.jsonForCards;

import JSON.JsonAdapter;
import models.Cards.*;
import models.Cards.Card.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Cards.minions.*;
import models.Cards.spells.*;
import models.Cards.spells.questAndReward.LearnDraconic;
import models.Cards.spells.questAndReward.StrengthInNumbers;
import models.Cards.weapons.FieryWarAxe;
import models.Cards.weapons.LightsJustice;
import models.Cards.weapons.SerratedTooth;
import models.Heroes.Hero;

import java.io.*;

public class jsonForCards {

    public static void jsonFileMakerForCards(Card card) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Card.class, new JsonAdapter<Card>());
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("./src/main/java/JSON/jsonForCards/jsonFilesForCards/" + card.getName() + ".json");
        gson.toJson(card, fileWriter);
        fileWriter.close();
    }

    public static Card creatCardFromjson(String name) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Card.class, new JsonAdapter<Card>());
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./src/main/java/JSON/jsonForCards/jsonFilesForCards/" + name + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Card card = null;
        switch (name){
            case "Sprint": card = gson.fromJson(fileReader,Sprint.class); break;
            case "SwarmOfLocusts": card = gson.fromJson(fileReader, SwarmOfLocusts.class);break;
            case "PharaohsBlessing": card = gson.fromJson(fileReader,PharaohsBlessing.class);break;
            case "Polymorph": card = gson.fromJson(fileReader,Polymorph.class);break;
            case "FriendlySmith": card = gson.fromJson(fileReader,FriendlySmith.class);break;
            case "DreadScale": card = gson.fromJson(fileReader,DreadScale.class);break;
            case "SwampKingDred": card = gson.fromJson(fileReader,SwampKingDred.class);break;
            case "HighPriestAmet": card = gson.fromJson(fileReader, HighPriestAmet.class);break;
            case "Sathrovarr": card = gson.fromJson(fileReader, Sathrovarr.class);break;
            case "TombWarden": card = gson.fromJson(fileReader,TombWarden.class);break;
            case "SecurityRover": card = gson.fromJson(fileReader,SecurityRover.class);break;
            case "CurioCollector": card = gson.fromJson(fileReader,CurioCollector.class);break;
            case "StrengthInNumbers": card = gson.fromJson(fileReader, StrengthInNumbers.class);break;
            case "LearnDraconic": card = gson.fromJson(fileReader, LearnDraconic.class);break;
            case "ScrapDeadlyShot": card = gson.fromJson(fileReader,ScrapDeadlyShot.class);break;
            case "Locust": card = gson.fromJson(fileReader,Locust.class);break;
            case "GoblinBomb": card = gson.fromJson(fileReader,GoblinBomb.class);break;
            case "BonechewerVanguard": card = gson.fromJson(fileReader,BonechewerVanguard.class);break;
            case "BeamingSidekick": card = gson.fromJson(fileReader,BeamingSidekick.class);break;
            case "FrozenShadoweaver": card = gson.fromJson(fileReader,FrozenShadoweaver.class);break;
            case "LostSpirit": card = gson.fromJson(fileReader,LostSpirit.class);break;
            case "MagmaRager": card = gson.fromJson(fileReader,MagmaRager.class);break;
            case "MurlocRaider": card = gson.fromJson(fileReader,MurlocRaider.class);break;
            case "Ratcatcher": card = gson.fromJson(fileReader,Ratcatcher.class);break;
            case "ScavengingShivarra": card = gson.fromJson(fileReader,ScavengingShivarra.class);break;
            case "ConchguardWarlord": card = gson.fromJson(fileReader,ConchguardWarlord.class);break;
            case "Dragonrider": card = gson.fromJson(fileReader,Dragonrider.class);break;
            case "FungalBruiser": card = gson.fromJson(fileReader,FungalBruiser.class);break;
            case "Starscryer": card = gson.fromJson(fileReader,Starscryer.class);break;
            case "SerratedTooth": card = gson.fromJson(fileReader,SerratedTooth.class);break;
            case "LightsJustice": card = gson.fromJson(fileReader, LightsJustice.class);break;
            case "Sheep": card = gson.fromJson(fileReader,Sheep.class);break;
            case "FieryWarAxe": card = gson.fromJson(fileReader,FieryWarAxe.class);break;
            case "RocketAugmerchant": card = gson.fromJson(fileReader,RocketAugmerchant.class);break;
            case "Lifedrinker": card = gson.fromJson(fileReader,Lifedrinker.class);break;
            case "PsycheSplit": card = gson.fromJson(fileReader,PsycheSplit.class);break;
            case "BookOfSpecters": card = gson.fromJson(fileReader,BookOfSpecters.class);break;
//            default: card = gson.fromJson(fileReader,Card.class);
//            default: card = gson.fromJson(fileReader,Card.class);
//            case "Sprint": card = gson.fromJson(fileReader,Sprint.class);
//            case "Sprint": card = gson.fromJson(fileReader,Sprint.class);
//            case "Sprint": card = gson.fromJson(fileReader,Sprint.class);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return card;
    }








//    static Spell Polymorph = new Spell(4, "Polymorph", "Transform minion in to a 1/1 sheep.", rarity.RARE, HeroClass.MAGE, type.SPELL, 10, "", "");
//    static  Spell RollingFireball = new Spell(5, "RollingFireball", "Deal 8 damage to a minion. Any excess damage continues to the left or right.1 sheep.", rarity.EPIC, HeroClass.MAGE, type.SPELL, 15, "", "");
//    static  Spell FriendlySmith = new Spell(1, "FriendlySmith", "Discover a weapon from any class. Add it to your Adventure Deck with +2/+2", rarity.COMMON, HeroClass.ROGUE, type.SPELL, 5, "", "");
//    static Spell BlinkFox = new Spell(3, "BlinkFox", "Battlecry: Add a random card to your hand (from your opponent's class).", rarity.COMMON, HeroClass.ROGUE, type.SPELL, 5, "", "");
//    static Minion Ratcatcher = new Minion(3, "Ratcatcher", "Rush   Battlecry: Destroy a friendly minion and gain its Attack and Health.", rarity.LEGENDARY, HeroClass.WARLOCK, type.MINION, 20, 2, 2);
//    static Minion AbusiveSergeant = new Minion(1, "AbusiveSergeant", "Battlecry: Give a minion +2 Attack this turn.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 1);
//    static Minion HungryCrab = new Minion(1, "HungryCrab", "Destroy a Murloc and gain +2/+2.", rarity.EPIC, HeroClass.NEUTRAL, type.MINION, 15, 2, 1);
//    static Minion GoblinBomb = new Minion(1, "GoblinBomb", "Deal 2 damage to the enemy hero.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 2, 0);
//    static Minion BeamingSidekick = new Minion(1, "BeamingSidekick", "Give a friendly minion +2 Health.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 2, 1);
//    static Minion LostSpirit = new Minion(2, "LostSpirit", "Deathrattle: Give your minions +1 Attack.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 1);
//    static Minion MagmaRager = new Minion(3, "MagmaRager", "-", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 5);
//    static Minion MurlocRaider = new Minion(1, "MurlocRaider", "-", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 2);
//    static Minion EtherealAugmerchant = new Minion(1, "EtherealAugmerchant", "Battlecry: Deal 1 damage to a minion and give it Spell Damage +1.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 4);
//    static Minion FrozenShadoweaver = new Minion(3, "FrozenShadoweaver", "Battlecry: Freeze an enemy.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 4);
//    static Minion BonechewerVanguard = new Minion(7, "BonechewerVanguard", "Taunt , Whenever this minion takes damage, gain +2 Attack.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 10, 4);
//    static Minion ImprisonedVilefiend = new Minion(2, "ImprisonedVilefiend", "Dormant for 2 turns.\n" + "Rush", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 5, 3);
//    static Minion DisguisedWanderer = new Minion(4, "DisguisedWanderer", "Deathrattle: Summon a 9/1 Inquisitor.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 3);
//    static Minion ScavengingShivarra = new Minion(6, "ScavengingShivarra", "Battlecry: Deal 6 damage randomly split among all other minions.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 6);
//    static Minion RustswornCultist = new Minion(4, "RustswornCultist", "Battlecry: Give your other minions \"Deathrattle: Summon a 1/1 Demon.\"", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 3);
//    static Minion RocketAugmerchant= new Minion(1, "RocketAugmerchant", "Battlecry: Deal 1 damage to a minion and give it Rush.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 2);
//    static Minion RuststeedRaider= new Minion(5, "RuststeedRaider", "Battlecry: Deal 1 damage to a minion and give it Rush.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 2);
//    static Minion DragonmawSkyStalker = new Minion(6, "DragonmawSkyStalker", "Deathrattle: Summon a 3/4 Dragonrider.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 6, 5);
//    static Minion InfectiousSporeling= new Minion(1, "InfectiousSporeling", "After this damages a minion, turn it into an Infectious Sporeling.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 2, 1);
//    static Minion Helboar= new Minion(1, "Helboar", "Deathrattle: Give a random Beast in your hand +1/+1.", rarity.COMMON, HeroClass.HUNTER, type.MINION, 5, 1, 2);
//    static Minion SethekkVeilweaver = new Minion(2, "SethekkVeilweaver", "After you cast a spell on a minion, add a Priest spell to your hand.", rarity.EPIC, HeroClass.PRIEST, type.MINION, 15, 3, 2);
//    static Minion CurioCollector = new Minion(5, "CurioCollector", "Whenever you draw a card, gain +1/+1.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 4, 4);
//    static Minion SecurityRover = new Minion(6, "SecurityRover", "Whenever this minion takes damage, summon a 2/3 Mech with Taunt.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 6, 2);
//    static Minion TombWarden = new Minion(8, "TombWarden", "Taunt\n" + "Battlecry: Summon a copy of this minion.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 6, 3);
//    static Minion Sathrovarr = new Minion(9, "Sathrovarr", "Choose a friendly minion. Add a copy of it to your hand, deck and battlefield.", rarity.LEGENDARY, HeroClass.NEUTRAL, type.MINION, 20, 5, 5);
//    static Minion Locust = new Minion(1, "Locust", "Rush", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 1);
//    static Spell Backstab = new Spell(0, "Backstab", "Deal 2 damage to an undamaged minion.", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
//    static Spell MalygossFrostbolt = new Spell(0, "MalygossFrostbolt", "Deal 3 damage to a character and Freeze it.", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
//    static Spell MalygossNova = new Spell(1, "MalygossNova", "Freeze all enemy minions.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
//    static Spell MalygossExplosion = new Spell(2, "MalygossExplosion", "Deal 2 damage to all enemy minions.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
//    static Spell TimeRip = new Spell(5, "TimeRip", "Destroy a minion. Invoke Galakrond.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
//    static Spell CandleBreath = new Spell(6, "CandleBreath", "Destroy a minion. Invoke Galakrond.", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
//    static Spell ScrapShot = new Spell(4, "ScrapShot", "Deal 3 damage. Give a random Beast in your hand +3/+3.", rarity.RARE, HeroClass.HUNTER, type.SPELL, 10, "", "");
//    static Spell Renew = new Spell(1, "Renew", "Restore 3 Health. Discover a spell.", rarity.COMMON, HeroClass.PRIEST, type.SPELL, 5, "", "");
//    static Spell Sprint = new Spell(7, "Sprint", "Draw 4 cards.\n", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
//    static Spell PharaohsBlessing = new Spell(6, "PharaohsBlessing", "Give a minion +4/+4, Divine Shield, and Taunt.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
//    static Spell SwarmOfLocusts = new Spell(6, "SwarmOfLocusts", "Summon seven 1/1 Locusts with Rush.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
//    static Spell BookOfSpecters = new Spell(2, "BookOfSpecters", "Draw 3 cards. Discard any spells drawn.", rarity.EPIC, HeroClass.NEUTRAL, type.SPELL, 15, "", "");
//    static Spell StrengthInNumbers = new Spell(1, "StrengthInNumbers", "-", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "Sidequest: Spend 10 Mana on minions.", "Reward: Summon a minion from your deck.");
//    static Spell LearnDraconic= new Spell(1, "LearnDraconic", "-", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "Sidequest: Spend 8 Mana on spells.", "Reward: Summon a 6/6 Dragon.");
//    static Weapon LightsJustice = new Weapon(1, "LightsJustice", "", rarity.COMMON, HeroClass.NEUTRAL, type.WEAPON, 5, 4, 1);
//    static Weapon SerratedTooth = new Weapon(1, "SerratedTooth", "Deathrattle: Give your minions Rush.", rarity.COMMON, HeroClass.NEUTRAL, type.WEAPON, 5, 1, 1);
//    static Weapon HeadhuntersHatchet = new Weapon(2, "HeadhuntersHatchet", "Battlecry: If you control a Beast, gain +1 Durability.", rarity.COMMON, HeroClass.NEUTRAL, type.WEAPON, 5, 2, 2);
//


    public static void main(String[] args) throws IOException {

        Sprint sprint = new Sprint();
        SwarmOfLocusts SwarmOfLocusts = new SwarmOfLocusts();
        PharaohsBlessing PharaohsBlessing = new PharaohsBlessing();
        PharaohsBlessing BookOfSpecters = new PharaohsBlessing();
        Polymorph polymorph = new Polymorph();
        jsonFileMakerForCards(polymorph);
        FriendlySmith friendlySmith = new FriendlySmith();
        DreadScale dreadScale = new DreadScale();
        SwampKingDred swampKingDred = new SwampKingDred();
        HighPriestAmet highPriestAmet = new HighPriestAmet();
        Sathrovarr sathrovarr = new Sathrovarr();
        TombWarden tombWarden = new TombWarden();
        SecurityRover securityRover = new SecurityRover();
        CurioCollector curioCollector = new CurioCollector();
        StrengthInNumbers strengthInNumbers = new StrengthInNumbers();
        LearnDraconic learnDraconic = new LearnDraconic();
        ScrapDeadlyShot scrapDeadlyShot = new ScrapDeadlyShot();
        Locust locust = new Locust();
        GoblinBomb goblinBomb = new GoblinBomb();
        BonechewerVanguard bonechewerVanguard = new BonechewerVanguard();
        BeamingSidekick beamingSidekick = new BeamingSidekick();
        FrozenShadoweaver frozenShadoweaver = new FrozenShadoweaver();
        LostSpirit lostSpirit = new  LostSpirit();
        MagmaRager magmaRager = new MagmaRager();
        MurlocRaider murlocRaider = new MurlocRaider();
        Ratcatcher ratcatcher = new Ratcatcher();
        ScavengingShivarra scavengingShivarra = new ScavengingShivarra();
        ConchguardWarlord conchguardWarlord = new ConchguardWarlord();
        Dragonrider dragonrider = new Dragonrider();
        FungalBruiser fungalBruiser = new FungalBruiser();
        Starscryer starscryer = new Starscryer();        //helboar
        SerratedTooth serratedTooth = new SerratedTooth();
        LightsJustice lightsJustice = new LightsJustice();
        Sheep sheep = new Sheep();
        FieryWarAxe fieryWarAxe = new FieryWarAxe();
        BookOfSpecters bookOfSpecters = new BookOfSpecters();
        jsonFileMakerForCards(bookOfSpecters);
//        FriendlySmith friendlySmith = new FriendlySmith();
        jsonFileMakerForCards(friendlySmith);
        RocketAugmerchant rocketAugmerchant =new RocketAugmerchant();
        jsonFileMakerForCards(rocketAugmerchant);
        Lifedrinker lifedrinker = new Lifedrinker();
        jsonFileMakerForCards(lifedrinker);
        PsycheSplit psycheSplit = new PsycheSplit();
        jsonFileMakerForCards(psycheSplit);
//
        jsonFileMakerForCards(friendlySmith);
        jsonFileMakerForCards(dreadScale);
        jsonFileMakerForCards(swampKingDred);
        jsonFileMakerForCards(highPriestAmet);
        jsonFileMakerForCards(sathrovarr);
        jsonFileMakerForCards(tombWarden);
        jsonFileMakerForCards(securityRover);
        jsonFileMakerForCards(curioCollector);
        jsonFileMakerForCards(strengthInNumbers);
        jsonFileMakerForCards(learnDraconic);
        jsonFileMakerForCards(scrapDeadlyShot);
        jsonFileMakerForCards(locust);
        jsonFileMakerForCards(goblinBomb);
        jsonFileMakerForCards(bonechewerVanguard);
        jsonFileMakerForCards(beamingSidekick);
        jsonFileMakerForCards(frozenShadoweaver);
        jsonFileMakerForCards(lostSpirit);
        jsonFileMakerForCards(magmaRager);
        jsonFileMakerForCards(murlocRaider);
        jsonFileMakerForCards(ratcatcher);
        jsonFileMakerForCards(scavengingShivarra);
        jsonFileMakerForCards(conchguardWarlord);
        jsonFileMakerForCards(dragonrider);
        jsonFileMakerForCards(fungalBruiser);
        jsonFileMakerForCards(serratedTooth);
        jsonFileMakerForCards(lightsJustice);
        jsonFileMakerForCards(sheep);
        jsonFileMakerForCards(fieryWarAxe);
        jsonFileMakerForCards(starscryer);
        jsonFileMakerForCards(sprint);
        jsonFileMakerForCards(PharaohsBlessing);
        jsonFileMakerForCards(SwarmOfLocusts);
        jsonFileMakerForCards(BookOfSpecters);


//        jsonFileMakerForCards(Polymorph);
//        jsonFileMakerForCards(RollingFireball);
//        jsonFileMakerForCards(FriendlySmith);
//        jsonFileMakerForCards(BlinkFox);
//        jsonFileMakerForCards(Ratcatcher);
//        jsonFileMakerForCards(AbusiveSergeant);
//        jsonFileMakerForCards(HungryCrab);
//        jsonFileMakerForCards(GoblinBomb);
//        jsonFileMakerForCards(BeamingSidekick);
//        jsonFileMakerForCards(LostSpirit);
//        jsonFileMakerForCards(MagmaRager);
//        jsonFileMakerForCards(MurlocRaider);
//        jsonFileMakerForCards(Backstab);
//        jsonFileMakerForCards(MalygossFrostbolt);
//        jsonFileMakerForCards(MalygossNova);
//        jsonFileMakerForCards(MalygossExplosion);
//        jsonFileMakerForCards(TimeRip);
//        jsonFileMakerForCards(CandleBreath);
//        jsonFileMakerForCards(LightsJustice);
//        jsonFileMakerForCards(SerratedTooth);
//        jsonFileMakerForCards(HeadhuntersHatchet);
//        jsonFileMakerForCards(EtherealAugmerchant);
//        jsonFileMakerForCards(FrozenShadoweaver);
//        jsonFileMakerForCards(BonechewerVanguard);
//        jsonFileMakerForCards(ImprisonedVilefiend);
//        jsonFileMakerForCards(DisguisedWanderer);
//        jsonFileMakerForCards(ScavengingShivarra);
//        jsonFileMakerForCards(RustswornCultist);
//        jsonFileMakerForCards(RocketAugmerchant);
//        jsonFileMakerForCards(RuststeedRaider);
//        jsonFileMakerForCards(DragonmawSkyStalker);
//        jsonFileMakerForCards(InfectiousSporeling);
//        jsonFileMakerForCards(Helboar);
//        jsonFileMakerForCards(SethekkVeilweaver);
//        jsonFileMakerForCards(CurioCollector);
//        jsonFileMakerForCards(SecurityRover);
//        jsonFileMakerForCards(TombWarden);
//        jsonFileMakerForCards(Sathrovarr);
//        jsonFileMakerForCards(ScrapShot);
//        jsonFileMakerForCards(Renew);
//        jsonFileMakerForCards(Sprint);

//        jsonFileMakerForCards(BookOfSpecters);
//        jsonFileMakerForCards(StrengthInNumbers);
//        jsonFileMakerForCards(LearnDraconic);

//        Card card = creatCardFromjson("Polymorph");
      //  System.out.println(Polymorph.getHeroClass().toString());
        // public  ArrayList<card> AllExistingCardsInGame = new ArrayList();


//    public static void jsonFileMakerForSpellCards(String name, Long manaCost, String type, String heroClass, String rarity, String description, String Quest, String Reward) {
//        JSONObject cardInfo = new JSONObject();
//        cardInfo.put("name",name);
//        cardInfo.put("manaCost", new Long(manaCost));
//        cardInfo.put("type", type);
//        cardInfo.put("heroClass",heroClass);
//        cardInfo.put("rarity",rarity);
//        cardInfo.put("description",description);
//        cardInfo.put("Quest",Quest);
//        cardInfo.put("Reward",Reward);
//        try (FileWriter file = new FileWriter("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/"+ name +".json")) {
//            file.write(cardInfo.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject CardObject = new JSONObject();
//        CardObject.put("SpellCard", cardInfo);
//        JSONParser jsonParser = new JSONParser();
//        JSONArray CardsJList = null;
//        try(FileReader fileReader = new FileReader("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/ALLCardsExistingInGame.json")) {
//            Object obj = jsonParser.parse(fileReader);
//            CardsJList = (JSONArray) obj;
//            CardsJList.add(CardObject);
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//
//        try (FileWriter file = new FileWriter("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/ALLCardsExistingInGame.json")) {
//            file.write(CardsJList.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void jsonFileMakerForMinionCards(String name, Long manaCost, String type, String heroClass, String rarity, String description, Long HP, Long Attack) {
//        JSONObject cardInfo = new JSONObject();
//        cardInfo.put("name",name);
//        cardInfo.put("manaCost", new Long(manaCost));
//        cardInfo.put("type", type);
//        cardInfo.put("heroClass",heroClass);
//        cardInfo.put("rarity",rarity);
//        cardInfo.put("description",description);
//        cardInfo.put("HP",HP);
//        cardInfo.put("Attack",Attack);
//        try (FileWriter file = new FileWriter("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/"+name+".json")) {
//            file.write(cardInfo.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject CardObject = new JSONObject();
//        CardObject.put("MinionCard", cardInfo);
//        JSONParser jsonParser = new JSONParser();
//        JSONArray CardsJList = null;
//        try(FileReader fileReader = new FileReader("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/ALLCardsExistingInGame.json")) {
//            Object obj = jsonParser.parse(fileReader);
//            CardsJList = (JSONArray) obj;
//            CardsJList.add(CardObject);
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//
//        try (FileWriter file = new FileWriter("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/ALLCardsExistingInGame.json")) {
//            file.write(CardsJList.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void jsonFileMakerForWeaponCards(String name, Long manaCost, String type, String heroClass, String rarity, String description, Long durability, Long Attack) {
//        JSONObject cardInfo = new JSONObject();
//        cardInfo.put("name",name);
//        cardInfo.put("manaCost", new Long(manaCost));
//        cardInfo.put("type", type);
//        cardInfo.put("heroClass",heroClass);
//        cardInfo.put("rarity",rarity);
//        cardInfo.put("description",description);
//        cardInfo.put("durability",durability);
//        cardInfo.put("Attack",new Long(Attack));
//        try (FileWriter file = new FileWriter("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/"+name+".json")) {
//            file.write(cardInfo.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject CardObject = new JSONObject();
//        CardObject.put("WeaponCard", cardInfo);
//        JSONParser jsonParser = new JSONParser();
//        JSONArray CardsJList = null;
//        try(FileReader fileReader = new FileReader("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/ALLCardsExistingInGame.json")) {
//            Object obj = jsonParser.parse(fileReader);
//            CardsJList = (JSONArray) obj;
//            CardsJList.add(CardObject);
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//
//        try (FileWriter file = new FileWriter("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/ALLCardsExistingInGame.json")) {
//            file.write(CardsJList.toJSONString());
//            file.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//
//    public static card creatCard(String name) {
//
//        JSONParser jsonParser = new JSONParser();
//        FileReader reader = null;
//        try {
//            reader = new FileReader( "/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForCards/jsonFilesForCards/"+ name +".json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = (JSONObject) jsonParser.parse(reader);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (jsonObject.get("type").equals("SPELL")) {
//            return  new Spell((long) jsonObject.get("manaCost"), (String) jsonObject.get("name"), (String) jsonObject.get("description"), card.rarity.valueOf((String) jsonObject.get("rarity")), HeroClass.valueOf((String) jsonObject.get("heroClass")) , card.type.valueOf((String) jsonObject.get("type")) , (String) jsonObject.get("Quest"), (String) jsonObject.get("Reward"));
//        }
//        if (jsonObject.get("type").equals("MINION")) {
//            return  new Minion((long) jsonObject.get("manaCost"), (String) jsonObject.get("name"), (String) jsonObject.get("description"), card.rarity.valueOf((String) jsonObject.get("rarity")), HeroClass.valueOf((String) jsonObject.get("heroClass")) , card.type.valueOf((String) jsonObject.get("type")), (long) jsonObject.get("HP"), (long) jsonObject.get("Attack"));
//        }
//        else if(jsonObject.get("type").equals("WEAPON")){
//            return  new Minion((long) jsonObject.get("manaCost"), (String) jsonObject.get("name"), (String) jsonObject.get("description"), card.rarity.valueOf((String) jsonObject.get("rarity")), HeroClass.valueOf((String) jsonObject.get("heroClass")) , card.type.valueOf((String) jsonObject.get("type")), (long) jsonObject.get("durability"), (long) jsonObject.get("Attack"));
//        }
//        else {
//            return null;
//        }
//
//    }
//    public static void convertFileToJsonForCards(String fileName){
//        //JSON parser object to parse read file
//        JSONParser jsonParser = new JSONParser();
//
//        try (FileReader reader = new FileReader(fileName+".json")) {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray Jlist = (JSONArray) obj;
//            System.out.println(Jlist);
//
//            //Iterate over MageCards array
//            for(int i =0 ; i< Jlist.size(); i++){
//                parseCardObject((JSONObject)Jlist.get(i));
//            }
//            // Jlist.forEach(card -> parseCardObject((JSONObject) card));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void parseCardObject(JSONObject card){
//
//        //Get card object within list
//        JSONObject cardObject = (JSONObject) card.get("card");
//
//        //Get card first name
//        String name = (String) cardObject.get("name");
//        System.out.println(name);
//
//        //Get card mana cost
//        String manaCost = (String) cardObject.get("manaCost");
//        System.out.println(manaCost);
//
//        //Get card type
//        String type = (String) cardObject.get("type");
//        System.out.println(type);
//
//        //Get card first heroClass
//        String heroClass = (String) cardObject.get("heroClass");
//        System.out.println(heroClass);
//
//        //Get card  rarity
//        String rarity = (String) cardObject.get("rarity");
//        System.out.println(rarity);
//
//        //Get card description
//        String description = (String) cardObject.get("description");
//        System.out.println(description + "\n");
//
//
//    }

//        public  static  void jsonFileMakerForCards(String cardName) throws IOException {
//            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
//            FileWriter fileWriter = new FileWriter("./src/main/java/JSON/jsonForCards/jsonFilesForCards/" + cardName + ".json");
//            try {
//                Class<?> cls = Class.forName(cardName);
//                Object clsInstance = (Object) cls.getConstructor(new Class[]{int.class, String.class, String.class, rarity.class, HeroClass.class,type.class,int.class,int.class,int.class});
//                gson.toJson(clsInstance, fileWriter);
//            } catch (ClassNotFoundException | NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            fileWriter.close();
//        }

    }
}
