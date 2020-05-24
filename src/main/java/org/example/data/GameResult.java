package org.example.data;

import java.math.BigDecimal;

public class GameResult {

    private BigDecimal winningProbabilityP1;
    private BigDecimal winningProbabilityP2;
    private int winner;

    public GameResult(BigDecimal winningProbabilityP1, BigDecimal winningProbabilityP2, int winner) {
        this.winningProbabilityP1 = winningProbabilityP1;
        this.winningProbabilityP2 = winningProbabilityP2;
        this.winner = winner;
    }

    public BigDecimal getWinningProbabilityP1() {
        return winningProbabilityP1;
    }

    public void setWinningProbabilityP1(BigDecimal winningProbabilityP1) {
        this.winningProbabilityP1 = winningProbabilityP1;
    }

    public BigDecimal getWinningProbabilityP2() {
        return winningProbabilityP2;
    }

    public void setWinningProbabilityP2(BigDecimal winningProbabilityP2) {
        this.winningProbabilityP2 = winningProbabilityP2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
