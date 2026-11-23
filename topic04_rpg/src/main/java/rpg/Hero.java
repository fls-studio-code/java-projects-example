package rpg;

import java.util.ArrayList;

/**
 * Eroul: nume, viață (HP), armă și inventar.
 */
public class Hero {
    public static final int MAX_HP = 30;

    private final String name;
    private int hp = MAX_HP;
    private Weapon weapon = Weapon.FISTS;
    private final ArrayList<String> inventory = new ArrayList<>();

    public Hero(String name) {
        this.name = name;
    }

    public void takeDamage(int amount) {
        hp = hp - amount;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void heal(int amount) {
        hp = hp + amount;
        if (hp > MAX_HP) {
            hp = MAX_HP; // nu trecem peste maxim
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    public void removeItem(String item) {
        inventory.remove(item);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }
}
