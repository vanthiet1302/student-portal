package dev.nlu.portal.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailSender {
    public static boolean send(String to, String subject, String body) {
        String host = getEnvOrDefault("SMTP_HOST", "smtp.gmail.com");
        String port = getEnvOrDefault("SMTP_PORT", "587");
        String username = System.getenv("SMTP_USER");
        String password = System.getenv("SMTP_PASS");
        String from = getEnvOrDefault("SMTP_FROM", username);

        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(username != null && password != null));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session;
        if (username != null && password != null) {
            session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } else {
            session = Session.getInstance(props);
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getEnvOrDefault(String key, String def) {
        String val = System.getenv(key);
        return val != null && !val.isEmpty() ? val : def;
    }
}