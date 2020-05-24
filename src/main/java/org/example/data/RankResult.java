package org.example.data;

import java.math.BigDecimal;
import java.util.PriorityQueue;

public class RankResult {

    private RankType rankType;
    private PriorityQueue<PriorityCard> priorityCards;

    public RankResult() {
    }

    public RankResult(RankType rankType, PriorityQueue<PriorityCard> priorityCards) {
        this.rankType = rankType;
        this.priorityCards = priorityCards;
    }

    public RankType getRankType() {
        return rankType;
    }

    public void setRankType(RankType rankType) {
        this.rankType = rankType;
    }

    public PriorityQueue<PriorityCard> getPriorityCards() {
        return new PriorityQueue<PriorityCard>(priorityCards);
    }

    public void setPriorityCards(PriorityQueue<PriorityCard> priorityCards) {
        this.priorityCards = priorityCards;
    }

    /**
     * Calculates the probability of the other player getting a worse
     * ranking + the probability of getting the same ranking and winning by a higher card value
     *
     * @return BigDecimal probability of winning in percentage
     */
    public BigDecimal calculateWinningProbability() {
        int bestCardValue = priorityCards.peek().getValue();
        BigDecimal sameRankWinningProbability = BigDecimal.valueOf((bestCardValue - 2) / 13f).multiply(rankType.getDrawingProbability());
        return rankType.getHigherRankProbability().add(sameRankWinningProbability).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
