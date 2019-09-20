import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void send(String from, String password, String to, String subject, String message) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, password);
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("message sent successfully");
	}

	public static void main(String[] args) {
		send("ramakrishna227@gmail.com", "ynilvmeqojgfouko", "supriya0207@gmail.com", "testMail", "Love you yaaa..");
	}
}
