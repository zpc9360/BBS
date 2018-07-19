package com.bbs.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.controller.AdminController;

@WebServlet("/AdminDispatcher/*")
public class AdminDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminController adminController;
       
    public AdminDispatcher() {
        super();
        adminController = new AdminController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminController.setRequest(request);
		adminController.setResponse(response);
		String traget = "";
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		switch (action) {
		case "admin":
			traget = "/WEB-INF/content/admin/admin.jsp";
			break;
		case "addArea":
			traget = adminController.addArea();
			break;
		case "addSection":
			traget = adminController.addSection();
			break;
		case "toAddArea":
			traget = adminController.toAddArea();
			break;
		case "areaDelete":
			traget = adminController.areaDelete();
			break;
		case "sectionDelete":
			traget = adminController.sectionDelete();
			break;
		case "topicDelete":
			traget = adminController.topicDelete();
			break;
		case "userDelete":
			traget = adminController.userDelete();
			break;
		case "toAddSection":
			traget = adminController.toAddSection();
			break;
		case "userManage":
			traget = adminController.userManage();
			break;
		case "areaManage":
			traget = adminController.areaManage();
			break;
		case "sectionManage":
			traget = adminController.sectionManage();
			break;
		case "topicManage":
			traget = adminController.topicManage();
			break;
		case "adminAreaUpdate":
			traget = adminController.adminAreaUpdate();
			break;
		case "adminSectionUpdate":
			traget = adminController.adminSectionUpdate();
			break;
		case "adminTopicUpdate":
			traget = adminController.adminTopicUpdate();
			break;
		case "adminUserUpdate":
			traget = adminController.adminUserUpdate();
			break;
		case "toAreaUpdate":
			traget = adminController.toAreaUpdate();
			break;
		case "toUserUpdate":
			traget = adminController.toUserUpdate();
			break;
		case "toSectionUpdate":
			traget = adminController.toSectionUpdate();
			break;
		case "toTopicUpdate":
			traget = adminController.toTopicUpdate();
			break;
		default:
			traget = "/WEB-INF/content/admin/admin.jsp";
			break;
		}
		if (traget.endsWith(".jsp")) {
			request.getRequestDispatcher(traget).forward(request, response);
		} else {
			response.sendRedirect(traget);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
