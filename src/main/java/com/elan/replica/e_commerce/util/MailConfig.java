package com.elan.replica.e_commerce.util;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // SMTP server configuration
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587); // Port 587 is for TLS. Use 465 if you prefer SSL.
        mailSender.setUsername("elangaming994@gmail.com");
        mailSender.setPassword("ewerjqossfafssgs"); // Replace with your App Password, not your Gmail password.

        // SMTP properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS for port 587
        props.put("mail.smtp.ssl.enable", "false"); // Disable SSL if using port 587
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.debug", "true"); // Debugging to log SMTP communication

        return mailSender;
    }
}

