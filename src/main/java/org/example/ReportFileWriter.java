package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ReportFileWriter {

    public void writeReport(ReportObject reportObject, Path outputDir, String fileName) {
        File outputFile = outputDir.resolve(fileName).toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writeLine(writer, "1: " + reportObject.getP1Wins());
            writeLine(writer, "2: " + reportObject.getP2Wins());
            writeLine(writer, "3: " + reportObject.getDraw());
            writeLine(writer, "4: ");
            writeLine(writer, "------Player 1------|------Player 2------");
            for (HandResult handResult : reportObject.getHandResults()) {
                String percentLine = String.format("       %s%%       |       %s%%       ",
                        handResult.getWinningProbabilityP1().setScale(2).toPlainString(), handResult.getWinningProbabilityP2().setScale(2).toPlainString());
                writeLine(writer, percentLine);
            }
        } catch (IOException e) {
            System.out.println("Error trying to write the output file" + e.getMessage());
        }
    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

}
