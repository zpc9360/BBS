package com.bbs.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bbs.po.Area;
import com.bbs.po.Section;
import com.bbs.service.BaseService;

public class BodyTag extends SimpleTagSupport {

	private JspContext context;
	private JspWriter out;
	private BaseService baseService;
	
	@Override
	public void doTag() throws JspException, IOException {

		this.context = getJspContext();
		this.out = context.getOut();
		baseService = new BaseService();
		init();

	}

	private void init() throws IOException {
		for (Object obj : baseService.quaryAll(Area.class)) {
			Area area = (Area) obj;
			out.write("<!-- Area 循环-->\r\n");
			out.write("<div class=\"card border-info mb-3\">\r\n");
			out.write("	<div class=\"card-header border-info\" style=\"color:#4169E1;\">\r\n");
			out.write("		<h4 style=\"\">");
			out.write(area.getAreaName());
			out.write("		</h4>\r\n");
			out.write("	</div>\r\n");
			out.write("	<div class=\"card-body\">\r\n");
			out.write("		<div class=\"row\">\r\n");
			getSection(area);
			out.write("		</div>\r\n");
			out.write("	</div>\r\n");
			out.write("</div>\r\n");
		}
	}

	private void getSection(Area area) throws IOException {
		List<?> list = baseService.quaryAllByPram(Section.class, "areaId", area.getId());
		if(list.isEmpty()) {
			out.print("<p style=\"padding:0 15px 0 15px;\">啊偶，主上大人好像还没有在这个版块下面加东西呢（*^.^*）</p>");
		}
		for(Object obj : list) {
			Section section = (Section) obj;
					out.write("			<!-- Section 循环-->\r\n");
					out.write("			<div class=\"my-2 col-sm-6 col-md-4 col-lg-3\">\r\n");
					out.write("				<div class=\"card\">\r\n");
					out.write("					<div class=\"card-body\">\r\n");
					out.write("						<h5 class=\"card-title\"><a class=\"text-info\" href=\"/BBS/SectionDispatcher/section?sectionId="+section.getId()+"\">"+section.getSectionName()+"</a></h5>\r\n");
					out.write("						<p class=\"card-text\">总帖数：  "+section.getSectionSum()+"</p>\r\n");
					out.write("					</div>\r\n");
					out.write("				</div>\r\n");
					out.write("			</div>\r\n");
		}
	}

}
