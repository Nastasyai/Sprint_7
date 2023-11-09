package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ListOrders {
    private final OrderResponse check = new OrderResponse();
    Response orderResponse;
    private final OrderClient client = new OrderClient();
    Order order;

    @Test
    @DisplayName("Получение списка заказов")
        public void getOrderList() {
        ValidatableResponse response = client.getOrderList();
        check.statusCodeIsOk(response);
        orderResponse = response.extract().body().as(Response.class);
        assertEquals("The status code is invalid", HTTP_OK);
        assertNotNull("The list of orders is not provided", order);
    }

    @Test
    //@DisplayName("Создание заказа")
    public void orderCreate() {
        var order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new String[]{"BLACK"});
        ValidatableResponse response = client.createOrder(order);
        check.assertCreatedOrder(response);
    }
}
