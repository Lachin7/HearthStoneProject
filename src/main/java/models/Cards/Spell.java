package models.Cards;
import com.google.gson.annotations.Expose;

public abstract class Spell extends Card {
    @Expose boolean canRestore = false;
    @Expose int restoreAmount = 0;
    @Expose boolean hasDiscovery = false;
    public Spell(int manaCost, String name, String description, rarity rarity, HeroClass heroClass, type type, int price) {
        super(manaCost, name, description, rarity,  heroClass, type,price);
    }

    public boolean getCanRestore() {
        return canRestore;
    }

    public void setCanRestore(boolean canRestore) {
        this.canRestore = canRestore;
    }

    public int getRestoreAmount() {
        return restoreAmount;
    }

    public void setRestoreAmount(int restoreAmount) {
        this.restoreAmount = restoreAmount;
    }

    public boolean HasDiscovery() {
        return hasDiscovery;
    }

    public void setHasDiscovery(boolean hasDiscovery) {
        this.hasDiscovery = hasDiscovery;
    }

    //    @Override
//    public String toString() {
//        return super.toString().substring(0,super.toString().indexOf("}")) + ", Quest : "+ this.Quest + ", Reward : "+ this.Reward + " } ";
//    }
//Spell Polymorph = new Spell(4,"Polymorph","Transform minion in to a 1/1 sheep.",rarity.RARE, Mage.getInstance(),type.SPELL,"","");
//    static Spell RollingFireball = new Spell(5,"RollingFireball","Deal 8 damage to a minion. Any excess damage continues to the left or right.1 sheep.",rarity.EPIC,"MAGE",type.SPELL,"","");
//
//    public static void main(String[] args) throws IOException {
//        jsonFileMakerForCards(Polymorph);
//        jsonFileMakerForCards();
//    }
}
