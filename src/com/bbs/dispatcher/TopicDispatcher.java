package com.bbs.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.controller.TopicController;

@WebServlet("/TopicDispatcher/*")
public class TopicDispatcher extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TopicController topicController;
	
    public TopicDispatcher() {
        super();
        topicController = new TopicController();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		topicController.setRequest(request);
		topicController.setResponse(response);
		String traget = "";
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		switch(action) {
		case "allTopic":
			traget = topicController.allTopic();
			break;
		case "toAddTopic":
			traget = topicController.toAddTopic();
			break;
		case "addTopic":
			traget = topicController.AddTopic();
			break;
		case "newTopic":
			traget = topicController.newTopic();
			break;
		case "topic":
			traget = topicController.getTopicById();
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
