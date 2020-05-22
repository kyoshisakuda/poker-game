package org.example;

import java.util.Comparator;

public class PlayerComparator implements Comparator<RankResult> {

    @Override
    public int compare(RankResult result1, RankResult result2) {
        if (result1.getRankType() == result2.getRankType()) {
            while (result1.getPriorityCards().peek() != null) {
                int value1 = result1.getPriorityCards().poll().getValue();
                int value2 = result2.getPriorityCards().poll().getValue();
                if (value1 != value2) {
                    System.out.println(String.format("Player 1: HighCard(%d) vs Player 2: HighCard(%d)", value1, value2));
                    return value1 > value2 ? 1 : -1;
                }
            }
            return 0;
        }

        System.out.println(String.format("Player 1: %s vs Player 2: %s", result1.getRankType().name(), result2.getRankType().name()));
        return result1.getRankType().getPriority() > result2.getRankType().getPriority() ? 1 : -1;
    }

}
