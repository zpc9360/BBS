<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bbs" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="my" uri="/WEB-INF/tagtld/my.tld"%>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>

	<bbs:nav />
	<div class="container">
		<my:breadNav value="${section}"/>
		<div class="card border-info" >
			<div class="card-header border-info text-info">
					<div class="row">
						<div class="col-md-10">
							<h4>${section.sectionName }</h4>
						</div>
						<div class="col-md-2 text-right">
							<a class="btn btn-success"
								href="TopicDispatcher/toAddTopic?sectionId=${section.id}">我要发帖</a>
						</div>
					</div>
			</div>
			<div class="card-body">
				<div class="list-group">
					<form action="TopicDispatcher/topic" method="GET">
						<c:forEach items="${topicList }" var="topic">
							<button class="list-group-item list-group-item-action my-1" type="submit" style="cursor:pointer;"
								value="${topic.id }" name="id" style="margin: 0 0 5px 0;">
								<div class="row">
									<div class="col-md-9">${topic.topicName }</div>
									<div class="col-md-3 text-right">
										<span class="badge">回复：${topic.totalResponse }</span> <span
											class="badge">浏览：${topic.totalView }</span>
									</div>
								</div>
							</button>
						</c:forEach>
					</form>
					${pageBar }
				</div>
			</div>
		</div>
	</div>
</body>
</html>