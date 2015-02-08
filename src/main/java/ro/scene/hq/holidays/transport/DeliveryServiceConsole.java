package ro.scene.hq.holidays.transport;

import ro.scene.hq.holidays.DeliveryService;

import java.util.Collection;

public class DeliveryServiceConsole implements DeliveryService {

    @Override
    public void deliver(String from, String to, String subject, String body, Collection<String> cc) {
        System.out.println("-------------------------------");
        System.out.println("Sending message");
        System.out.println("-------------------------------");
        System.out.println("From: " + from);
        System.out.println("To: " + to);

        for (String email : cc) {
            System.out.println("Cc: " + email);
        }

        System.out.println("Subject: " + subject);
        System.out.println("---");
        System.out.println(body);
    }
}
