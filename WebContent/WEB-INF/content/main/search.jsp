<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bbs" tagdir="/WEB-INF/tags/"%>
<base href="basePath">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<title></title>
</head>
<body>

	<bbs:nav />
	<div class="container">
		<div class="card border-info">
			<div class="card-header border-info text-info">
				<h4>搜索结果</h4>
			</div>
			<div class="card-body">
				<div class="list-group">
					<form action="TopicDispatcher/topic" method="GET">
						<c:forEach items="${topicList }" var="topic">
							<button class="list-group-item list-group-item-action my-1" style="cursor:pointer;" type="submit"
								value="${topic.id }" name="id" style="margin: 0 0 5px 0;">
								<div class="row">
									<div class="col-md-8"><h4><strong>${topic.topicName }</strong></h4></div>
									<div class="col-md-4 text-right">
										<span class="badge badge-pill badge-light">回复：${topic.totalResponse }</span> <span
											class="badge badge-pill badge-light">浏览：${topic.totalView }</span>
									</div>
								</div>
							</button>
						</c:forEach>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>