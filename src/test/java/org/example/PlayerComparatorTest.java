package org.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PlayerComparatorTest {

    private PlayerComparator playerComparator;

    @Before
    public void setUp() {
        playerComparator = new PlayerComparator();
    }

    @Test
    public void compare_whenSameRankTypeAndFirstResultGreaterThanSecondResult_returnOne() {
        RankResult result1 = new RankResult(RankType.ONE_PAIR,
                buildPriorityCards(Arrays.asList(
                        new HandCard(8, 2),
                        new HandCard(10),
                        new HandCard(3),
                        new HandCard(2))));

        RankResult result2 = new RankResult(RankType.ONE_PAIR,
                buildPriorityCards(Arrays.asList(
                        new HandCard(5, 2),
                        new HandCard(13),
                        new HandCard(7),
                        new HandCard(6))));

        assertThat(playerComparator.compare(result1, result2), is(1));
    }

    @Test
    public void compare_whenSameRankTypeAndFirstResultLesserThanSecondResult_returnMinusOne() {
        RankResult result1 = new RankResult(RankType.HIGH_CARD,
                buildPriorityCards(Arrays.asList(
                        new HandCard(2),
                        new HandCard(5),
                        new HandCard(7),
                        new HandCard(8),
                        new HandCard(12))));

        RankResult result2 = new RankResult(RankType.HIGH_CARD,
                buildPriorityCards(Arrays.asList(
                        new HandCard(5),
                        new HandCard(8),
                        new HandCard(9),
                        new HandCard(11),
                        new HandCard(14))));

        assertThat(playerComparator.compare(result1, result2), is(-1));
    }

    @Test
    public void compare_whenFirstResultRankHigher_returnOne() {
        RankResult result1 = new RankResult(RankType.FLUSH,
                buildPriorityCards(Arrays.asList(
                        new HandCard(8),
                        new HandCard(10),
                        new HandCard(3),
                        new HandCard(5),
                        new HandCard(2))));

        RankResult result2 = new RankResult(RankType.THREE_OF_A_KIND,
                buildPriorityCards(Arrays.asList(
                        new HandCard(5, 3),
                        new HandCard(13),
                        new HandCard(6))));

        assertThat(playerComparator.compare(result1, result2), is(1));
    }

    @Test
    public void compare_whenSecondResultRankHigher_returnMinusOne() {
        RankResult result1 = new RankResult(RankType.FULL_HOUSE,
                buildPriorityCards(Arrays.asList(
                        new HandCard(8, 3),
                        new HandCard(3, 2))));

        RankResult result2 = new RankResult(RankType.ROYAL_FLUSH,
                buildPriorityCards(Arrays.asList(
                        new HandCard(11),
                        new HandCard(13),
                        new HandCard(10),
                        new HandCard(14),
                        new HandCard(12))));

        assertThat(playerComparator.compare(result1, result2), is(-1));
    }

    private static PriorityQueue<HandCard> buildPriorityCards(List<HandCard> cards) {
        PriorityQueue<HandCard> priorityQueue = new PriorityQueue<>(cards.size(), Collections.reverseOrder());
        cards.forEach(priorityQueue::add);
        return priorityQueue;
    }

}