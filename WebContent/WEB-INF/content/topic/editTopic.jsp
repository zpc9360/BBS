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
<div style="padding-top: 50px;"></div>
	<div class="container" style="width: 400px;">
		<form action="editTopic" method="POST">
			<div class="form-group">
				<label for="text">帖子标题:</label> <input type="text" readonly
					class="form-control" name="topicName" placeholder="Enter topicname"
					value="${currentopic.topicName}">
			</div>
			<div class="form-group">
				<label for="text">正文内容:</label> <input type="text" readonly
					class="form-control" value="${currentopic.content}">
			</div>
		
			<br>
			<button type="submit" class="btn btn-outline-warning btn-block">更新</button>
		</form>
	</div>



</body>
</html>