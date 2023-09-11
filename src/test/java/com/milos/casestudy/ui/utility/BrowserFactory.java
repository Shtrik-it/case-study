package com.milos.casestudy.ui.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BrowserFactory {

    public static WebDriver driver;
    private static final String browserChoice = Reader.readProperty("config.properties","BROWSER_CHOICE");
    private static final Boolean headlessMode = Boolean.parseBoolean(Reader.readProperty("config.properties", "HEADLESS_MODE"));

    public static WebDriver startBrowser (String url) {
        driver = chooseBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    private static WebDriver chooseBrowser() {
        if (BrowserFactory.browserChoice == null) {
            System.out.println("WebDriver choice not specified in the property file.");
        } else switch (BrowserFactory.browserChoice.toLowerCase()) {
            case "chrome" -> driver = new ChromeDriver(new ChromeOptions().addArguments(isHeadless()));
            case "firefox" -> driver = new FirefoxDriver(new FirefoxOptions().addArguments(isHeadless()));
        }
        return driver;
    }

    private static String isHeadless() {
        return BrowserFactory.headlessMode ? "--headless" : "--no-headless";
    }
}
