package models;

import lombok.Getter;
import lombok.Setter;
import models.Cards.Card;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private long id;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column
    private List<Card> cards;
    @Column
    @Setter
    @Getter
    private String name;
    @Column
    @Getter
    @Setter
    private int allGamesPlayed = 0, winGamesPlayed = 0, loses = 0, cups = 0;
    @Column
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Card.HeroClass hero;
    @Setter
    @Column
    private double winToAll;
//    @JoinColumn(name = "most used card")
//    @OneToOne
//    @Getter
//    @Setter
    @Transient
    private Card mostUsedCard;

    public Deck(){}

    public Deck(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
        this.winToAll = winToAll();
    }

    public int averagePrice() {
        if (cards.size() == 0) return 0;
        int result = 0;
        for (Card card : cards) result += card.getPrice();
        result = result / cards.size();
        return result;
    }

    public double winToAll() {
        if (this.getAllGamesPlayed() == 0) return 0;
        else return this.getWinGamesPlayed() / this.getAllGamesPlayed();
    }

    public int usedTimes(Card card) {
        int result = 0;
        for (Card card1 : cards) if (card == card1) result++;
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Card> getCards() {
        cards.sort(Comparator.comparing(Card::getName));
        return cards;
    }

    public List<Card> getCardsSortedByValue() {
        cards.sort(getMostUsedCardCompare());
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @Transient
    private final java.util.Comparator<Card> mostUsedCardCompare = (card2, card1) -> {
        int usedTimes = usedTimes(card1) - usedTimes(card2);
        int price = card1.getPrice() - card2.getPrice();
        int mana = card1.getManaCost() - card2.getManaCost();
        if (usedTimes != 0) return usedTimes;
        if (price != 0) return price;
        return mana;
    };

    public Comparator<Card> getMostUsedCardCompare() {
        return mostUsedCardCompare;
    }

    public double getWinToAll() {
        return winToAll;
    }

}
