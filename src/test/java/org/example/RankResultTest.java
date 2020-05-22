package org.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class RankResultTest {

    @Test
    public void calculateWinningProbability_whenOnePair() {
        RankResult rankResult = new RankResult(RankType.ONE_PAIR, buildPriorityCards(Arrays.asList(
                new HandCard(3),
                new HandCard(6),
                new HandCard(7),
                new HandCard(12, 2)
        )));

        BigDecimal probability = rankResult.calculateWinningProbability();
        assertThat(probability, is(BigDecimal.valueOf(82.61)));
    }

    @Test
    public void calculateWinningProbability_whenHighCard() {
        RankResult rankResult = new RankResult(RankType.HIGH_CARD, buildPriorityCards(Arrays.asList(
                new HandCard(3),
                new HandCard(6),
                new HandCard(7),
                new HandCard(4),
                new HandCard(12)
        )));

        BigDecimal probability = rankResult.calculateWinningProbability();
        assertThat(probability, is(BigDecimal.valueOf(38.55)));
    }

    private static PriorityQueue<HandCard> buildPriorityCards(List<HandCard> cards) {
        PriorityQueue<HandCard> priorityQueue = new PriorityQueue<>(cards.size(), Collections.reverseOrder());
        cards.forEach(priorityQueue::add);
        return priorityQueue;
    }
}