package org.example;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PokerDataLineReaderTest {

    private PokerDataLineReader lineReader;

    @Before
    public void setUp() {
        lineReader = new PokerDataLineReader(new RankEvaluator());
    }

    @Test
    public void readLine_whenPlayer1HasBetterHand_returnWinnerOne() {
        String line = "8C TS AC 9H 4S 7D 2S 5D 3S KC";
        HandResult result = lineReader.readLine(line);
        assertThat(result.getWinner(), is(1));
    }

    @Test
    public void readLine_whenPlayer2HasBetterHand_returnWinnerTwo() {
        String line = "2D 9C AS AH AC 3D 6D 7D TD QD";
        HandResult result = lineReader.readLine(line);
        assertThat(result.getWinner(), is(2));
    }

    @Test
    public void readLine_whenBothPlayersHaveSameValueHand_returnWinnerZero() {
        String line = "7C 2S 5S 3S AH 7D 2S 5D 3S AC";
        HandResult result = lineReader.readLine(line);
        assertThat(result.getWinner(), is(0));
    }
}