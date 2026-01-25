package com.delonic.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmation(String to, String orderId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // WAJIB diisi
        message.setTo(to);
        message.setSubject("Order Confirmation");
        message.setText("Your order with ID " + orderId + " has been processed successfully!");
        mailSender.send(message);
    }
}
