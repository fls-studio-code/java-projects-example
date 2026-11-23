# Тема 2. Виселица / Hangman / Tema 2. Spânzurătoarea / Hangman   ★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Загаданное слово показано маской «_ _ _». Игрок вводит букву: открываются все вхождения или снимается жизнь (6 жизней). Рисунок виселицы растёт с каждой ошибкой, список названных букв виден, одну букву дважды не считает.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic02_hangman` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/hangman/HangmanApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/hangman/`)
| Файл | Что внутри |
|---|---|
| `HangmanGame.java` | логика: маска, жизни, использованные буквы (ArrayList<Character>) |
| `GuessResult.java` | enum: CORRECT / WRONG / ALREADY_USED / INVALID |
| `WordBank.java` | слова по категориям, лёгкий / сложный уровень |
| `GallowsPanel.java` | рисует виселицу (paintComponent) |
| `HangmanFrame.java` | окно: маска, поле для буквы, кнопки |
| `HangmanApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Проверка ввода: не буква / больше одной буквы / повтор — сообщение, без падения. Победа и проигрыш с диалогом. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | HangmanGame, WordBank, GallowsPanel, enum GuessResult, ArrayList. |
| Одна фишка | 1 | Категории, 2 уровня (короткое / длинное слово), слова с диакритиками (ă, â, î, ș, ț), рисунок виселицы, повтор. |
| Презентация | 2 | Демо: Категория «Fructe», уровень «Ușor» (короткое слово). Показать метод guess() и getMask(). |

---

## RO

### Ce face programul
Cuvântul ascuns apare ca mască „_ _ _”. Jucătorul scrie o literă: se deschid toate aparițiile sau se pierde o viață (6 vieți). Spânzurătoarea se desenează la fiecare greșeală, literele folosite se văd, aceeași literă nu se numără de două ori.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic02_hangman` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/hangman/HangmanApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/hangman/`)
| Fișier | Ce conține |
|---|---|
| `HangmanGame.java` | logica: masca, viețile, literele folosite (ArrayList<Character>) |
| `GuessResult.java` | enum cu rezultatul literei |
| `WordBank.java` | cuvinte pe categorii, nivel ușor / greu |
| `GallowsPanel.java` | desenează spânzurătoarea (paintComponent) |
| `HangmanFrame.java` | fereastra: masca, câmpul pentru literă, butoane |
| `HangmanApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Verificarea intrării: nu e literă / mai multe litere / repetare — mesaj, fără erori. Victorie și pierdere cu dialog. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | HangmanGame, WordBank, GallowsPanel, enum GuessResult, ArrayList. |
| O fișă în plus | 1 | Categorii, 2 niveluri, cuvinte cu diacritice, desenul spânzurătorii, joc nou. |
| Prezentarea | 2 | Demo: Categoria „Fructe”, nivel „Ușor”. Arată metodele guess() și getMask(). |
