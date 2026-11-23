# Тема 3. Морской бой против компьютера / Tema 3. Bătălia navală împotriva calculatorului   ★★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Два поля 5×5 (char[][]): твоё и компьютера. 3 корабля по одной клетке расставляются случайно. Кликаешь по клетке поля компьютера: «попал / мимо», компьютер стреляет в ответ. 15 выстрелов. Конец — все корабли найдены, твои потоплены или кончились выстрелы.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic03_battleship` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/battleship/BattleshipApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/battleship/`)
| Файл | Что внутри |
|---|---|
| `Board.java` | поле char[5][5], расстановка, выстрел, сколько кораблей осталось |
| `ShotResult.java` | enum: HIT / MISS / ALREADY / INVALID |
| `BattleshipGame.java` | логика: два поля, выстрелы игрока и компьютера, конец игры |
| `BattleshipFrame.java` | окно: две сетки 5×5 из JButton |
| `BattleshipApp.java` | main — запуск (Metal look&feel, чтобы на Mac были видны цвета) |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Координаты выбираются кликом — неверный ввод невозможен; повторный выстрел в ту же клетку не считается. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Board, BattleshipGame, enum ShotResult, двумерный массив. |
| Одна фишка | 1 | Случайная расстановка, компьютер стреляет в ответ, запрет стрелять дважды, повтор игры, галочка «Arată navele (demo)» для показа. |
| Презентация | 2 | Демо: Включить «Arată navele (demo)», чтобы быстро попасть. Показать Board.shoot(). |

---

## RO

### Ce face programul
Două table 5×5 (char[][]): a ta și a calculatorului. 3 nave de o celulă, puse la întâmplare. Dai click pe tabla calculatorului: „lovit / ratat”, calculatorul trage înapoi. 15 lovituri. Final — toate navele găsite, navele tale scufundate sau s-au terminat loviturile.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic03_battleship` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/battleship/BattleshipApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/battleship/`)
| Fișier | Ce conține |
|---|---|
| `Board.java` | tabla char[5][5], plasare, lovitură, nave rămase |
| `ShotResult.java` | enum cu rezultatul loviturii |
| `BattleshipGame.java` | logica: două table, loviturile, sfârșitul jocului |
| `BattleshipFrame.java` | fereastra: două grile 5×5 din JButton |
| `BattleshipApp.java` | main — pornirea (aspect Metal, ca să se vadă culorile pe Mac) |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Coordonatele se aleg prin click; lovitura repetată în aceeași celulă nu se numără. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Board, BattleshipGame, enum ShotResult, tablou bidimensional. |
| O fișă în plus | 1 | Plasare aleatorie, calculatorul trage înapoi, interdicție de a trage de două ori, joc nou, bifa „Arată navele (demo)”. |
| Prezentarea | 2 | Demo: Bifează „Arată navele (demo)” ca să nimerești repede. Arată Board.shoot(). |
