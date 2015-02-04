package ro.scene.hq.holidays;

import java.time.LocalDate;

public class Launcher {

    private static DeliveryService deliveryService;

    public static void main(String... args) {
        HolidayRequest request = new HolidayRequest()
                                     .fromEmployee(new Identity("benishor@gmail.com", "Adrian Scripca"))
                                     .toManager(new Identity("boss@iquestgroup.com", "Dilbert Boss"))
                                     .startingOn(LocalDate.of(2014, 11, 24))
                                     .lastingForDays(5);
        request.submit(getDeliveryService());
    }

    public static void approveRequest(String id) {
        HolidayRequest request = Holidays.getById(id);
        if (request != null) {
            request.accept(getDeliveryService());
        }
    }

    private static DeliveryService getDeliveryService() {
        if (deliveryService == null) {
            deliveryService = new DeliveryServiceConsole();
        }
        return deliveryService;
    }
}