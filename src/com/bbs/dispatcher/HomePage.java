package com.bbs.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.controller.HomePageController;

@WebServlet("/HomePage/*")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomePageController homePageController;

	public HomePage() {
		super();
		homePageController = new HomePageController();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		homePageController.setRequest(request);
		homePageController.setResponse(response);
		String traget = "";
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		switch (action) {
		case "authorZone":
			request.getSession().setAttribute("currenPage", "");
			traget = homePageController.authorZone();
			break;
		case "homePage":
			request.getSession().setAttribute("currenPage", "homePage");
			traget = homePageController.homePage();
			break;
		case "hotPoint":
			request.getSession().setAttribute("currenPage", "hotPoint");
			traget = homePageController.hotPoint();
			break;
		case "vipZone":
			request.getSession().setAttribute("currenPage", "vipZone");
			traget = homePageController.vipZone();
			break;
		case "myZone":
			request.getSession().setAttribute("currenPage", "myZone");
			traget = homePageController.myZone();
			break;
		default:
			request.getSession().setAttribute("currenPage", "homePage");
			traget = "/WEB-INF/content/main/homePage.jsp";

		}
		if (traget.endsWith(".jsp")) {
			request.getRequestDispatcher(traget).forward(request, response);
		} else {
			response.sendRedirect(traget);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
