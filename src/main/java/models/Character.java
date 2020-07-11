package models;

public interface Character {
   int getHP();
   void setHP(int hp);
   int getAttack();
   void setAttack(int attack);
   int getMaxHp();
   void setMaxHp(int hp);
   int getMaxAttack();
   void setMaxAttack(int attack);
   long getId();
   String getName();
   void setName(String name);
}
