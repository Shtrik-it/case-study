package com.bosch.casestudy.ui.utility.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bosch.casestudy.ui.utility.Helper;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Reporter {

    static ExtentReports extent;
    static ExtentSparkReporter reporter;
    static ExtentTest test;

    private static final String reportUri = System.getProperty("user.dir") + "/reports/report.html";

    public static void startReportInstance() {
        extent = new ExtentReports();
        reporter = new ExtentSparkReporter(reportUri);
        stylizeReport(reporter);
        extent.attachReporter(reporter);
    }

    public static void startTestReport(String testName) {
        test = extent.createTest(testName);
    }

    public static void endReport() {
        extent.flush();
        displayReportInBrowser();
    }

    public static void info(String details)
    {
        StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        String simpleClassName = e.getClassName().substring(e.getClassName().lastIndexOf('.') + 1);
        test.info(simpleClassName+" > "+e.getMethodName()+":  "+details);
    }

    private static void stylizeReport(ExtentSparkReporter sparkReporter) {
        sparkReporter.config().setOfflineMode(true);
        sparkReporter.config().setDocumentTitle("Bosch - Case Study");
        sparkReporter.config().setReportName("BOSCH - Case Study");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");
        sparkReporter.config().setTheme(Theme.DARK);
    }

    private static void displayReportInBrowser() {
        try {
            Helper.pauseFor(2); //The HTML report page needs time to render properly
            Desktop.getDesktop().browse(new File(reportUri).toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}