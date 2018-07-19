<!DOCTYPE HTML>
<html lang="en">
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
	<bbs:nav/>

	<h1 align="center">用户个人资料</h1>
	<div class="container" align="center">

		<table class="table">
			<tr class="alert alert-info">
				<th class="text-center">用户邮箱</th>
				<th class="text-center">用户密码</th>
				<th class="text-center">昵称</th>
				<th class="text-center">性别</th>
				<th class="text-center">用户角色</th>
				<th class="text-center">操作</th>
			</tr>
			<tr>
				<td class="text-center">${currenUser.userName }</td>
				<td class="text-center">${currenUser.passWord }</td>
				<td class="text-center">${currenUser.nickName }</td>
				<td class="text-center">${currenUser.gender }</td>
				<td class="text-center">${currenUser.roleId }</td>
				<td   class="text-center" scope="col"> <a class="btn btn-outline-warning"
				href="UserDispatcher/toUserDetailUpdate">修改</a></td>
			</tr>
		</table>
				<label class="text-danger">用户头像修改：</label>
        <div class="all border border-dark" style="width:202px;height:202px">  
            <div id="oDiv">
            	<img style="width:200px;height:200px" src="pictures/head/${currenUser.headImg }"/>
            </div>  
        </div>  
		<form action="UserDispatcher/headUpload" method="POST" enctype="multipart/form-data" class="my-2">
			<div class="ml-5 form-group" style="width:200px;">
				<input type="file" id="headPhoto" name="headPhoto">
				<p class="help-block">请上传小于1MB的图片</p>
			</div>
			<div class="from-group">
				<button class="btn btn-outline-success">上传</button>
			</div>
		</form>
	</div>
	
	    <script type="text/javascript">  
        document.getElementById("headPhoto").addEventListener("change",function(e){  
            var files = this.files;  
              
            var img = new Image();  
            var render  = new FileReader();  
            render.readAsDataURL(files[0]);  
            render.onloadstart = function(){  
//              alert("start")  
            };  
            render.onload = function(){  
                img.src = this.result;  
                img.style.height = "100%";  
                img.style.width = "100%";  
                var oDiv = document.getElementById("oDiv");  
                oDiv.innerHTML = "";  
                oDiv.appendChild(img);  
//              alert("success");  
            };  
            render.onloadend = function(){  
//              alert("end");  
            }  
        });  
  
    </script>
</body>
</html>