# Тема 8. Камень-ножницы-бумага / Tema 8. Piatră, foarfecă, hârtie   ★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Кнопки с жестами, компьютер выбирает случайно. Программа говорит, кто выиграл раунд, ведёт счёт и статистику жестов. Игра до 3 побед, в конце — общий счёт и «сыграть ещё?». Галочка включает вариант с ящерицей и Споком.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic08_rock_paper_scissors` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/rps/RpsApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/rps/`)
| Файл | Что внутри |
|---|---|
| `Move.java` | enum жестов + метод beats() (правила) |
| `RoundResult.java` | enum: PLAYER / COMPUTER / DRAW |
| `RpsGame.java` | логика: раунд, счёт, статистика, конец игры |
| `RpsFrame.java` | окно: кнопки жестов, счёт |
| `RpsApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Ввод только кнопками; кнопка «Termină» показывает счёт в любой момент. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Move, RpsGame (для темы ★ необязательно, но есть). |
| Одна фишка | 1 | enum, «до трёх побед», ящерица-Спок, статистика жестов. |
| Презентация | 2 | Демо: 3–4 раунда; показать метод beats() в Move. |

---

## RO

### Ce face programul
Butoane cu gesturi, calculatorul alege la întâmplare. Programul spune cine a câștigat runda, ține scorul și statistica. Joc până la 3 victorii, la final — scorul și „mai joci?”. Bifa pornește varianta cu Șopârlă și Spock.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic08_rock_paper_scissors` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/rps/RpsApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/rps/`)
| Fișier | Ce conține |
|---|---|
| `Move.java` | enum gesturi + metoda beats() (regulile) |
| `RoundResult.java` | enum cine a câștigat |
| `RpsGame.java` | logica: runda, scorul, statistica, sfârșitul |
| `RpsFrame.java` | fereastra: butoane, scor |
| `RpsApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Doar butoane; „Termină” arată scorul oricând. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Move, RpsGame (pentru tema ★ nu e obligatoriu, dar există). |
| O fișă în plus | 1 | enum, „până la 3 victorii”, Șopârlă-Spock, statistica gesturilor. |
| Prezentarea | 2 | Demo: 3–4 runde; arată metoda beats() din Move. |
