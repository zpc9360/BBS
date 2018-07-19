package com.bbs.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.po.Area;
import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.po.User;
import com.bbs.service.BaseService;

public class MyUtil {

	public static boolean isNotLogin(HttpServletRequest request) {
		if (null == request.getSession().getAttribute("currenUser")) {
			return true;
		}
		return false;
	}

	public static User updateUser(long id, HttpServletRequest request, HttpServletResponse response) {
		User user = (User) new BaseService().quaryById(User.class, id);
		String userName = request.getParameter("userName");
		if (notNull(userName)) {
			user.setUserName(userName);
		}
		String passWord = request.getParameter("passWord");
		if (notNull(passWord)) {
			user.setUserName(passWord);
		}
		String NickName = request.getParameter("nickName");
		if (notNull(NickName)) {
			user.setUserName(NickName);
		}
		return user;
	}
	public static User updateUser(User user, HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		if (notNull(userName)) {
			user.setUserName(userName);
		}
		String passWord = request.getParameter("passWord");
		if (notNull(passWord)) {
			user.setPassWord(passWord);
		}
		String NickName = request.getParameter("nickName");
		if (notNull(NickName)) {
			user.setNickName(NickName);
		}
		return user;
	}

	public static Section updateSection(long id, HttpServletRequest request, HttpServletResponse response) {
		Section section = (Section) new BaseService().quaryById(Section.class, id);
		String sectionName = request.getParameter("sectionName");
		if (notNull(sectionName)) {
			section.setSectionName(sectionName);
		}
		String _areaId = request.getParameter("areaId");
		if (notNull(_areaId)) {
			section.setAreaId(Long.parseLong(_areaId));
		}
		return section;
	}

	public static Area updateArea(long id, HttpServletRequest request, HttpServletResponse response) {
		Area area = (Area) new BaseService().quaryById(Area.class, id);
		String areaName = request.getParameter("areaName");
		System.out.println(areaName);
		if (notNull(areaName)) {
			area.setAreaName(areaName);
		}
		return area;
	}

	public static Topic updateTopic(long id, HttpServletRequest request, HttpServletResponse response) {
		Topic topic = (Topic) new BaseService().quaryById(Topic.class, id);
		return topic;
	}

	public static User getUser(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setPassWord(request.getParameter("passWord"));
		user.setNickName(request.getParameter("nickName"));
		user.setGender(request.getParameter("gender"));
		user.setRoleId(2);
		return user;
	}

	public static Topic getTopic(HttpServletRequest request, HttpServletResponse response) {
		Topic topic = new Topic();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String topicName = request.getParameter("topicName");
		if (notNull(topicName))
			topic.setTopicName(topicName.replaceAll("[<>]", ""));
		topic.setContent(request.getParameter("content"));

		// int id = Integer.parseInt(session.getAttribute("sectionId").toString());
		// topic.setSectionId(id);
		String _topicId = request.getParameter("topicId");
		int topicId = MyUtil.notNull(_topicId) ? Integer.parseInt(_topicId) : 0;
		String _sectionId = request.getParameter("sectionId");
		int sectionId = MyUtil.notNull(_sectionId) ? Integer.parseInt(_sectionId) : 0;
		topic.setSectionId(sectionId);
		topic.setTopicId(topicId);
		topic.setCreateDate(df.format(new Date()));
		topic.setTotalFavour(0);
		topic.setTotalResponse(0);
		topic.setTotalView(0);
		User user = (User) request.getSession().getAttribute("currenUser");
		topic.setUserId(user.getId());
		return topic;
	}

	public static Area getArea(HttpServletRequest request, HttpServletResponse response) {
		Area area = new Area();
		area.setAreaName(request.getParameter("areaName"));
		area.setUserId(1);
		return area;
	}

	public static Section getSection(HttpServletRequest request, HttpServletResponse response) {
		Section section = new Section();
		section.setSectionName(request.getParameter("sectionName"));
		section.setAreaId(Long.parseLong(request.getParameter("areaId")));
		section.setUserId(1);
		return section;
	}

	public static void setBaseConfig(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		request.setAttribute("basePath", basePath);
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void cleanMyCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
	public static String getRole(int roleId) {
		switch (roleId) {
		case 2:
			return "用户";
		case 3:
			return "Vip用户";
		case 4:
			return "版块管理员";
		case 5:
			return "区域管理员";
		case 6:
			return "论坛管理员";
		default:
			return "用户";
		}
	}

	public static boolean notNull(String str) {
		if (null != str && !"".equals(str)) {
			return true;
		}
		return false;
	}
}
