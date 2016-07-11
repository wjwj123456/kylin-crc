package bl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * send email to users who were invited by others
 * 
 * @author song
 */
public class SendMail {

	/**
	 * send email
	 * 
	 * @param from
	 *            sender email address
	 * @param to
	 *            receivers email address
	 * @param userName
	 *            userName of the sender
	 * 
	 * @return 0 --- success 1 --- failure
	 */
	public int sendMail(String from, String[] to, String userName, String taskName, String url) {
		String host = "localhost";

		// 获取系统属性对象
		Properties properties = System.getProperties();
		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);

		// 获取默认Session对象
		Session mailSession = Session.getDefaultInstance(properties);
		try {
			// 创建一个默认的MimeMessage对象。
			MimeMessage message = new MimeMessage(mailSession);
			// 设置 From: 头部的header字段
			message.setFrom(new InternetAddress(from));
			for (String receiver : to) {
				// 设置 To: 头部的header字段
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			}
			// 设置 Subject: header字段
			message.setSubject("Invitation from " + userName);
			StringBuilder text = new StringBuilder();
			text.append("Hi!\n").append("this is an invitation from ").append(userName).append("\n");
			text.append("Please use the following link to join in\n");
			text.append(url);
			// 现在设置的实际消息
			message.setText(text.toString());
			// 发送消息
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}
}
