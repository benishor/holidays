package ro.scene.hq.holidays;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Adrian Scripca
 */
public class AddressBook {

    private final Map<String, Identity> identities = new LinkedHashMap<>();

    public void add(Identity identity) {
        identities.put(identity.email, identity);
    }

    public Identity byName(String email) {
        return identities.get(email);
    }
}
