package com.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.BaseDao;
import com.bbs.daoImpl.BaseDaoImpl;
import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.po.User;
import com.bbs.service.BaseService;
import com.bbs.util.DbUtil;
import com.bbs.util.MyUtil;
import com.bbs.util.Page;

public class HomePageController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private BaseService baseService;
	private BaseDao baseDao2;
	
	public HomePageController() {
		super();
		baseService = new BaseService();
		baseDao2 = new BaseDaoImpl(DbUtil.getInstance().getConnetion());
	}
	
	public String homePage() {
		return "/WEB-INF/content/main/homePage.jsp";
	}
	public String hotPoint() {
		request.setAttribute("topicList", baseDao2.quaryByPage(Topic.class, "topicId", 0, 0, 15, "order by totalView desc"));
		return "/WEB-INF/content/main/hotPoint.jsp";
	}
	public String vipZone() {
		return "/WEB-INF/content/main/vipZone.jsp";
	}
	public String myZone() {
		if(MyUtil.isNotLogin(request)) {
			return "UserDispatcher/userToLogin.jsp";
		}
		User user = (User) request.getSession().getAttribute("currenUser");
//		System.out.println(user.getUserName());
		Page page = new Page();
	
		String curPage = request.getParameter("curPage");
		if (MyUtil.notNull(curPage)) {
			page.setCurPage(Integer.parseInt(curPage));
		}
		page.setActionUrl("HomePage/myZone");
		page.setOrderStr("order by createDate desc");
		page.init(Topic.class, "topicId=0 and userId", user.getId());
		request.setAttribute("pageBar", page.getUrl().toString());
		request.setAttribute("myRole", MyUtil.getRole(user.getRoleId()));
		request.setAttribute("topicList", page.getList());
		request.setAttribute("sectionList", baseService.quaryAll(Section.class,"sectionName"));
		return "/WEB-INF/content/main/myZone.jsp";
	}
	public String authorZone() {

		String id = request.getParameter("userId");
		if(!MyUtil.notNull(id)) {
			return "HomePage";
		}
		int userId = Integer.parseInt(id);
		
		User currenUser = (User) request.getSession().getAttribute("currenUser");
		if(currenUser!=null) {
			if(currenUser.getId()==userId) {
				request.getSession().setAttribute("currenPage", "myZone");
				return myZone();
			}
		}
		
		Page page = new Page();
	
		String curPage = request.getParameter("curPage");
		if (MyUtil.notNull(curPage)) {
			page.setCurPage(Integer.parseInt(curPage));
		}
		User author = (User) baseService.quaryById(User.class, userId);
		page.setActionUrl("HomePage/authorZone");
		page.setOrderStr("order by totalView desc");
		page.init(Topic.class, "topicId=0 and userId", userId);
		request.setAttribute("authorRole", MyUtil.getRole(author.getRoleId()));
		request.setAttribute("pageBar", page.getUrl().toString());
		request.setAttribute("topicList", page.getList());
		request.setAttribute("author", author);
		return "/WEB-INF/content/main/authorZone.jsp";
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
