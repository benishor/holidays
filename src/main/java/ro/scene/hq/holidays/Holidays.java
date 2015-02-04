package ro.scene.hq.holidays;

/**
 * @author Adrian Scripca
 */
public class Holidays {

    private static HolidayRepository repository;

    private static HolidayRepository getRepository() {
        if (repository == null) {
            repository = new HolidayRepository();
        }
        return repository;
    }

    public static void save(HolidayRequest request) {
        .save(request);
    }

    public static HolidayRequest getById(String id) {
        return repository.getById(id);
    }
}
