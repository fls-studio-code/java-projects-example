# Тема 4. Текстовый квест (RPG) / Tema 4. Aventură text (RPG)   ★★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Квест в окне: текст сцены и две кнопки выбора. Герой (имя, HP, оружие, инвентарь) идёт в пещеру или в деревню, бьётся с волком и драконом. 4 концовки: победа, победа «тайком», проигрыш (HP = 0), побег домой.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic04_rpg` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/rpg/RpgApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/rpg/`)
| Файл | Что внутри |
|---|---|
| `Hero.java` | герой: имя, HP, оружие, инвентарь (ArrayList<String>) |
| `Enemy.java` | враг: имя, HP, сила удара |
| `Weapon.java` | enum оружия: кулаки, палка, меч |
| `Scene.java` | enum сцен и концовок |
| `RpgGame.java` | логика: текст сцены, выбор 1/2 (switch), бой |
| `RpgFrame.java` | окно: текст, полоса HP, 2 кнопки |
| `RpgApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Выбор только кнопками; любая ветка доходит до конца (проверено тестом). |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Hero, Enemy, RpgGame, enum Weapon, enum Scene, ArrayList инвентаря. |
| Одна фишка | 1 | Инвентарь, класс врага и бой, enum, несколько концовок, щит и зелье (пьётся автоматически при HP < 10). |
| Презентация | 2 | Демо: Короткий путь: пещера → бой с волком → к замку → атаковать. Показать метод choose() и fight(). |

---

## RO

### Ce face programul
Aventură în fereastră: textul scenei și două butoane. Eroul (nume, HP, armă, inventar) merge în peșteră sau în sat, luptă cu lupul și dragonul. 4 finaluri: victorie, victorie pe furiș, pierdere (HP = 0), fuga acasă.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic04_rpg` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/rpg/RpgApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/rpg/`)
| Fișier | Ce conține |
|---|---|
| `Hero.java` | eroul: nume, HP, armă, inventar |
| `Enemy.java` | dușmanul: nume, HP, putere |
| `Weapon.java` | enum arme |
| `Scene.java` | enum scene și finaluri |
| `RpgGame.java` | logica: textul scenei, alegerea 1/2 (switch), lupta |
| `RpgFrame.java` | fereastra: text, bara HP, 2 butoane |
| `RpgApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Alegerea doar prin butoane; orice ramură ajunge la final (verificat prin test). |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Hero, Enemy, RpgGame, enum Weapon, enum Scene, ArrayList pentru inventar. |
| O fișă în plus | 1 | Inventar, clasa dușmanului și lupta, enum, mai multe finaluri, scut și poțiune (se bea automat la HP < 10). |
| Prezentarea | 2 | Demo: Drum scurt: peșteră → lupul → castel → atac. Arată metodele choose() și fight(). |
