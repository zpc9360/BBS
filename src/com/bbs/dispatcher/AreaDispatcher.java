package com.bbs.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.controller.AreaController;

@WebServlet("/AreaDispatcher/*")
public class AreaDispatcher extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AreaController areaController;
	
    public AreaDispatcher() {
        super();
        areaController = new AreaController();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		areaController.setRequest(request);
		areaController.setResponse(response);
		String traget = "";
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		switch(action) {
		case "allArea":
			traget = areaController.allArea();
			break;
		case "toAddArea":
			traget = areaController.toAddArea();
			break;
		case "addArea":
			traget = areaController.AddArea();
			break;
		default:
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
