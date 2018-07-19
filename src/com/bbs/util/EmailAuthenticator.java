package com.bbs.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {

	private String emailAccount;
	private String emailPassword;

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public EmailAuthenticator() {
	}

	public EmailAuthenticator(String emailAccount, String emailPassword) {
		super();
		this.emailAccount = emailAccount;
		this.emailPassword = emailPassword;
	}
	
	public PasswordAuthentication getPA() {
		return new PasswordAuthentication(emailAccount, emailPassword);
	}

}
