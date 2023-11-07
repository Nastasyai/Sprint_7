package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class listOrders {
    private final OrderResponse check = new OrderResponse();
    OrderClient orderClient;
    OrderResponse orderResponse;
    private final OrderClient client = new OrderClient();

    @Test
    @DisplayName("Получение списка заказов")
        public void getOrderList() {
        ValidatableResponse response = client.getOrderList();
        check.statusCodeIsOk(response);
        }

    @Test
    //@DisplayName("Создание заказа")
    public void orderCreate() {
        var order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new String[]{"BLACK"});
        ValidatableResponse response = client.createOrder(order);
        check.assertCreatedOrder(response);
    }
}
