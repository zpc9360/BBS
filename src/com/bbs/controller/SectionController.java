package com.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.service.BaseService;
import com.bbs.util.MyUtil;
import com.bbs.util.Page;

public class SectionController {
	private BaseService baseService;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public SectionController() {
		super();
		baseService = new BaseService();
	}

	// 返回帖子列表
	public String getTopics() {
		Page page = new Page();
		int id = Integer.parseInt(request.getParameter("sectionId"));
		request.setAttribute("section", baseService.quaryById(Section.class, id));

		String curPage = request.getParameter("curPage");
		if (MyUtil.notNull(curPage)) {
			page.setCurPage(Integer.parseInt(curPage));
		}
		page.setActionUrl("SectionDispatcher/section?sectionId=" + id);
		page.setOrderStr("order by createDate desc");
		page.init(Topic.class, "topicId=0 and sectionId", id);
		request.setAttribute("pageBar", page.getUrl().toString());
		request.setAttribute("topicList", page.getList());
		return "/WEB-INF/content/topic/allTopics.jsp";
	}

	// 该板块下的全部帖子
	public String allSection() {
		request.setAttribute("sectionList", baseService.quaryAll(Section.class));
		return "/WEB-INF/content/section/allSection.jsp";
	}

	// 去板块增加页面
	public String toAddSection() {
		return "/WEB-INF/content/section/addSection.jsp";
	}

	// 添加板块
	public String AddSection() {
		baseService.add(MyUtil.getSection(request, response));
		return "/WEB-INF/content/section/allTopic.jsp";
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
