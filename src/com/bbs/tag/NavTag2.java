package com.bbs.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bbs.po.Menu;
import com.bbs.po.User;
import com.bbs.service.BaseService;

public class NavTag2 extends SimpleTagSupport {

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
		navBar();
	}

	private void navBar() throws IOException {
		out.write("<nav class=\"navbar navbar-expand-sm navbar-light bg-light sticky-top\">\r\n");
		out.write("	<a class=\"navbar-brand\" href=\"HomePage/homePage\">Zero</a>");
		out.write(
				"	<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n"
						+ "   	<span class=\"navbar-toggler-icon\"></span>\r\n" + "  	</button>");
		out.write("<!-- ---------------------这个是大屏幕的导航栏----------------------- -->\r\n");
		out.write("	<div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">");
		out.write("<!-- -----------------各个子模块的开始------------------ -->\r\n");
		out.write("		<ul class=\"navbar-nav mr-auto\">");
		for (Object obj : baseService.quaryAll(Menu.class)) {
			Menu menu = (Menu) obj;
			out.write("<li class=\"nav-item");
			if (menu.getMenuDetail().equals(pageContext.getSession().getAttribute("currenPage"))) {
				out.write(" active");
			}
			out.write("\"><a class=\"nav-link\" href=\"HomePage/" + menu.getMenuDetail() + "\">");
			out.write(menu.getMenuName());
			out.write("</a></li>\r\n");
		}
		User user = (User) pageContext.getSession().getAttribute("currenUser");
		if (user != null) {
			if (user.getRoleId() >= 3) {
				out.write("<li class=\"nav-item");
				if ("admin".equals(pageContext.getSession().getAttribute("currenPage"))) {
					out.write(" active");
				}
				out.write("\"><a class=\"nav-link\" href=\"UserDispatcher/admin\">论坛管理</a></li>\r\n");
			}
		}
		out.write("		</ul>\r\n");
		out.write("<!---------------------------- 搜索框 ---------------------->\r\n");
		out.write("	<form action=\"Search\" class=\"form-inline mr-5\">\r\n"
				+ "      <input class=\"form-control mr-sm-2\" style=\"width:160px;\" type=\"search\" placeholder=\"主题 / 帖子 / 回复\" aria-label=\"Search\" name=\"search\">\r\n"
				+ "      <button class=\"btn btn-outline-success my-2 my-sm-0\" type=\"submit\">搜索</button>\r\n"
				+ " </form>");
		out.write("<!---------------------------- 搜索框 ---------------------->\r\n");
		if (user == null) {
			out.write("<!-- --------------------这里是    登录   的开始------------------------ -->\r\n");
			out.write("	<ul class=\"navbar-nav\">\r\n");
			out.write(
					"		<li class=\"nav-item\"><a class=\"nav-link\" href=\"UserDispatcher/userToLogin\">登录</a></li>\r\n");
			out.write(
					"		<li class=\"nav-item\"><a class=\"nav-link\" href=\"UserDispatcher/userToRegister\">注册</a></li>\r\n");
			out.write("	</ul>\r\n");
			out.write("<!-- ---------------------这里是     登录   的结束--------------------- -->\r\n");
		} else {
			out.write("<!--------------------- 这里是    用户   的开始 ------------------------->\r\n");
			out.write("	<ul class=\"navbar-nav\">\r\n");
			out.write("<!-- 用户头像-->\r\n");
			out.write("	<li class=\"nav-item dropdown mr-0\">");
			out.write(
					"		<a class=\"nav-link dropdown-toggle m-0 p-0\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
			out.write("			<img height=\"40\"width=\"40\" class=\"rounded-circle\" src=\"pictures/head/"+user.getHeadImg()+"\">");
//			System.out.println(user.getHeadImg());
			out.write("		</a>");
			out.write("		<div class=\"dropdown-menu dropdown-menu-right\" aria-labelledby=\"navbarDropdown\">");
			out.write("			<a class=\"dropdown-item\" href=\"UserDispatcher/userDetail\">个人信息</a>");
			if (user.getRoleId() >= 3) {
				out.write("		<div class=\"dropdown-divider\"></div>");
				out.write("		<a class=\"dropdown-item\" href=\"UserDispatcher/admin\">论坛管理</a>");
			}
			out.write("			<div class=\"dropdown-divider\"></div>");
			out.write("			<a class=\"dropdown-item\" href=\"UserDispatcher/userLogout\">注销</a>");
			out.write("</div></li></ul>\r\n");
		}
		out.write("<!-- ---------------------各个子模块的结束-------------------------- -->\r\n");
		out.write("	</div>\r\n");
		out.write("</nav>\r\n");
		out.write("<div style=\"margin-top:25px\"></div>");
	}

}
