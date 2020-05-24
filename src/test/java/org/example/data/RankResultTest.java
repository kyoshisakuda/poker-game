package org.example.data;

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
                new PriorityCard(3),
                new PriorityCard(6),
                new PriorityCard(7),
                new PriorityCard(12, 2)
        )));

        BigDecimal probability = rankResult.calculateWinningProbability();
        assertThat(probability, is(BigDecimal.valueOf(82.61)));
    }

    @Test
    public void calculateWinningProbability_whenHighCard() {
        RankResult rankResult = new RankResult(RankType.HIGH_CARD, buildPriorityCards(Arrays.asList(
                new PriorityCard(3),
                new PriorityCard(6),
                new PriorityCard(7),
                new PriorityCard(4),
                new PriorityCard(12)
        )));

        BigDecimal probability = rankResult.calculateWinningProbability();
        assertThat(probability, is(BigDecimal.valueOf(38.55)));
    }

    private static PriorityQueue<PriorityCard> buildPriorityCards(List<PriorityCard> cards) {
        PriorityQueue<PriorityCard> priorityQueue = new PriorityQueue<>(cards.size(), Collections.reverseOrder());
        cards.forEach(priorityQueue::add);
        return priorityQueue;
    }
}