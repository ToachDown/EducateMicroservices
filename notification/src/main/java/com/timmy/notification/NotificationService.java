package com.timmy.notification;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    private void sendMailMessage (String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendNotification(CustomerNotifyRequest request) {
        String message = String.format("%s %s %s", request.firstname(), request.lastname(), request.message());
        sendMailMessage(request.email(), "System", message);
    }
}
