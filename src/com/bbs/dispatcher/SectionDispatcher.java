package com.bbs.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.controller.SectionController;

@WebServlet("/SectionDispatcher/*")
public class SectionDispatcher extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private SectionController sectionController;
    public SectionDispatcher() {
        super();
     sectionController = new SectionController();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sectionController.setRequest(request);
		sectionController.setResponse(response);
		String traget = "";
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		switch(action) {
		case "section":
			traget = sectionController.getTopics();
			break;
		case "allSection":
			traget = sectionController.allSection();
			break;
		case "toAddSection":
			traget = sectionController.toAddSection();
			break;
		case "addSection":
			traget = sectionController.AddSection();
			break;
		default:
			traget = sectionController.allSection();
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
