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
    public void readLine_whenPlayer1HasBetterHand_returnOne() {
        String line = "8C TS KC 9H 4S 7D 2S 5D 3S AC";
        int winner = lineReader.readLine(line);
        assertThat(winner, is(2));
    }

    @Test
    public void readLine_whenPlayer2HasBetterHand_returnTwo() {
        String line = "2D 9C AS AH AC 3D 6D 7D TD QD";
        int winner = lineReader.readLine(line);
        assertThat(winner, is(2));
    }

    @Test
    public void readLine_whenBothPlayersHaveSameValueHand_return0() {
        String line = "7C 2S 5S 3S AH 7D 2S 5D 3S AC";
        int winner = lineReader.readLine(line);
        assertThat(winner, is(0));
    }
}