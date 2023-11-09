package config;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static order.Order.BASE_URL;

public class ConfigurationApi {
    public final static String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    public RequestSpecification getRequestSpec () {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);
    }
}