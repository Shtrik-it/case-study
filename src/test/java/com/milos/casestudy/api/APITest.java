package com.milos.casestudy.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class APITest {

    private final String apiKey = "1a4c7f90d0842c251548010ce65d1869";
    private final String city = "Belgrade";

    @BeforeClass
    public void setup() {
        baseURI = "https://api.openweathermap.org/data/2.5/weather";
    }

    @Test
    public void testAuthorizedAccess() {
        Response response = given()
                .param("q", city)
                .param("appid", apiKey)
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testUnauthorizedAccess() {
        Response response = given()
                .param("q", city)
                .get();

        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertTrue(response.jsonPath().get("message").toString().contains("Invalid API key"));
    }

    @Test
    public void testValidCoordinates() {
        Response response = given()
                .param("lat", 44.804)
                .param("lon", 20.4651)
                .param("appid", apiKey)
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("name"), city);
    }

    @Test
    public void testInvalidCoordinates() {
        Response response = given()
                .param("lat", "9999")
                .param("lon", "9999")
                .param("appid", apiKey)
                .get();

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("message"), "wrong latitude");
    }

    @Test
    public void testInvalidCityNames() {
        Response response = given()
                .param("q", "NonExistentCity")
                .param("appid", apiKey)
                .get();

        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("message"),"city not found");
    }

    @DataProvider(name = "units")
    public Object[][] units() {
        return new Object[][] {{"metric"},{"imperial"}};
    }

    @Test(dataProvider = "units")
    public void testSupportedUnits(String unit) {
        Response response = given()
                .param("q", city)
                .param("appid", apiKey)
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
        float kelvinUnitValue =   response.jsonPath().get("main.temp");

        response = given()
                .param("q", city)
                .param("appid", apiKey)
                .param("units", unit)
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
        float otherUnitValue = response.jsonPath().get("main.temp");

        Assert.assertTrue(kelvinUnitValue != otherUnitValue);
    }

    @Test
    public void testLanguageSupport() {
        Response response = given()
                .param("q", city)
                .param("appid", apiKey)
                .param("lang", "sr")
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("sys.country"), "RS");
    }

    @Test
    public void testModeXmlSupport() {
        Response response = given()
                .param("q", city)
                .param("appid", apiKey)
                .param("mode", "xml")
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getContentType().contains("application/xml"));
    }
}