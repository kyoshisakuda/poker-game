package org.example;

import java.io.File;
import java.util.Objects;

public class PokerGameApp {

    private static PokerGameApp instance;

    private PokerDataFileReader pokerDataFileReader;
    private ReportWriter reportWriter;

    private PokerGameApp(PokerDataFileReader pokerDataFileReader, ReportWriter reportWriter) {
        this.pokerDataFileReader = pokerDataFileReader;
        this.reportWriter = reportWriter;
    }

    public static PokerGameApp getInstance() {
        if (Objects.isNull(instance)) {
            PokerDataFileReader pokerDataFileReader = new PokerDataFileReader(new PokerDataLineReader(new RankEvaluator()));
            ReportWriter reportWriter = new ReportFileWriter();
            System.out.println("Initializing Poker Game App...");
            instance = new PokerGameApp(pokerDataFileReader, reportWriter);
        }
        return instance;
    }

    public PokerDataFileReader getPokerDataFileReader() {
        return pokerDataFileReader;
    }

    public ReportWriter getReportWriter() {
        return reportWriter;
    }

    public static void main(String[] args) {
        PokerGameApp app = PokerGameApp.getInstance();
        File inputFile = new File(System.getProperty("user.home") + "/pokerdata.txt");
        File outputFile = new File(System.getProperty("user.home") + "/pokerdata-result.txt");

        ReportObject report = app.getPokerDataFileReader().readFile(inputFile);
        app.getReportWriter().writeReport(report, outputFile);
        System.out.println("---- Finish ----");
    }

}
