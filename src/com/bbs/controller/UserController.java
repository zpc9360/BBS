package com.bbs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bbs.factory.AuthorityFactory;
import com.bbs.po.Section;
import com.bbs.po.Topic;
import com.bbs.po.User;
import com.bbs.service.BaseService;
import com.bbs.service.UserService;
import com.bbs.util.Mail;
import com.bbs.util.MyUtil;

public class UserController {

	private UserService userService;
	private BaseService baseService;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserController() {
		super();
		userService = new UserService();
		baseService = new BaseService();
	}

	public String userTopicDelete() {
		long topicId = Long.parseLong(request.getParameter("topicId"));
		User user = (User) request.getSession().getAttribute("currenUser");
		Topic topic = (Topic) baseService.quaryById(Topic.class, topicId);
		if(user==null||user.getId()!=topic.getUserId()) {
			return "/WEB-INF/content/main/error.jsp";
		}
		Section section = (Section) baseService.quaryById(Section.class, topic.getSectionId());
		section.setSectionSum(section.getSectionSum()-1);
		baseService.update(section);
		baseService.deleteById(Topic.class, topicId);
		return "/BBS/HomePage/myZone";
	}

	// 用户是否存在
	public void isExist() {
		String flag = new BaseService().isExist(User.class, "userName='" + request.getParameter("email") + "'") ? "true"
				: "false";
		response.addHeader("flag", flag);
	}

	// 删除用户
	public String userDelete() {
		return "/BBS/Index";
	}

	// 用户详情
	public String userDetail() {
		return "/WEB-INF/content/user/userDetail.jsp";
	}

	// 去管理员页面
	public String toAdmin() {
		return "/WEB-INF/content/admin/admin.jsp";
	}

	// 去用户注册页面
	public String userToRegister() {
		return "/WEB-INF/content/user/userRegister.jsp";
	}

	// 更新后用户信息
	public String userDetailUpdate() {
		User user = (User) request.getSession().getAttribute("currenUser");
		if(user == null) {
			return "/WEB-INF/content/user/userLogin.jsp";
		}
		baseService.update(MyUtil.updateUser(user, request, response));
		return "/WEB-INF/content/user/userDetail.jsp";
	}
	// 去更新用户信息
	public String toUserDetailUpdate() {
		
		return "/WEB-INF/content/user/userDetailUpdate.jsp";
		
	}

	public void getVerifyCode() {
		Mail mail = new Mail(request.getParameter("email"));
		int safeCode = mail.getVaryCode();
		// System.out.println(safeCode);
		AuthorityFactory.setSafeCode(safeCode);
	}

	// 用户注册页面
	public String userRegister() {
		Object obj = request.getServletContext().getAttribute("safeCode");
		int safeCode = 0;
		if (obj != null) {
			safeCode = (int) obj;
		}
		String _safeCode = request.getParameter("verifyCode");
		if (MyUtil.notNull(_safeCode)) {
			if (Integer.parseInt(_safeCode) != safeCode) {
				request.setAttribute("errorVerifyCode", "验证码错误");
				request.setAttribute("userName", request.getParameter("userName"));
				request.setAttribute("passWord", request.getParameter("passWord"));
				request.setAttribute("nickName", request.getParameter("nickName"));
				return "/WEB-INF/content/user/userRegister.jsp";
			}
		}
		if ((new BaseService()).isExist(User.class, "userName='" + request.getParameter("email") + "'")) {
			request.setAttribute("errorUserName", "用户已存在");
			return "/WEB-INF/content/user/userRegister.jsp";
		}
		boolean flag = false;
		User user = MyUtil.getUser(request, response);
		if (user.getUserName().length() < 3 & user.getPassWord().length() < 6) {
			request.setAttribute("errorUserName", "用户名不能小于3字符");
			request.setAttribute("errorPassWord", "密码不能小于6字符");
			request.setAttribute("nickName", user.getNickName());
			return "/WEB-INF/content/user/userRegister.jsp";
		}
		if (user.getUserName().length() < 3) {
			request.setAttribute("errorUserName", "用户名不能小于3字符");
			request.setAttribute("nickName", user.getNickName());
			return "/WEB-INF/content/user/userRegister.jsp";
		}
		if (user.getPassWord().length() < 6) {
			request.setAttribute("errorPassWord", "用密码不能小于6字符");
			request.setAttribute("userName", user.getUserName());
			request.setAttribute("nickName", user.getNickName());
			return "/WEB-INF/content/user/userRegister.jsp";
		}
		if (user.getNickName().length() < 1) {
			request.setAttribute("errorNickName", "请输入昵称");
			request.setAttribute("userName", user.getUserName());
			return "/WEB-INF/content/user/userRegister.jsp";
		}
		String regex = ".*[^a-zA-Z0-9@.]+.*";
		if (Pattern.compile(regex).matcher(user.getUserName()).find()) {
			request.setAttribute("errorUserName", "用户名包含非法字符");
			request.setAttribute("userName", user.getUserName());
			return "/WEB-INF/content/user/userRegister.jsp";
		}
		flag = userService.userRegist(user);
		if (flag) {
			request.setAttribute("userName", user.getUserName());
			request.setAttribute("passWord", user.getPassWord());
			System.out.println("success");
			return "/WEB-INF/content/user/userLogin.jsp";
		}
		return "/BBS/UserDispatcher/userToRegister";
	}

	// 用户登出
	public String userLogout() {
		request.getSession().invalidate();
		MyUtil.cleanMyCookie("bbs_user", request, response);
		return "/BBS/Index";
	}

	// 用户登录
	public String userLogin() {
		String remeber = request.getParameter("remeber");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		// System.out.println(userName);
		if (userName.length() < 3 || passWord.length() < 6 || !MyUtil.notNull(userName) || !MyUtil.notNull(passWord)) {
			request.setAttribute("errorInfo", "请输入正确的用户名和密码");
			return "/WEB-INF/content/user/userLogin.jsp";
		}
		User u = new User();
		u.setUserName(userName);
		u.setPassWord(passWord);
		User user = userService.userLogin(u);
		if (null != user) {
			if (MyUtil.notNull(remeber)) {
				Cookie cookie = new Cookie("bbs_user", userName + "_" + passWord);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			AuthorityFactory.setRoleLevel(user.getRoleId());
			request.getSession().setAttribute("currenUser", user);
			return "/BBS/Index";
		}
		request.setAttribute("errorInfo", "用户名或密码错误");
		return "/WEB-INF/content/user/userLogin.jsp";
	}

	// 去用户登出页面
	public String userToLogin() {
		return "/WEB-INF/content/user/userLogin.jsp";
	}
	
	// 用COOKIE缓存登录用户
	public String userLoginByCookie() {
		Cookie[] cookies = request.getCookies();
		if (request.getSession().getAttribute("currenUser") == null) {
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("bbs_user".equals(c.getName())) {
						User u = new User();
						u.setUserName(c.getValue().split("_")[0]);
						u.setPassWord(c.getValue().split("_")[1]);
						User user = userService.userLogin(u);
						// System.out.println(user.getUserName());
						AuthorityFactory.setRoleLevel(user.getRoleId());
						request.getSession().setAttribute("currenUser", user);
					}
				}
			}
		}
		request.getSession().setAttribute("currenPage", "homePage");
		return "/BBS/HomePage/homePage";
	}

	public String headUpload() {

		// System.out.println("开始上传头像");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			// System.out.println("格式错误");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> items = sfu.parseRequest(request);
			if (items.isEmpty()) {
				// System.out.println("文件为空");
			}
			for (FileItem item : items) {
				// System.out.println("开始生成文件");
				// 在服务器上找一个存放文件的地方
				String filePath = request.getServletContext().getRealPath("\\pictures\\head");
				File storeDirectory = new File(filePath);
				if (!storeDirectory.exists()) {
					storeDirectory.mkdirs();
				}
				// 截取上传的文件名
				// filename=filename.substring(filename.lastIndexOf(File.separator)+1);
				User user = (User) request.getSession().getAttribute("currenUser");
				String fileName = user.getUserName() + ".jpg";
				String fullFileName = filePath + "\\" + fileName;
				user.setHeadImg(fileName);
				baseService.update(user);
				request.getSession().setAttribute("currenUser", user);
				// 获取item中的上传文件的输入流
				InputStream in = item.getInputStream();
				// 创建一个文件输出流
				// System.out.println(fullFileName);
				FileOutputStream out = new FileOutputStream(fullFileName);
				// 创建一个缓冲区
				byte buffer[] = new byte[1024];
				// 判断输入流中的数据是否已经读完的标识
				int len = 0;
				// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				while ((len = in.read(buffer)) > 0) {
					// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
					out.write(buffer, 0, len);
				}
				// 关闭输入流
				out.flush();
				in.close();
				// 关闭输出流
				out.close();
			}
		} catch (FileUploadException | IOException e1) {
			e1.printStackTrace();
		}
		return "/WEB-INF/content/user/userDetail.jsp";
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
