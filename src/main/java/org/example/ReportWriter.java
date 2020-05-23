package org.example;

import java.io.File;

public interface ReportWriter {

    void writeReport(ReportObject reportObject, File outputFile);

}
