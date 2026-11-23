package rpg;

import java.util.Random;

/**
 * Logica poveștii: scena curentă, alegerile 1 / 2 și luptele.
 */
public class RpgGame {
    public static final String POTION = "poțiune";
    public static final String SHIELD = "scut";

    private final Hero hero;
    private final Random random;
    private Scene scene = Scene.START;
    private String lastEvent = ""; // ce s-a întâmplat la ultimul pas (luptă, poțiune...)

    public RpgGame(String heroName, Random random) {
        this.hero = new Hero(heroName);
        this.random = random;
    }

    // textul scenei curente
    public String getSceneText() {
        switch (scene) {
            case START:
                return "Ești " + hero.getName() + ", la marginea Pădurii Întunecate.\n"
                        + "Un dragon a furat comoara satului. Unde mergi?";
            case CAVE:
                return "În peșteră găsești o sabie veche (arma ta e acum sabia).\n"
                        + "Din întuneric iese un lup și mârâie la tine!";
            case VILLAGE:
                return "În sat, bătrânul îți dă o poțiune și un băț de lemn.\n"
                        + "Fierarul are un scut, dar îți cere ajutorul la forjă (pierzi 5 HP de oboseală).";
            case AFTER_WOLF:
                return "Lupul a fugit! Lângă el găsești o poțiune.\n"
                        + "Drumul spre castel e liber, dar poți face și un popas lângă foc.";
            case CASTLE:
                return "Ai ajuns la castel. Dragonul doarme lângă comoară.\n"
                        + "Lupți cu el sau încerci să furi comoara pe furiș?";
            case END_WIN:
                return "VICTORIE! Ai învins dragonul și ai adus comoara în sat.\nToți te sărbătoresc!";
            case END_LOSE:
                return "AI PIERDUT... HP a ajuns la 0.\nPoate data viitoare alegi alt drum.";
            case END_SNEAK:
                return "VICTORIE pe furiș! Ai luat comoara fără să trezești dragonul.\nSatul e salvat!";
            case END_HOME:
                return "Te-ai speriat și te-ai întors acasă.\nComoara rămâne la dragon. (final neutru)";
            default:
                return "";
        }
    }

    public String getChoice1() {
        switch (scene) {
            case START: return "Intră în peșteră";
            case CAVE: return "Luptă cu lupul";
            case VILLAGE: return "Mergi direct la castel";
            case AFTER_WOLF: return "Mergi la castel";
            case CASTLE: return "Atacă dragonul";
            default: return "Joc nou";
        }
    }

    public String getChoice2() {
        switch (scene) {
            case START: return "Mergi în sat";
            case CAVE: return "Fugi acasă";
            case VILLAGE: return "Ajută fierarul și ia scutul (-5 HP)";
            case AFTER_WOLF: return "Popas lângă foc (+10 HP)";
            case CASTLE: return "Încearcă să furi comoara";
            default: return "Ieșire";
        }
    }

    // jucătorul a apăsat 1 sau 2
    public void choose(int option) {
        lastEvent = "";
        switch (scene) {
            case START:
                if (option == 1) {
                    hero.setWeapon(Weapon.SWORD);
                    scene = Scene.CAVE;
                } else {
                    hero.addItem(POTION);
                    if (hero.getWeapon() == Weapon.FISTS) {
                        hero.setWeapon(Weapon.STICK);
                    }
                    scene = Scene.VILLAGE;
                }
                break;
            case CAVE:
                if (option == 1) {
                    boolean won = fight(new Enemy("Lupul", 15, 3, 6));
                    if (won) {
                        hero.addItem(POTION);
                        scene = Scene.AFTER_WOLF;
                    } else {
                        scene = Scene.END_LOSE;
                    }
                } else {
                    scene = Scene.END_HOME;
                }
                break;
            case VILLAGE:
                if (option == 2) {
                    hero.takeDamage(5);
                    hero.addItem(SHIELD);
                    lastEvent = "Ai primit scutul! Dușmanii te lovesc cu 2 mai puțin. HP: " + hero.getHp();
                }
                scene = Scene.CASTLE;
                break;
            case AFTER_WOLF:
                if (option == 2) {
                    hero.heal(10);
                    lastEvent = "Te-ai odihnit lângă foc. HP: " + hero.getHp();
                }
                scene = Scene.CASTLE;
                break;
            case CASTLE:
                if (option == 2) {
                    // 50% șanse să reușești pe furiș
                    if (random.nextInt(2) == 0) {
                        lastEvent = "Pași ușori... dragonul nu s-a trezit!";
                        scene = Scene.END_SNEAK;
                        break;
                    }
                    hero.takeDamage(8);
                    String wake = "Dragonul s-a trezit și te-a ars! -8 HP (HP " + hero.getHp() + ")";
                    boolean won = hero.isAlive() && fight(new Enemy("Dragonul", 40, 5, 9));
                    lastEvent = wake + "\n" + lastEvent;
                    scene = won ? Scene.END_WIN : Scene.END_LOSE;
                } else {
                    boolean won = fight(new Enemy("Dragonul", 40, 5, 9));
                    scene = won ? Scene.END_WIN : Scene.END_LOSE;
                }
                break;
            default:
                break; // în finaluri nu facem nimic
        }
    }

    // lupta: lovim pe rând până moare unul dintre noi; întoarce true dacă eroul a câștigat
    private boolean fight(Enemy enemy) {
        StringBuilder log = new StringBuilder("--- Lupta cu " + enemy.getName() + " ---\n");
        while (hero.isAlive() && enemy.isAlive()) {
            int heroHit = hero.getWeapon().getDamage() + random.nextInt(3);
            enemy.takeDamage(heroHit);
            log.append("Lovești cu ").append(hero.getWeapon().getTitle()).append(": -").append(heroHit)
                    .append(" (").append(enemy.getName()).append(" HP ").append(enemy.getHp()).append(")\n");
            if (!enemy.isAlive()) {
                break;
            }
            int enemyHit = enemy.getMinAttack() + random.nextInt(enemy.getMaxAttack() - enemy.getMinAttack() + 1);
            if (hero.hasItem(SHIELD)) {
                enemyHit = enemyHit - 2; // scutul oprește o parte din lovitură
            }
            hero.takeDamage(enemyHit);
            log.append(enemy.getName()).append(" te lovește: -").append(enemyHit)
                    .append(" (HP tău ").append(hero.getHp()).append(")\n");
            // dacă HP e mic și avem poțiune, o bem automat
            if (hero.isAlive() && hero.getHp() < 10 && hero.hasItem(POTION)) {
                hero.removeItem(POTION);
                hero.heal(15);
                log.append("Bei poțiunea: +15 HP (HP tău ").append(hero.getHp()).append(")\n");
            }
        }
        log.append(hero.isAlive() ? "Ai câștigat lupta!" : "Ai fost învins...");
        lastEvent = log.toString();
        return hero.isAlive();
    }

    public boolean isOver() {
        return scene == Scene.END_WIN || scene == Scene.END_SNEAK
                || scene == Scene.END_LOSE || scene == Scene.END_HOME;
    }

    public Scene getScene() {
        return scene;
    }

    public Hero getHero() {
        return hero;
    }

    public String getLastEvent() {
        return lastEvent;
    }
}
