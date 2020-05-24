package org.example.game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.example.data.Card;
import org.example.data.GameResult;
import org.example.data.RankResult;
import org.example.game.PokerGameEvaluator;
import org.example.game.RankEvaluator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PokerGameEvaluatorTest {

    @InjectMocks
    private PokerGameEvaluator gameEvaluator;

    @Mock
    private RankEvaluator rankEvaluator;

    @Mock
    private Comparator<RankResult> comparator;

    @Mock
    private RankResult mockResultP1;

    @Mock
    private RankResult mockResultP2;

    @Test
    public void playCards_whenPlayer1HasBetterHand_returnWinnerOne() {
        List<Card> gameCards = getDummyCards(10);

        BigDecimal expectedProbabilityP1 = BigDecimal.valueOf(52.29);
        BigDecimal expectedProbabilityP2 = BigDecimal.valueOf(26.57);

        when(rankEvaluator.evaluate(gameCards.subList(0, 5))).thenReturn(mockResultP1);
        when(rankEvaluator.evaluate(gameCards.subList(5, 10))).thenReturn(mockResultP2);
        when(comparator.compare(mockResultP1, mockResultP2)).thenReturn(1);
        when(mockResultP1.calculateWinningProbability()).thenReturn(expectedProbabilityP1);
        when(mockResultP2.calculateWinningProbability()).thenReturn(expectedProbabilityP2);

        GameResult result = gameEvaluator.playCards(gameCards);
        assertThat(result.getWinner(), is(1));
        assertThat(result.getWinningProbabilityP1(), is(expectedProbabilityP1));
        assertThat(result.getWinningProbabilityP2(), is(expectedProbabilityP2));
    }

    @Test
    public void readLine_whenPlayer2HasBetterHand_returnWinnerTwo() {
        List<Card> gameCards = getDummyCards(10);

        BigDecimal expectedProbabilityP1 = BigDecimal.valueOf(52.29);
        BigDecimal expectedProbabilityP2 = BigDecimal.valueOf(26.57);

        when(rankEvaluator.evaluate(gameCards.subList(0, 5))).thenReturn(mockResultP1);
        when(rankEvaluator.evaluate(gameCards.subList(5, 10))).thenReturn(mockResultP2);
        when(comparator.compare(mockResultP1, mockResultP2)).thenReturn(-1);
        when(mockResultP1.calculateWinningProbability()).thenReturn(expectedProbabilityP1);
        when(mockResultP2.calculateWinningProbability()).thenReturn(expectedProbabilityP2);

        GameResult result = gameEvaluator.playCards(gameCards);
        assertThat(result.getWinner(), is(2));
        assertThat(result.getWinningProbabilityP1(), is(expectedProbabilityP1));
        assertThat(result.getWinningProbabilityP2(), is(expectedProbabilityP2));
    }

    @Test
    public void readLine_whenBothPlayersHaveSameValueHand_returnWinnerZero() {
        List<Card> gameCards = getDummyCards(10);

        BigDecimal expectedProbabilityP1 = BigDecimal.valueOf(52.29);
        BigDecimal expectedProbabilityP2 = BigDecimal.valueOf(26.57);

        when(rankEvaluator.evaluate(gameCards.subList(0, 5))).thenReturn(mockResultP1);
        when(rankEvaluator.evaluate(gameCards.subList(5, 10))).thenReturn(mockResultP2);
        when(comparator.compare(mockResultP1, mockResultP2)).thenReturn(0);
        when(mockResultP1.calculateWinningProbability()).thenReturn(expectedProbabilityP1);
        when(mockResultP2.calculateWinningProbability()).thenReturn(expectedProbabilityP2);

        GameResult result = gameEvaluator.playCards(gameCards);
        assertThat(result.getWinner(), is(0));
        assertThat(result.getWinningProbabilityP1(), is(expectedProbabilityP1));
        assertThat(result.getWinningProbabilityP2(), is(expectedProbabilityP2));
    }

    private List<Card> getDummyCards(int quantity) {
        List<Card> dummyCards = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            dummyCards.add(new Card());
        }
        return dummyCards;
    }
}