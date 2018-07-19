package com.bbs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.bbs.factory.AuthorityFactory;
import com.bbs.util.MyUtil;

@WebFilter("/*")
public class AuthorityFilter implements Filter {

	public AuthorityFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		MyUtil.setBaseConfig(req, resp);
		String uri = req.getRequestURI();
		if (uri.contains("/ueditor/")|| uri.contains("mp4")|| uri.contains("mpeg")) {
			chain.doFilter(req, resp);
			return;
		}
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		// System.out.println(action);
		if (action.endsWith(".css") || action.endsWith(".json") || action.endsWith(".js") || action.endsWith(".png")
				|| action.endsWith(".jpg") || action.endsWith(".gif") || action.endsWith(".html")|| action.endsWith(".mp4")) {
			chain.doFilter(req, resp);
			return;
		} else if (action.equals("") || action.equals("index") || action.equals("index.jsp")
				|| action.equals("Index")) {
			resp.sendRedirect("UserDispatcher/userLoginByCookie");
			return;
		} else if (!AuthorityFactory.authorityIn(action)) {
			if (MyUtil.isNotLogin(req)) {
//				System.out.println("not login");
				request.getRequestDispatcher("/WEB-INF/content/user/userLogin.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/WEB-INF/content/main/error.jsp").forward(request, response);
		} else {
			chain.doFilter((new MyRequest(req)), response);
		}
	}

	class MyRequest extends HttpServletRequestWrapper {

		public MyRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String[] charFileter = { "傻逼", "傻X", "傻x", "sX", "SX", "SB", "sb", "Sx", "<script>", "fuck", "Fuck", "fUck",
					"fuCk", "fucK", "FUck", "fUCk", "fuCK", "FUCk", "fUCK", "FUCK" };
			String option = super.getParameter(name);
			if (MyUtil.notNull(option)) {
				for (String str : charFileter) {
					if (option.contains(str)) {
						option = option.replace(str, "");
					}
				}
			}
			return option;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
