<!DOCTYPE HTML>
<html lang="en">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bbs" tagdir="/WEB-INF/tags/"%>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<title></title>
</head>
<body>
	<bbs:nav/>
	<div style="padding-top: 50px;"></div>
	<div class="container" style="width: 400px;">
		<form action="UserDispatcher/userDetailUpdate" method="POST">
			<div class="form-group">
				<label for="text">用户名:</label> <input type="text" readonly
					class="form-control" name="userName" placeholder="Enter username"
					value="${currenUser.userName}">
			</div>
			<div class="form-group">
				<label for="pwd">密码:</label> <input type="password"
					class="form-control" name="passWord" placeholder="Enter password"
					value="${currenUser.passWord }">
			</div>
			<div class="form-group">
				<label for="text">昵称:</label> <input type="text"
					class="form-control" name="nickName" placeholder="Enter nickname"
					value="${currenUser.nickName }">
			</div>

			<br>
			<button type="submit" class="btn btn-outline-warning btn-block">更新</button>
		</form>
	</div>

</body>
</html>