package models;
import cliAndMenus.gameCLI;
import models.Cards.Card;
import com.google.gson.annotations.Expose;
import models.Heroes.Hero;
import models.Heroes.Mage;
import models.Heroes.Rogue;
import models.Heroes.Warlock;
import models.board.InfoPassive;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;
import static JSON.jsonForPlayers.jsonForPlayers.*;
import static controller.CardController.getALLCardsExistingInGame;

public class Player {

    /** defining fields in Player class */
    @Expose private String PlayerName;
    @Expose private String PlayerPassword;
    @Expose private long PlayerCoins ,PlayerID;
    @Expose private Hero PlayersChoosedHero = new Hero();
    @Expose private  ArrayList<Card> ALLPlayersCards = new ArrayList<>();
    @Expose private ArrayList<Card> playersDeckCards = new ArrayList<>();
    @Expose private Deck playersDeck ;
    @Expose private ArrayList<Hero> PlayersUnlockedHeroes = new ArrayList<>();
    @Expose private Mage PlayersMage = new Mage(); @Expose private Rogue PlayersRogue = new Rogue(); @Expose private Warlock PlayersWarlock = new Warlock();
    @Expose public ArrayList<Card> mageDeckCards =new ArrayList<>();
    @Expose public ArrayList<Card> rogueDeckCards =new ArrayList<>();
    @Expose public ArrayList<Card> warlockDeckCards =new ArrayList<>();
    @Expose private ArrayList<Deck> Decks = new ArrayList<>() ;
    @Expose private ArrayList<Card> HandsCards =new ArrayList<>() ;
    @Expose private ArrayList<Card> DeckCardsInGame =new ArrayList<>() ;
    @Expose private ArrayList<Card> FieldCardsInGame =new ArrayList<>() ;
    @Expose private String cardSkin = "BlushRoomCardBack.png";
    @Expose private String playBackGround = "HSplayBoard copy.jpg";
    @Expose private Boolean makeNewGame;
    @Expose private Boolean isSignedUp = false;

    @Expose private int currentMana;
    @Expose private InfoPassive infoPassive;


    private Logger PlayerLOGGER = Logger.getLogger("PlayerLog");

    /** defining getters and setters for the fields  */
    public String getPlayerName() {
        return PlayerName;
    }
    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }
    public String getPlayerPassword() {
        return PlayerPassword;
    }
    public void setPlayerPassword(String playerPassword) {
        PlayerPassword = playerPassword;
    }
    public long getPlayerCoins() {
        return PlayerCoins;
    }
    public void setPlayerCoins(long playerCoins) {
        PlayerCoins = playerCoins;
    }
    public Hero getPlayersChoosedHero() {
        return PlayersChoosedHero;
    }
    public void setPlayersChoosedHero(Hero playersChoosedHero) {
        PlayersChoosedHero = playersChoosedHero;
    }
    public void setPlayersChoosedHero(Card.HeroClass heroClass){
        if(heroClass== Card.HeroClass.MAGE) setPlayersChoosedHero(getPlayersMage());
        if(heroClass== Card.HeroClass.ROGUE) setPlayersChoosedHero(getPlayersRogue());
        if(heroClass== Card.HeroClass.WARLOCK) setPlayersChoosedHero(getPlayersWarlock());
    }
    public ArrayList<Card> getALLPlayersCards() {
        return ALLPlayersCards;
    }
    public long getPlayerID() {
        return PlayerID;
    }
    public void setPlayerID(long playerID) {
        PlayerID = playerID;
    }
    public void setALLPlayersCards(ArrayList<Card> ALLPlayersCards) {
        this.ALLPlayersCards = ALLPlayersCards;
    }

    public ArrayList<Card> getPlayersDeckCards() {
        return playersDeckCards;
    }

    public void setPlayersDeckCards(ArrayList<Card> playersDeckCards) {
        this.playersDeckCards = playersDeckCards;
    }

    public ArrayList<Deck> getDecks() {
       Collections.sort(Decks, new Deck("",null).getDeckComparator());
        return Decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        Decks = decks;
    }

    public ArrayList<Hero> getPlayersUnlockedHeroes() {
        return PlayersUnlockedHeroes;
    }

    public void setPlayersUnlockedHeroes(ArrayList<Hero> playersUnlockedHeroes) {
        PlayersUnlockedHeroes = playersUnlockedHeroes;
    }

    public Deck getPlayersDeck() {
        return playersDeck;
    }

    public void setPlayersDeck(Deck playersDeck) {
        this.playersDeck = playersDeck;
    }
//
    public Logger getPlayerLOGGER() {
        return PlayerLOGGER;
    }
    public void setPlayerLOGGER(Logger playerLOGGER) {
        PlayerLOGGER = playerLOGGER;
    }

    Scanner scanner = new Scanner(System.in);

    public void Signup() throws IOException {
        System.out.println("*** so let's sign up :) ***");
        boolean flagName = false , flagPass = false;

        while (!flagName) {
            System.out.println("Creat your user name: ");
            this.setPlayerName(scanner.nextLine());
            /** checking if the name already exist in players json files or not */
            if(getPlayerFiles(PlayerName)==null){
                flagName = true;
            }
            else {
                System.out.println("Sorry this name is taken, Try something else..");
            }
        }
        while (!flagPass) {
            System.out.println("Creat your password: ");
            String input = scanner.nextLine();
            if(input.length()>=4) {
                this.setPlayerPassword(getHashedPassword(input));
                flagPass = true;
            }
            else {
                System.out.println("your password should contain more than 3 Characters , try again");
            }
        }
        /** mkiang a json file for this player */
        this.setPlayerCoins(50);
        this.setPlayersChoosedHero(PlayersMage);
        this.setPlayerID(System.currentTimeMillis());
//        this.mageDeckCards = (new ArrayList<Card>(Arrays.asList(creatCardFromjson("Polymorph"),creatCardFromjson("RollingFireball"),creatCardFromjson("MurlocRaider"),creatCardFromjson("MalygossExplosion"),creatCardFromjson("MalygossNova"),creatCardFromjson("Backstab"),creatCardFromjson("GoblinBomb"),creatCardFromjson("LostSpirit"),creatCardFromjson("SerratedTooth"),creatCardFromjson("MagmaRager"))));
//        for(Card card : getALLCardsExistingInGame()){
//            if(card.getHeroClass() == Card.HeroClass.MAGE){
//                this.PlayersMage.getHeroAllCards().add(card);
//            }
//            if(card.getHeroClass() == Card.HeroClass.ROGUE){
//                this.PlayersRogue.getHeroAllCards().add(card);
//            }
//            if(card.getHeroClass() == Card.HeroClass.WARLOCK){
//                this.PlayersWarlock.getHeroAllCards().add(card);
//            }
//        }
     //   this.PlayersDeckCards = PlayersMage.getHeroDeckCards();
        this.setALLPlayersCards((new ArrayList<Card>(Arrays.asList(creatCardFromjson("Polymorph"),creatCardFromjson("RollingFireball"),creatCardFromjson("MurlocRaider"),creatCardFromjson("MalygossExplosion"),creatCardFromjson("MalygossNova"),creatCardFromjson("Backstab"),creatCardFromjson("GoblinBomb"),creatCardFromjson("LostSpirit"),creatCardFromjson("SerratedTooth"),creatCardFromjson("MagmaRager"),creatCardFromjson("TimeRip"),creatCardFromjson("BlinkFox"),creatCardFromjson("HungryCrab")))));
       // this.setPlayersDeckCards(this.getPlayersChoosedHero().getHeroDeckCards());
        this.PlayersUnlockedHeroes.add(PlayersMage);
        jsonTofilePlayer(this);
        gameCLI.getInstance().setCurrentPlayer(this);

        LogManager.getLogManager().reset();
        FileHandler fileHandler = new FileHandler("src/logs/"+ getPlayerName()+"-"+getPlayerID()+".log",true);
        fileHandler.setFormatter(new SimpleFormatter());
        PlayerLOGGER.addHandler(fileHandler);
        PlayerLOGGER.info("USER  : " + getPlayerName() + "\nCREATED AT :" +  new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + getPlayerPassword() + "\n");
        System.out.println("you are signed up successfully! BEGIN YOUR JOURNEY IN HEARTH STONE!!");
    }

    public String getHashedPassword(String playerPassword){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(playerPassword.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length ; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void Signin() throws IOException {
        System.out.println("*** so let's sign in :) ***");
        boolean flagName = false, flagPass = false;
        String CorrespondingPassword = "";
        while (!flagName) {
            System.out.println("Enter your user name: ");
            PlayerName = scanner.nextLine();
            /**check if players name exists ...*/
            if (getPlayerFiles(getPlayerName()) == null) {
                System.out.println("There isn't an account in this name , Try again..");
            } else {
                flagName = true;
                CorrespondingPassword = jsonFileReader(getPlayerName()).getPlayerPassword();
            }
        }
        while (!flagPass) {
            System.out.println("Enter your Password: ");
            String pass = scanner.nextLine();
            /**check if players password is correct for the corresponding name ...*/
            if (!getHashedPassword(pass).equals(CorrespondingPassword)) {
                System.out.println("Wrong password , Try again..");
            } else {
                flagPass = true;
            }
        }
        gameCLI.getInstance().setCurrentPlayer(jsonFileReader(this.getPlayerName()));

        LogManager.getLogManager().reset();
        FileHandler fileHandler = new FileHandler("src/logs/"+ getPlayerName()+"-"+getPlayerID()+".log",true);
        fileHandler.setFormatter(new SimpleFormatter());
        PlayerLOGGER.addHandler(fileHandler);
        PlayerLOGGER.info("USER  : " + getPlayerName() + "\nSigned_In AT :" +  new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + getPlayerPassword() + "\n");
        System.out.println("you are signed in successfully! WELCOME BACK " + getPlayerName() + " !!");
    }

    public  void deleteThePlayer() throws IOException {
        Boolean isvalid = false;
        while (!isvalid) {
            System.out.println("If you are sure of DELETING your account, enter your Password : ");
            String pass = scanner.nextLine();
            if (getHashedPassword(pass).equals(this.PlayerPassword)) {
                isvalid = true;
                /** adding deleted to the log file in line 4 */
                File file = new File("src/logs/"+ getPlayerName()+"-"+getPlayerID()+".log");
                File temp = File.createTempFile("temp-file-name", ".log");
                BufferedReader br = new BufferedReader(new FileReader( file));
                PrintWriter pw =  new PrintWriter(new FileWriter( temp ));
                String line;
                int lineCount = 0;
                while ((line = br.readLine()) != null) {
                    pw.println(line);
                    if(lineCount==3){
                        pw.println("PLAYER_DELETED_AT : " +  new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\n");
                    }
                    lineCount++;
                }
                br.close();
                pw.close();
                file.delete();
                temp.renameTo(file);

                getPlayerFiles(PlayerName).deleteOnExit();
                System.exit(0);
            } else {
                System.out.println("Wrong password! ");
            }
        }
    }

    public Hero getPlayerHero(String HeroName){
        for (Hero hero : PlayersUnlockedHeroes){
            if(hero.toString().equals(HeroName))
                return hero;
        }
        System.out.println("get player hero , doesnt exists");
        return null;
    }

    public Mage getPlayersMage() {
        return PlayersMage;
    }

    public Rogue getPlayersRogue() {
        return PlayersRogue;
    }

    public Warlock getPlayersWarlock() {
        return PlayersWarlock;
    }

    public ArrayList<Card> getHandsCards() {
        return HandsCards;
    }

    public void setHandsCards(ArrayList<Card> handsCards) {
        HandsCards = handsCards;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public ArrayList<Card> getDeckCardsInGame() {
        return DeckCardsInGame;
    }

    public void setDeckCardsInGame(ArrayList<Card> deckCardsInGame) {
        DeckCardsInGame = deckCardsInGame;
    }

    public ArrayList<Card> getFieldCardsInGame() {
        return FieldCardsInGame;
    }

    public void setFieldCardsInGame(ArrayList<Card> fieldCardsInGame) {
        FieldCardsInGame = fieldCardsInGame;
    }

    public String getCardSkin() {
        return cardSkin;
    }

    public void setCardSkin(String cardSkin) {
        this.cardSkin = cardSkin;
    }

    public String getPlayBackGround() {
        return playBackGround;
    }

    public void setPlayBackGround(String playBackGround) {
        this.playBackGround = playBackGround;
    }

    public Boolean getMakeNewGame() {
        return makeNewGame;
    }

    public void setMakeNewGame(Boolean makeNewGame) {
        this.makeNewGame = makeNewGame;
    }

    public Boolean getSignedUp() {
        return isSignedUp;
    }

    public void setSignedUp(Boolean signedUp) {
        isSignedUp = signedUp;
    }

    /** if all cards of a hero were needed i will un comment this part */
//    public static ArrayList<card> getPlayersMageCards(){
//        ArrayList<card> arrayList = new ArrayList<>();
//        for (card card : ALLPlayersCards) {
//            if((Hero)card.getHeroClass()== Mage.getInstance())
//                arrayList.add(card);
//        }
//        return arrayList;
//    }
//    public static ArrayList<card> getPlayersRougeCards(){
//        ArrayList<card> arrayList = new ArrayList<>();
//        for (card card : ALLPlayersCards) {
//            if((Hero)card.getHeroClass()== Rogue.getInstance())
//                arrayList.add(card);
//        }
//        return arrayList;
//    }
//    public static ArrayList<card> getPlayersWarlockCards(){
//        ArrayList<card> arrayList = new ArrayList<>();
//        for (card card : ALLPlayersCards) {
//            if((Hero)card.getHeroClass()== Mage.getInstance())
//                arrayList.add(card);
//        }
//        return arrayList;
//    }

/** may be needed in future :D */
    //  public static  Player enemyPlayer = new Player();
//    public ArrayList<card> getPlayersBoardCards() {
//        return PlayersBoardCards;
//    }
//
//    public void setPlayersBoardCards(ArrayList<card> playersBoardCards) {
//        PlayersBoardCards = playersBoardCards;
//    }
//   private ArrayList<card> PlayersBoardCards = new ArrayList<>();


//    public Player(/**String playerName,String playerPassword , long playerCoins, Hero playersChoosedHero, ArrayList ALLPlayersCards, ArrayList playersDeckCards*/) {
//        this.PlayerName = PlayerName;
//        this.PlayerPassword = PlayerPassword;
//        this.PlayerCoins = PlayerCoins;
//        this.PlayersChoosedHero = PlayersChoosedHero;
//        this.ALLPlayersCards = ALLPlayersCards;
//        this.PlayersDeckCards = PlayersDeckCards;
//    }

    //            try {
//                JSONParser jsonParser = new JSONParser();
//                FileReader reader = new FileReader("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForPlayers/jsonFilesForPlayers/ALLPlayers.json");
//                    Object obj = jsonParser.parse(reader);
//                    JSONArray PlayersJList = (JSONArray) obj;
//                for (int i = 0; i <PlayersJList.size() ; i++) {
//                    if(((JSONObject)(((JSONObject)(PlayersJList.get(i))).get("Player"))).get("PlayerName").equals(this.getPlayerName()) ){
//                        throw new PlayerAlreadyExistsException() ;
//                    }
//                }

//            }
//            catch (PlayerAlreadyExistsException | FileNotFoundException e){
//                System.out.println("Sorry this name is taken, Try something else..");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


//                boolean theNameExists = true;
//
//                JSONParser jsonParser = new JSONParser();
//                FileReader reader = new FileReader("/Users/shahinnaghashyar/Desktop/HearthStone/src/JSON/jsonForPlayers/jsonFilesForPlayers/ALLPlayers.json");
//                Object obj = jsonParser.parse(reader);
//                JSONArray PlayersJList = (JSONArray) obj;
//                for (int i = 0; i < PlayersJList.size() ; i++) {
//                    if(((String)((JSONObject)(((JSONObject)(PlayersJList.get(i))).get("Player"))).get("PlayerName")).equals(PlayerName) ){
//                        theNameExists = false;
//                        /** also getting its password to check later in password part */
//                        CorrespondingPassword =(String)((JSONObject)(((JSONObject)(PlayersJList.get(i))).get("Player"))).get("PlayerPassword");
//                        reader.close();
//                        break;
//                    }
//                }
//                if(theNameExists==true){
//                    throw new PlayerNotFoundException();
//                }
//                flagName = true;
//            }
//            catch (PlayerNotFoundException | FileNotFoundException e1){
//                System.out.println("There isn't an account in this name , Try again..");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



//        Scanner scanner = new Scanner(System.in);
//        if(this.IsSignedin == true) {
//            Boolean isvalid = false;
//            while (!isvalid) {
//                System.out.println("If you are sure of DELETING your account, enter your Password : ");
//                String pass = scanner.nextLine();
//                if (pass == this.PlayerPassword) {
//                    isvalid = true;
//                    this.setPlayerName("deleted account");
//                    this.setPlayerPassword("-");
//                    this.setPlayerCoins(0);
//                    this.ALLPlayersCards = null;
//                    this.PlayersChoosedHero=null;
//                    this.PlayersDeckCards=null;
//                     jsonTofilePlayer(this);
//                    System.exit(0);
//                } else {
//                    System.out.println("Wrong password! ");
//                }
//            }
}
