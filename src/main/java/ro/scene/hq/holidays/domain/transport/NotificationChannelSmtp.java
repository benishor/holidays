package ro.scene.hq.holidays.domain.transport;

import ro.scene.hq.holidays.domain.NotificationChannel;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class NotificationChannelSmtp implements NotificationChannel {

    private static final String SMTP_USERNAME = "benishor.github.tests@gmail.com";

    private static final String SMTP_PASSWORD = "testpass123";

    private Session smtpSession;

    public NotificationChannelSmtp() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        smtpSession = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });
    }

    @Override
    public void send(ro.scene.hq.holidays.domain.Message msg) {
        try {
            Message email = new MimeMessage(smtpSession);
            email.setSubject(msg.subject);
            email.setText(msg.body);

            email.setFrom(new InternetAddress(msg.sender.email));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.recipients.get(0).email));

            List<String> ccEmails = msg.recipients.subList(1, msg.recipients.size())
                    .stream()
                    .map(identity -> identity.email)
                    .collect(Collectors.toCollection(LinkedList::new));

            email.addRecipients(Message.RecipientType.CC, toInternetAddresses(ccEmails));

            Transport.send(email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Address[] toInternetAddresses(Collection<String> emails) throws AddressException {
        Address[] result = new Address[emails.size()];

        int i = 0;
        for (String email : emails) {
            result[i++] = new InternetAddress(email);
        }
        return result;
    }
}
