package ro.scene.hq.holidays;

import java.util.Collection;

public interface DeliveryService {

    void deliver(String from, String to, String subject, String body, Collection<String> cc);
}
