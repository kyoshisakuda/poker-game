package org.example.io;

import org.example.data.Card;
import org.example.data.GameResult;
import org.example.data.ReportObject;
import org.example.data.mapper.CardMapper;
import org.example.game.PokerGameEvaluator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerDataFileReader {

    private static final String DELIMITER = " ";

    private PokerGameEvaluator pokerGame;

    public PokerDataFileReader(PokerGameEvaluator pokerGame) {
        this.pokerGame = pokerGame;
    }

    public ReportObject readFile(File file) {
        System.out.println("Reading from input file: " + file.toString());
        ReportObject reportObject = new ReportObject();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Map<Integer, Integer> summary = initSummaryMap();
            List<GameResult> gameResults = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                List<Card> cards = mapLineToCards(line);
                GameResult result = pokerGame.playCards(cards);
                summary.put(result.getWinner(), summary.get(result.getWinner()) + 1);
                gameResults.add(result);
                line = reader.readLine();
            }
            reportObject.setDraw(summary.get(0));
            reportObject.setP1Wins(summary.get(1));
            reportObject.setP2Wins(summary.get(2));
            reportObject.setGameResults(gameResults);
        } catch (FileNotFoundException e) {
            System.out.println("Error locating the given file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong when reading the file: " + e.getMessage());
        }
        return reportObject;
    }

    private Map<Integer, Integer> initSummaryMap() {
        Map<Integer, Integer> summary = new HashMap<>();
        summary.put(0, 0);
        summary.put(1, 0);
        summary.put(2, 0);
        return summary;
    }

    private List<Card> mapLineToCards(String line) {
        return Stream.of(line.split(DELIMITER))
                .map(CardMapper::fromString)
                .collect(Collectors.toList());
    }

}
