package org.example.io;

import org.example.data.ReportObject;

import java.io.File;

public interface ReportWriter {

    void writeReport(ReportObject reportObject, File outputFile);

}
