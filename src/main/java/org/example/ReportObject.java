package org.example;

import java.util.List;

public class ReportObject {

    private int p1Wins;
    private int p2Wins;
    private int draw;
    private List<HandResult> handResults;

    public ReportObject() {
    }

    public ReportObject(int p1Wins, int p2Wins, int draw, List<HandResult> handResults) {
        this.p1Wins = p1Wins;
        this.p2Wins = p2Wins;
        this.draw = draw;
        this.handResults = handResults;
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

    public List<HandResult> getHandResults() {
        return handResults;
    }

    public void setHandResults(List<HandResult> handResults) {
        this.handResults = handResults;
    }
}
