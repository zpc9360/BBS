<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="bbs" tagdir="/WEB-INF/tags/"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>板块修改</title>
</head>
<body>
	<div style="padding-top: 50px;"></div>
	<div class="container" style="width: 400px;">
		<form action="AdminDispatcher/adminSectionUpdate" method="POST">
			<div class="form-group">
				<label for="text">所属区域:</label> <select class="form-control"
					name="areaId">
					<c:forEach items="${areaList }" var="area">
						<option value="${area.id }">${area.areaName }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="text">板块标题:</label> <input type="text"
					class="form-control" name="sectionName"
					value="${section.sectionName}">
			</div>
			<div>
				<input type="hidden" name="id" value="${section.id}">
			</div>

			<br>
			<button type="submit" class="btn btn-outline-warning btn-block">更新</button>
		</form>
	</div>



</body>
</html>