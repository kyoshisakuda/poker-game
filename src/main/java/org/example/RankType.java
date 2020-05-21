package org.example;

public enum RankType {

    HIGH_CARD(1),
    ONE_PAIR(2),
    TWO_PAIRS(3),
    THREE_OF_A_KIND(4),
    STRAIGHT(5),
    FLUSH(6),
    FULL_HOUSE(7),
    FOUR_OF_A_KIND(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);

    private int priority;

    RankType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
