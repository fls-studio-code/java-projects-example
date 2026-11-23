# Proiect final Java — soluții Swing / Решения на Swing

10 готовых решений (по одному на тему) — **Java 17 + Swing**, каждое — отдельный Maven-проект для IntelliJ.
10 soluții gata (câte una pentru fiecare temă) — **Java 17 + Swing**, fiecare e un proiect Maven separat pentru IntelliJ.

## RU

**Как открыть:** IntelliJ → File → Open… → папка нужной темы (где `pom.xml`) → Open as Project → JDK 17 → ▶ рядом с `main`.
В каждой папке есть свой `README.md`: что делает программа, структура и какие пункты барема закрывает.
Везде есть тесты JUnit 5 на логику (правый клик по `src/test/java` → Run 'All Tests'). Внешних библиотек нет, только Swing.

| № | Папка | Тема | Главный класс | Коротко |
|---|---|---|---|---|
| 1 | `topic01_quiz/` | Викторина / Quiz ★★ | `quiz.QuizApp` | Окно с вопросами и тремя вариантами ответа (a / b / c). |
| 2 | `topic02_hangman/` | Виселица / Hangman ★★ | `hangman.HangmanApp` | Загаданное слово показано маской «_ _ _». |
| 3 | `topic03_battleship/` | Морской бой против компьютера ★★★ | `battleship.BattleshipApp` | Два поля 5×5 (char[][]): твоё и компьютера. |
| 4 | `topic04_rpg/` | Текстовый квест (RPG) ★★★ | `rpg.RpgApp` | Квест в окне: текст сцены и две кнопки выбора. |
| 5 | `topic05_minibank/` | Банкомат / мини-банк ★★ | `bank.BankApp` | Окно банкомата: выбрать счёт, ввести PIN (3 попытки, потом счёт блокируется). |
| 6 | `topic06_library/` | Библиотека ★★ | `library.LibraryApp` | Таблица книг (JTable), 5 книг уже при старте. |
| 7 | `topic07_calculator/` | Калькулятор / конвертер (Swing + JUnit) ★★★ | `calculator.CalculatorApp` | Окно с двумя вкладками. |
| 8 | `topic08_rock_paper_scissors/` | Камень-ножницы-бумага ★ | `rps.RpsApp` | Кнопки с жестами, компьютер выбирает случайно. |
| 9 | `topic09_guess_number/` | Угадай число ★ | `guess.GuessApp` | Компьютер загадывает число (Random). |
| 10 | `topic10_grade_book/` | Каталог оценок ★★ | `grades.GradeBookApp` | Таблица оценок: предмет + оценка 1–10. |

**Важно:** это эталон учителя для разбора и проверки. Ученикам для зачёта хватает консольной версии из `starters/`; Swing — это бонус.

## RO

**Cum deschizi:** IntelliJ → File → Open… → folderul temei (unde e `pom.xml`) → Open as Project → JDK 17 → ▶ lângă `main`.
Fiecare folder are `README.md`: ce face programul, structura și ce criterii din barem acoperă.
Toate au teste JUnit 5 pentru logică (click dreapta pe `src/test/java` → Run 'All Tests'). Fără biblioteci externe, doar Swing.

| Nr | Folder | Tema | Clasa principală | Pe scurt |
|---|---|---|---|---|
| 1 | `topic01_quiz/` | Victorină / Quiz ★★ | `quiz.QuizApp` | O fereastră cu întrebări și trei variante (a / b / c). |
| 2 | `topic02_hangman/` | Spânzurătoarea / Hangman ★★ | `hangman.HangmanApp` | Cuvântul ascuns apare ca mască „_ _ _”. |
| 3 | `topic03_battleship/` | Bătălia navală împotriva calculatorului ★★★ | `battleship.BattleshipApp` | Două table 5×5 (char[][]): a ta și a calculatorului. |
| 4 | `topic04_rpg/` | Aventură text (RPG) ★★★ | `rpg.RpgApp` | Aventură în fereastră: textul scenei și două butoane. |
| 5 | `topic05_minibank/` | Bancomat / mini-bancă ★★ | `bank.BankApp` | Fereastra bancomatului: alegi contul, introduci PIN-ul (3 încercări, apoi contul se blochează). |
| 6 | `topic06_library/` | Bibliotecă ★★ | `library.LibraryApp` | Tabel cu cărți (JTable), 5 cărți de la pornire. |
| 7 | `topic07_calculator/` | Calculator / convertor (Swing + JUnit) ★★★ | `calculator.CalculatorApp` | Fereastră cu două file. |
| 8 | `topic08_rock_paper_scissors/` | Piatră, foarfecă, hârtie ★ | `rps.RpsApp` | Butoane cu gesturi, calculatorul alege la întâmplare. |
| 9 | `topic09_guess_number/` | Ghicește numărul ★ | `guess.GuessApp` | Calculatorul alege un număr (Random). |
| 10 | `topic10_grade_book/` | Catalog de note ★★ | `grades.GradeBookApp` | Tabel cu note: materia + nota 1–10. |

**Important:** aceasta este soluția-model a profesorului. Pentru elevi e suficientă varianta de consolă din `starters/`; Swing este bonus.
