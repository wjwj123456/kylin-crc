<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">

<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<link href="css/otherTheme.css" rel="stylesheet">
<link rel="stylesheet" href="css/waitMe.min.css">
<link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
.nav>li>a:focus, .nav>li>a:hover {
	text-decoration: none;
	background-color: transparent;
}

.nav .open>a, .nav .open>a:focus, .nav .open>a:hover {
	background-color: transparent;
	border-color: #337ab7;
}
</style>
<title>CRC new Task</title>
</head>
<body role="document">
	<nav class="navbar">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">CRC在线评审系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%
					if (session.getAttribute("username") != null) {
				%>
				<li class="dropdown" id="mesSpan"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("username")%><span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a style="cursor: pointer;" href="myTasks.jsp">我的评审 </a></li>
						<li><a style="cursor: pointer;" href="message.jsp">我的消息 </a></li>
						<li><a style="cursor: pointer;" href="My CRC.jsp">我的资料</a></li>
						<li role="separator" class="divider"></li>
						<li><a id="login" style="cursor: pointer;">登出</a></li>
					</ul></li>
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
	<div class="container" id="waitArea">
		<div class="jumbotron">
			<h2>新任务</h2>
			<p>创建你的新任务</p>
		</div>
		<iframe name="newFrame" style="display: none;"></iframe>
		<form class="form-horizontal" action="/crc/FileServlet?type=upload"
			target="newFrame" method="post" enctype="multipart/form-data">
			<div class="form-group" id="nameGroup">
				<label for="inputName" class="col-sm-2 control-label">评审名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputName"
						placeholder="关于***项目的n号评审">
				</div>
			</div>
			<div class="form-group" id="deadGroup">
				<label for="deadline" class="col-sm-2 control-label">结束时间</label>
				<div class="col-sm-4">
					<div class='input-group date' id='datetimepicker1'>
						<input type='text' class="form-control" id="deadline" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="grant" class="col-sm-2 control-label ">评审权限</label>
				<div class="col-sm-4">
					<select class="form-control" id="privacy">
						<option id="public">公有</option>
						<option id="private">私有</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="type" class="col-sm-2 control-label">评审类型</label>
				<div class="col-sm-4">
					<select class="form-control" id="type">
						<option id="doc">文档评审</option>
						<option id="code">代码评审</option>
					</select>
				</div>
			</div>
			<div class="form-group " id="languageBlock" style="display: none;">
				<label for="language" class="col-sm-2 control-label">代码语言</label>
				<div class="col-sm-4">
					<select class="form-control" id="language">
						<option value="c">C</option>
						<option value="java">Java</option>
						<option value="Objective_C">Objective_C</option>
						<option value="cpp">C++</option>
						<option value="csharp">C#</option>
						<option value="JavaScript">JavaScript</option>
						<option value="pointnet">.net</option>
						<option value="Ruby">Ruby</option>
						<option value="PHP">PHP</option>
						<option value="Swift">Swift</option>
						<option value="D">D</option>
						<option value="R">R</option>
						<option value="MATLAB">MATLAB</option>
						<option value="Perl">Perl</option>
						<option value="python">python</option>
						<option value="Groovy">Groovy</option>
						<option value="other">other</option>
					</select>
				</div>
			</div>
			<div class="form-group" id="discripGroup">
				<label for="discription" class="col-sm-2 control-label">评审说明</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="3" id="discription"></textarea>
				</div>
			</div>
			<div class="form-group" style="text-align: right;">
				<button class="btn" id="createTask">确认创建</button>
			</div>
		</form>
		<form action="/crc/FileServlet?type=upload" method="post"
			target="newFrame" enctype="multipart/form-data" id="form" style="display: none;">
			<div class="form-group" id="file">
				<label for="filein" class="col-sm-2 control-label">文件上传（可选）</label>
				<div class="col-sm-10">
					<%--<input type="file" multiple="multiple" id="filein"><br/>--%>
					<%--<input type="submit" value="upload">--%>
					<a href="javascript:;" class="btn fileInput"
						style="height: 37px; width: 90px;">上传文件 <input type="file" id="theInput"
						name="file" multiple="multiple" class="btn"
						style="opacity: 0; height: 37px; width: 90px; position: absolute; left: 15px; top: 0;" />
					</a>
					<lable class="showFileName"></lable>
					<button type="submit" value="Upload File" class="btn" id="upload">提交文件</button>
					<button class="btn" id="cancel">取消</button>
				</div>
			</div>
		</form>
	</div>
	<div class="mastfoot">
		<div class="inner">
			<p>
				page for <a href="index.jsp">CRC</a>, by <a
					href="https://github.com/wjwj123456/kylin-crc" target="_blank">kylin</a>.
			</p>
		</div>
	</div>
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src='js/moment-with-locales.js'></script>
	<script src='js/bootstrap-datetimepicker.js'></script>
	<script src="js/login.js"></script>
	<script src="js/newTask.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker1').datetimepicker({
				format : 'YYYY-MM-DD HH:mm',
				minDate : new Date(),
				locale : 'zh-cn'
			});
		});
	</script>
	<script type="text/javascript">
		$(".fileInput").on("change", "input[type='file']", function() {
			var filePath = $(this).val();
			$(".fileerrorTip").html("").hide();
			var arr = filePath.split('\\');
			var fileName = arr[arr.length - 1];
			$(".showFileName").html(fileName);

		})
	</script>
	<script type="text/javascript">
		var num = ${messageNum};
	</script>
	<script src='js/mesSpan.js'></script>
</body>

</html>