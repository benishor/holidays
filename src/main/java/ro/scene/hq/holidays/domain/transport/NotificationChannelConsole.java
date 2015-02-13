package ro.scene.hq.holidays.domain.transport;

import ro.scene.hq.holidays.domain.Identity;
import ro.scene.hq.holidays.domain.Message;
import ro.scene.hq.holidays.domain.NotificationChannel;

import java.io.PrintStream;

public class NotificationChannelConsole implements NotificationChannel {

    @Override
    public void send(Message message) {
        PrintStream out = System.out;

        out.println("-------------------------------");
        out.println("Sending message");
        out.println("-------------------------------");
        out.println("From: " + message.sender);

        for (Identity recipient : message.recipients) {
            out.println("To: " + recipient);
        }

        out.println("Subject: " + message.subject);
        out.println("---");
        out.println(message.body);
    }
}
