package com.bosch.casestudy.ui.utility.report;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.bosch.casestudy.ui.utility.BrowserFactory.driver;
import static com.bosch.casestudy.ui.utility.report.Reporter.test;
import static com.bosch.casestudy.ui.utility.report.ScreenshotManager.takeScreenshot;

public class TestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.startTestReport(simpleClassName(result.getTestClass().getName()) + " -> " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, MarkupHelper.createLabel("<b>TEST PASSED: </b>" + result.getMethod().getMethodName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, MarkupHelper.createLabel("<b>TEST FAILED: <b>" + result.getMethod().getMethodName(), ExtentColor.RED));
        String screenshotPath = takeScreenshot(driver, result.getName()+"_"+getTimeStamp());
        test.fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip(MarkupHelper.createLabel("<b>TEST SKIPPED: <b>" + result.getMethod().getMethodName(), ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    private static String simpleClassName(String className) {

        String[] parts = className.split("\\.");
        className = parts[parts.length - 1];
        return className;
    }

    private static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(new Date());
    }
}
