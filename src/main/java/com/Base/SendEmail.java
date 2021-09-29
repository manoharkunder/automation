package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

	public void sendEmail()

			throws IOException, InterruptedException {
		System.out.println("#####################email start");
		Properties properties = new Properties();
		System.out.println("#####################email path" + System.getProperty("user.dir") + "/Qa.properties");
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/Qa.properties"));
		properties.load(file);
		SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String fromemail = properties.getProperty("from_emailid");
		final String frompassword = properties.getProperty("from_password");
		String[] toemail = properties.getProperty("to_emailid").split(","); // List of recipient email addresses
		String subjectValue = properties.getProperty("subject");
		String subject = MessageFormat.format(subjectValue, datetime.format(new Date()));
		String bodyValue = properties.getProperty("email_body")+"<img src=\"cid:image\">";
		// MessageFormat.format(bodyValue, "...........");

		// File filename = new File(System.getProperty("user.dir") +
		// "/Report/Report.png");

		String host = "smtp.gmail.com";

		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", fromemail);
		properties.put("mail.smtp.password", frompassword);
		properties.put("mail.smtp.port", properties.getProperty("smtp_port"));
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.timeout", "5000");
		properties.put("mail.smtp.socketFactory.port", properties.getProperty("smtp_port"));
		properties.put("mail.smtp.socketFactory.fallback", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("javax.net.debug", "ssl,session");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromemail, frompassword);
			}
		});

		MimeMessage message = new MimeMessage(session);

		try {

			InternetAddress[] toAddress = new InternetAddress[toemail.length];

			for (int i = 0; i < toemail.length; i++) {
				toAddress[i] = new InternetAddress(toemail[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromemail));

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			// BodyPart messageBodyPart = new MimeBodyPart();

			MimeMultipart multipart = new MimeMultipart("related");

			BodyPart messageBodyPart = new MimeBodyPart();

			// String htmlText = "<H1>Hi Team here is our automation report</H1><img
		//	 src=\"cid:image\">";

			// Now set the actual message
			messageBodyPart.setContent(bodyValue, "text/html");
			// messageBodyPart.setText(body);

			// Create a multipart message
			// Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			//TestBase.updateScreenShot();
		//	DataSource source = new FileDataSource("/home/niveus/Afya-Automation/Report/ReportPic.png");
			DataSource source = new FileDataSource("http://localhost:8080/job/Automation/ws/Report/ReportPic.png");

			
			messageBodyPart.setDataHandler(new DataHandler(source));
			// messageBodyPart.setFileName(filename.getName());
			messageBodyPart.setHeader("Content-ID", "<image>");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);
			// Send message
			Thread.sleep(90000L);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, fromemail, frompassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Email has been sent sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
