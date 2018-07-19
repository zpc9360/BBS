<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bbs" tagdir="/WEB-INF/tags/"%>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>用户管理页面</title>
</head>
<body>
	<bbs:nav />
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link text-info"
				href="AdminDispatcher/admin">管理首页</a></li>
			<li class="nav-item"><a class="nav-link text-info"
				href="AdminDispatcher/topicManage">帖子管理</a></li>
			<li class="nav-item"><a class="nav-link text-info"
				href="AdminDispatcher/sectionManage">版块管理</a></li>
			<li class="nav-item"><a class="nav-link text-info"
				href="AdminDispatcher/areaManage">区块管理</a></li>
			<li class="nav-item"><a class="nav-link active"
				href="AdminDispatcher/userManage">用户管理</a></li>
		</ul>
		<table class="table mt-2 table-hover">
			<thead class="thead-light">
				<tr>
					<th scope="col">id</th>
					<th scope="col">用户名</th>
					<th scope="col">密码</th>
					<th scope="col">昵称</th>
					<th scope="col">角色</th>
					<th scope="col">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList }" var="user">
					<tr>
						<th scope="col">${user.id }</th>
						<th scope="col">${user.userName }</th>
						<th scope="col">${user.passWord }</th>
						<th scope="col">${user.nickName }</th>
						<th scope="col">${user.roleId }</th>
						<th scope="col"><a class="btn btn-outline-warning"
							href="AdminDispatcher/toUserUpdate?userId=${user.id }">修改</a><a
							class="btn btn-outline-danger"
							href="AdminDispatcher/userDelete?userId=${user.id }"
							onclick="if(!confirm('确定删除？'))return false;">删除</a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		${pageBar }
	</div>
</body>
</html>