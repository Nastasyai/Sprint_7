package order;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParametrizationCreateOrder {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] colour;
    private static final String[] BLACK = {"BLACK"};
    private static final String[] GRAY = {"GRAY"};
    private static final String[] BLACK_AND_GRAY = {"BLACK", "GRAY"};
    private static final String[] UNDEFINED_COLOUR = {};

    public ParametrizationCreateOrder(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] colour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] dataOrder() {
        return new Object[][]{
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", BLACK},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", GRAY},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", BLACK_AND_GRAY},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", UNDEFINED_COLOUR},
        };
    }
    private final OrderClient client = new OrderClient();
    private final OrderResponse check = new OrderResponse();
    OrderClient orderClient;
    OrderResponse orderResponse;

    @Test
    //@DisplayName("Параметризованный тест для создания заказа")
    public void parametrizationCreateOrderTest() {
        var order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, colour);
        ValidatableResponse response = client.createOrder(order);
        check.assertCreatedOrder(response);
    }
}
