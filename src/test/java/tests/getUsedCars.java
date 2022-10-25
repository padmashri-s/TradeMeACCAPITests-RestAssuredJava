package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class getUsedCars {

    static String _baseURL = "https://api.trademe.co.nz/v1/";

    @Test
    public void getNamedUsedCarMakes_Count_ContentType_InJSON()
    {
        RestAssured.baseURI= _baseURL;
        RestAssured.defaultParser = Parser.JSON;

        given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .when().get("Categories/UsedCars.JSON").
                then().
                statusCode(200).
                assertThat().body("Subcategories.Name.size", equalTo(78));
    }

    @Test
    public void getNamedUsedCarMakes_Count_ContentType_InXML()
    {
        RestAssured.baseURI= _baseURL;
        RestAssured.defaultParser = Parser.XML;
        var response = given().headers("Content-Type", ContentType.XML, "Accept", ContentType.XML)
                .when().get("Categories/UsedCars.XML").
                then().
                assertThat().statusCode(200).
                body("Category.Subcategories.Category", Matchers.iterableWithSize(78));
    }

}
