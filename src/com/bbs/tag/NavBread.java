package com.bbs.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bbs.po.Area;
import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.service.BaseService;

public class NavBread extends SimpleTagSupport {

	private Object value;
	private BaseService baseService;
	private JspWriter out;

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public void doTag() throws JspException, IOException {
		baseService = new BaseService();
		out = getJspContext().getOut();
		// 获取类的名字
		String cName = value.getClass().getSimpleName();
		// 开始制作面包屑导航栏
		switch (cName) {
		case "Section":
			section();
			break;
		case "Topic":
			topic();
			break;
		}
	}

	private void section() throws IOException {
		Section section = (Section) value;
		Area area = (Area) baseService.quaryById(Area.class, section.getAreaId());
		out.write("<nav class=\"my-0\" aria-label=\"breadcrumb\" style=\"heigth:30px\" >\r\n"
				+ "  <ol class=\"breadcrumb\" style=\"background-color:#FFFFFF;\">\r\n"
				+ "    <li class=\"breadcrumb-item\"><a href=\"#\">" + area.getAreaName() + "</a></li>\r\n"
				+ "    <li class=\"breadcrumb-item active\" aria-current=\"page\">" + section.getSectionName()
				+ "</li>\r\n" + "  </ol>\r\n" + "</nav>");
	}

	private void topic() throws IOException {
		Topic topic = (Topic) value;
		Section section = (Section) baseService.quaryById(Section.class, topic.getSectionId());
		Area area = (Area) baseService.quaryById(Area.class, section.getAreaId());
		out.write("<nav class=\"my-0\" aria-label=\"breadcrumb\" style=\"heigth:30px\" >\r\n"
				+ "  <ol class=\"breadcrumb\" style=\"background-color:#FFFFFF;\">\r\n"
				+ "    <li class=\"breadcrumb-item\"><a href=\"#\">" + area.getAreaName() + "</a></li>\r\n"
				+ "    <li class=\"breadcrumb-item\"><a href=\"SectionDispatcher/section?sectionId=" + section.getId()
				+ "\">" + section.getSectionName() + "</a></li>\r\n"
				+ "    <li class=\"breadcrumb-item active\" aria-current=\"page\">" + topic.getTopicName() + "</li>\r\n"
				+ "  </ol>\r\n" + "</nav>");

	}

}
