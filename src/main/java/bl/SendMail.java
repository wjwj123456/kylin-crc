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
	public static int sendMail(String from, String[] to, String userName, String taskName, String url) {
		String host = "localhost";

		// 鑾峰彇绯荤粺灞炴�у璞�
		Properties properties = System.getProperties();
		// 璁剧疆閭欢鏈嶅姟鍣�
		properties.setProperty("mail.smtp.host", host);

		// 鑾峰彇榛樿Session瀵硅薄
		Session mailSession = Session.getDefaultInstance(properties);
		try {
			// 鍒涘缓涓�涓粯璁ょ殑MimeMessage瀵硅薄銆�
			MimeMessage message = new MimeMessage(mailSession);
			// 璁剧疆 From: 澶撮儴鐨刪eader瀛楁
			message.setFrom(new InternetAddress(from));
			for (String receiver : to) {
				// 璁剧疆 To: 澶撮儴鐨刪eader瀛楁
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			}
			// 璁剧疆 Subject: header瀛楁
			message.setSubject("Invitation from " + userName);
			StringBuilder text = new StringBuilder();
			text.append("Hi!\n").append("this is an invitation from ").append(userName).append("\n");
			text.append("Please use the following link to join in\n");
			text.append(url);
			// 鐜板湪璁剧疆鐨勫疄闄呮秷鎭�
			message.setText(text.toString());
			// 鍙戦�佹秷鎭�
			Transport.send(message);
			System.out.println("success");
		} catch (MessagingException e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}
}
