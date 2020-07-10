package controller.modes;

import controller.BoardController;
import controller.Controller;
import models.Cards.Card;
import models.Heroes.Mage;
import models.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;

public class Practice extends BoardController {

    //TODO method to switch between enemy and friendly

    public Practice(){

    }


    @Override
    protected void setPlayers() {
        if(isANewGame()) {
            friendlyPlayer = Controller.getInstance().getMainPlayer();
            friendlyPlayer.setCurrentMana(0);
            friendlyPlayer.setHandsCards(new ArrayList<>());
            friendlyPlayer.setFieldCardsInGame(new ArrayList<>());
            friendlyPlayer.setDeckCardsInGame(new ArrayList<>());
            for(Card card : friendlyPlayer.getPlayersDeck().getCards()) friendlyPlayer.getDeckCardsInGame().add(card);
            //todo you can go and choose controller.getMainPlayer ... or create them here
            enemyPlayer = new Player();
            enemyPlayer.setPlayersDeckCards(new ArrayList<>());
            for(Card card : friendlyPlayer.getDeckCardsInGame())enemyPlayer.getDeckCardsInGame().add(card);
//            enemyPlayer.setDeckCardsInGame(new ArrayList<Card>(Arrays.asList(creatCardFromjson("SwarmOfLocusts"),creatCardFromjson("SwarmOfLocusts"),creatCardFromjson("PharaohsBlessing"),creatCardFromjson("BookOfSpecters"),creatCardFromjson("DreadScale"),creatCardFromjson("Starscryer"),creatCardFromjson("FungalBruiser"),creatCardFromjson("BeamingSidekick"),creatCardFromjson("SerratedTooth"),creatCardFromjson("BonechewerVanguard"),creatCardFromjson("CurioCollector"),creatCardFromjson("Sathrovarr"),creatCardFromjson("ScrapDeadlyShot"),creatCardFromjson("FriendlySmith"),creatCardFromjson("LostSpirit"),creatCardFromjson("HighPriestAmet"),creatCardFromjson("DreadScale"),creatCardFromjson("LearnDraconic"),creatCardFromjson("ScrapDeadlyShot"),creatCardFromjson("Starscryer"),creatCardFromjson("FrozenShadoweaver"),creatCardFromjson("CurioCollector"))));
            enemyPlayer.setPlayersChoosedHero(new Mage());
            enemyPlayer.setPlayerName("aaa");

        }
    }

//    @Override
//    public void checkForNewGame() {
//        super.checkForNewGame();
//        switchPlayers();
//        super.checkForNewGame();
//    }

//    @Override
//    public void endTurn() {
//        switchPlayers();
//        super.endTurn();
//    }
}
