package com.bbs.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bbs.service.BaseService;

public class AdminNavTag extends SimpleTagSupport {

	private JspContext jspContext;
	private JspWriter out;
	private PageContext pageContext;
	private BaseService baseService;

	@Override
	public void doTag() throws JspException, IOException {

		this.jspContext = getJspContext();
		this.pageContext = (PageContext) jspContext;
		this.out = jspContext.getOut();
		baseService = new BaseService();
		init();
		
	}

	private void init() throws IOException {
	}

	
}
