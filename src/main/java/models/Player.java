package models;
import models.Cards.Card;
import com.google.gson.annotations.Expose;
import models.Cards.Minion;
import models.Cards.Weapon;
import models.Heroes.Hero;
import models.board.InfoPassive;

import java.security.*;
import java.util.*;
import java.util.logging.*;

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

    @Expose private ArrayList<Deck> Decks = new ArrayList<>() ;
    @Expose private ArrayList<Card> HandsCards =new ArrayList<>() ;
    @Expose private ArrayList<Card> DeckCardsInGame =new ArrayList<>() ;
    @Expose private ArrayList<Minion> FieldCardsInGame =new ArrayList<>() ;
    @Expose private int initialMana = 0;
//    @Expose private Weapon weaponInGame;
    @Expose private String cardSkin = "BlushRoomCardBack.png";
    @Expose private String playBackGround = "HSplayBoard copy.jpg";
    @Expose private Boolean makeNewGame;
    @Expose private Boolean isSignedUp = false;

    @Expose private int currentMana = 0;
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
    public void setPlayersChoosedHero(Card.HeroClass playersChoosedHero) {
        //TODO
//        if(playersChoosedHero ==Card.HeroClass.MAGE) playersChoosedHero =
//        PlayersChoosedHero = playersChoosedHero;
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

    public void setPlayersDeckCards(ArrayList<Card> playersDeckCards) {
        this.playersDeckCards = playersDeckCards;
    }

    public int getInitialMana() {
        return initialMana;
    }

    public void setInitialMana(int initialMana) {
        this.initialMana = initialMana;
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

    Scanner scanner = new Scanner(System.in);

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


    public Hero getPlayerHero(String HeroName) {
        for (Hero hero : PlayersUnlockedHeroes) {
            if (hero.toString().equals(HeroName))
                return hero;
        }
        System.out.println("get player hero , doesnt exists");
        return null;
    }

    public ArrayList<Card> getHandsCards() {
        return HandsCards;
    }

    public void setHandsCards(ArrayList<Card> handsCards) {
        HandsCards = handsCards;
    }

    public int getCurrentMana() {
        return Math.min(currentMana + initialMana,10);
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

    public ArrayList<Minion> getFieldCardsInGame() {
        return FieldCardsInGame;
    }

    public void setFieldCardsInGame(ArrayList<Minion> fieldCardsInGame) {
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

//    public Weapon getWeaponInGame() {
//        return weaponInGame;
//    }
//
//    public void setWeaponInGame(Weapon weaponInGame) {
//        this.weaponInGame = weaponInGame;
//    }

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
