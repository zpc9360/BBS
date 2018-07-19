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

</head>
<body>
	<div style="padding-top: 50px;"></div>
	<div class="container" style="width: 400px;">
		<form action="AdminDispatcher/addArea" method="POST">
			<div class="form-group">
				<label for="text">区域名:</label> <input type="text"
					class="form-control" name="areaName" placeholder="Enter Areaname">
			</div>

			<br>
			<button type="submit" class="btn btn-outline-warning btn-block">添加</button>
			<a class="btn btn-outline-warning" href="AdminDispatcher/areaManage">返回</a>
		</form>
	</div>

</body>
</html>