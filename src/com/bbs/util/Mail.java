package com.bbs.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private String myEmailAccount = "15147144922@163.com";
	private String myEmailPassword = "hao19960822";
	private String myEmailSMTPHost = "smtp.163.com";
	// private String myEmailPOP3Host = "pop.163.com";
	// private String myEmailIMAPHost = "imap.163.com";
	private String userMail;
	private Properties props;
	private final int varyCode = new Random().nextInt(899999) + 100000;

	public int getVaryCode() {
		return varyCode;
	}

	public Mail(String userMail) {
		super();
		this.userMail = userMail;
		newMail();
	}

	private void newMail() {
		try {
			// 1. 创建参数配置, 用于连接邮件服务器的参数配置
			props = new Properties(); // 参数配置
			props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
			props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
//			props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
			props.put("mail.debug", "true");//便于调试
			props.put("mail.smtp.localhost", "211.82.160.195");
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.port", "25");
			// 2. 根据配置创建会话对象, 用于和邮件服务器交互
			Session session = Session.getInstance(props);
			session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
			// 3. 创建一封邮件
			// 3-1. 创建一封邮件
			MimeMessage message = new MimeMessage(session);
			// 3-2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
			message.setFrom(new InternetAddress(myEmailAccount, "Zero论坛管理团队", "UTF-8"));
			// 3-3. To: 收件人（可以增加多个收件人、抄送、密送）
			// Cc: 抄送（可选）
			message.setRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress("15147144922@163.com", "Zero论坛管理员", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(userMail, "尊敬的用户", "UTF-8"));
			// 3-4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
			message.setSubject("[Zero] Email 地址验证", "UTF-8");
			// 3-5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
			message.setContent("Email 地址验证<br><br>" + "这封信是由 Zero 论坛管理团队发送的。<br>" + "<br>"
					+ "您收到这封邮件，是由于在 Zero 论坛进行了新用户注册，或用户修改Email使用了这个邮箱地址。如果您并没有访问过Zero论坛，或没有进行上述操作，请忽 略这封邮件。您不需要退订或进行其他进一步的操作。"
					+ "<br><br>" + "----------------------------------------------------------------------<br>"
					+ "帐号激活说明<br>" + "----------------------------------------------------------------------<br><br>"
					+ "如果您是 Zero 论坛的新用户，或在修改您的注册 Email 时使用了本地址，我们需要对您的地址有效性进行验证以避免垃圾邮件或地址被滥用。<br><br>"
					+ "以下数字为您的验证码：<br><h2><strong>" + varyCode + "</strong></h2><br>"
					+ "感谢您的访问，祝您使用愉快！<br><br>此致<br>" + " Zero 论坛 管理团队<br>"
					+ "http://www.zeroxf.top/", "text/html;charset=UTF-8");

			// 3-6. 设置发件时间
			message.setSentDate(new Date());
			// 3-7. 保存设置
			message.saveChanges();
			// 4. 根据 Session 获取邮件传输对象
			Transport transport = session.getTransport();
			// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
			transport.connect(myEmailAccount, myEmailPassword);
			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人,
			// 密送人
			transport.sendMessage(message, message.getAllRecipients());
			// 7. 关闭连接
			transport.close();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
	
}
