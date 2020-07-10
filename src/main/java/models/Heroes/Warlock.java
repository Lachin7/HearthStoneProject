package models.Heroes;

public class Warlock extends Hero {

    public Warlock() {
        this.HP = 35;
        this.name="Warlock";
        this.heroPowerCost =0;
   }

    public void HeroPower() {
        this.HP -=2;
    }

    @Override
    public String toString() {
        return "WARLOCK";
    }
    public String getName() {
        return "Warlock";
    }

}
//    public void SpecialPower() {
//    this.HP= 35;
//    }
    //    @Override
//    public  ArrayList<card> getHeroAllCards() {
//        return this.WarlockAllCards;
//    }
//    @Override
//    public void setHeroAllCards(ArrayList<card> heroAllCards) {
//        this.WarlockAllCards = heroAllCards;
//    }
    //
//    @Override
//    public ArrayList<card> getHeroDeckCards() {
//        return this.WarlockDeckCards;
//    }
//    @Override
//    public void setHeroDeckCards(ArrayList<card> heroDeckCards) {
//        this.WarlockDeckCards = heroDeckCards;
//    }
//
//    @Expose public ArrayList<Card> warlockAllCards =new ArrayList<>();
//    @Override
//    public  ArrayList<Card> getHeroAllCards() {
//        return this.warlockAllCards;
//    }
//
//     for(Card card : getALLCardsExistingInGame()) {
//             if(card.getHeroClass() == Card.HeroClass.WARLOCK){
//             warlockAllCards.add(card);
//             }
//             }