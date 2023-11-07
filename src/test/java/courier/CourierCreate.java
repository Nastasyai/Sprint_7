package courier;

import helper.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CourierCreate {
    private final CourierClient client = new CourierClient();
    private final CourierApiAnswer check = new CourierApiAnswer();
    private int courierId;

    @Test
    public void courierCreate() {
        var courier = CourierGenerator.random();
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
        LoginCourier loginCourier = LoginCourier.from(courier);
        ValidatableResponse loginResponse = client.login(loginCourier);
        courierId = check.loggedIsSuccessfully(loginResponse);
        check.checkLoggedIdNotNull(loginResponse);
    }

    @Test
    @DisplayName("Создание курьера")
    public void courierNotCreation(){
        var courier = CourierGenerator.random();
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
        LoginCourier loginCourier = LoginCourier.from(courier);
        ValidatableResponse loginResponseSuccessfully = client.login(loginCourier);
        courierId = check.loggedIsSuccessfully(loginResponseSuccessfully);
        ValidatableResponse responseNew = client.create(courier);
        check.createdNotSuccessfully(responseNew);
    }
}
