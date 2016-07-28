<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/cover.css" rel="stylesheet">
<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/waitMe.min.css">
<title>CRC Index</title>
</head>
<body id="waitArea">
	<div class="site-wrapper">

		<div class="site-wrapper-inner">

			<div class="cover-container">

				<div class="masthead clearfix"  style="margin-left: -292px;">
					<div class="inner">
						<h3 class="masthead-brand">CRC Review System</h3>
						<nav >
						<ul class="nav masthead-nav" style="margin-right: -560px;">
							<%
								if (session.getAttribute("username") != null) {
							%>
							<li><a href="myTasks.jsp" id="user-name">${username}</a></li>
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
						</nav>
					</div>
				</div>
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
									<input type="text" name='username'
										class="form-control input-lg" id="username" placeholder="用户名">
								</div>
								<div class="form-group" id="passgroup">
									<input type="password" name="password" id="password"
										class="form-control input-lg" placeholder="登录密码">
								</div>
								<div class="form-group">
									<button class="btn btn-primary btn-lg btn-block"
										onclick="login()" id="loginNow">立刻登录</button>
									<span><a data-toggle="modal" data-target="#signUpModal"
										data-dismiss="modal" href="" class="pull-right"
										style="color: #555">注册</a></span>

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
							<div class="modal-body" style="height: 370px">


								<div class="form-group" id="regUserGroup">
									<input type="text" name='username'
										class="form-control input-lg" id="regUsername"
										placeholder="用户名">
								</div>
								<div class="form-group" id="regPassGroup">
									<input type="password" name="password"
										class="form-control input-lg" id="regPassword"
										placeholder="登录密码">
								</div>
								<div class="form-group" id="regConfirmGroup">
									<input type="password" name="confirmPassword"
										class="form-control input-lg" id="regConfirm"
										placeholder="确认密码">
								</div>
								<div class="form-group" id="regMailGroup">
									<input type="text" name="mail" class="form-control input-lg"
										id="regMail" placeholder="邮箱">
								</div>
								<div class="form-group">
									<button class="btn btn-primary btn-lg btn-block"
										onclick="register()" id="registNow">立刻注册</button>
									<span><a data-toggle="modal" data-target="#loginModal"
										data-dismiss="modal" href="" class="pull-right"
										style="color: #555">已有账号，点此登陆</a></span>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="inner cover"  style="padding-top: -20px; ">
					<h1 class="cover-heading">Let's Start Review.</h1>

					<div>
						<div class=row>
							<div class="col-md-10 col-md-offest-2" style="padding-left: 98px; padding-top: 27px;padding-right: 0px;">
								<input
									style="position: relative; vertical-align: top; background-color: transparent; color:white"
									name="term" placeholder="Search review..." required=""
									class="store-search form-control tt-input"
									type="text" id="searchContent">
							</div>
							<div class="col-md-2 col-md-offest-0" style="padding-top: 26px;">
								<button type="submit" class="btn-primary btn"  style="height:36px;margin-left: -71px;" id="search">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								</button>
							</div>
						</div>
					</div>
				</div>

				<div class="mastfoot">
					<div class="inner">
						<p>
							Project  for<a href="https://github.com/wjwj123456/kylin-crc.git"> Crc Review</a>,
							by <a href="https://github.com/wjwj123456/kylin-crc.git">@kylin</a>.
						</p>
					</div>
				</div>
				<div id="userAlert" class="hidden" role="alert" style="padding-top:50px ;color:#ffcccc" >
					请先登陆再进行操作</div>
				<script type="text/javascript">
					var unsigned =
				<%=request.getParameter("unsigned")%>
					;
					if (unsigned) {
						document.getElementById("userAlert").className = "";
					}
				</script>
			</div>

		</div>

	</div>

	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script src="js/login.js"></script>
	<script src="js/search.js"></script>
</body>

</html>