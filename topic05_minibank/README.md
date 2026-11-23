# Тема 5. Банкомат / мини-банк / Tema 5. Bancomat / mini-bancă   ★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Окно банкомата: выбрать счёт, ввести PIN (3 попытки, потом счёт блокируется). Меню: баланс, пополнение, снятие, перевод на другой счёт, смена PIN, история операций, выход.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic05_minibank` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/bank/BankApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/bank/`)
| Файл | Что внутри |
|---|---|
| `Account.java` | счёт: владелец, PIN, баланс, история (ArrayList<String>) |
| `InsufficientFundsException.java` | своё исключение «недостаточно денег» |
| `Bank.java` | список из 2 счетов |
| `BankFrame.java` | окно с двумя экранами (CardLayout): вход и меню |
| `BankApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Буквы вместо суммы, сумма ≤ 0, снятие больше баланса — сообщение об ошибке, программа не падает. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Account, Bank, своё исключение, ArrayList счетов и истории. |
| Одна фишка | 1 | Два счёта и перевод, история операций, смена PIN, проверка суммы > 0, блокировка после 3 ошибок. |
| Презентация | 2 | Демо: Тестовые счета: **Ana — PIN 1234** (500 лей), **Ion — PIN 1111** (200 лей). Войти, пополнить, снять; отдельно показать неверный PIN и снятие 10000. |

---

## RO

### Ce face programul
Fereastra bancomatului: alegi contul, introduci PIN-ul (3 încercări, apoi contul se blochează). Meniu: sold, depunere, retragere, transfer, schimbare PIN, istoric, ieșire.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic05_minibank` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/bank/BankApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/bank/`)
| Fișier | Ce conține |
|---|---|
| `Account.java` | contul: proprietar, PIN, sold, istoric |
| `InsufficientFundsException.java` | excepția proprie „fonduri insuficiente” |
| `Bank.java` | lista cu 2 conturi |
| `BankFrame.java` | fereastra cu 2 ecrane (CardLayout): logare și meniu |
| `BankApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Litere în loc de sumă, sumă ≤ 0, retragere mai mare decât soldul — mesaj de eroare, programul nu cade. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Account, Bank, excepție proprie, ArrayList de conturi și istoric. |
| O fișă în plus | 1 | Două conturi și transfer, istoric, schimbare PIN, sumă > 0, blocare după 3 greșeli. |
| Prezentarea | 2 | Demo: Conturi de test: **Ana — PIN 1234** (500 lei), **Ion — PIN 1111** (200 lei). Intră, depune, retrage; arată separat PIN greșit și retragerea a 10000. |
