package org.example.game;

import org.example.data.Card;
import org.example.data.CardSuit;
import org.example.data.CardValue;
import org.example.data.PriorityCard;
import org.example.data.RankResult;
import org.example.data.RankType;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

public class RankEvaluator {

    public RankResult evaluate(List<Card> hand) {
        Set<CardValue> distinctValues = new HashSet();
        Map<CardValue, Integer> repeatedValues = new HashMap<>();
        CardSuit prevSuit = hand.get(0).getSuit();
        boolean isFlush = true;
        int minValue = 14;
        int maxValue = 0;

        for (Card card : hand) {
            if (distinctValues.contains(card.getValue())) {
                int counter = Optional.ofNullable(repeatedValues.get(card.getValue())).orElse(1);
                repeatedValues.put(card.getValue(), ++counter);
            } else {
                distinctValues.add(card.getValue());
            }

            if (prevSuit != card.getSuit()) {
                isFlush = false;
            }

            int cardNumValue = card.getValue().getValue();

            if (minValue > cardNumValue) {
                minValue = cardNumValue;
            }
            if (maxValue < cardNumValue) {
                maxValue = cardNumValue;
            }
        }

        PriorityQueue<PriorityCard> priorityCards = buildPriorityCards(distinctValues, repeatedValues);

        boolean consecutiveValues = maxValue - minValue == 4 && repeatedValues.isEmpty();
        if (consecutiveValues) {
            if (isFlush) {
                if (minValue == 10) {
                    return new RankResult(RankType.ROYAL_FLUSH, priorityCards) ;
                }
                return new RankResult(RankType.STRAIGHT_FLUSH, priorityCards) ;
            }
            return new RankResult(RankType.STRAIGHT, priorityCards) ;
        }

        if (isFlush) {
            return new RankResult(RankType.FLUSH, priorityCards) ;
        }

        if (repeatedValues.size() == 1) {
            Integer quantity = repeatedValues.values().stream().findFirst().get();
            if (quantity == 2) {
                return new RankResult(RankType.ONE_PAIR, priorityCards) ;
            } else if (quantity == 3) {
                return new RankResult(RankType.THREE_OF_A_KIND, priorityCards) ;
            } else if (quantity == 4) {
                return new RankResult(RankType.FOUR_OF_A_KIND, priorityCards) ;
            }
        } else if (repeatedValues.size() == 2) {
            if (repeatedValues.values().stream().mapToInt(x -> x).sum() == 5) {
                return new RankResult(RankType.FULL_HOUSE, priorityCards) ;
            }
            return new RankResult(RankType.TWO_PAIRS, priorityCards) ;
        }

        return new RankResult(RankType.HIGH_CARD, priorityCards) ;
    }

    private PriorityQueue<PriorityCard> buildPriorityCards(Set<CardValue> distinctValues, Map<CardValue, Integer> repeatedValues) {
        PriorityQueue<PriorityCard> priorityCards = new PriorityQueue<>(distinctValues.size(), Collections.reverseOrder());
        for (CardValue cardValue : distinctValues) {
            if (repeatedValues.get(cardValue) == null) {
                priorityCards.add(new PriorityCard(cardValue.getValue(), 1));
            } else {
                priorityCards.add(new PriorityCard(cardValue.getValue(), repeatedValues.get(cardValue)));
            }
        }
        return priorityCards;
    }

}
