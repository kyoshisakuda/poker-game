package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PlayerComparator implements Comparator<RankResult> {

    @Override
    public int compare(RankResult result1, RankResult result2) {
        if (result1.getRankType() == result2.getRankType()) {
            PriorityQueue<HandCard> priorityCardsP1 = result1.getPriorityCards();
            PriorityQueue<HandCard> priorityCardsP2 = result2.getPriorityCards();
            while (priorityCardsP1.peek() != null) {
                int value1 = priorityCardsP1.poll().getValue();
                int value2 = priorityCardsP2.poll().getValue();
                if (value1 != value2) {
                    return value1 > value2 ? 1 : -1;
                }
            }
            return 0;
        }

        return result1.getRankType().getPriority() > result2.getRankType().getPriority() ? 1 : -1;
    }

}
