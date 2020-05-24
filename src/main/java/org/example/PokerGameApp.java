package org.example;

import static java.util.Objects.isNull;

import org.example.data.ReportObject;
import org.example.game.PlayerComparator;
import org.example.game.PokerGameEvaluator;
import org.example.game.RankEvaluator;
import org.example.io.PokerDataFileReader;
import org.example.io.ReportFileWriter;
import org.example.io.ReportWriter;

import java.io.File;

public class PokerGameApp {

    private static PokerGameApp instance;

    private PokerDataFileReader pokerDataFileReader;
    private ReportWriter reportWriter;

    private PokerGameApp(PokerDataFileReader pokerDataFileReader, ReportWriter reportWriter) {
        this.pokerDataFileReader = pokerDataFileReader;
        this.reportWriter = reportWriter;
    }

    public static PokerGameApp getInstance() {
        if (isNull(instance)) {
            PokerDataFileReader pokerDataFileReader = new PokerDataFileReader(new PokerGameEvaluator(new RankEvaluator(), new PlayerComparator()));
            ReportWriter reportWriter = new ReportFileWriter();
            System.out.println("Initializing Poker Game App...");
            instance = new PokerGameApp(pokerDataFileReader, reportWriter);
        }
        return instance;
    }

    public File getInputFileFromResource() {
        return new File(getClass().getClassLoader().getResource("pokerdata.txt").getFile());
    }

    public PokerDataFileReader getPokerDataFileReader() {
        return pokerDataFileReader;
    }

    public ReportWriter getReportWriter() {
        return reportWriter;
    }

    public static void main(String[] args) {
        PokerGameApp app = PokerGameApp.getInstance();
        File inputFile = app.getInputFileFromResource();
        File outputFile = new File(System.getProperty("user.home") + "/pokerdata-result.txt");

        ReportObject report = app.getPokerDataFileReader().readFile(inputFile);
        app.getReportWriter().writeReport(report, outputFile);
        System.out.println("---- Finish ----");
    }

}
