package org.example.game;

import org.example.data.Card;
import org.example.data.GameResult;
import org.example.data.RankResult;

import java.util.Comparator;
import java.util.List;

public class PokerGameEvaluator {

    private RankEvaluator rankEvaluator;
    private Comparator<RankResult> comparator;

    public PokerGameEvaluator(RankEvaluator rankEvaluator, Comparator<RankResult> comparator) {
        this.rankEvaluator = rankEvaluator;
        this.comparator = comparator;
    }

    public GameResult playCards(List<Card> gameCards) {
        RankResult player1 = rankEvaluator.evaluate(gameCards.subList(0, 5));
        RankResult player2 = rankEvaluator.evaluate(gameCards.subList(5, 10));
        int result = comparator.compare(player1, player2);
        return new GameResult(player1.calculateWinningProbability(), player2.calculateWinningProbability(), result == 1 ? 1 : (result == 0 ? 0 : 2));
    }

}
