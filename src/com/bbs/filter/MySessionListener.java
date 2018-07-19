package com.bbs.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bbs.factory.AuthorityFactory;

public class MySessionListener implements HttpSessionListener {

	private long userCount = 0;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		arg0.getSession().getServletContext().setAttribute("userCount", ++userCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		AuthorityFactory.setRoleLevel(1);
		arg0.getSession().getServletContext().setAttribute("userCount", --userCount);
	}

}
