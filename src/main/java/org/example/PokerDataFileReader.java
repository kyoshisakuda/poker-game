package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerDataFileReader {

    private PokerDataLineReader lineReader;
    private Map<Integer, Integer> summary;

    public PokerDataFileReader(PokerDataLineReader lineReader) {
        this.lineReader = lineReader;
        this.summary = new HashMap<>();
        summary.put(0, 0);
        summary.put(1, 0);
        summary.put(2, 0);
    }

    public ReportObject readFile(File file) {
        System.out.println("Reading from input file: " + file.toString());
        ReportObject reportObject = new ReportObject();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<HandResult> handResults = new ArrayList<>();
            String currentLine = reader.readLine();
            while (currentLine != null) {
                HandResult result = lineReader.readLine(currentLine);
                summary.put(result.getWinner(), summary.get(result.getWinner()) + 1);
                currentLine = reader.readLine();
                handResults.add(result);
            }
            reportObject.setDraw(summary.get(0));
            reportObject.setP1Wins(summary.get(1));
            reportObject.setP2Wins(summary.get(2));
            reportObject.setHandResults(handResults);
        } catch (FileNotFoundException e) {
            System.out.println("Error locating the given file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong when reading the file: " + e.getMessage());
        }
        return reportObject;
    }

}
