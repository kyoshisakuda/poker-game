package org.example.data;

import java.util.List;

public class ReportObject {

    private int p1Wins;
    private int p2Wins;
    private int draw;
    private List<GameResult> gameResults;

    public ReportObject() {
    }

    public ReportObject(int p1Wins, int p2Wins, int draw, List<GameResult> gameResults) {
        this.p1Wins = p1Wins;
        this.p2Wins = p2Wins;
        this.draw = draw;
        this.gameResults = gameResults;
    }

    public int getP1Wins() {
        return p1Wins;
    }

    public void setP1Wins(int p1Wins) {
        this.p1Wins = p1Wins;
    }

    public int getP2Wins() {
        return p2Wins;
    }

    public void setP2Wins(int p2Wins) {
        this.p2Wins = p2Wins;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }

    public void setGameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }
}
