package ro.scene.hq.holidays;

public class Holidays {

    public static HolidayRequest getById(String id) {
        return ServiceLocator.getHolidayRequestRepository().getById(id);
    }
}
