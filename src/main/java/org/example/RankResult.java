package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
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
        return new PriorityQueue<HandCard>(priorityCards);
    }

    public void setPriorityCards(PriorityQueue<HandCard> priorityCards) {
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
