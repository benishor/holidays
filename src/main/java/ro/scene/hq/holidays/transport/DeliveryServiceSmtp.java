package ro.scene.hq.holidays.transport;

import ro.scene.hq.holidays.DeliveryService;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Properties;

public class DeliveryServiceSmtp implements DeliveryService {

    private static final String SMTP_USERNAME = "benishor.github.tests@gmail.com";

    private static final String SMTP_PASSWORD = "testpass123";

    private Session smtpSession;

    public DeliveryServiceSmtp() {
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
    public void deliver(String from, String to, String subject, String body, Collection<String> cc) {
        try {
            Message message = new MimeMessage(smtpSession);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            if (!cc.isEmpty()) {
                message.addRecipients(Message.RecipientType.CC, toInternetAddresses(cc));
            }

            Transport.send(message);
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
