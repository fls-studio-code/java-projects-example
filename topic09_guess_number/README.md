# Тема 9. Угадай число / Tema 9. Ghicește numărul   ★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Компьютер загадывает число (Random). Вводишь число — «слишком маленькое / большое» + «тепло / холодно». Счётчик попыток, лимит попыток, рекорд, после победы — «сыграть ещё?».

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic09_guess_number` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/guess/GuessApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/guess/`)
| Файл | Что внутри |
|---|---|
| `GuessGame.java` | логика: загаданное число, попытки, подсказки, рекорд |
| `Difficulty.java` | enum уровней: 1–20 / 1–100 / 1–100 и 7 попыток |
| `GuessResult.java` | enum: TOO_LOW / TOO_HIGH / CORRECT / OUT_OF_RANGE |
| `GuessFrame.java` | окно: поле, кнопка, подсказка |
| `GuessApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Буквы → «Scrie un număr întreg!»; число вне диапазона не считается попыткой. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | GuessGame + enum (для темы ★ необязательно, но есть). |
| Одна фишка | 1 | Уровни сложности, окно Swing, рекорд, «тепло / холодно». |
| Презентация | 2 | Демо: Уровень «Ușor (1–20)», чтобы демо было коротким. Показать метод guess(). |

---

## RO

### Ce face programul
Calculatorul alege un număr (Random). Scrii un număr — „prea mic / prea mare” + „cald / rece”. Contor de încercări, limită, record, după victorie — „mai joci?”.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic09_guess_number` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/guess/GuessApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/guess/`)
| Fișier | Ce conține |
|---|---|
| `GuessGame.java` | logica: numărul secret, încercări, indicii, record |
| `Difficulty.java` | enum niveluri |
| `GuessResult.java` | enum rezultat |
| `GuessFrame.java` | fereastra: câmp, buton, indiciu |
| `GuessApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Litere → „Scrie un număr întreg!”; număr în afara intervalului nu se numără. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | GuessGame + enum (pentru tema ★ nu e obligatoriu, dar există). |
| O fișă în plus | 1 | Niveluri, fereastră Swing, record, „cald / rece”. |
| Prezentarea | 2 | Demo: Nivelul „Ușor (1–20)”, ca demo să fie scurt. Arată metoda guess(). |
