package org.example.io;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.example.data.GameResult;
import org.example.data.ReportObject;
import org.example.io.ReportFileWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

public class ReportFileWriterTest {

    private ReportFileWriter reportFileWriter;

    @Before
    public void setUp() {
        reportFileWriter = new ReportFileWriter();
    }

    @Test
    public void writeReport_thenReportFileCreated() {
        ReportObject reportObject = new ReportObject();
        reportObject.setP1Wins(2);
        reportObject.setP2Wins(1);
        reportObject.setDraw(0);
        reportObject.setGameResults(Arrays.asList(
                new GameResult(BigDecimal.valueOf(51.1), BigDecimal.valueOf(75.68), 2),
                new GameResult(BigDecimal.valueOf(47.90), BigDecimal.valueOf(28.35), 1),
                new GameResult(BigDecimal.valueOf(32.47), BigDecimal.valueOf(58.74), 2)
        ));

        Path outputDir = null;
        try {
            outputDir = Files.createTempDirectory("report_output-" + System.nanoTime());
            File outputFile = outputDir.resolve("test-report.txt").toFile();
            reportFileWriter.writeReport(reportObject, outputFile);
            assertCorrectFile(reportObject, outputFile);
            assertThat(outputFile.exists(), is(true));
        } catch (IOException e) {
            System.out.println("Error writing in temp file: " + e.getMessage());
        } finally {
            deleteDir(outputDir);
        }
    }

    private void assertCorrectFile(ReportObject reportObject, File generatedFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(generatedFile))) {
            assertThat(reader.readLine(), containsString("1: " + reportObject.getP1Wins()));
            assertThat(reader.readLine(), containsString("2: " + reportObject.getP2Wins()));
            assertThat(reader.readLine(), containsString("3: " + reportObject.getDraw()));
            assertThat(reader.readLine(), containsString("4:"));
            assertThat(reader.readLine(), containsString("------Player 1------|------Player 2------"));
            for (GameResult gameResult : reportObject.getGameResults()) {
                assertThat(reader.readLine(), containsString(String.format("       %s%%       |       %s%%       ",
                        gameResult.getWinningProbabilityP1().setScale(2).toPlainString(), gameResult.getWinningProbabilityP2().setScale(2).toPlainString())));
            }
            assertThat(reader.readLine(), isEmptyOrNullString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteDir(Path dir) {
        try {
            if (dir != null && dir.toFile().exists()) {
                Files.walk(dir).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            }
        } catch (IOException e) {
            System.out.println("Error trying to delete directory: " + e.getMessage());
        }
    }
}
