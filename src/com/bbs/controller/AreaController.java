package com.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.po.Area;
import com.bbs.service.BaseService;
import com.bbs.util.MyUtil;

public class AreaController {

	private BaseService baseService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	

	public AreaController() {
		super();
		baseService = new BaseService();
	}
	//显示全部区块
	public String allArea() {
		request.setAttribute("areaList", baseService.quaryAll(Area.class));
		return "/WEB-INF/content/area/allArea.jsp";
	}
	//去添加区块页面
	public String toAddArea() {
		return "/WEB-INF/content/area/addArea.jsp";
	}
	//添加区块页面
	public String AddArea() {
		baseService.add(MyUtil.getArea(request, response));
		return "/BBS/AreaDispatcher/allArea";
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
