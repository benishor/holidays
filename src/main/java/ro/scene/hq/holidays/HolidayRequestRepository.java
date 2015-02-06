package ro.scene.hq.holidays;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Adrian Scripca
 */
public class HolidayRequestRepository {

    private final Map<String, HolidayRequest> requests = new LinkedHashMap<>();

    private final String filename;

    public HolidayRequestRepository(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    public void save(HolidayRequest request) {
        synchronized (requests) {
            if (request.getId() == null) {
                do {
                    request.setId(java.util.UUID.randomUUID().toString());
                } while (requests.containsKey(request.getId()));
            }
            requests.put(request.getId(), request);

            saveToDisk();
        }
    }

    public HolidayRequest getById(String id) {
        return requests.get(id);
    }

    public void printAll() {
        for (HolidayRequest request : requests.values())
            request.print();
    }

    @SuppressWarnings("unchecked")
    private void loadFromDisk() {
        try (
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            requests.clear();
            requests.putAll((Map<String, HolidayRequest>) ois.readObject());

            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load holidays from disk. Reason: " + e.getMessage());
        }
    }

    private void saveToDisk() {
        try (
                FileOutputStream fout = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fout)
        ) {
            out.writeObject(requests);
            out.close();
            fout.close();
        } catch (IOException e) {
            System.err.println("Failed to save holidays to disk. Reason: " + e.getMessage());
        }
    }
}
