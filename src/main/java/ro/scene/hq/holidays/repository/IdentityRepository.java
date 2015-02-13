package ro.scene.hq.holidays.repository;

import ro.scene.hq.holidays.Identity;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class IdentityRepository {

    private final Map<String, Identity> identities = new LinkedHashMap<>();
    private final String filename;

    public IdentityRepository(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    public void save(Identity identity) {
        synchronized (identities) {
            if (identity.getId() == null) {
                if (getByEmail(identity.email) != null) {
                    throw new IllegalArgumentException("An identity with this email already exists.");
                }

                do {
                    identity.setId(UUID.randomUUID().toString());
                } while (identities.containsKey(identity.getId()));
            }
            identities.put(identity.getId(), identity);

            saveToDisk();
        }
    }

    public Identity getById(String id) {
        return identities.get(id);
    }

    public Identity getByEmail(String email) {
        for (Identity identity : identities.values()) {
            if (identity.email.equals(email)) {
                return identity;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void loadFromDisk() {
        try (
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            identities.clear();
            identities.putAll((Map<String, Identity>) ois.readObject());

            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load identities from disk. Reason: " + e.getMessage());
        }
    }

    private void saveToDisk() {
        try (
                FileOutputStream fout = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(fout)
        ) {
            out.writeObject(identities);
            out.close();
            fout.close();
        } catch (IOException e) {
            System.err.println("Failed to save identities to disk. Reason: " + e.getMessage());
        }
    }

    public void printAll() {
        identities.values().forEach(System.out::println);
    }
}
