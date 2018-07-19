<!DOCTYPE HTML>
<%@page import="com.bbs.service.BaseService"%>
<html lang="zh-CN">
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

<script src="ueditor/ueditor.config.js"></script>
<script src="ueditor/ueditor.all.js"></script>


<script type="text/javascript">
	window._bd_share_config = {
		"common" : {
			"bdSnsKey" : {},
			"bdText" : "",
			"bdMini" : "1",
			"bdMiniList" : false,
			"bdPic" : "",
			"bdStyle" : "0",
			"bdSize" : "16"
		},
		"share" : {}
	};
	with (document)
		0[(getElementsByTagName('head')[0] || body)
				.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
				+ ~(-new Date() / 36e5)];
</script>
<script>
	var flag = 0;
	function start() {
		var text = document.getElementById("myDiv");
		if (!flag) {
			text.style.color = "red";
			text.style.background = "#FF0000";
			flag = 1;
		} else {
			text.style.color = "";
			text.style.background = "";
			flag = 0;
		}
		setTimeout("start()", 100);
	}
</script>

<title>${topic.topicName }</title>
</head>
<body onload="start()">

	<bbs:nav />
	<div class="container">
	<my:breadNav value="${topic }"/>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="border border-info rounded">
					<img alt="头像" src="pictures/head/${User.headImg }"
						class="rounded img-fluid" />
				</div>
				<a href="HomePage/authorZone?userId=${User.id }"><span
					class="py-2 mt-2 btn btn-block badge badge-secondary ">帖主：${User.nickName }</span></a><br>
				<span class="py-2 mt-2 badge badge-warning">帖主性别：${User.gender }</span><br>
				<c:if test="${User.roleId > 2 }">
					<span class="py-2 mt-2 badge badge-danger" id="myDiv">帖主级别：${userRole }</span>
					<br>
				</c:if>
				<c:if test="${User.roleId eq 2 }">
					<span class="py-2 mt-2 badge badge-danger">帖主级别：${userRole }</span>
					<br>
				</c:if>
			</div>
			<div class="col-md-9">
				<ul class="list-group">
					<li class="list-group-item list-group-item-primary">
						<h2>
							<samp>${topic.topicName }</samp>
						</h2>
					</li>
					<li class="list-group-item"><samp>${topic.content }</samp> <br>
						<span style="display: block; float: right;">${delete } <span
							class="badge badge-success">#楼主#</span> <span
							class="badge badge-light"> 回复数：${topic.totalResponse }</span> <span
							class="badge badge-light">浏览总数：${topic.totalView }</span> <span
							class="badge badge-secondary"> 发帖时间：${topic.createDate }</span>
					</span></li>
				</ul>
				<div class="bdsharebuttonbox my-2">
					<a href="#" class="bds_more" data-cmd="more"></a> <a href="#"
						class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a> <a
						href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a> <a
						href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a> <a
						href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
					<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
				</div>
				<c:if test="${topicList.size() != 0}">
					<div>
						<span>评论列表：</span>
						<table class="table table-hover">
							<%
								int i = 2;
							%>
							<c:forEach items="${topicList }" var="topic">
								<tr>
									<td width="42px">

									<a
										href="HomePage/authorZone?userId=${topic.userId }"><img
											class="rounded-circle" style="width: 50px; heigth: 50px;"
											src="pictures/head/${topic.headImg }" /></a></td>
									<td>
										<span class = "text-left">
											<span class="badge badge-secondary badge-pill"> #<%out.print(i);i++;%>楼#</span>  
											<span class="badge badge-light">${topic.nickName }</span> 
											<span class="badge badge-info">${topic.role }</span> 
											<c:if test="${User.id eq topic.userId}">
													<span class="badge badge-danger">楼主</span>	
											</c:if>
										</span>
										<span class = "text-right">
											<span class="badge badge-secondary">回帖时间：${topic.createDate }</span>
										</span>
											<br>
											${topic.content }
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<div class="row">
					<div class="col-md" align="center">
						<form action="TopicDispatcher/newTopic" method="post">
							<input type="hidden" name="topicId" value="${topic.id}">
							<textarea id="container" name="content"></textarea>
							<input type="submit" value="回帖" class="btn btn-default" />
							<input type="reset" value="清空" class="btn btn-default">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var ue = UE.getEditor('container');
</script>
</html>