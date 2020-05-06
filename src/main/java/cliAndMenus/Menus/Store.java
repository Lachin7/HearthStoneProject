package cliAndMenus.Menus;

import models.Cards.Card;
import cliAndMenus.gameCLI;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;


import static JSON.jsonForCards.jsonForCards.creatCardFromjson;
import static JSON.jsonForPlayers.jsonForPlayers.jsonTofilePlayer;
import static controller.CardController.getALLCardsExistingInGame;


public class Store{

    private static Store store = new Store();
    public static Store getInstance(){return store;}
    public void goToStoreMenu() throws IOException {
        gameCLI.getInstance().getCurrentPlayer().getPlayerLOGGER().log(Level.INFO,"OPENED_STORE");
        Scanner scanner = new Scanner(System.in);
        Boolean isValidInput1= false , isValidInput2 = false ;

        while (!isValidInput1) {
            System.out.println("Choose to : 1.See models.Cards in store 2.buy a card 3.sell a card 4.see your wallet (1/2/3/4/back) : ");
            String input1 = scanner.nextLine();
            if (input1.equalsIgnoreCase("1") || input1.equalsIgnoreCase("See models.Cards in store")) {
                isValidInput1 = true;
                while (!isValidInput2){
                    System.out.println("choose to see 1.the cards you can sell them 2.the cards you can buy from store (1/2) :");
                    String input2 = scanner.nextLine();
                    if(input2.equalsIgnoreCase("1")||input2.equalsIgnoreCase("the cards you can sell them")){
                        showCardsUCanSell();
                        goToStoreMenu();
                        break;
                    }
                    if(input2.equalsIgnoreCase("2")||input2.equalsIgnoreCase("the cards you can buy from store")){
                        System.out.println(getALLCardsExistingInGame());
                        goToStoreMenu();
                        isValidInput2 = true;
                    }
                    if(input2.equalsIgnoreCase("back")){
                        gameCLI.getInstance().goToMenus();
                        isValidInput2 = true;
                    }
                    else {
                        System.out.println("invalid input!!");
                        gameCLI.getInstance().getCurrentPlayer().getPlayerLOGGER().log(Level.INFO, "ERROR : invalid input  IN_LINE:45");
                    }
                }
            }
            if(input1.equalsIgnoreCase("2") || input1.equalsIgnoreCase("buy a card")){
                isValidInput1 =true;
                buyTheCard();
               o : while (true) {
                   System.out.println("1.back to Store Menu/ 2.buy another card? (1/2)");
                   i : switch (scanner.nextLine()) {
                        case "1":
                            goToStoreMenu();
                            break o ;
                        case "2":
                            buyTheCard();
                            break o ;
                        default:
                            System.out.println("invalid input");
                            break i;
                    }
                }
            }
            if(input1.equalsIgnoreCase("3") || input1.equalsIgnoreCase("sell a card")){
                isValidInput1 =true;
                sellTheCard();
                o : while (true) {
                    System.out.println("1.back to Store Menu/ 2.sell card? (1/2)");
                    i : switch (scanner.nextLine()) {
                        case "1":
                            goToStoreMenu();
                            break o ;
                        case "2":
                            sellTheCard();
                            break o ;
                        default:
                            System.out.println("invalid input");
                    }
                }
            }
            if(input1.equalsIgnoreCase("4") || input1.equalsIgnoreCase("see your wallet")){
                System.out.println("You have "+ gameCLI.getInstance().getCurrentPlayer().getPlayerCoins()+ " coins!");
                goToStoreMenu();
                isValidInput1 =true;
            }
            if(input1.equalsIgnoreCase("back")){
                gameCLI.getInstance().goToMenus();
                isValidInput1 =true;
            }
            else {
                System.out.println("invalid input ");
            }
        }
    }
    public static void buyTheCard() throws IOException {
        Boolean isValid = false;
      o :  while (!isValid) {
            System.out.println("Enter the Card's name to buy it from store");
            String cardName = new Scanner(System.in).nextLine();
            for (Card card : getALLCardsExistingInGame()) {
                if (card.getName().equalsIgnoreCase(cardName)&& (card.getHeroClass().toString().equalsIgnoreCase(gameCLI.getInstance().getCurrentPlayer().getPlayersChoosedHero().toString())||card.getHeroClass().toString().equalsIgnoreCase("NEUTRAL"))) {
                    if(card.getPrice() <= gameCLI.getInstance().getCurrentPlayer().getPlayerCoins()) {
                        gameCLI.getInstance().getCurrentPlayer().getPlayersDeckCards().add(card);
                        gameCLI.getInstance().getCurrentPlayer().getALLPlayersCards().add(card);
                        gameCLI.getInstance().getCurrentPlayer().setPlayerCoins(gameCLI.getInstance().getCurrentPlayer().getPlayerCoins() - card.getPrice());
                        System.out.println(cardName + "has been bought from store successfully !");
                        gameCLI.getInstance().getCurrentPlayer().getPlayerLOGGER().log(Level.INFO,"bought this card from store : " + card.toString());
                        jsonTofilePlayer(gameCLI.getInstance().getCurrentPlayer());
                        isValid = true;
                        break o;
                    }
                    else {
                        System.out.println("you don't have enough coins to buy this card :'(  ...");
                    }
                }
            }
            System.out.println("your input is invalid!! ");
            gameCLI.getInstance().getCurrentPlayer().getPlayerLOGGER().log(Level.INFO, "ERROR : invalid input  IN_LINE:115");
        }
    }
    public  void showCardsUCanSell(){
        gameCLI.getInstance().getCurrentPlayer().getPlayerLOGGER().log(Level.INFO,"SHOW_CARDS_USER_CAN_SELL");
        System.out.println("lets see which cards you can sell : ");
        for(Card card : gameCLI.getInstance().getCurrentPlayer().getALLPlayersCards()){
            boolean canSell = true;
            if(gameCLI.getInstance().getCurrentPlayer().mageDeckCards.contains(card)||gameCLI.getInstance().getCurrentPlayer().rogueDeckCards.contains(card)||gameCLI.getInstance().getCurrentPlayer().warlockDeckCards.contains(card)){
                canSell =false;
            }
            if(canSell==true){
                System.out.println(card.toString());
            }
        }
    }
    public  void sellTheCard() throws IOException {
        showCardsUCanSell();
        System.out.println("enter the cards name for sell : ");
        String inputCard = new Scanner(System.in).nextLine();
        Boolean canSell = true;
            if(gameCLI.getInstance().getCurrentPlayer().mageDeckCards.contains(inputCard)||gameCLI.getInstance().getCurrentPlayer().rogueDeckCards.contains(inputCard)||gameCLI.getInstance().getCurrentPlayer().warlockDeckCards.contains(inputCard)){
                canSell =false;
            }
        if(canSell==true){
            Card cardi = creatCardFromjson(inputCard);
            gameCLI.getInstance().getCurrentPlayer().getALLPlayersCards().remove(gameCLI.getInstance().getCurrentPlayer().getALLPlayersCards().indexOf(cardi));
            gameCLI.getInstance().getCurrentPlayer().getPlayersDeckCards().remove(cardi);
            gameCLI.getInstance().getCurrentPlayer().setPlayerCoins(gameCLI.getInstance().getCurrentPlayer().getPlayerCoins() + cardi.getPrice() - 4 );
            gameCLI.getInstance().getCurrentPlayer().getPlayerLOGGER().log(Level.INFO,"SOLD_THE_CARD : " + cardi.toString());
            jsonTofilePlayer(gameCLI.getInstance().getCurrentPlayer());
            System.out.println("you sold your card successfully");
        }
        else {
            System.out.println("invalid ! ");
        }

    }

}
