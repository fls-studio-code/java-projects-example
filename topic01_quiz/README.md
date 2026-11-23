# Тема 1. Викторина / Quiz / Tema 1. Victorină / Quiz   ★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Окно с вопросами и тремя вариантами ответа (a / b / c). Сразу пишет «верно / неверно», в конце показывает счёт «3 из 5» и рекорд, предлагает сыграть ещё раз.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic01_quiz` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/quiz/QuizApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/quiz/`)
| Файл | Что внутри |
|---|---|
| `Question.java` | класс вопроса: текст, 3 варианта, правильный ответ |
| `QuestionBank.java` | 10 вопросов в ArrayList, 3 темы (Java, Istorie, Sport) |
| `QuizGame.java` | логика: текущий вопрос, счёт, рекорд (без окна) |
| `QuizFrame.java` | окно Swing: вопрос, 3 кнопки, счёт |
| `QuizApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. 10 вопросов, ответ кнопкой — неправильный ввод невозможен; конец игры с диалогом счёта. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Question, QuestionBank, QuizGame + ArrayList<Question>. |
| Одна фишка | 1 | Темы на выбор, варианты a/b/c, рекорд, перемешивание вопросов, повтор игры. |
| Презентация | 2 | Демо: Выбрать тему «Java», ответить на 2 вопроса (один специально неверно), показать класс Question и метод answer() в QuizGame. |

---

## RO

### Ce face programul
O fereastră cu întrebări și trei variante (a / b / c). Spune imediat „corect / greșit”, la final arată scorul „3 din 5” și recordul, apoi propune un joc nou.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic01_quiz` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/quiz/QuizApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/quiz/`)
| Fișier | Ce conține |
|---|---|
| `Question.java` | clasa întrebării: text, 3 variante, răspunsul corect |
| `QuestionBank.java` | 10 întrebări în ArrayList, 3 categorii |
| `QuizGame.java` | logica: întrebarea curentă, scorul, recordul (fără fereastră) |
| `QuizFrame.java` | fereastra Swing: întrebarea, 3 butoane, scorul |
| `QuizApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. 10 întrebări, răspuns prin buton — nu poți introduce ceva greșit; final cu dialog de scor. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Question, QuestionBank, QuizGame + ArrayList<Question>. |
| O fișă în plus | 1 | Categorii, variante a/b/c, record, întrebări amestecate, joc nou. |
| Prezentarea | 2 | Demo: Alege categoria „Java”, răspunde la 2 întrebări (una greșit intenționat), arată clasa Question și metoda answer() din QuizGame. |
