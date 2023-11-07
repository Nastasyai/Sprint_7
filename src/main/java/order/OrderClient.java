package order;

import config.ConfigurationApi;
import courier.CourierClient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static order.Order.BASE_URL;

public class OrderClient {
    public static final String ORDER_PATH = "/api/v1/orders";
    private final CourierClient client = new CourierClient();

    public ValidatableResponse createOrder(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(ConfigurationApi.BASE_URL)
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    public ValidatableResponse getOrderList () {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(ConfigurationApi.BASE_URL)
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }
}
