package org.example.game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.example.data.PriorityCard;
import org.example.data.RankResult;
import org.example.data.RankType;
import org.example.game.PlayerComparator;
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
                        new PriorityCard(8, 2),
                        new PriorityCard(10),
                        new PriorityCard(3),
                        new PriorityCard(2))));

        RankResult result2 = new RankResult(RankType.ONE_PAIR,
                buildPriorityCards(Arrays.asList(
                        new PriorityCard(5, 2),
                        new PriorityCard(13),
                        new PriorityCard(7),
                        new PriorityCard(6))));

        assertThat(playerComparator.compare(result1, result2), is(1));
    }

    @Test
    public void compare_whenSameRankTypeAndFirstResultLesserThanSecondResult_returnMinusOne() {
        RankResult result1 = new RankResult(RankType.HIGH_CARD,
                buildPriorityCards(Arrays.asList(
                        new PriorityCard(2),
                        new PriorityCard(5),
                        new PriorityCard(7),
                        new PriorityCard(8),
                        new PriorityCard(12))));

        RankResult result2 = new RankResult(RankType.HIGH_CARD,
                buildPriorityCards(Arrays.asList(
                        new PriorityCard(5),
                        new PriorityCard(8),
                        new PriorityCard(9),
                        new PriorityCard(11),
                        new PriorityCard(14))));

        assertThat(playerComparator.compare(result1, result2), is(-1));
    }

    @Test
    public void compare_whenFirstResultRankHigher_returnOne() {
        RankResult result1 = new RankResult(RankType.FLUSH,
                buildPriorityCards(Arrays.asList(
                        new PriorityCard(8),
                        new PriorityCard(10),
                        new PriorityCard(3),
                        new PriorityCard(5),
                        new PriorityCard(2))));

        RankResult result2 = new RankResult(RankType.THREE_OF_A_KIND,
                buildPriorityCards(Arrays.asList(
                        new PriorityCard(5, 3),
                        new PriorityCard(13),
                        new PriorityCard(6))));

        assertThat(playerComparator.compare(result1, result2), is(1));
    }

    @Test
    public void compare_whenSecondResultRankHigher_returnMinusOne() {
        RankResult result1 = new RankResult(RankType.FULL_HOUSE,
                buildPriorityCards(Arrays.asList(
                        new PriorityCard(8, 3),
                        new PriorityCard(3, 2))));

        RankResult result2 = new RankResult(RankType.ROYAL_FLUSH,
                buildPriorityCards(Arrays.asList(
                        new PriorityCard(11),
                        new PriorityCard(13),
                        new PriorityCard(10),
                        new PriorityCard(14),
                        new PriorityCard(12))));

        assertThat(playerComparator.compare(result1, result2), is(-1));
    }

    private static PriorityQueue<PriorityCard> buildPriorityCards(List<PriorityCard> cards) {
        PriorityQueue<PriorityCard> priorityQueue = new PriorityQueue<>(cards.size(), Collections.reverseOrder());
        cards.forEach(priorityQueue::add);
        return priorityQueue;
    }

}