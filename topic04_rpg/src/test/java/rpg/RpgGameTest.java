package rpg;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RpgGameTest {

    @Test
    void heroHpStaysBetweenZeroAndMax() {
        Hero hero = new Hero("Test");
        hero.heal(100);
        assertEquals(Hero.MAX_HP, hero.getHp());
        hero.takeDamage(100);
        assertEquals(0, hero.getHp());
        assertFalse(hero.isAlive());
    }

    @Test
    void runningHomeIsAnEnding() {
        RpgGame game = new RpgGame("Ana", new Random(1));
        game.choose(1); // peștera
        assertEquals(Scene.CAVE, game.getScene());
        assertEquals(Weapon.SWORD, game.getHero().getWeapon());
        game.choose(2); // fuge acasă
        assertEquals(Scene.END_HOME, game.getScene());
        assertTrue(game.isOver());
    }

    @Test
    void everyPathReachesAnEnding() {
        // încercăm toate combinațiile de alegeri, cu mai multe seed-uri
        for (int seed = 0; seed < 50; seed++) {
            for (int a = 1; a <= 2; a++) {
                for (int b = 1; b <= 2; b++) {
                    for (int c = 1; c <= 2; c++) {
                        RpgGame game = new RpgGame("X", new Random(seed));
                        int[] path = {a, b, c, 1, 1};
                        for (int option : path) {
                            if (!game.isOver()) {
                                game.choose(option);
                            }
                        }
                        assertTrue(game.isOver(), "path " + a + b + c);
                    }
                }
            }
        }
    }

    @Test
    void potionHeals() {
        RpgGame game = new RpgGame("X", new Random(3));
        game.choose(2); // sat: primește poțiune + băț
        assertTrue(game.getHero().hasItem(RpgGame.POTION));
        assertEquals(Weapon.STICK, game.getHero().getWeapon());
    }
}
