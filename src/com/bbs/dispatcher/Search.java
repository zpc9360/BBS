package com.bbs.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.po.Topic;
import com.bbs.service.BaseService;
import com.bbs.util.MyUtil;


@WebServlet("/Search/*")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BaseService baseService;
  
    public Search() {
        super();
        baseService = new BaseService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		if(MyUtil.notNull(search)) {
			StringBuffer str = new StringBuffer("");
			for(int i = 0;i<search.length();i++) {
				str.append("%").append(search.charAt(i));
			}
			str.append("%");
			String sql = "where topicName like '"+str.toString()+"' or content like '"+str.toString()+"' and topicId=0 order by totalView desc";
			request.setAttribute("topicList", baseService.quaryAllCustom(Topic.class, sql));
			request.getRequestDispatcher("/WEB-INF/content/main/search.jsp").forward(request, response);
			return ;
		}
		response.sendRedirect("HomePage");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
