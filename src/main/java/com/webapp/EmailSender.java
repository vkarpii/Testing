package com.webapp;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    private EmailSender(){}
    private static final String USER ="testing.contacts.e.a@gmail.com";//change accordingl
    private static final String TOKEN ="izuwdsjocdxfzdqs";
    private static final Session session;

    static {
        Properties properties = new Properties();
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "25");//465
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER,TOKEN);
                    }
                });
    }
    public static void sendEmail(String toEmail,String subject,String text) {
//2nd step)compose message
        try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
        message.setSubject(subject);
        message.setText(text);
        Transport.send(message);

    } catch (
    MessagingException e) {
        e.printStackTrace();
    }
}


}
