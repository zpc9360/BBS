<!DOCTYPE HTML>
<html lang="zh-CN">
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

<script src="ueditor/ueditor.config.js"></script>
<script src="ueditor/ueditor.all.js"></script>

<title>发帖页面</title>
</head>
<body>
	<bbs:nav />
	<div class="container">
		<div class="row">
			<div class="col-md" align="center">
				<form action="TopicDispatcher/addTopic">
					<input type="hidden" name="sectionId" value="${sectionId }">
					<input type="text" name="topicName" class="form-control"
						placeholder="请输入标题" /><br />
					<textarea id="container" name="content"></textarea>
					<br /> <input type="submit" value="发帖" class="btn btn-default" />
					<input type="reset" value="清空" class="btn btn-default">
				</form>
			</div>
		</div>
	</div>
</body>
<script>
	var ue = UE.getEditor('container');
</script>
</html>