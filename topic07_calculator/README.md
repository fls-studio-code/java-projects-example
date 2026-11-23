# Тема 7. Калькулятор / конвертер (Swing + JUnit) / Tema 7. Calculator / convertor (Swing + JUnit)   ★★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Окно с двумя вкладками. «Calculator»: два числа и кнопки + − × ÷. «Convertor»: °C↔°F, лей↔EUR, км↔мили. Справа — история операций. Есть тесты JUnit на расчёты.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic07_calculator` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/calculator/CalculatorApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/calculator/`)
| Файл | Что внутри |
|---|---|
| `Calculator.java` | логика: 4 операции, деление на 0 → исключение, разбор числа |
| `Converter.java` | 6 режимов перевода |
| `CalculatorFrame.java` | окно: JTabbedPane, поля, кнопки, история (JList) |
| `CalculatorApp.java` | main — запуск |
| `test/…/CalculatorTest.java, ConverterTest.java` | тесты JUnit 5 |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Буквы и пустое поле → сообщение; деление на ноль → сообщение. Запятая (3,5) тоже принимается. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Calculator, Converter — логика отдельно от окна. |
| Одна фишка | 1 | Окно Swing, тесты JUnit, история операций, конвертер валют/единиц — все 4 фишки темы. |
| Презентация | 2 | Демо: Посчитать 12 + 3, 7 / 2, затем 5 / 0. Показать тесты: правый клик по папке test → Run 'All Tests'. |

---

## RO

### Ce face programul
Fereastră cu două file. „Calculator”: două numere și butoanele + − × ÷. „Convertor”: °C↔°F, lei↔EUR, km↔mile. În dreapta — istoricul. Are teste JUnit pentru calcule.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic07_calculator` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/calculator/CalculatorApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/calculator/`)
| Fișier | Ce conține |
|---|---|
| `Calculator.java` | logica: 4 operații, împărțire la 0 → excepție, citirea numărului |
| `Converter.java` | 6 moduri de conversie |
| `CalculatorFrame.java` | fereastra: JTabbedPane, câmpuri, butoane, istoric |
| `CalculatorApp.java` | main — pornirea |
| `test/…/CalculatorTest.java, ConverterTest.java` | teste JUnit 5 |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Litere și câmp gol → mesaj; împărțire la zero → mesaj. Virgula (3,5) este acceptată. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Calculator, Converter — logica separată de fereastră. |
| O fișă în plus | 1 | Fereastră Swing, teste JUnit, istoric, convertor valută/unități — toate cele 4 fișe. |
| Prezentarea | 2 | Demo: Calculează 12 + 3, 7 / 2, apoi 5 / 0. Arată testele: click dreapta pe folderul test → Run 'All Tests'. |
