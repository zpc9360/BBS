<!DOCTYPE HTML>
<html lang="en">
<head>
<base href="${basePath}">
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
<title>作者空间</title>
</head>
<body>
	<bbs:nav />
	<div class="container">
		<div class="row">
			<div class="col-md-3 border border-info my-2">
					<img alt="头像" src="pictures/head/${author.headImg }"
						class="my-2 rounded img-fluid border border-info" />
				<span class="py-2 mt-2 badge badge-warning">帖主性别：${author.gender }</span>
				<span class="py-2 mt-2 badge badge-danger">帖主级别：${authorRole }</span>
			</div>
			<div class="col-md-9 my-2">
				<div class="card border-info">
					<div class="card-header border-info text-info">
						<div class="row">
							<div class="col align-self-start">
								<h4>${author.nickName }的贴子</h4>
							</div>
						</div>
					</div>
					<div class="card-body">
						<div class="list-group">
							<form action="TopicDispatcher/topic" method="GET">
								<c:forEach items="${topicList }" var="topic">
									<button class="list-group-item list-group-item-action my-1"
										style="cursor: pointer;" type="submit" value="${topic.id }"
										name="id">
										<div class="row">
											<div class="col-md-10">${topic.topicName }</div>
											<div class="col-md-2 text-right">
												<span class="badge badge-pill badge-light">回复：${topic.totalResponse }</span>
												<span class="badge badge-pill badge-light">浏览：${topic.totalView }</span>
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
		</div>
	</div>

</body>
</html>