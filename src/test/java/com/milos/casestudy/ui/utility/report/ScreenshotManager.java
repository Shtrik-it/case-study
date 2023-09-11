package com.milos.casestudy.ui.utility.report;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import static org.apache.commons.io.FileUtils.*;

public class ScreenshotManager {
    public static String takeScreenshot(WebDriver driver, String screenshotName)
    {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File imageBuffered = ts.getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + ".jpg";
            File destination = new File(dest);
            copyFile(imageBuffered, destination);
            return dest;
        } catch (IOException | WebDriverException e) {
            System.out.println("Exception fired while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }
}
