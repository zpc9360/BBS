package com.bbs.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTag<T> extends SimpleTagSupport {
	
	private List<T> t;

	public void setObj(List<T> obj) {
		this.t = obj;
	}

	StringWriter sw = new StringWriter();
	
	public void doTag() throws IOException, JspException {
		if(t != null) {
			/* 从属性中使用消息 */
			JspWriter out = getJspContext().getOut();
			out.println("<a href=\"#\">Hello Custom Tag !</a>" + t);
		}else {
			/* 从内容体中使用消息 */
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
	}
	
}
