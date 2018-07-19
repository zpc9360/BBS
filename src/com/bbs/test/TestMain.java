package com.bbs.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.service.BaseService;
import com.bbs.util.Mail;

public class TestMain {

	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
	// 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	public static String myEmailAccount = "15147144922@163.com";
	public static String myEmailPassword = "hao19960822";

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.163.com";

	// 收件人邮箱（替换为自己知道的有效邮箱）
	public static String receiveMailAccount = "809491730@qq.com";
	public static Properties props;

	private final static int varyCode = new Random().nextInt(899999) + 100000;

	public static void main(String[] args) throws Exception {
		// Mail mail = new Mail("");
		// System.out.println(mail.getVaryCode());
		addData();

	}

	public void mail() throws Exception {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
		props.put("userName", "15147144922@163.com");

		// PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
		// 如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
		// 打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
		/*
		 * // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接, // 需要改为对应邮箱的 SMTP
		 * 服务器的端口, 具体可查看对应邮箱服务的帮助, // QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看) final String
		 * smtpPort = "465"; props.setProperty("mail.smtp.port", smtpPort);
		 * props.setProperty("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 * props.setProperty("mail.smtp.socketFactory.fallback", "false");
		 * props.setProperty("mail.smtp.socketFactory.port", smtpPort);
		 */

		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

		// 3. 创建一封邮件
		MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();

		// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
		//
		// PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
		// 仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
		// 类型到对应邮件服务器的帮助网站上查看具体失败原因。
		//
		// PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
		// (1) 邮箱没有开启 SMTP 服务;
		// (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
		// (3) 邮箱服务器要求必须要使用 SSL 安全连接;
		// (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
		// (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
		//
		// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
		transport.connect(myEmailAccount, myEmailPassword);

		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人,
		// 密送人
		transport.sendMessage(message, message.getAllRecipients());

		// 7. 关闭连接
		transport.close();

	}

	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
		message.setFrom(new InternetAddress(sendMail, "Zero论坛管理团队", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		// Cc: 抄送（可选）
		message.setRecipient(MimeMessage.RecipientType.CC,
				new InternetAddress("15147144922@163.com", "Zero论坛管理员", "UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "尊敬的用户", "UTF-8"));
		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
		message.setSubject("[Zero] Email 地址验证", "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
		message.setContent("Email 地址验证\r\n" + "\r\n" + "Zero，\r\n" + "这封信是由 Zero 论坛 发送的。\r\n" + "\r\n"
				+ "您收到这封邮件，是由于在 Zero 论坛 进行了新用户注册，或用户修改 Email 使用 了这个邮箱地址。如果您并没有访问过  Zero 论坛论坛，或没有进行上述操作，请忽 略这封邮件。您不需要退订或进行其他进一步的操作。\r\n"
				+ "\r\n" + "\r\n" + "----------------------------------------------------------------------\r\n"
				+ "帐号激活说明\r\n" + "----------------------------------------------------------------------\r\n" + "\r\n"
				+ "如果您是 Zero 论坛 的新用户，或在修改您的注册 Email 时使用了本地址，我们需 要对您的地址有效性进行验证以避免垃圾邮件或地址被滥用。\r\n" + "\r\n"
				+ "您只需点击下面的链接即可激活您的帐号：\r\n" + "http://bbs.fishc.com/member.php?mod=activate&uid=528554&id=SsWOro \r\n"
				+ "(如果上面不是链接形式，请将该地址手工粘贴到浏览器地址栏再访问)\r\n" + "\r\n" + "感谢您的访问，祝您使用愉快！\r\n" + "\r\n" + "此致\r\n"
				+ " Zero 论坛 管理团队.\r\n" + "http://bbs.fishc.com/", "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	private void newMail() {
		try {
			// 1. 创建一封邮件
			Properties props = new Properties(); // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
			Session session = Session.getInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
			MimeMessage message = new MimeMessage(session); // 创建邮件对象

			// 2. From: 发件人
			// 其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
			// 真正要发送时, 邮箱必须是真实有效的邮箱。
			message.setFrom(new InternetAddress("2721002772@qq.com", "Zero系统管理员", "UTF-8"));
			// 3. To: 收件人
			message.setRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress("cc@receive.com", "USER_CC", "UTF-8"));
			// To: 增加收件人（可选）
			message.addRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
			// Cc: 抄送（可选）
			message.setRecipient(MimeMessage.RecipientType.CC,
					new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
			// Bcc: 密送（可选）
			message.setRecipient(MimeMessage.RecipientType.BCC,
					new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

			// 4. Subject: 邮件主题
			message.setSubject("TEST邮件主题", "UTF-8");

			// 5. Content: 邮件正文（可以使用html标签）
			message.setContent("TEST这是邮件正文。。。", "text/html;charset=UTF-8");

			// 6. 设置显示的发件时间
			message.setSentDate(new Date());

			// 7. 保存前面的设置
			message.saveChanges();

			// 8. 将该邮件保存到本地
			OutputStream out = new FileOutputStream("MyEmail.eml");
			message.writeTo(out);
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	private void request() {
		URL url;
		try {
			url = new URL("http://10.0.60.57:8080/BBS/TopicDispatcher/topic?id=1806");
			int sum = 10000;
			while (sum > 0) {
				url.openConnection();// 打开URL
				sum--;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} // 根据链接（字符串格式），生成一个URL对象

	}

	private static void addData() {
		BaseService bs = new BaseService();
		Topic t = null;
		Random rand = new Random();
		long sectionId = 19;
		Section section = (Section) bs.quaryById(Section.class, sectionId);
		int sum = 50;
		for (int i = 1; i <= sum; i++) {
			t = new Topic();
			t.setId(i);
			t.setContent("这是第 " + i + " 号文章的正文，" + section.getSectionName() + section.getSectionName()
					+ section.getSectionName() + section.getSectionName() + section.getSectionName()
					+ section.getSectionName() + section.getSectionName() + section.getSectionName()
					+ section.getSectionName() + section.getSectionName() + "一堆看不懂的东西。");
			t.setTopicName(section.getSectionName() + " 的第 " + i + " 号文章");
			t.setSectionId(section.getId());
			t.setTopicId(0);
			t.setTotalFavour(0);
			t.setTotalResponse(0);
			t.setTotalView(0);
			t.setUserId(1);
			int _day = rand.nextInt(23) + 1;
			String day = _day < 10 ? "0" + _day : "" + _day;
			int _hour = rand.nextInt(24);
			String hour = _hour < 10 ? "0" + _hour : "" + _hour;
			int _minitues = rand.nextInt(60);
			String minitues = _minitues < 10 ? "0" + _minitues : "" + _minitues;
			int _second = rand.nextInt(60);
			String second = _second < 10 ? "0" + _second : "" + _second;
			t.setCreateDate("2018-04-" + day + " " + hour + ":" + minitues + ":" + second);
			// bs.update(t, "id", t.getId());
			bs.add(t);
			section.setSectionSum(section.getSectionSum() + 1);
			bs.update(section);
		}
	}
}
