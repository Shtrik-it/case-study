package com.bosch.casestudy.ui.pages;

import com.bosch.casestudy.ui.utility.Helper;
import com.bosch.casestudy.ui.utility.report.Reporter;
import com.google.common.collect.Comparators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchResultPage {

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver driver;

    public static final String SORT_OPTION_POPULAR = "Popularnije";

    @FindBy(css = "button[aria-label='Sortiraj']")
    private WebElement sortButton;

    @FindBy(xpath = "//div[contains(@class, 'BreadcrumbHolder')]//a")
    private List<WebElement> productListBreadcrumbs;

    @FindBy(xpath = "//div[contains(@class, 'AdItem_viewAndFavorite')]/div[1]/span")
    private List<WebElement> viewCountField;

    public SearchResultPage sortResultsBy(String sortOption) {
        sortButton.click();
        WebElement sortDropdownOption = driver.findElement(By.cssSelector("button[aria-label='" + sortOption + "']"));
        sortDropdownOption.click();
        Reporter.info("Search results sorted by '"+ sortOption +"' sorting option");
        return this;
    }

    public boolean isSortedByPopular() {
        ArrayList<String> viewCountStringList = Helper.getStringArray(viewCountField);
        ArrayList<Integer> viewCountIntegerList = Helper.convertToIntegerArrayList(viewCountStringList);
        return Comparators.isInOrder(viewCountIntegerList, Comparator.reverseOrder());
    }

    public boolean isInCorrectCategory(String categoryName) {
        ArrayList<String> breadCrumbTextList = Helper.getStringArray(productListBreadcrumbs);
        return breadCrumbTextList.contains(categoryName);
    }
}
