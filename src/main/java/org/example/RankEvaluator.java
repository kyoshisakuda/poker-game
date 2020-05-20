package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

public class RankEvaluator {

    public RankResult evaluate(List<String> hand) {
        Set<String> memory = new HashSet();
        Map<String, Integer> duplicated = new HashMap<>();
        String prevSuit = null;
        boolean isFlush = true;
        int min = 0;
        int max = 0;
        PriorityQueue<HandCard> priorityCards = new PriorityQueue<>(5, Collections.reverseOrder());

        for (String card : hand) {
            String cardValue = card.substring(0, 1);
            String cardSuit = card.substring(1, 2);

            if (memory.contains(cardValue)) {
                int counter = Optional.ofNullable(duplicated.get(cardValue)).orElse(1);
                duplicated.put(cardValue, ++counter);
            } else {
                memory.add(cardValue);
            }

            if (prevSuit == null) {
                prevSuit = cardSuit;
            } else if (!prevSuit.equals(cardSuit)) {
                isFlush = false;
            }

            int cardNumValue = getCardValue(cardValue);

            if (min == 0 || min > cardNumValue) {
                min = cardNumValue;
            }
            if (max == 0 || max < cardNumValue) {
                max = cardNumValue;
            }
        }

        for (String cardValue : memory) {
            if (duplicated.get(cardValue) == null) {
                priorityCards.add(new HandCard(getCardValue(cardValue), 1));
            } else {
                priorityCards.add(new HandCard(getCardValue(cardValue), duplicated.get(cardValue)));
            }
        }

        if (max - min == 4 && duplicated.size() == 0) {
            if (isFlush) {
                if (min == 10) {
                    return new RankResult(RankType.ROYAL_FLUSH, priorityCards) ;
                }
                return new RankResult(RankType.STRAIGHT_FLUSH, priorityCards) ;
            }
            return new RankResult(RankType.STRAIGHT, priorityCards) ;
        }

        if (isFlush) {
            return new RankResult(RankType.FLUSH, priorityCards) ;
        }

        if (duplicated.size() == 1) {
            if (duplicated.values().stream().findFirst().get() == 2) {
                return new RankResult(RankType.ONE_PAIR, priorityCards) ;
            } else if (duplicated.values().stream().findFirst().get() == 3) {
                return new RankResult(RankType.THREE_OF_A_KIND, priorityCards) ;
            } else if (duplicated.values().stream().findFirst().get() == 4) {
                return new RankResult(RankType.FOUR_OF_A_KIND, priorityCards) ;
            }
        } else if (duplicated.size() == 2) {
            if (duplicated.values().stream().mapToInt(x -> x).sum() == 5) {
                return new RankResult(RankType.FULL_HOUSE, priorityCards) ;
            }
            return new RankResult(RankType.TWO_PAIRS, priorityCards) ;
        }

        return new RankResult(RankType.HIGH_CARD, priorityCards) ;
    }

    private int getCardValue(String value) {
        switch (value) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "T":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return 0;
        }
    }

}
