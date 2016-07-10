<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/otherTheme.css" rel="stylesheet">
<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="http://v3.bootcss.com/assets/css/docs.min.css"
	rel="stylesheet">
<title>CRC Task</title>
</head>
<body role="document">
	<nav class="navbar navbar-inverse">
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
				<%
					if (session.getAttribute("username") != null) {
				%>
				<li><a href="My CRC.jsp" id="user-name">${username}</a></li>
				<li><a id="login" style="cursor: pointer;">登出</a></li>
				<%
					} else {
				%>
				<%--username--%>

				<li><a id="login" style="cursor: pointer;">登录</a></li>
				<%
					}
				%>
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

					<div class="form-group " id="usergroup">
						<input type="text" name='username' class="form-control input-lg"
							id="username" placeholder="用户名">
					</div>
					<div class="form-group" id="passgroup">
						<input type="password" name="password" id="password"
							class="form-control input-lg" placeholder="登录密码">
					</div>
					<div class="form-group">
						<button class="btn btn-primary btn-lg btn-block" onclick="login()"
							id="loginNow">立刻登录</button>
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
							<button class="btn btn-primary btn-lg btn-block"
								onclick="register()" id="registNow">立刻注册</button>
							<span><a data-toggle="modal" data-target="#loginModal"
								data-dismiss="modal" href="" class="pull-right">已有账号，点此登陆</a></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div id="suspensionNavigation" class="col-md-2" role="complementary">
			<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm">
			<ul class="nav bs-docs-sidenav">

				<li><a href="#title">评审名</a></li>
				<li><a href="#message">说明</a></li>
				<li><a href="#deadline">截止时间</a></li>
				<li><a href="#review">评审</a></li>
				<li><a href="#report">评审报告</a></li>
			</ul>
			<a class="back-to-top" href="#top"> 返回顶部 </a> </nav>
		</div>
		<div class="col-md-10 bs-docs-section">
			<h2 id="title">XXX</h2>
			<hr>
			<h2 id="message">说明</h2>
			<p>类型：XX评审</p>
			<p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>
			<button class="btn btn-success" id="join">参加评审</button>
			<hr>
			<h2 id="deadline">截止时间</h2>
			<strong>XXXX-XX-XX XX:XX</strong>
			<hr>
			<h2 id="review">评审</h2>
			<p id="preWord">开始评审前，请确认</p>
			<button class="btn btn-success" id="startReview">开始评审</button>
			<div class="codeBlock">
				<div style="height: 300px; overflow: auto;border: 1px solid #AAAAAA;border-radius:2%;margin-bottom: 20px">
					<table class="table">
						<tr id="start">
							<th width=120px>文件名</th>
							<th width=50px>行数</th>
							<th>描述</th>
							<th width=10px></th>
						</tr>
						
						<tr>
							<td>文件名</td>
							<td>行数</td>
							<td>描述</td>
							<td><button type="button" class="close"
						aria-hidden="true" id="delete" onclick="deleteItem(this)">x</button></td>
						</tr>
					</table>
				</div>

				<form action="">
					<div class="row">
						<div class="form-group col-sm-6" id="fileGroup">
							<label for="fileName" class="col-sm-2 control-label">文件名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="fileName"
									placeholder="**.*">
							</div>
						</div>
						<div class="form-group col-sm-6" id="lineGroup">
							<label for="lineNum" class="col-sm-2 control-label">行数</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="lineNum"
									placeholder="">
							</div>
						</div>
					</div>

					<div class="form-group" id="discripGroup">
						<label for="discription" class="col-sm-1 control-label">描述</label>
						<div class="col-sm-11">
							<textarea class="form-control" rows="1" id="discription"></textarea>
						</div>
					</div>
					
				</form>
				<button class="btn btn-success" id="add">添加</button>
			</div>
			<div class="docBlock">
				<div style="height: 300px; overflow: auto;border: 1px solid #AAAAAA;border-radius:2%;margin-bottom: 20px">
					<table class="table">
						<tr id="start">
							<th width=120px>文件名</th>
							<th width=50px>页码</th>
							<th width=50px>行数</th>
							<th>描述</th>
							<th width=10px></th>
						</tr>
						
						<tr>
							<td>文件名</td>
							<td>页码</td>
							<td>行数</td>
							<td>描述</td>
							<td><button type="button" class="close"
						aria-hidden="true" id="delete" onclick="deleteItem(this)">x</button></td>
						</tr>
					</table>
				</div>

				<form action="">
					<div class="row">
						<div class="form-group col-sm-4" id="fileGroup">
							<label for="fileName" class="col-sm-3 control-label">文件名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="fileName"
									placeholder="**.*">
							</div>
						</div>
						<div class="form-group col-sm-4" id="lineGroup">
							<label for="lineNum" class="col-sm-3 control-label">页码</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="lineNum"
									placeholder="">
							</div>
						</div>
						<div class="form-group col-sm-4" id="lineGroup">
							<label for="lineNum" class="col-sm-3 control-label">行数</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="lineNum"
									placeholder="">
							</div>
						</div>
					</div>

					<div class="form-group" id="discripGroup">
						<label for="discription" class="col-sm-1 control-label">描述</label>
						<div class="col-sm-11">
							<textarea class="form-control" rows="1" id="discription"></textarea>
						</div>
					</div>				
				</form>
				<button class="btn btn-success" id="add">添加</button>
			</div>


			<hr>
			<h2 id="report">评审报告</h2>
			<hr>
		</div>
	</div>







	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
	<script src="js/review.js"></script>
</body>

</html>