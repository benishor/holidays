package ro.scene.hq.holidays;

public class ServiceLocator {

    private static HolidayRepository holidayRepository;

    private static DeliveryService deliveryService;

    public static HolidayRepository getHolidayRepository() {
        return holidayRepository;
    }

    public static void setHolidayRepository(HolidayRepository holidayRepository) {
        ServiceLocator.holidayRepository = holidayRepository;
    }

    public static DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public static void setDeliveryService(DeliveryService deliveryService) {
        ServiceLocator.deliveryService = deliveryService;
    }
}
