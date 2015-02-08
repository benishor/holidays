package ro.scene.hq.holidays;

import ro.scene.hq.holidays.repository.HolidayRequestRepository;
import ro.scene.hq.holidays.repository.IdentityRepository;

public class ServiceLocator {

    private static HolidayRequestRepository holidayRequestRepository;

    private static IdentityRepository identityRepository;

    private static DeliveryService deliveryService;

    public static HolidayRequestRepository getHolidayRequestRepository() {
        return holidayRequestRepository;
    }

    public static void setHolidayRequestRepository(HolidayRequestRepository holidayRequestRepository) {
        ServiceLocator.holidayRequestRepository = holidayRequestRepository;
    }

    public static IdentityRepository getIdentityRepository() {
        return identityRepository;
    }

    public static void setIdentityRepository(IdentityRepository identityRepository) {
        ServiceLocator.identityRepository = identityRepository;
    }

    public static DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public static void setDeliveryService(DeliveryService deliveryService) {
        ServiceLocator.deliveryService = deliveryService;
    }
}
