package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class RankEvaluator {

    public RankType evaluate(List<String> hand) {
        Set<String> memory = new HashSet();
        Map<String, Integer> duplicated = new HashMap<>();
        String prevSuit = null;
        boolean isFlush = true;
        int min = 0;
        int max = 0;

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

        if (max - min == 4 && duplicated.size() == 0) {
            if (isFlush) {
                if (min == 10) {
                    return RankType.ROYAL_FLUSH;
                }
                return RankType.STRAIGHT_FLUSH;
            }
            return RankType.STRAIGHT;
        }

        if (isFlush) {
            return RankType.FLUSH;
        }

        if (duplicated.size() == 1) {
            if (duplicated.values().stream().findFirst().get() == 2) {
                return RankType.ONE_PAIR;
            } else if (duplicated.values().stream().findFirst().get() == 3) {
                return RankType.THREE_OF_A_KIND;
            } else if (duplicated.values().stream().findFirst().get() == 4) {
                return RankType.FOUR_OF_A_KIND;
            }
        } else if (duplicated.size() == 2) {
            if (duplicated.values().stream().mapToInt(x -> x).sum() == 5) {
                return RankType.FULL_HOUSE;
            }
            return RankType.TWO_PAIRS;
        }

        return RankType.HIGH_CARD;
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
