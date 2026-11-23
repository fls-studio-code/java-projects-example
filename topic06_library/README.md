# Тема 6. Библиотека / Tema 6. Bibliotecă   ★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Таблица книг (JTable), 5 книг уже при старте. Поиск по части названия, добавление (книга или журнал), «выдать» и «вернуть» меняют статус, сортировка по названию.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic06_library` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/library/LibraryApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/library/`)
| Файл | Что внутри |
|---|---|
| `Book.java` | книга: название, автор, тип, статус; Comparable для сортировки |
| `Library.java` | ArrayList книг: поиск, добавить, выдать, вернуть, сортировка |
| `LibraryFrame.java` | окно: поиск, таблица, кнопки |
| `LibraryApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Пустые поля при добавлении, действие без выбранной строки, повторная выдача — сообщение, без падения. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Book, Library, ArrayList<Book>. |
| Одна фишка | 1 | Возврат книги, сортировка, две категории (Carte / Revistă), счётчик доступных. |
| Презентация | 2 | Демо: Найти «harap», выдать — статус в таблице поменялся; потом вернуть. |

---

## RO

### Ce face programul
Tabel cu cărți (JTable), 5 cărți de la pornire. Căutare după o parte din titlu, adăugare (carte sau revistă), „împrumută” și „returnează” schimbă statusul, sortare după titlu.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic06_library` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/library/LibraryApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/library/`)
| Fișier | Ce conține |
|---|---|
| `Book.java` | cartea: titlu, autor, tip, status; Comparable pentru sortare |
| `Library.java` | ArrayList de cărți: căutare, adăugare, împrumut, returnare, sortare |
| `LibraryFrame.java` | fereastra: căutare, tabel, butoane |
| `LibraryApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Câmpuri goale, acțiune fără rând selectat, împrumut repetat — mesaj, fără erori. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Book, Library, ArrayList<Book>. |
| O fișă în plus | 1 | Returnare, sortare, două categorii (Carte / Revistă), numărul celor disponibile. |
| Prezentarea | 2 | Demo: Caută „harap”, împrumută — statusul s-a schimbat; apoi returnează. |
