package org.example.game;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.example.data.Card;
import org.example.data.CardSuit;
import org.example.data.CardValue;
import org.example.data.GameResult;
import org.example.game.PlayerComparator;
import org.example.game.PokerGameEvaluator;
import org.example.game.RankEvaluator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PokerGameEvaluatorITest {

    private PokerGameEvaluator gameEvaluator;

    @Before
    public void setUp() {
        gameEvaluator = new PokerGameEvaluator(new RankEvaluator(), new PlayerComparator());
    }

    @Test
    public void readLine_whenPlayer1HasBetterHand_returnWinnerOne() {
        List<Card> gameCards = Arrays.asList(
                new Card(CardValue.EIGHT, CardSuit.CLUBS),
                new Card(CardValue.TEN, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.CLUBS),
                new Card(CardValue.NINE, CardSuit.HEARTS),
                new Card(CardValue.FOUR, CardSuit.SPADE),
                new Card(CardValue.SEVEN, CardSuit.DIAMOND),
                new Card(CardValue.TWO, CardSuit.SPADE),
                new Card(CardValue.FIVE, CardSuit.DIAMOND),
                new Card(CardValue.THREE, CardSuit.SPADE),
                new Card(CardValue.KING, CardSuit.CLUBS)
        );

        GameResult result = gameEvaluator.playCards(gameCards);
        assertThat(result.getWinner(), is(1));
    }

    @Test
    public void readLine_whenPlayer2HasBetterHand_returnWinnerTwo() {
        List<Card> gameCards = Arrays.asList(
                new Card(CardValue.TWO, CardSuit.DIAMOND),
                new Card(CardValue.NINE, CardSuit.CLUBS),
                new Card(CardValue.ACE, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.HEARTS),
                new Card(CardValue.ACE, CardSuit.CLUBS),
                new Card(CardValue.THREE, CardSuit.DIAMOND),
                new Card(CardValue.SIX, CardSuit.DIAMOND),
                new Card(CardValue.SEVEN, CardSuit.DIAMOND),
                new Card(CardValue.TEN, CardSuit.DIAMOND),
                new Card(CardValue.QUEEN, CardSuit.DIAMOND)
        );

        GameResult result = gameEvaluator.playCards(gameCards);
        assertThat(result.getWinner(), is(2));
    }

    @Test
    public void readLine_whenBothPlayersHaveSameValueHand_returnWinnerZero() {
        List<Card> gameCards = Arrays.asList(
                new Card(CardValue.SEVEN, CardSuit.CLUBS),
                new Card(CardValue.TWO, CardSuit.SPADE),
                new Card(CardValue.FIVE, CardSuit.SPADE),
                new Card(CardValue.THREE, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.HEARTS),
                new Card(CardValue.SEVEN, CardSuit.DIAMOND),
                new Card(CardValue.TWO, CardSuit.SPADE),
                new Card(CardValue.FIVE, CardSuit.DIAMOND),
                new Card(CardValue.THREE, CardSuit.SPADE),
                new Card(CardValue.ACE, CardSuit.CLUBS)
        );

        GameResult result = gameEvaluator.playCards(gameCards);
        assertThat(result.getWinner(), is(0));
    }
}