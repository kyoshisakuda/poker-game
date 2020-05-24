package org.example.data;

import java.math.BigDecimal;

public enum RankType {

    HIGH_CARD(1, BigDecimal.valueOf(50.1177), BigDecimal.ZERO),
    ONE_PAIR(2, BigDecimal.valueOf(42.2569), BigDecimal.valueOf(50.1)),
    TWO_PAIRS(3, BigDecimal.valueOf(4.7539), BigDecimal.valueOf(92.38)),
    THREE_OF_A_KIND(4, BigDecimal.valueOf(2.1128), BigDecimal.valueOf(97.13)),
    STRAIGHT(5, BigDecimal.valueOf(0.3925), BigDecimal.valueOf(99.24)),
    FLUSH(6, BigDecimal.valueOf(0.1965), BigDecimal.valueOf(99.633)),
    FULL_HOUSE(7, BigDecimal.valueOf(0.1441), BigDecimal.valueOf(99.83)),
    FOUR_OF_A_KIND(8, BigDecimal.valueOf(0.024), BigDecimal.valueOf(99.9744)),
    STRAIGHT_FLUSH(9, BigDecimal.valueOf(0.00139), BigDecimal.valueOf(99.9985)),
    ROYAL_FLUSH(10, BigDecimal.valueOf(0.000154), BigDecimal.valueOf(99.999846));

    private int priority;
    private BigDecimal drawingProbability;
    private BigDecimal higherRankProbability;

    RankType(int priority, BigDecimal drawingProbability, BigDecimal higherRankProbability) {
        this.priority = priority;
        this.drawingProbability = drawingProbability;
        this.higherRankProbability = higherRankProbability;
    }

    public int getPriority() {
        return priority;
    }

    public BigDecimal getDrawingProbability() {
        return drawingProbability;
    }

    public BigDecimal getHigherRankProbability() {
        return higherRankProbability;
    }

}
