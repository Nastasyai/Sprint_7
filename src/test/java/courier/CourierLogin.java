package courier;

import helper.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CourierLogin {
    private final CourierClient client = new CourierClient();
    private final CourierApiAnswer check = new CourierApiAnswer();
    private int courierId;

    @Test
    @DisplayName("Успешная авторизация курьера")
    public void courierLogin() {
        var courier = CourierGenerator.random();
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
        LoginCourier loginCourier = LoginCourier.from(courier);
        ValidatableResponse loginResponse = client.login(loginCourier);
        courierId = check.loggedIsSuccessfully(loginResponse);
        check.checkLoggedIdNotNull(loginResponse);
    }

    @Test
    @DisplayName("Некорректный логин курьера")
    public void courierLoginLogin404() {
        var courier = CourierGenerator.random();
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
        LoginCourier loginCourier = LoginCourier.from(courier);
        ValidatableResponse loginResponseSuccessfully = client.login(loginCourier);
        courierId = check.loggedIsSuccessfully(loginResponseSuccessfully);
        Map<String, String> logData = new HashMap<>();
        logData.put("login", "0000");
        logData.put("password", courier.getPassword());
        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully404(loginResponse);
    }

    @Test
    @DisplayName("Некорректный пароль курьера")
    public void courierLoginPassword404() {
        var courier = CourierGenerator.random();
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
        LoginCourier loginCourier = LoginCourier.from(courier);
        ValidatableResponse loginResponseSuccessfully = client.login(loginCourier);
        courierId = check.loggedIsSuccessfully(loginResponseSuccessfully);
        Map<String, String> logData = new HashMap<>();
        logData.put("login", courier.getLogin());
        logData.put("password", courier.getPassword() + "0000");
        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully404(loginResponse);
    }
}
