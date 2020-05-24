package org.example.io;

import org.example.data.GameResult;
import org.example.data.ReportObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ReportFileWriter implements ReportWriter {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void writeReport(ReportObject reportObject, File outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            logger.info("Writing to output file: " + outputFile.toString());
            writeLine(writer, "1: " + reportObject.getP1Wins());
            writeLine(writer, "2: " + reportObject.getP2Wins());
            writeLine(writer, "3: " + reportObject.getDraw());
            writeLine(writer, "4: ");
            writeLine(writer, "------Player 1------|------Player 2------");
            for (GameResult gameResult : reportObject.getGameResults()) {
                String percentLine = String.format("       %s%%       |       %s%%       ",
                        gameResult.getWinningProbabilityP1().setScale(2).toPlainString(), gameResult.getWinningProbabilityP2().setScale(2).toPlainString());
                writeLine(writer, percentLine);
            }
        } catch (IOException e) {
            logger.error("Error trying to write the output file: {}", e.getMessage());
        }
    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

}
