package request_response.request;

import models.Cards.Card;
import models.Deck;
import models.Heroes.Mage;
import models.Player;
import server.ClientHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;


public class SignUp extends Request {
    private final String name, pass;
    public SignUp(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        String message = " ";
        Player player = clientHandler.getServer().getDataBase().fetch(Player.class,name);
        if(player!=null) message ="Error! Sorry this user name is taken, Try something else..";
        else if(name.length()<3) message = "Error! your username should contain more than 2 Characters , try again";
        else if(pass.length()<3) message = "Error! your password should contain more than 2 Characters , try again";
        else {
            player = new Player();
            player.setName(name);
            player.setPassword(clientHandler.getPlayerController().getHashedPassword(pass));
            player.setCoins(50);
            player.setID(System.currentTimeMillis());
            player.setChoosedHero(new Mage());
            List<Card> optionalMageDeck = null;
            optionalMageDeck = clientHandler.getServer().getModelLoader().getDefaultCards();
//            optionalMageDeck = (new ArrayList<Card>(Arrays.asList(clientHandler.getCardController().createCard("LearnDraconic"),clientHandler.getCardController().createCard("LearnDraconic"),clientHandler.getCardController().createCard("LearnDraconic"),clientHandler.getCardController().createCard("LearnDraconic"),clientHandler.getCardController().createCard("PharaohsBlessing"),clientHandler.getCardController().createCard("PharaohsBlessing"),clientHandler.getCardController().createCard("PharaohsBlessing"),clientHandler.getCardController().createCard("PharaohsBlessing"),clientHandler.getCardController().createCard("SecurityRover"),clientHandler.getCardController().createCard("SecurityRover"),clientHandler.getCardController().createCard("SwampKingDred"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("HighPriestAmet"),clientHandler.getCardController().createCard("RocketAugmerchant"),clientHandler.getCardController().createCard("PsycheSplit"),clientHandler.getCardController().createCard("CurioCollector"))));

            Deck deck = new Deck("optional Mage deck",optionalMageDeck);
            deck.setHero(Card.HeroClass.MAGE);
            player.setDeck(deck);
            player.setDecks(new ArrayList<>()) ;
            player.getDecks().add(player.getDeck());
            ArrayList<Card> optionalAllCards = new ArrayList<>();
            optionalAllCards.addAll(optionalMageDeck);
            player.setAllCards(optionalAllCards);
            player.getPlayersUnlockedHeroes().add(new Mage());
            clientHandler.getServer().getDataBase().save(player);
            clientHandler.setMainPlayer(player);
            LogManager.getLogManager().reset();
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("./src/main/java/logs/"+ player.getName()+"-"+player.getID()+".log",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileHandler.setFormatter(new SimpleFormatter());
            clientHandler.getPlayerLOGGER().addHandler(fileHandler);
            clientHandler.getPlayerLOGGER().info("USER  : " + player.getName() + "\nCREATED AT :" +
                    new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + player.getPassword() + "\n");
            message = "you are signed up successfully! BEGIN YOUR JOURNEY IN HEARTH STONE!!";
        }
        clientHandler.sendResponse("SignUp",new request_response.response.SignUp(message));
    }
}
