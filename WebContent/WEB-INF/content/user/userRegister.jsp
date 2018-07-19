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
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>用户注册</title>
</head>
<body>
	<bbs:nav />
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-9">
				<h3>
					<a class="text-success" href="/BBS/Index">有朋自远方来,不亦乐乎？</a>
				</h3>
			</div>
		</div>
	</div>

	<div class="container" style="width: 450px;">
		<form action="UserDispatcher/userRegister" method="POST">
			<div class="form-group">
				<label for="email">邮箱:</label><span class="badge badge-danger"
					id="errEmail" style="margin-left: 5px;">${errorUserName }</span>
				<div class="row">
					<div class="col-8">
						<input type="email" class="form-control" name="userName"
							onblur="checkEmail()" placeholder="请输入邮箱"
							maxlength="20" value="${userName }">
					</div>
					<div class="col">
						<button id="getCode" type="button" class="btn btn-outline-success" disabled
							onclick="getVerifyCode()">获取验证码</button>
					</div>
				</div>

			</div>
			<div class="form-group">
				<label for="pwd">密码:</label><span class="badge badge-danger"
					style="margin-left: 5px;">${errorPassWord }</span>
				<div class="row">
					<div class="col-8">
						<input type="password" class="form-control" name="passWord"
							placeholder="Enter password Length(6~20)" maxlength="20"
							value="${passWord }">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="text">昵称:</label><span class="badge badge-danger"
					style="margin-left: 5px;">${errorNickName }</span>
				<div class="row">
					<div class="col-8">
						<input type="text" class="form-control" name="nickName"
							placeholder="Enter nickname" maxlength="20" value="${nickName }">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="text">验证码:</label><span class="badge badge-danger"
					style="margin-left: 5px;">${errorVerifyCode }</span>
				<div class="row">
					<div class="col-8">
						<input type="text" class="form-control" name="verifyCode"
							placeholder="Enter verifyCode" maxlength="6">
					</div>
				</div>
			</div>

			<div class="form-radio" align="center">
				<div class="row">
					<div class="col-8">
						<label class="form-radio-label"> <input
							class="form-radio-input" type="radio" name="gender" value="男">&nbsp;男
						</label> <label class="form-radio-label" style="padding-left: 50px">
							<input class="form-radio-input" type="radio" name="gender"
							value="女">&nbsp;女
						</label>
					</div>
				</div>
			</div>
			<br>
			<div class="form-group row">
				<div class="col-8">
					<button type="submit" class="btn btn-outline-success btn-block">注册</button>
				</div>
			</div>
		</form>
		<div style="padding-top: 5px;" align="right" class="row">
			<div class="col-8">
				<p>
					已有账号？ <a class="text-success"
						href="/BBS/UserDispatcher/userToLogin">登录</a>
				</p>
			</div>
		</div>
	</div>

	<script>
		function checkEmail(){
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
			var obj = document.getElementsByName("userName")[0]; //要验证的对象
			if(obj.value === ""){ //输入不能为空
				alert("输入不能为空!");
				return false;
			}else if(!reg.test(obj.value)){ //正则验证不通过，格式不对
				alert("验证不通过!");
				return false;
			}else{
				checkEmailExist();
				document.getElementById("getCode").disabled="";
			}
		}
		function getVerifyCode(){
			fetch(window.location.protocol+"//"+window.location.host+"/BBS/UserDispatcher/getVerifyCode",{
				method: 'POST',
				headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
				body: 'email='+(document.getElementsByName("userName")[0]).value
			})
			document.getElementById("getCode").disabled='disabled'
			
		}
		function checkEmailExist() {
			fetch(window.location.protocol+"//"+window.location.host+"/BBS/UserDispatcher/isExist",{
				method: 'POST',
				headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
				body: 'email='+(document.getElementsByName("userName")[0]).value
			})
			.then(function(response){
		        isExit(response.headers.get('flag'));
			})
			.catch(function(err){
			    console.log("Fetch错误:"+err);
			});
		}
		function isExit(flag){
			if(flag=="true"){
				document.getElementById("errEmail").innerHTML="账户已存在";
				document.getElementById("getCode").disabled="disabled";
			}else{
				document.getElementById("errEmail").innerHTML="";
				document.getElementById("getCode").disabled="";
			}
		}
	</script>

</body>
</html>