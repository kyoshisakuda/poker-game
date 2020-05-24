package org.example.io;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.example.data.GameResult;
import org.example.data.ReportObject;
import org.example.game.PlayerComparator;
import org.example.game.PokerGameEvaluator;
import org.example.game.RankEvaluator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class PokerDataFileReaderTest {

    private PokerDataFileReader fileReader;

    @Before
    public void setUp() {
        PokerGameEvaluator lineReader = new PokerGameEvaluator(new RankEvaluator(), new PlayerComparator());
        fileReader = new PokerDataFileReader(lineReader);
    }

    @Test
    public void readFile_returnReportWithWinsSummary() {
        File testFile = new File(getClass().getClassLoader().getResource("pokerdata-test.txt").getFile());
        ReportObject report = fileReader.readFile(testFile);
        assertThat(report.getP1Wins(), is(2));
        assertThat(report.getP2Wins(), is(3));
        assertThat(report.getDraw(), is(0));
    }

    @Test
    public void testReadFile_returnReportWithListOfResults() {
        File testFile = new File(getClass().getClassLoader().getResource("pokerdata-test.txt").getFile());
        ReportObject report = fileReader.readFile(testFile);
        List<GameResult> gameResults = report.getGameResults();
        assertThat(gameResults, hasSize(5));
        assertThat(gameResults.get(0).getWinner(), is(2));
        assertThat(gameResults.get(1).getWinner(), is(1));
        assertThat(gameResults.get(2).getWinner(), is(1));
        assertThat(gameResults.get(3).getWinner(), is(2));
        assertThat(gameResults.get(4).getWinner(), is(2));
    }

}