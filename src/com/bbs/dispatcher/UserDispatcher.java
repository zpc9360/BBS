package com.bbs.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.controller.UserController;

@WebServlet("/UserDispatcher/*")
public class UserDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserController userController;
	
	public UserDispatcher() {
		super();
		userController = new UserController();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		userController.setRequest(request);
		userController.setResponse(response);
		String traget = "";
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		switch (action) {
		case "admin":
			request.getSession().setAttribute("currenPage", "admin");
			traget = userController.toAdmin();
			break;
		case "userTopicDelete":
			traget = userController.userTopicDelete();
			break;
		case "userToLogin":
			traget = userController.userToLogin();
			break;
		case "userLogin":
			traget = userController.userLogin();
			break;
		case "userLogout":
			traget = userController.userLogout();
			break;
		case "userToRegister":
			traget = userController.userToRegister();
			break;
		case "userRegister":
			traget = userController.userRegister();
			break;
		case "userDetailUpdate":
			traget = userController.userDetailUpdate();
			break;
		case "toUserDetailUpdate":
			traget = userController.toUserDetailUpdate();
			break;
		case "userDetail":
			request.getSession().setAttribute("currenPage", "");
			traget = userController.userDetail();
			break;
		case "userDelete":
			traget = userController.userDelete();
			break;
		case "userLoginByCookie":
			traget = userController.userLoginByCookie();
			break;
		case "isExist":
			userController.isExist();
			return ;
		case "getVerifyCode":
			userController.getVerifyCode();
			return ;
		case "headUpload":
			traget = userController.headUpload();
			break;
		default:
			traget = "/BBS/HomePage/homePage";
			break;
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
