package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PokerDataLineReader {

    private static final String SEPARATOR = " ";

    private RankEvaluator rankEvaluator;
    private Comparator<RankResult> comparator;

    public PokerDataLineReader(RankEvaluator rankEvaluator) {
        this.rankEvaluator = rankEvaluator;
        this.comparator = new PlayerComparator();
    }

    public HandResult readLine(String line) {
        List<String> cards = Arrays.asList(line.split(SEPARATOR));
        RankResult player1 = rankEvaluator.evaluate(cards.subList(0, 5));
        RankResult player2 = rankEvaluator.evaluate(cards.subList(5, 10));
        int result = comparator.compare(player1, player2);
        return new HandResult(player1.calculateWinningProbability(), player2.calculateWinningProbability(), result == 1 ? 1 : (result == 0 ? 0 : 2));
    }

}
