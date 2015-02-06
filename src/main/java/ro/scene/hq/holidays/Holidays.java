package ro.scene.hq.holidays;

/**
 * @author Adrian Scripca
 */
public class Holidays {

    public static HolidayRequest getById(String id) {
        return ServiceLocator.getHolidayRepository().getById(id);
    }
}
