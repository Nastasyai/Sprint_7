package helper;

import courier.CreateCourier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static CreateCourier generic () {
        var CreateCourierRequest = new CreateCourier("", "1234", "saske");
        return CreateCourierRequest;
    }

    public static CreateCourier random() {
        return new CreateCourier(RandomStringUtils.randomAlphanumeric(5, 10), "1234", "saske");
    }
}
