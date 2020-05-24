package org.example.game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.example.data.Card;
import org.example.data.CardSuit;
import org.example.data.CardValue;
import org.example.data.PriorityCard;
import org.example.data.RankResult;
import org.example.data.RankType;
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
        List<Card> hand = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.NINE, CardSuit.HEARTS),
                new Card(CardValue.THREE, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.SPADE),
                new Card(CardValue.JACK, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.HIGH_CARD));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(14, 11, 9, 8, 3));
    }

    @Test
    public void evaluate_whenThereIsAPair_returnPair() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.NINE, CardSuit.HEARTS),
                new Card(CardValue.EIGHT, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.SPADE),
                new Card(CardValue.THREE, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.ONE_PAIR));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 14, 9, 3));
    }

    @Test
    public void evaluate_whenThereAreTwoPairs_returnTwoPairs() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.NINE, CardSuit.HEARTS),
                new Card(CardValue.EIGHT, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.SPADE),
                new Card(CardValue.NINE, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.TWO_PAIRS));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(9, 8, 14));
    }

    @Test
    public void evaluate_whenThereAreThreeOfAKind_returnThreeOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.NINE, CardSuit.HEARTS),
                new Card(CardValue.EIGHT, CardSuit.SPADE),
                new Card(CardValue.EIGHT, CardSuit.HEARTS),
                new Card(CardValue.THREE, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.THREE_OF_A_KIND));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 9, 3));
    }

    @Test
    public void evaluate_whenAllCardsAreConsecutiveValues_returnStraight() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.NINE, CardSuit.HEARTS),
                new Card(CardValue.TEN, CardSuit.SPADE),
                new Card(CardValue.JACK, CardSuit.HEARTS),
                new Card(CardValue.QUEEN, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.STRAIGHT));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(12, 11, 10, 9, 8));
    }

    @Test
    public void evaluate_whenAllCardsAreSameSuit_returnFlush() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.THREE, CardSuit.DIAMOND),
                new Card(CardValue.SIX, CardSuit.DIAMOND),
                new Card(CardValue.SEVEN, CardSuit.DIAMOND),
                new Card(CardValue.TEN, CardSuit.DIAMOND),
                new Card(CardValue.QUEEN, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.FLUSH));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(12, 10, 7, 6, 3));
    }

    @Test
    public void evaluate_whenThereAreThreeOfAKindAndAPair_returnFullHouse() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.THREE, CardSuit.HEARTS),
                new Card(CardValue.EIGHT, CardSuit.SPADE),
                new Card(CardValue.EIGHT, CardSuit.HEARTS),
                new Card(CardValue.THREE, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.FULL_HOUSE));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 3));
    }

    @Test
    public void evaluate_whenThereAreFourCardsWithSameValue_returnFourOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.EIGHT, CardSuit.DIAMOND),
                new Card(CardValue.EIGHT, CardSuit.SPADE),
                new Card(CardValue.EIGHT, CardSuit.HEARTS),
                new Card(CardValue.JACK, CardSuit.DIAMOND)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.FOUR_OF_A_KIND));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(8, 11));
    }

    @Test
    public void evaluate_whenAllCardsAreConsecutiveValuesAndSameSuit_returnStraightFlush() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.TWO, CardSuit.HEARTS),
                new Card(CardValue.THREE, CardSuit.HEARTS),
                new Card(CardValue.FOUR, CardSuit.HEARTS),
                new Card(CardValue.FIVE, CardSuit.HEARTS),
                new Card(CardValue.SIX, CardSuit.HEARTS)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.STRAIGHT_FLUSH));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(6, 5, 4, 3, 2));
    }

    @Test
    public void evaluate_whenThereAreFourCardsWithSameValue_returnRoyalFlush() {
        List<Card> hand = Arrays.asList(
                new Card(CardValue.TEN, CardSuit.SPADE),
                new Card(CardValue.JACK, CardSuit.SPADE),
                new Card(CardValue.QUEEN, CardSuit.SPADE),
                new Card(CardValue.KING, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.SPADE)
        );
        RankResult rankResult = rankEvaluator.evaluate(hand);
        assertThat(rankResult.getRankType(), is(RankType.ROYAL_FLUSH));
        assertCardPriorityOrder(rankResult.getPriorityCards(), Arrays.asList(14, 13, 12, 11, 10));
    }

    public static void assertCardPriorityOrder(PriorityQueue<PriorityCard> priorityQueue, List<Integer> expectedOrder) {
        for (Integer expectedItem : expectedOrder) {
            assertThat(priorityQueue.poll().getValue(), is(expectedItem));
        }
    }

}