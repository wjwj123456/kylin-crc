<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/theme.css" rel="stylesheet">
<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<title>CRC Index</title>
</head>
<body role="document">
	<nav class="navbar  navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">CRC在线评审系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="#">首页</a></li>
				<li><a href="#about">关于</a></li>
				<li><a href="#about">联系我们</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">帮助<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#introduction">功能介绍</a></li>
						<li><a href="#details">使用说明</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#about">git地址</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" >
				
				<li><a data-toggle="modal" data-target="#loginModal" href="" id="login">登录</a></li>

			</ul>
		</div>
	</div>
	</nav>
	<div id="loginModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">x</button>
					<h1 class="text-center text-primary">登录</h1>
				</div>
				<div class="modal-body" style="height: 250px">
					
						<div class="form-group">
							<input type="text" name='username' class="form-control input-lg" id="username"
								placeholder="用户名">
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password"
								class="form-control input-lg" placeholder="登录密码">
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-lg btn-block" onclick="submit()">立刻登录</button>
							<span><a data-toggle="modal" data-target="#signUpModal"
								data-dismiss="modal" href="" class="pull-right">注册</a></span>

						</div>
					
				</div>
			</div>
		</div>
	</div>

	<div id="signUpModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">x</button>
					<h1 class="text-center text-primary">注册</h1>
				</div>
				<div class="modal-body" style="height: 300px">
					<form action="/register" class="form col-md-12 center-block"
						 method="get">
						<div class="form-group">
							<input type="text" name='username' class="form-control input-lg"
								placeholder="用户名">
						</div>
						<div class="form-group">
							<input type="password" name="password"
								class="form-control input-lg" placeholder="登录密码">
						</div>
						<div class="form-group">
							<input type="text" name="mail"
								class="form-control input-lg" placeholder="邮箱">
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-lg btn-block">立刻注册</button>
							<span><a data-toggle="modal" data-target="#loginModal"
								data-dismiss="modal" href="" class="pull-right">已有账号，点此登陆</a></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="jumbotron">
		<div class="container">
			<div class="col-md-4">
				<h1>欢迎</h1>
			</div>
			<div class="col-md-8">
				<ul>
					<li><a href="#new">新建评审</a></li>
				</ul>
			</div>
			
			
		</div>
		
	</div>
	
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug --> 
	<script src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
</body>

</html>