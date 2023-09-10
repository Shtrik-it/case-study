package com.bosch.casestudy.ui.pages;

import com.bosch.casestudy.ui.utility.report.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver driver;

    @FindBy(id = "keywords")
    public WebElement searchField;

    public HomePage searchProductByName(String productName) {
        searchField.sendKeys(productName);
        Reporter.info("Searched for '"+ productName +"'");
        return this;
    }
    public HomePage chooseSearchSuggestionCategoryByName(String categoryName) {
        WebElement searchSuggestionItem = driver.findElement(By.xpath("//span[normalize-space()='"+ categoryName +"']"));
        searchSuggestionItem.click();
        Reporter.info("Category '"+ categoryName +"' has been clicked");
        return this;
    }
}
