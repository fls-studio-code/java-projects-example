package rpg;

/**
 * Un dușman simplu: nume, HP și cât de tare lovește (de la min la max).
 */
public class Enemy {
    private final String name;
    private int hp;
    private final int minAttack;
    private final int maxAttack;

    public Enemy(String name, int hp, int minAttack, int maxAttack) {
        this.name = name;
        this.hp = hp;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
    }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }
}
