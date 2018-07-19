package com.bbs.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.factory.AuthorityFactory;
import com.bbs.po.Area;
import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.po.User;
import com.bbs.service.BaseService;
import com.bbs.util.MyUtil;
import com.bbs.util.Page;

public class AdminController {

	private BaseService baseService;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public AdminController() {
		super();
		baseService = new BaseService();
	}

	public String areaDelete() {
		try {
			baseService.getConnection().setAutoCommit(false);
			long areaId = Long.parseLong(request.getParameter("areaId"));
			List<?> list = baseService.quaryAllByPram(Section.class, "areaId", areaId);
			for (Object obj : list) {
				Section section = (Section) obj;
				baseService.deleteById(Section.class, section.getId());
			}
			baseService.deleteById(Area.class, areaId);
			baseService.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaManage();
	}

	public String sectionDelete() {
		baseService.deleteById(Section.class, Long.parseLong(request.getParameter("sectionId")));
		return sectionManage();
	}

	public String topicDelete() {
		try {
			baseService.getConnection().setAutoCommit(false);
			long topicId = Long.parseLong(request.getParameter("topicId"));
			Topic topic = (Topic) baseService.quaryById(Topic.class, topicId);
			if (topic.getTopicId() == 0) {
				Section section = (Section) baseService.quaryById(Section.class, topic.getSectionId());
				section.setSectionSum(section.getSectionSum() - 1);
				List<?> list = baseService.quaryAllByPram(Topic.class, "topicId", topic.getId());
				for(Object obj : list) {
					baseService.deleteById(Topic.class, ((Topic) obj).getId());
				}
				baseService.update(section);
			}
			baseService.deleteById(Topic.class, topicId);
			baseService.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topicManage();
	}

	public String userDelete() {
//		System.out.println(baseService.deleteById(User.class, Long.parseLong(request.getParameter("userId"))));
		return userManage();
	}

	public String addArea() {
		baseService.add(MyUtil.getArea(request, response));
		return areaManage();
	}

	public String addSection() {
		baseService.add(MyUtil.getSection(request, response));
		return sectionManage();
	}

	public String toAreaUpdate() {
		request.setAttribute("area",
				baseService.quaryById(Area.class, Integer.parseInt(request.getParameter("areaId"))));
		return "/WEB-INF/content/admin/update/adminAreaUpdate.jsp";
	}

	public String toSectionUpdate() {
		request.setAttribute("areaList", baseService.quaryAll(Area.class));
		request.setAttribute("section",
				baseService.quaryById(Section.class, Integer.parseInt(request.getParameter("sectionId"))));
		return "/WEB-INF/content/admin/update/adminSectionUpdate.jsp";
	}

	public String toUserUpdate() {
		request.setAttribute("user",
				baseService.quaryById(User.class, Integer.parseInt(request.getParameter("userId"))));
		return "/WEB-INF/content/admin/update/adminUserUpdate.jsp";
	}

	public String toTopicUpdate() {
		request.setAttribute("topic",
				baseService.quaryById(Topic.class, Integer.parseInt(request.getParameter("topicId"))));
		return "/WEB-INF/content/admin/update/adminTopicUpdate.jsp";
	}

	public String adminAreaUpdate() {
		String _id = request.getParameter("id");
		long id = 0;
		if (MyUtil.notNull(_id)) {
			id = Long.parseLong(_id);
		}
		if (id == 0) {
			return areaManage();
		}
		baseService.update(MyUtil.updateArea(id, request, response));
		return areaManage();
	}

	public String adminSectionUpdate() {
		String _id = request.getParameter("id");
		long id = 0;
		if (MyUtil.notNull(_id)) {
			id = Long.parseLong(_id);
		}
		if (id == 0) {
			return sectionManage();
		}
		baseService.update(MyUtil.updateSection(id, request, response));
		return sectionManage();
	}

	public String adminTopicUpdate() {
		String _id = request.getParameter("id");
		long id = 0;
		if (MyUtil.notNull(_id)) {
			id = Long.parseLong(_id);
		}
		if (id == 0) {
			return topicManage();
		}
		baseService.update(MyUtil.updateTopic(id, request, response));
		return topicManage();
	}

	public String adminUserUpdate() {
		String _id = request.getParameter("id");
		long id = 0;
		if (MyUtil.notNull(_id)) {
			id = Long.parseLong(_id);
		}
		if (id == 0) {
			return userManage();
		}
		baseService.update(MyUtil.updateUser(id, request, response));
		return userManage();
	}

	public String toAddArea() {
		return "/WEB-INF/content/area/addArea.jsp";
	}

	public String toAddSection() {
		request.setAttribute("areaList", baseService.quaryAll(Area.class));
		return "/WEB-INF/content/section/addSection.jsp";
	}

	public String userManage() {
		Page page = new Page();
		String _curPage = request.getParameter("curPage");
		if (MyUtil.notNull(_curPage)) {
			int curPage = Integer.parseInt(_curPage);
			page.setCurPage(curPage);
		}
		page.setActionUrl("AdminDispatcher/userManage");
		page.setOrderStr("order by roleId desc");
		page.init(User.class, "where roleId<" + AuthorityFactory.getRoleLevel());
		request.setAttribute("userList", page.getList());
		request.setAttribute("pageBar", page.getUrl());
		return "/WEB-INF/content/admin/userManage.jsp";
	}

	public String areaManage() {
		Page page = new Page();
		String _curPage = request.getParameter("curPage");
		if (MyUtil.notNull(_curPage)) {
			int curPage = Integer.parseInt(_curPage);
			page.setCurPage(curPage);
		}
		page.setActionUrl("AdminDispatcher/areaManage");
		page.setOrderStr("order by id");
		page.init(Area.class);
		request.setAttribute("areaList", page.getList());
		request.setAttribute("pageBar", page.getUrl());
		return "/WEB-INF/content/admin/areaManage.jsp";
	}

	public String sectionManage() {
		Page page = new Page();
		String _curPage = request.getParameter("curPage");
		if (MyUtil.notNull(_curPage)) {
			int curPage = Integer.parseInt(_curPage);
			page.setCurPage(curPage);
		}
		page.setActionUrl("AdminDispatcher/sectionManage");
		page.setOrderStr("order by id");
		page.init(Section.class);
		request.setAttribute("sectionList", page.getList());
		request.setAttribute("pageBar", page.getUrl());
		return "/WEB-INF/content/admin/sectionManage.jsp";
	}

	public String topicManage() {
		Page page = new Page();
		String _curPage = request.getParameter("curPage");
		if (MyUtil.notNull(_curPage)) {
			int curPage = Integer.parseInt(_curPage);
			page.setCurPage(curPage);
		}
		page.setActionUrl("AdminDispatcher/topicManage");
		page.setOrderStr("order by createDate desc");
		page.init(Topic.class);
		request.setAttribute("topicList", page.getList());
		request.setAttribute("pageBar", page.getUrl());
		return "/WEB-INF/content/admin/topicManage.jsp";
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