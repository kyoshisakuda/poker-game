package org.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RankEvaluatorTest {

    private RankEvaluator rankEvaluator;

    @Before
    public void setUp() {
        rankEvaluator = new RankEvaluator();
    }

    @Test
    public void evaluate_whenNoRankCombination_returnHighCard() {
        List<String> hand = Arrays.asList("8C", "9H", "3S", "AS", "JD");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.HIGH_CARD));
    }

    @Test
    public void evaluate_whenThereIsAPair_returnPair() {
        List<String> hand = Arrays.asList("8C", "9H", "8S", "AS", "3D");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.ONE_PAIR));
    }

    @Test
    public void evaluate_whenThereAreTwoPairs_returnTwoPairs() {
        List<String> hand = Arrays.asList("8C", "9H", "8S", "AS", "9D");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.TWO_PAIRS));
    }

    @Test
    public void evaluate_whenThereAreThreeOfAKind_returnThreeOfAKind() {
        List<String> hand = Arrays.asList("8C", "9H", "8S", "8H", "3D");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.THREE_OF_A_KIND));
    }

    @Test
    public void evaluate_whenAllCardsAreConsecutiveValues_returnStraight() {
        List<String> hand = Arrays.asList("8C", "9H", "TS", "JH", "QD");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.STRAIGHT));
    }

    @Test
    public void evaluate_whenAllCardsAreSameSuit_returnFlush() {
        List<String> hand = Arrays.asList("3D", "6D", "7D", "TD", "QD");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.FLUSH));
    }

    @Test
    public void evaluate_whenThereAreThreeOfAKindAndAPair_returnFullHouse() {
        List<String> hand = Arrays.asList("8C", "3H", "8S", "8H", "3D");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.FULL_HOUSE));
    }

    @Test
    public void evaluate_whenThereAreFourCardsWithSameValue_returnFourOfAKind() {
        List<String> hand = Arrays.asList("8C", "8D", "8S", "8H", "3D");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.FOUR_OF_A_KIND));
    }

    @Test
    public void evaluate_whenAllCardsAreConsecutiveValuesAndSameSuit_returnStraightFlush() {
        List<String> hand = Arrays.asList("2H", "3H", "4H", "5H", "6H");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.STRAIGHT_FLUSH));
    }

    @Test
    public void evaluate_whenThereAreFourCardsWithSameValue_returnRoyalFlush() {
        List<String> hand = Arrays.asList("TS", "JS", "QS", "KS", "AS");
        RankType rankType = rankEvaluator.evaluate(hand);
        assertThat(rankType, is(RankType.ROYAL_FLUSH));
    }

}