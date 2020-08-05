package models;

import lombok.Getter;
import lombok.Setter;
import models.Cards.Card;
import models.Cards.Minion;
import models.Heroes.*;
import models.board.InfoPassive;
import org.hibernate.annotations.Cascade;
import server.controller.util.MyDeckComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Player {

    /**
     * defining fields in Player class
     */

    @Id
    @Getter
    @Setter
    private String name;
    @Column
    @Getter
    @Setter
    private String password;
    @Column
    @Getter
    @Setter
    private long coins, ID;
    @Setter
    @ManyToOne
    @JoinColumn
    private Hero choosedHero;
    @Getter
    @Setter
    @JoinColumn
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Deck deck;
    @Column
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "unlockedHeroes")
    private List<Hero> PlayersUnlockedHeroes;
    @Column
    @Setter
    @ManyToMany
    @JoinTable(name = "decks")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Deck> Decks;
    @Column
    @OneToMany
    @Getter
    @Setter
    @JoinTable(name = "allCards")
    private List<Card> allCards;
    @Column
    @Getter
    @Setter
    @OneToMany
    @JoinTable(name = "hands cards")
    private List<Card> HandsCards;
    @Column
    @Getter
    @Setter
    @OneToMany
    @JoinTable(name = "deck cards in games")
    private List<Card> DeckCardsInGame;
    @Column
    @Getter
    @Setter
    @OneToMany
    @JoinTable(name = "field cards in game")
    private List<Minion> FieldCardsInGame;
    @Column
    @Getter
    @Setter
    private int initialMana = 0, wins = 0, loses = 0, currentMana = 0;
    @Column
    @Getter
    @Setter
    private String cardSkin = "BlushRoomCardBack.png", playBackGround = "HSplayBoard copy.jpg";
    @Column
    private Boolean makeNewGame, isSignedUp = false;
    @Getter
    @Setter
    @JoinColumn
    private InfoPassive infoPassive;
    @Column
    @Getter
    @Setter
    @ElementCollection
    private List<Integer> winLoseHistory;


    public Player() {
        choosedHero = new Mage();
        allCards = new ArrayList<>();
        PlayersUnlockedHeroes = new ArrayList<>();
        Decks = new ArrayList<>();
        HandsCards = new ArrayList<>();
        DeckCardsInGame = new ArrayList<>();
        FieldCardsInGame = new ArrayList<>();
        winLoseHistory = new LinkedList<>();

    }


//    public Player(String name,String password,long id, long coins,Hero choosedHero,)



    /**
     * defining getters and setters and methods
     */
    public Hero getChoosedHero() {
        //todo
        if (choosedHero == null) return convert(getDeck().getHero());
        return choosedHero;
    }


    public void setPlayersChoosedHero(Card.HeroClass heroClass) {
        setChoosedHero(convert(heroClass));
    }

    private Hero convert(Card.HeroClass heroClass) {
        if (heroClass == Card.HeroClass.MAGE) return (new Mage());
        else if (heroClass == Card.HeroClass.ROGUE) return (new Rogue());
        else if (heroClass == Card.HeroClass.WARLOCK) return (new Warlock());
        else if (heroClass == Card.HeroClass.HUNTER) return (new Hunter());
        else return (new Priest());
    }


    public List<Deck> getDecks() {
        Collections.sort(Decks, new MyDeckComparator().getDeckComparator());
        return Decks;
    }

    public int getCups() {
        return Math.max(getWins() - getLoses(), 0);
    }

    public void addWin() {
        this.wins = wins + 1;
        winLoseHistory.add(1);
    }

    public void addLoses() {
        this.loses = loses + 1;
        winLoseHistory.add(-1);
    }

    public int getWinLoseHistoryIn(int games) {
        int result = 0;
        for (int i = 0; i < games; i++) if (i < winLoseHistory.size()) result += winLoseHistory.get(i);
        return result;
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

}
