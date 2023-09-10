package com.bosch.casestudy.ui.utility;

import com.bosch.casestudy.ui.utility.report.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public static void beforeSuite() { Reporter.startReportInstance();}

    @AfterSuite
    public static void afterSuite() {
        Reporter.endReport();
    }
}
