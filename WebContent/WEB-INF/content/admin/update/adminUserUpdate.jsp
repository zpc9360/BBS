<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="bbs" tagdir="/WEB-INF/tags/"%>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>用户信息修改</title>
</head>
<body>
	<div style="padding-top: 50px;"></div>
	<div class="container" style="width: 400px;">
		<form action="AdminDispatcher/adminUserUpdate" method="POST">
			<div class="form-group">
				<label for="text">用户名:</label> <input type="text"
					class="form-control" name="userName" value="${user.userName}">
			</div>
			<div class="form-group">
				<label for="text">密码:</label> <input type="text"
					class="form-control" name="userName" value="${user.passWord}">
			</div>
			<div class="form-group">
				<label for="text">用户昵称:</label> <input type="text"
					class="form-control" name="nickName" value="${user.nickName}">
			</div>
			<div>
				<input type="hidden" name="id" value="${user.id}">
			</div>
			 <br>
			<button type="submit" class="btn btn-outline-warning btn-block">更新</button>
		</form>
	</div>


</body>
</html>