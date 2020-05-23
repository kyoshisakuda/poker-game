package org.example;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PokerDataFileReaderTest {

    private PokerDataFileReader fileReader;

    @Before
    public void setUp() {
        PokerDataLineReader lineReader = new PokerDataLineReader(new RankEvaluator());
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
        List<HandResult> handResults = report.getHandResults();
        assertThat(handResults, hasSize(5));
        assertThat(handResults.get(0).getWinner(), is(2));
        assertThat(handResults.get(1).getWinner(), is(1));
        assertThat(handResults.get(2).getWinner(), is(1));
        assertThat(handResults.get(3).getWinner(), is(2));
        assertThat(handResults.get(4).getWinner(), is(2));
    }

}