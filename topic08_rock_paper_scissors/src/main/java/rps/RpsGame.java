package rps;

import java.util.Random;

/**
 * Logica jocului: scorul, modul (3 sau 5 gesturi), statistica gesturilor.
 */
public class RpsGame {
    public static final int WINS_NEEDED = 3; // jucăm "până la 3 victorii"

    private final Random random;
    private boolean extended = false; // false = 3 gesturi, true = 5 gesturi
    private int playerWins = 0;
    private int computerWins = 0;
    private int draws = 0;
    private int rounds = 0;
    private final int[] moveStats = new int[Move.values().length]; // de câte ori a apărut fiecare gest

    public RpsGame(Random random) {
        this.random = random;
    }

    // calculatorul alege la întâmplare din gesturile permise
    public Move randomComputerMove() {
        int count = extended ? 5 : 3;
        return Move.values()[random.nextInt(count)];
    }

    public RoundResult playRound(Move player, Move computer) {
        rounds++;
        moveStats[player.ordinal()]++;
        moveStats[computer.ordinal()]++;
        if (player == computer) {
            draws++;
            return RoundResult.DRAW;
        }
        if (player.beats(computer)) {
            playerWins++;
            return RoundResult.PLAYER;
        }
        computerWins++;
        return RoundResult.COMPUTER;
    }

    public boolean isOver() {
        return playerWins >= WINS_NEEDED || computerWins >= WINS_NEEDED;
    }

    public void reset() {
        playerWins = 0;
        computerWins = 0;
        draws = 0;
        rounds = 0;
        for (int i = 0; i < moveStats.length; i++) {
            moveStats[i] = 0;
        }
    }

    public String getStatsText() {
        StringBuilder sb = new StringBuilder();
        for (Move m : Move.values()) {
            if (extended || m.ordinal() < 3) {
                sb.append(m.getTitle()).append(": ").append(moveStats[m.ordinal()]).append("   ");
            }
        }
        return sb.toString().trim();
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getComputerWins() {
        return computerWins;
    }

    public int getDraws() {
        return draws;
    }

    public int getRounds() {
        return rounds;
    }
}
