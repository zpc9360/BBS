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
<title>论坛管理页面</title>
</head>
<body>
	<bbs:nav />

	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active" href="AdminDispatcher/admin">管理首页</a></li>
			<li class="nav-item"><a class="nav-link text-info" href="AdminDispatcher/topicManage">帖子管理</a></li>
			<li class="nav-item"><a class="nav-link text-info" href="AdminDispatcher/sectionManage">版块管理</a></li>
			<li class="nav-item"><a class="nav-link text-info" href="AdminDispatcher/areaManage">区块管理</a></li>
			<li class="nav-item"><a class="nav-link text-info" href="AdminDispatcher/userManage">用户管理</a></li>
		</ul>
	</div>
</body>
</html>