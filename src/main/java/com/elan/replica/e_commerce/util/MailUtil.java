package com.elan.replica.e_commerce.util;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailUtil {
	
	@Autowired
	private JavaMailSender mailSender;

	public void emailSuccesssSend(String emailId, String userName) {
		String subject = "Welcome to Our Service, " + userName + "!";
		String messageBody = "<h1>Welcome to Our e-commerce Service!</h1>" + "<p>Dear <strong>" + userName
				+ "</strong>,</p>"
				+ "<p>We are pleased to inform you that your registration has been successfully completed.</p>"
				+ "<p>You can now enjoy our secure and convenient e-commerce services.</p>"
				+ "<p>For any assistance, please feel free to reach out to our customer support team at <strong>supportevilan@ecommerce.com</strong>.</p>"
				+ "<p>Thank you for choosing us!</p>" + "<p>Best regards,<br> EVILAN E-commerce Team</p>";
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			String from = CommonConstant.EVILAN_TEAM + " <" + CommonConstant.SENDER_MAIL + ">";
			messageHelper.setFrom(from);
			messageHelper.setTo(emailId);
			messageHelper.setSubject(subject);
			messageHelper.setText(messageBody, true);
		};
		mailSender.send(preparator);
	}

	public String generateOtp() {
		SecureRandom random = new SecureRandom();
		int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
		return String.valueOf(otp);
	}

	
	public void sendOtpEmail(String toEmail, String otp) {
		String subject = "Your requested OTP!";
		String messageBody = "<html>" + "<body style='font-family: Arial, sans-serif;'>"
				+ "<h2 style='color: #4CAF50;'>Your OTP Code</h2>" + "<p style='font-size: 16px;'>Hello,</p>"
				+ "<p style='font-size: 16px;'>We received your request for a single-use code to use with your Evilan e-commerce account.</p>"
				+ "<p style='font-size: 16px;'>Your OTP code is: <strong style='font-size: 18px; color: #FF5733;'>"
				+ otp + "</strong></p>"
				+ "<p style='font-size: 14px;'>If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake.</p>"
				+ "<footer style='font-size: 12px; color: #888; margin-top: 20px;'>Regards,<br>EVILAN e-commerce Account Team</footer>"
				+ "</body>" + "</html>";
		MimeMessagePreparator preparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			String from = CommonConstant.EVILAN_TEAM + " <" + CommonConstant.SENDER_MAIL + ">";
			messageHelper.setFrom(from);
			messageHelper.setTo(toEmail);
			messageHelper.setSubject(subject);
			messageHelper.setText(messageBody, true);
		};
		mailSender.send(preparator);
	}

}
