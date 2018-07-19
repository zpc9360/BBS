package com.bbs.util;

import java.util.List;

import com.bbs.service.BaseService;

public class Page {

	private BaseService baseService;
	// 当前页
	private int curPage = 1;// get set
	// 每页显示多少页，默认15条
	private int rowsPrePage = 15;
	// 通过数据库来获取数据总记录数
	private int DBRowCount;
	// 计算总页数
	private int MaxPage;// get set
	// 从数据库获取的已分页的list
	private List<?> list;// get set

	// 要显示几页,默认为5
	private int urlMaxPage = 5;
	private StringBuffer url;// 要返回的链接
	//

	private String orderStr = "";

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	private String actionUrl;

	public Page() {
		baseService = new BaseService();
		url = new StringBuffer("");
	}

	private void setMyUrl() {
		// System.out.println("总记录条数" + DBRowCount);
		// System.out.println("总记页数" + MaxPage);
		if (actionUrl.contains("?")) {
			actionUrl = actionUrl + "&";
		} else {
			actionUrl = actionUrl + "?";
		}
		url.append("<nav aria-label=\"curPage\">\r\n");
		url.append("	<ul class=\"pagination justify-content-center\">\r\n");
		// 上一页
		if (curPage > 1) {
			url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + (curPage - 1)
					+ "\">上一页</a></li>\r\n");
		}
		// 如果获取到的总页数小于urlMaxPage+1也就是6的话
		if (MaxPage <= urlMaxPage + 1) {
			smallPage();
		} else {
			bigPage();
		}
		// 下一页
		if (curPage < MaxPage) {
			url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + (curPage + 1)
					+ "\" aria-label=\"Next\">下一页</a></li>\r\n");
		}
		url.append("	</ul>\r\n");
		url.append("</nav>\r\n");
	}

	private void bigPage() {
		// 如果当前页大于1的话，加上上一页的链接
		if (curPage == 1) {
			url.append("<li class=\"page-item active\"><span  class=\"page-link\">" + 1 + "</span></li>\r\n");
		} else {
			url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + 1 + "\">" + 1
					+ "</a></li>\r\n");
		}
		if (curPage <= 3) {
			for (int i = 2; i <= 5; i++) {
				// 如果是当前页数，设置为文版显示；如果不是，设置为链接
				if (curPage == i) {
					url.append("<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
				} else {
					url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i
							+ "\">" + i + "</a></li>\r\n");
				}
			}
			url.append("<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
		}
		if (curPage > 3 && curPage < MaxPage - 2) {
			if (curPage > 3 && MaxPage != 7) {
				url.append("<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
			}
			for (int i = curPage - 2; i <= curPage + 2; i++) {
				if (curPage == i) {
					url.append("<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
				} else {
					url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i
							+ "\">" + i + "</a></li>\r\n");
				}
			}
			if (curPage < MaxPage - 3 && MaxPage != 7) {
				url.append("<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
			}
		}
		if (curPage >= MaxPage - 2) {
			url.append("<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
			for (int i = MaxPage - 4; i < MaxPage; i++) {
				// 如果是当前页数，设置为文版显示；如果不是，设置为链接
				if (curPage == i) {
					url.append("<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
				} else {
					url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i
							+ "\">" + i + "</a></li>\r\n");
				}
			}
		}
		if (curPage == MaxPage) {
			url.append("<li class=\"page-item active\"><span  class=\"page-link\">" + MaxPage + "</span></li>\r\n");
		} else {
			url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + MaxPage
					+ "\">" + MaxPage + "</a></li>\r\n");
		}
	}

	private void smallPage() {
		for (int i = 1; i <= MaxPage; i++) {
			// 如果是当前页数，设置为文版显示；如果不是，设置为链接
			if (curPage == i) {
				url.append("<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
			} else {
				url.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i + "\">"
						+ i + "</a></li>\r\n");
			}
		}
	}

	public void init(Class<?> c) {
		DBRowCount = baseService.total(c);
		MaxPage = DBRowCount % rowsPrePage == 0 ? (DBRowCount / rowsPrePage) : (DBRowCount / rowsPrePage + 1);
		this.list = (List<?>) baseService.quarryByPage(c, (curPage - 1) * rowsPrePage, rowsPrePage, orderStr);
		setMyUrl();
	}

	public void init(Class<?> c, String rule) {
		DBRowCount = baseService.total(c);
		MaxPage = DBRowCount % rowsPrePage == 0 ? (DBRowCount / rowsPrePage) : (DBRowCount / rowsPrePage + 1);
		this.list = (List<?>) baseService.quarryByPage(c, rule, (curPage - 1) * rowsPrePage, rowsPrePage, orderStr);
		setMyUrl();
	}

	public void init(Class<?> c, String key, String value) {
		DBRowCount = baseService.total(c, key, value);
		MaxPage = DBRowCount % rowsPrePage == 0 ? (DBRowCount / rowsPrePage) : (DBRowCount / rowsPrePage + 1);
		this.list = (List<?>) baseService.quarryByPage(c, key, value, (curPage - 1) * rowsPrePage, rowsPrePage,
				orderStr);
		setMyUrl();
	}

	public void init(Class<?> c, String key, int value) {
		DBRowCount = baseService.total(c, key, value);
		MaxPage = DBRowCount % rowsPrePage == 0 ? (DBRowCount / rowsPrePage) : (DBRowCount / rowsPrePage + 1);
		this.list = (List<?>) baseService.quarryByPage(c, key, value, (curPage - 1) * rowsPrePage, rowsPrePage,
				orderStr);
		setMyUrl();
	}

	public void init(Class<?> c, String key, long value) {
		DBRowCount = baseService.total(c, key, value);
		MaxPage = DBRowCount % rowsPrePage == 0 ? (DBRowCount / rowsPrePage) : (DBRowCount / rowsPrePage + 1);
		this.list = (List<?>) baseService.quarryByPage(c, key, value, (curPage - 1) * rowsPrePage, rowsPrePage,
				orderStr);
		setMyUrl();
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getMaxPage() {
		return MaxPage;
	}

	public void setMaxPage(int maxPage) {
		MaxPage = maxPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public StringBuffer getUrl() {
		return url;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

}
