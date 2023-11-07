package courier;

import config.ConfigurationApi;
import io.restassured.response.ValidatableResponse;

import java.util.Map;


public class CourierClient extends ConfigurationApi {
    public static final String COURIER_PATH = "/api/v1/courier";

    public ValidatableResponse create(CreateCourier courierRequest) {
        return getRequestSpec()
                .body(courierRequest)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    public ValidatableResponse login (LoginCourier courierRequest) {
        return getRequestSpec()
                .body(courierRequest)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    public ValidatableResponse loginNotAllBody(Map login) {
        return getRequestSpec()
                .body(login)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }
    }



