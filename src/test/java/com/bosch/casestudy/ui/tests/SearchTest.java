package com.bosch.casestudy.ui.tests;

import com.bosch.casestudy.ui.pages.HomePage;
import com.bosch.casestudy.ui.pages.SearchResultPage;
import com.bosch.casestudy.ui.utility.BaseTest;
import com.bosch.casestudy.ui.utility.BrowserFactory;
import com.bosch.casestudy.ui.utility.report.TestNGListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class SearchTest extends BaseTest {

    WebDriver driver;
    HomePage homePage;
    SearchResultPage searchResultsPage;
    String productName = "Samsung";
    String categoryNameAndBrand = "Mobilni telefoni > Samsung";
    String categoryName = "Mobilni telefoni";


    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startBrowser("https://novi.kupujemprodajem.com/");
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultPage(driver);
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void productSortingTest() {
        homePage.searchProductByName(productName)
                .chooseSearchSuggestionCategoryByName(categoryNameAndBrand);
        searchResultsPage.sortResultsBy(SearchResultPage.SORT_OPTION_POPULAR);
        Assert.assertTrue(searchResultsPage.isSortedByPopular());
    }

    @Test
    public void productsAssignedToCorrectCategoryTest() {
        homePage.searchProductByName(productName)
                .chooseSearchSuggestionCategoryByName(categoryNameAndBrand);
        Assert.assertTrue(searchResultsPage.isInCorrectCategory(categoryName));
    }
}
