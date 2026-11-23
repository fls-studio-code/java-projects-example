package rpg;

/**
 * Tipul armei și câte daune face.
 */
public enum Weapon {
    FISTS("pumni", 3),
    STICK("băț", 5),
    SWORD("sabie", 8);

    private final String title;
    private final int damage;

    Weapon(String title, int damage) {
        this.title = title;
        this.damage = damage;
    }

    public String getTitle() {
        return title;
    }

    public int getDamage() {
        return damage;
    }
}
