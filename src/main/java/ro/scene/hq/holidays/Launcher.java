package ro.scene.hq.holidays;

import java.time.LocalDate;

public class Launcher {

    public static void main(String... args) {
        initializeServices();

        ServiceLocator.getHolidayRepository().printAll();

//        approveRequest("67fd0505-a4bb-4244-a945-0b7b1f6abaf1");
//
//        ServiceLocator.getHolidayRepository().printAll();
    }

    private static void initializeServices() {
        HolidayRepository holidayRepository = new HolidayRepository("requests.data");
        ServiceLocator.setHolidayRepository(holidayRepository);

        DeliveryService deliveryService = new DeliveryServiceConsole();
        ServiceLocator.setDeliveryService(deliveryService);
    }

    private static void addRequest() {
        HolidayRequest request = new HolidayRequest()
                .fromEmployee(new Identity("benishor@gmail.com", "Adrian Scripca"))
                .toManager(new Identity("boss@iquestgroup.com", "Dilbert Boss"))
                .startingOn(LocalDate.of(2014, 11, 24))
                .lastingForDays(5);
        request.submit(ServiceLocator.getDeliveryService());
    }

    public static void approveRequest(String id) {
        HolidayRequest request = Holidays.getById(id);
        if (request == null)
            throw new IllegalStateException("Cannot find request with id " + id);

        request.accept(ServiceLocator.getDeliveryService());
    }
}