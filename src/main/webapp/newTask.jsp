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
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link href="css/datetimepicker.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/application.js"></script>
<script src="js/holder.js"></script>


<title>CRC new Task</title>
</head>
<body role="document">
	<div id="alert-user" class="alert alert-danger hidden" role="alert">
		<strong>错误：</strong>请输入您用户名
	</div>
	<nav class="navbar navbar-inverse  navbar-fixed-top">
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
			<ul class="nav navbar-nav navbar-right">

				<%if(session.getAttribute("username")!=null){%>
					<li><a href="My CRC.jsp" id="user-name">${username}</a></li>
					<li><a id="login" style="cursor: pointer;">登出</a></li>
				<%}else{%>
				<%--username--%>
				
				<li><a id="login" style="cursor: pointer;">登录</a></li>
				<%} %>

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
						<input type="text" name='username' class="form-control input-lg"
							id="username" placeholder="用户名">
					</div>
					<div class="form-group">
						<input type="password" name="password" id="password"
							class="form-control input-lg" placeholder="登录密码">
					</div>
					<div class="form-group">
						<button class="btn btn-primary btn-lg btn-block">立刻登录</button>
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
							<input type="text" name="mail" class="form-control input-lg"
								placeholder="邮箱">
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
	<div class="container">
		<div class="jumbotron">
			<h2>新任务</h2>
			<p>创建你的新任务</p>
		</div>
		<form class="form-horizontal" action="">
			<div class="form-group">
				<label for="inputName" class="col-sm-2 control-label">评审名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputName"
						placeholder="关于***项目的n号评审">
				</div>
			</div>
			<div class="form-group">
				<label for="type" class="col-sm-2 control-label">评审类型</label>
				<div class="col-sm-10">
					<select class="form-control" id="type">
						<option>文档评审</option>
						<option>代码评审</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="discription" class="col-sm-2 control-label">评审说明</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="3" id="discription"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="deadline" class="col-sm-2 control-label">结束时间</label>
				<div class="col-sm-10">
					<div class="input-append date form_datetime">
						<input size="16" type="text" value="" readonly name="deadline">
						<span class="add-on"><i class="icon-remove"></i></span> <span
							class="add-on"><i class="icon-calendar"></i></span>
					</div>
				</div>
			</div>
		</form>
		<div class="form-group" style="text-align: right;">
			<button class="btn btn-success " id="createTask">确认创建</button>
		</div>
	</div>
	<script type="text/javascript">
		$(".form_datetime").datetimepicker({
			format : "dd MM yyyy - hh:ii",
			autoclose : true,
			todayBtn : true,
			startDate : "2013-02-14 10:00",
			minuteStep : 10
		});
	</script>

	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
</body>

</html>