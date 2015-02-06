package ro.scene.hq.holidays;

public class ServiceLocator {

    private static HolidayRequestRepository holidayRequestRepository;

    private static DeliveryService deliveryService;

    public static HolidayRequestRepository getHolidayRequestRepository() {
        return holidayRequestRepository;
    }

    public static void setHolidayRequestRepository(HolidayRequestRepository holidayRequestRepository) {
        ServiceLocator.holidayRequestRepository = holidayRequestRepository;
    }

    public static DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public static void setDeliveryService(DeliveryService deliveryService) {
        ServiceLocator.deliveryService = deliveryService;
    }
}
