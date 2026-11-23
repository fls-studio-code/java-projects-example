package battleship;

import java.util.Random;

/**
 * Tabla 5x5. Fiecare navă ocupă o singură celulă.
 * Simboluri: '~' apă, 'S' navă, 'X' lovit, 'o' ratat.
 */
public class Board {
    public static final int SIZE = 5;
    public static final char WATER = '~';
    public static final char SHIP = 'S';
    public static final char HIT = 'X';
    public static final char MISS = 'o';

    private final char[][] cells = new char[SIZE][SIZE];

    public Board() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = WATER;
            }
        }
    }

    // punem o navă manual (folosit în teste sau la demo)
    public void placeShip(int row, int col) {
        cells[row][col] = SHIP;
    }

    // punem nave la întâmplare, fără să se suprapună
    public void placeShipsRandom(int count, Random random) {
        int placed = 0;
        while (placed < count) {
            int r = random.nextInt(SIZE);
            int c = random.nextInt(SIZE);
            if (cells[r][c] == WATER) {
                cells[r][c] = SHIP;
                placed++;
            }
        }
    }

    public ShotResult shoot(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return ShotResult.INVALID;
        }
        if (cells[row][col] == HIT || cells[row][col] == MISS) {
            return ShotResult.ALREADY; // nu tragem de două ori în aceeași celulă
        }
        if (cells[row][col] == SHIP) {
            cells[row][col] = HIT;
            return ShotResult.HIT;
        }
        cells[row][col] = MISS;
        return ShotResult.MISS;
    }

    public boolean wasShot(int row, int col) {
        return cells[row][col] == HIT || cells[row][col] == MISS;
    }

    public char getCell(int row, int col) {
        return cells[row][col];
    }

    public int shipsLeft() {
        int count = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (cells[r][c] == SHIP) {
                    count++;
                }
            }
        }
        return count;
    }
}
