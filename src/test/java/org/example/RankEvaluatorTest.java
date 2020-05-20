package org.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class RankEvaluatorTest {

    private RankEvaluator rankEvaluator;

    @Before
    public void setUp() {
        rankEvaluator = new RankEvaluator();
    }

    @Test
    public void evaluate_whenNoRankCombination_returnHighCard() {
        List<String> hand = Arrays.asList("8C", "9H", "3S", "AS", "JD");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.HIGH_CARD));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(14, 11, 9, 8, 3));
    }

    @Test
    public void evaluate_whenThereIsAPair_returnPair() {
        List<String> hand = Arrays.asList("8C", "9H", "8S", "AS", "3D");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.ONE_PAIR));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 14, 9, 3));
    }

    @Test
    public void evaluate_whenThereAreTwoPairs_returnTwoPairs() {
        List<String> hand = Arrays.asList("8C", "9H", "8S", "AS", "9D");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.TWO_PAIRS));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(9, 8, 14));
    }

    @Test
    public void evaluate_whenThereAreThreeOfAKind_returnThreeOfAKind() {
        List<String> hand = Arrays.asList("8C", "9H", "8S", "8H", "3D");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.THREE_OF_A_KIND));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 9, 3));
    }

    @Test
    public void evaluate_whenAllCardsAreConsecutiveValues_returnStraight() {
        List<String> hand = Arrays.asList("8C", "9H", "TS", "JH", "QD");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.STRAIGHT));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(12, 11, 10, 9, 8));
    }

    @Test
    public void evaluate_whenAllCardsAreSameSuit_returnFlush() {
        List<String> hand = Arrays.asList("3D", "6D", "7D", "TD", "QD");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.FLUSH));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(12, 10, 7, 6, 3));
    }

    @Test
    public void evaluate_whenThereAreThreeOfAKindAndAPair_returnFullHouse() {
        List<String> hand = Arrays.asList("8C", "3H", "8S", "8H", "3D");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.FULL_HOUSE));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 3));
    }

    @Test
    public void evaluate_whenThereAreFourCardsWithSameValue_returnFourOfAKind() {
        List<String> hand = Arrays.asList("8C", "8D", "8S", "8H", "JD");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.FOUR_OF_A_KIND));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 11));
    }

    @Test
    public void evaluate_whenAllCardsAreConsecutiveValuesAndSameSuit_returnStraightFlush() {
        List<String> hand = Arrays.asList("2H", "3H", "4H", "5H", "6H");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.STRAIGHT_FLUSH));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(6, 5, 4, 3, 2));
    }

    @Test
    public void evaluate_whenThereAreFourCardsWithSameValue_returnRoyalFlush() {
        List<String> hand = Arrays.asList("TS", "JS", "QS", "KS", "AS");
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.ROYAL_FLUSH));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(14, 13, 12, 11, 10));
    }

    public static void assertCardPriorityOrder(PriorityQueue<HandCard> priorityQueue, List<Integer> expectedOrder) {
        for (Integer expectedItem : expectedOrder) {
            assertThat(priorityQueue.poll().getValue(), is(expectedItem));
        }
    }

}