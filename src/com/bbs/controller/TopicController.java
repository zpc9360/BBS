package com.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.bo.UserTopic;
import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.po.User;
import com.bbs.service.BaseService;
import com.bbs.service.UserToipcService;
import com.bbs.util.MyUtil;

public class TopicController {

	private BaseService baseService;
	private UserToipcService userTopicService;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public TopicController() {
		super();
		baseService = new BaseService();
		userTopicService = new UserToipcService();
	}

	// 显示帖子内容
	public String getTopicById() {
		int toipcId = Integer.parseInt(request.getParameter("id"));
		Topic topic = (Topic) baseService.quaryById(Topic.class, toipcId);
		if (topic == null || toipcId == 0) {
			return "/WEB-INF/content/main/topicNotFound.jsp";
		}
		User u = (User) baseService.quaryById(User.class, topic.getUserId());
		if (u == null)
			System.out.println("null");
		List<UserTopic> list = userTopicService.getAll(toipcId);
		if (list != null) {
			request.setAttribute("topicList", list);
		}
		// 浏览总数
		User user = (User) request.getSession().getAttribute("currenUser");
		if (user == null || user.getId() != topic.getUserId()) {
			topic.setTotalView(topic.getTotalView() + 1);
		}
		baseService.update(topic, "id", toipcId);
		request.setAttribute("topic", topic);
		request.setAttribute("User", u);
		request.setAttribute("userRole", MyUtil.getRole(u.getRoleId()));
		if (user != null && user.getId() == topic.getUserId()) {
			request.setAttribute("delete", "<a href=\"UserDispatcher/userTopicDelete?topicId=" + topic.getId()
					+ "\" onclick=\"if(!confirm('确定删除？'))return false;\"><span class=\"badge badge-danger\">删除</span></a>");
		}
		return "/WEB-INF/content/topic/topic.jsp";
	}

	// 返回所有帖子
	public String allTopic() {
		request.setAttribute("topicList", baseService.quaryAll(Topic.class));
		return "/WEB-INF/content/topic/allTopic.jsp";
	}

	// 去发帖页面
	public String toAddTopic() {
		request.setAttribute("sectionId", request.getParameter("sectionId"));
		return "/WEB-INF/content/topic/newTopic.jsp";
	}

	// 发帖
	public String AddTopic() {
		if (MyUtil.isNotLogin(request)) {
			return "/BBS/UserDispatcher/userToLogin";
		}
		Topic topic = MyUtil.getTopic(request, response);
		Section section = (Section) baseService.quaryById(Section.class, (int) topic.getSectionId());
		section.setSectionSum(section.getSectionSum() + 1);
		baseService.update(section);
		baseService.add(topic);
		String id = request.getParameter("sectionId");
		return "/BBS/SectionDispatcher/section?sectionId=" + id;
	}

	// 回帖
	public String newTopic() {
		if (MyUtil.isNotLogin(request)) {
			return "/BBS/UserDispatcher/userToLogin";
		}
		String topicId = request.getParameter("topicId");
		if (MyUtil.notNull(request.getParameter("content"))) {
			baseService.add(MyUtil.getTopic(request, response));
			// 回帖总数
			Object topic = baseService.quaryById(Topic.class, Integer.parseInt(topicId));
			Topic t = (Topic) topic;
			t.setTotalResponse(t.getTotalResponse() + 1);
			baseService.update(t);
		}
		return "/BBS/TopicDispatcher/topic?id=" + topicId;

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
