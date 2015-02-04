package ro.scene.hq.holidays;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Adrian Scripca
 */
public class HolidayRepository {

    private final Map<String, HolidayRequest> requests = new LinkedHashMap<>();

    public void save(HolidayRequest request) {
        synchronized (requests) {
            if (request.getId() == null) {
                do {
                    request.setId(java.util.UUID.randomUUID().toString());
                } while (requests.containsKey(request.getId()));
            }
            requests.put(request.getId(), request);
        }
    }

    public HolidayRequest getById(String id) {
        return requests.get(id);
    }
}
