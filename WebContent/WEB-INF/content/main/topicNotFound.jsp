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
<title>资源未找到</title>
</head>
<body>
	<bbs:nav />
	<div class="container">
	</div>
	<br>
	<p>
	<h1 align="center">404 Not Found<br>您所访问的资源不存在</h1>
	<p><h3 align="center">
		<button class="btn btn-link">5  秒后自动返回上一页</button>
		<a href="Index" class="btn btn-link">首页</a></h3>
	<hr>
</body>
<script type="text/javascript">setTimeout("window.history.go(-1)",3000)</script>
</html>