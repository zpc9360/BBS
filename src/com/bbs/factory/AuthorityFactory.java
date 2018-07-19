package com.bbs.factory;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bbs.po.Authority;
import com.bbs.service.BaseService;
import com.bbs.util.DbUtil;

public class AuthorityFactory implements ServletContextListener {

	private static int roleLevel = 1;//默认权限
	private static List<Authority> list = null;
	private static Set<String> set = null;
	private static BaseService baseService;
	private static ServletContext context;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		DbUtil.getInstance().destoryConnection();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		context = arg0.getServletContext();
		baseService = new BaseService();
		init();
	}

	private void init() {
		authorityUpdate();
	}

	@SuppressWarnings("unchecked")
	private static void authorityUpdate() {
		list = (List<Authority>) baseService.quaryAllByPram(Authority.class, "roleId<", roleLevel);
		set = new TreeSet<String>();
		for (Authority authority : list) {
			set.add(authority.getAuthorityName());
		}
	}
	public static void setSafeCode(int safeCode) {
		context.setAttribute("safeCode", safeCode);
	}

	public static boolean authorityIn(String action) {
		return set.contains(action);
	}

	public static int getRoleLevel() {
		return roleLevel;
	}

	public static void setRoleLevel(int roleLevel) {
		AuthorityFactory.roleLevel = roleLevel;
		authorityUpdate();
	}

}
