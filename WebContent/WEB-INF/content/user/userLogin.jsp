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
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<bbs:nav/>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-9">
				<h3><a class="text-info" href="/BBS/Index">花径不曾缘客扫，蓬门今始为君开。</a></h3>
			</div>
		</div>
	</div>
	<div class="container" style="width: 350px;">
		<form action="UserDispatcher/userLogin" method="POST">
			<div class="form-group">
				<label for="text">用户名:</label><span class="badge badge-danger" style="margin-left:5px;">${errorInfo }</span>
				<input type="text"
					class="form-control" name="userName" placeholder="Enter username" maxlength="20" value="${userName }">
			</div>
			<div class="form-group">
				<label for="pwd">密码:</label> <input type="password"
					class="form-control" name="passWord" placeholder="Enter password" maxlength="20" value="${passWord }">
			</div>
			<div class="form-check" style="padding-left: 25px;">
				<label class="form-check-label"> <input
					class="form-check-input" type="checkbox" name="remeber">
					一周之内记住我
				</label>
			</div>
			<br>
			<button type="submit" class="btn btn-outline-info btn-block">登录</button>
		</form>
		<div align="right" style="padding-top: 5px;">
			<p>
				还没账号？<a class="text-info" href="/BBS/UserDispatcher/userToRegister">注册</a>
			<p>
		</div>
	</div>

</body>
</html>