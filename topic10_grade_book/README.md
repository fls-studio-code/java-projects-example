# Тема 10. Каталог оценок / Tema 10. Catalog de note   ★★

Готовое решение учителя на **Java 17 + Swing** (Maven-проект для IntelliJ). Логика вынесена в отдельные классы, окно — в класс `…Frame`. Комментарии в коде — на румынском.

Soluția profesorului în **Java 17 + Swing** (proiect Maven pentru IntelliJ). Logica e în clase separate, fereastra — în clasa `…Frame`. Comentariile din cod sunt în română.

---

## RU

### Что делает программа
Таблица оценок: предмет + оценка 1–10. Добавить, удалить, среднее с одной цифрой после точки, лучший и худший предмет, «PROMOVAT / NEPROMOVAT» (среднее ≥ 5), поиск по предмету, отчёт, сохранение и загрузка из файла note.txt.

### Как запустить в IntelliJ
1. IntelliJ IDEA → **File → Open…** → выбрать папку `topic10_grade_book` (там, где `pom.xml`) → **Open as Project**. IntelliJ сам загрузит Maven.
2. Если спросит JDK — выбрать **17** (или новее): File → Project Structure → SDK.
3. Открыть `src/main/java/grades/GradeBookApp.java` и нажать зелёный ▶ рядом с `main`.
4. Тесты: правый клик по `src/test/java` → **Run 'All Tests'**.
5. Без IntelliJ: `mvn package` и `java -jar target/*.jar` (или `mvn compile exec:java`).

### Структура (`src/main/java/grades/`)
| Файл | Что внутри |
|---|---|
| `Grade.java` | оценка: предмет и значение |
| `GradeBook.java` | ArrayList оценок: проверка 1–10, среднее, лучший/худший, поиск, отчёт, файл |
| `GradeBookFrame.java` | окно: поля ввода, таблица, кнопки, среднее |
| `GradeBookApp.java` | main — запуск |

### Барем (10 баллов) — что закрывает
| Критерий | Баллы | Где в проекте |
|---|---|---|
| Программа работает | 3 | Компилируется, доходит до конца. Оценка 0, 11, буквы, пустой предмет → сообщение, без падения. |
| Аккуратный код | 2 | Понятные имена на английском, короткие комментарии, логика отдельно от окна. |
| Классы и/или ArrayList | 2 | Grade, GradeBook, ArrayList<Grade>. |
| Одна фишка | 1 | Лучший/худший предмет, «сдал / не сдал», поиск, отчёт, работа с файлом (урок про файлы). |
| Презентация | 2 | Демо: Добавить 2 оценки (одну специально 11 — покажет ошибку), открыть «Raport». |

---

## RO

### Ce face programul
Tabel cu note: materia + nota 1–10. Adaugă, șterge, media cu o zecimală, cea mai bună și cea mai slabă, „PROMOVAT / NEPROMOVAT” (media ≥ 5), căutare, raport, salvare și încărcare din fișierul note.txt.

### Cum pornești în IntelliJ
1. IntelliJ IDEA → **File → Open…** → alege folderul `topic10_grade_book` (unde e `pom.xml`) → **Open as Project**. IntelliJ încarcă singur Maven.
2. Dacă cere JDK — alege **17** (sau mai nou): File → Project Structure → SDK.
3. Deschide `src/main/java/grades/GradeBookApp.java` și apasă ▶ verde lângă `main`.
4. Teste: click dreapta pe `src/test/java` → **Run 'All Tests'**.
5. Fără IntelliJ: `mvn package` și `java -jar target/*.jar` (sau `mvn compile exec:java`).

### Structura (`src/main/java/grades/`)
| Fișier | Ce conține |
|---|---|
| `Grade.java` | nota: materia și valoarea |
| `GradeBook.java` | ArrayList de note: verificare 1–10, media, cea mai bună/slabă, căutare, raport, fișier |
| `GradeBookFrame.java` | fereastra: câmpuri, tabel, butoane, media |
| `GradeBookApp.java` | main — pornirea |

### Barem (10 puncte) — ce acoperă fiecare parte
| Criteriu | Puncte | Unde în proiect |
|---|---|---|
| Programul funcționează | 3 | Se compilează, ajunge până la final. Nota 0, 11, litere, materie goală → mesaj, fără erori. |
| Cod îngrijit | 2 | Nume clare în engleză, comentarii scurte, logica separată de fereastră. |
| Clase și/sau ArrayList | 2 | Grade, GradeBook, ArrayList<Grade>. |
| O fișă în plus | 1 | Cea mai bună/slabă, promovat/nepromovat, căutare, raport, lucru cu fișiere. |
| Prezentarea | 2 | Demo: Adaugă 2 note (una intenționat 11 — arată eroarea), apasă „Raport”. |
