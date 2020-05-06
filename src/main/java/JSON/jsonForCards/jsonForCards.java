package JSON.jsonForCards;

import models.Cards.*;
import models.Cards.Card.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class jsonForCards {

    public static void jsonFileMakerForCards(Card card) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("./src/main/java/JSON/jsonForCards/jsonFilesForCards/" + card.getName() + ".json");
        gson.toJson(card, fileWriter);
        fileWriter.close();
    }

    public static Card creatCardFromjson(String name) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./src/main/java/JSON/jsonForCards/jsonFilesForCards/" + name + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Card card = gson.fromJson(fileReader, Card.class);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return card;
    }

    static  Spell Polymorph = new Spell(4, "Polymorph", "Transform minion in to a 1/1 sheep.", rarity.RARE, HeroClass.MAGE, type.SPELL, 10, "", "");
    static  Spell RollingFireball = new Spell(5, "RollingFireball", "Deal 8 damage to a minion. Any excess damage continues to the left or right.1 sheep.", rarity.EPIC, HeroClass.MAGE, type.SPELL, 15, "", "");
    static  Spell FriendlySmith = new Spell(1, "FriendlySmith", "Discover a weapon from any class. Add it to your Adventure Deck with +2/+2", rarity.COMMON, HeroClass.ROGUE, type.SPELL, 5, "", "");
    static Spell BlinkFox = new Spell(3, "BlinkFox", "Battlecry: Add a random card to your hand (from your opponent's class).", rarity.COMMON, HeroClass.ROGUE, type.SPELL, 5, "", "");
    static Minion Ratcatcher = new Minion(3, "Ratcatcher", "Rush   Battlecry: Destroy a friendly minion and gain its Attack and Health.", rarity.LEGENDARY, HeroClass.WARLOCK, type.MINION, 20, 2, 2);
    static Minion AbusiveSergeant = new Minion(1, "AbusiveSergeant", "Battlecry: Give a minion +2 Attack this turn.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 1);
    static Minion HungryCrab = new Minion(1, "HungryCrab", "Destroy a Murloc and gain +2/+2.", rarity.EPIC, HeroClass.NEUTRAL, type.MINION, 15, 2, 1);
    static Minion GoblinBomb = new Minion(1, "GoblinBomb", "Deal 2 damage to the enemy hero.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 2, 0);
    static Minion BeamingSidekick = new Minion(1, "BeamingSidekick", "Give a friendly minion +2 Health.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 2, 1);
    static Minion LostSpirit = new Minion(2, "LostSpirit", "Deathrattle: Give your minions +1 Attack.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 1);
    static Minion MagmaRager = new Minion(3, "MagmaRager", "-", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 5);
    static Minion MurlocRaider = new Minion(1, "MurlocRaider", "-", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 2);
    static Minion EtherealAugmerchant = new Minion(1, "EtherealAugmerchant", "Battlecry: Deal 1 damage to a minion and give it Spell Damage +1.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 4);
    static Minion FrozenShadoweaver = new Minion(3, "FrozenShadoweaver", "Battlecry: Freeze an enemy.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 4);
    static Minion BonechewerVanguard = new Minion(7, "BonechewerVanguard", "Taunt , Whenever this minion takes damage, gain +2 Attack.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 10, 4);
    static Minion ImprisonedVilefiend = new Minion(2, "ImprisonedVilefiend", "Dormant for 2 turns.\n" + "Rush", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 5, 3);
    static Minion DisguisedWanderer = new Minion(4, "DisguisedWanderer", "Deathrattle: Summon a 9/1 Inquisitor.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 3);
    static Minion ScavengingShivarra = new Minion(6, "ScavengingShivarra", "Battlecry: Deal 6 damage randomly split among all other minions.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 6);
    static Minion RustswornCultist = new Minion(4, "RustswornCultist", "Battlecry: Give your other minions \"Deathrattle: Summon a 1/1 Demon.\"", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 3, 3);
    static Minion RocketAugmerchant= new Minion(1, "RocketAugmerchant", "Battlecry: Deal 1 damage to a minion and give it Rush.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 2);
    static Minion RuststeedRaider= new Minion(5, "RuststeedRaider", "Battlecry: Deal 1 damage to a minion and give it Rush.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 1, 2);
    static Minion DragonmawSkyStalker = new Minion(6, "DragonmawSkyStalker", "Deathrattle: Summon a 3/4 Dragonrider.", rarity.COMMON, HeroClass.NEUTRAL, type.MINION, 5, 6, 5);
    static Minion InfectiousSporeling= new Minion(1, "InfectiousSporeling", "After this damages a minion, turn it into an Infectious Sporeling.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 2, 1);
    static Minion Helboar= new Minion(1, "Helboar", "Deathrattle: Give a random Beast in your hand +1/+1.", rarity.COMMON, HeroClass.HUNTER, type.MINION, 5, 1, 2);
    static Minion SethekkVeilweaver = new Minion(2, "SethekkVeilweaver", "After you cast a spell on a minion, add a Priest spell to your hand.", rarity.EPIC, HeroClass.PRIEST, type.MINION, 15, 3, 2);
    static Minion CurioCollector = new Minion(5, "CurioCollector", "Whenever you draw a card, gain +1/+1.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 4, 4);
    static Minion SecurityRover = new Minion(6, "SecurityRover", "Whenever this minion takes damage, summon a 2/3 Mech with Taunt.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 6, 2);
    static Minion TombWarden = new Minion(8, "TombWarden", "Taunt\n" + "Battlecry: Summon a copy of this minion.", rarity.RARE, HeroClass.NEUTRAL, type.MINION, 10, 6, 3);
    static Minion Sathrovarr = new Minion(9, "Sathrovarr", "TChoose a friendly minion. Add a copy of it to your hand, deck and battlefield.", rarity.LEGENDARY, HeroClass.NEUTRAL, type.MINION, 20, 5, 5);
    static Spell Backstab = new Spell(0, "Backstab", "Deal 2 damage to an undamaged minion.", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
    static Spell MalygossFrostbolt = new Spell(0, "MalygossFrostbolt", "Deal 3 damage to a character and Freeze it.", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
    static Spell MalygossNova = new Spell(1, "MalygossNova", "Freeze all enemy minions.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
    static Spell MalygossExplosion = new Spell(2, "MalygossExplosion", "Deal 2 damage to all enemy minions.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
    static Spell TimeRip = new Spell(5, "TimeRip", "Destroy a minion. Invoke Galakrond.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
    static Spell CandleBreath = new Spell(6, "CandleBreath", "Destroy a minion. Invoke Galakrond.", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
    static Spell ScrapShot = new Spell(4, "ScrapShot", "Deal 3 damage. Give a random Beast in your hand +3/+3.", rarity.RARE, HeroClass.HUNTER, type.SPELL, 10, "", "");
    static Spell Renew = new Spell(1, "Renew", "Restore 3 Health. Discover a spell.", rarity.COMMON, HeroClass.PRIEST, type.SPELL, 5, "", "");
    static Spell Sprint = new Spell(7, "Sprint", "Draw 4 cards.\n", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "", "");
    static Spell PharaohsBlessing = new Spell(6, "PharaohsBlessing", "Give a minion +4/+4, Divine Shield, and Taunt.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
    static Spell SwarmOfLocusts = new Spell(6, "SwarmOfLocusts", "Summon seven 1/1 Locusts with Rush.", rarity.RARE, HeroClass.NEUTRAL, type.SPELL, 10, "", "");
    static Spell BookOfSpecters = new Spell(2, "BookOfSpecters", "Draw 3 cards. Discard any spells drawn.", rarity.EPIC, HeroClass.NEUTRAL, type.SPELL, 15, "", "");
    static Spell StrengthInNumbers = new Spell(1, "StrengthInNumbers", "-", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "Sidequest: Spend 10 Mana on minions.", "Reward: Summon a minion from your deck.");
    static Spell LearnDraconic= new Spell(1, "LearnDraconic", "-", rarity.COMMON, HeroClass.NEUTRAL, type.SPELL, 5, "Sidequest: Spend 8 Mana on spells.", "Reward: Summon a 6/6 Dragon.");
    static Weapon LightsJustice = new Weapon(1, "LightsJustice", "", rarity.COMMON, HeroClass.NEUTRAL, type.WEAPON, 5, 4, 1);
    static Weapon SerratedTooth = new Weapon(1, "SerratedTooth", "Deathrattle: Give your minions Rush.", rarity.COMMON, HeroClass.NEUTRAL, type.WEAPON, 5, 1, 1);
    static Weapon HeadhuntersHatchet = new Weapon(2, "HeadhuntersHatchet", "Battlecry: If you control a Beast, gain +1 Durability.", rarity.COMMON, HeroClass.NEUTRAL, type.WEAPON, 5, 2, 2);



    public static void main(String[] args) throws IOException {
        jsonFileMakerForCards(Polymorph);
        jsonFileMakerForCards(RollingFireball);
        jsonFileMakerForCards(FriendlySmith);
        jsonFileMakerForCards(BlinkFox);
        jsonFileMakerForCards(Ratcatcher);
        jsonFileMakerForCards(AbusiveSergeant);
        jsonFileMakerForCards(HungryCrab);
        jsonFileMakerForCards(GoblinBomb);
        jsonFileMakerForCards(BeamingSidekick);
        jsonFileMakerForCards(LostSpirit);
        jsonFileMakerForCards(MagmaRager);
        jsonFileMakerForCards(MurlocRaider);
        jsonFileMakerForCards(Backstab);
        jsonFileMakerForCards(MalygossFrostbolt);
        jsonFileMakerForCards(MalygossNova);
        jsonFileMakerForCards(MalygossExplosion);
        jsonFileMakerForCards(TimeRip);
        jsonFileMakerForCards(CandleBreath);
        jsonFileMakerForCards(LightsJustice);
        jsonFileMakerForCards(SerratedTooth);
        jsonFileMakerForCards(HeadhuntersHatchet);
        jsonFileMakerForCards(EtherealAugmerchant);
        jsonFileMakerForCards(FrozenShadoweaver);
        jsonFileMakerForCards(BonechewerVanguard);
        jsonFileMakerForCards(ImprisonedVilefiend);
        jsonFileMakerForCards(DisguisedWanderer);
        jsonFileMakerForCards(ScavengingShivarra);
        jsonFileMakerForCards(RustswornCultist);
        jsonFileMakerForCards(RocketAugmerchant);
        jsonFileMakerForCards(RuststeedRaider);
        jsonFileMakerForCards(DragonmawSkyStalker);
        jsonFileMakerForCards(InfectiousSporeling);
        jsonFileMakerForCards(Helboar);
        jsonFileMakerForCards(SethekkVeilweaver);
        jsonFileMakerForCards(CurioCollector);
        jsonFileMakerForCards(SecurityRover);
        jsonFileMakerForCards(TombWarden);
        jsonFileMakerForCards(Sathrovarr);
        jsonFileMakerForCards(ScrapShot);
        jsonFileMakerForCards(Renew);
        jsonFileMakerForCards(Sprint);
        jsonFileMakerForCards(PharaohsBlessing);
        jsonFileMakerForCards(SwarmOfLocusts);
        jsonFileMakerForCards(BookOfSpecters);
        jsonFileMakerForCards(StrengthInNumbers);
        jsonFileMakerForCards(LearnDraconic);

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

    }
}
