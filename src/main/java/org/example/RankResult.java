package org.example;

import java.util.PriorityQueue;

public class RankResult {

    private RankType rankType;
    private PriorityQueue<HandCard> priorityCards;

    public RankResult() {
    }

    public RankResult(RankType rankType, PriorityQueue<HandCard> priorityCards) {
        this.rankType = rankType;
        this.priorityCards = priorityCards;
    }

    public RankType getRankType() {
        return rankType;
    }

    public void setRankType(RankType rankType) {
        this.rankType = rankType;
    }

    public PriorityQueue<HandCard> getPriorityCards() {
        return priorityCards;
    }

    public void setPriorityCards(PriorityQueue<HandCard> priorityCards) {
        this.priorityCards = priorityCards;
    }
}
