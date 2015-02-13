package ro.scene.hq.holidays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message {
    public Identity sender;
    public List<Identity> recipients;
    public String subject;
    public String body;

    public Message(Identity sender, Identity recipient, String subject, String body) {
        this.sender = sender;
        this.recipients = new LinkedList<>(Arrays.asList(recipient));
        this.subject = subject;
        this.body = body;
    }

    public Message(Identity sender, List<Identity> recipients, String subject, String body) {
        this.sender = sender;
        this.recipients = new LinkedList<>(recipients);
        this.subject = subject;
        this.body = body;
    }

    public void addRecipient(Identity recipient) {
        this.recipients.add(recipient);
    }

    public void send(NotificationChannel notificationChannel) {
        notificationChannel.send(this);
    }

}
