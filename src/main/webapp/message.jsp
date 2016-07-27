<%@page import="tools.Cast"%>
<%@page import="vo.TaskVO"%>
<%@page import="java.util.List"%>
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
	<link href="css/otherTheme.css" rel="stylesheet">
<link href="http://v3.bootcss.com/assets/css/docs.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/waitMe.min.css">
<style type="text/css">
.myrow {
	padding-bottom: 10px;
}
</style>


<title>我的消息</title>
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
			<ul class="nav navbar-nav navbar-right">
				<%
					if (session.getAttribute("username") != null) {
				%>
					<li class="dropdown" id="mesSpan"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%=session.getAttribute("username")%><span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a style="cursor: pointer;" href="myTasks.jsp">我的评审 </a></li>
						<li><a style="cursor: pointer;" href="message.jsp">我的消息  </a></li>
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
	<div class="container" id="waitArea">
		<div id="suspensionNavigation" class="col-md-2" role="complementary"
			style="text-align: center; padding-top: 80px">
			<div class="myrow">
				<img alt="" src="${userInfo.picture} " width="50px" class="img-circle"
					height="50px">
			</div>
			<div class="myrow"><%=session.getAttribute("username")%><a
					href="describe.jsp"><button class="close" style="float: none;">
						<span class="glyphicon glyphicon-edit"></span>
					</button></a>
			</div>

			<a href="newTask.jsp"><button class="btn btn-success">新评审</button></a>

		</div>
		<div class="col-md-10 bs-docs-section">
			<h2 id="message">消息</h2>
			<table class="table" id="messageTable">
				<thead>
					<tr>
						<th>发信人</th>
						<th>项目名</th>
						<th width=50px>操作</th>
						<th width=10px></th>
					</tr>
				</thead>
				<tbody>
					<%
						List<TaskVO> messages = Cast.cast(session.getAttribute("invitationList"));
					%>
					<%
						for (TaskVO vo : messages) {
					%>
					<tr>
						<td><%=vo.getUserName()%></td>
						<td><a href="tasks.jsp?taskName=<%=vo.getTaskName()%>"><%=vo.getTaskName()%></a></td>
						<td><button class="btn btn-success" id="accept">接受邀请</button></td>
						<td><button class="btn btn-danger" id="read">已读</button></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<hr>
		</div>
	</div>
	<div class="mastfoot">
		<div class="inner">
			<p>
				page for <a href="index.jsp">CRC</a>, by <a
					href="https://github.com/wjwj123456/kylin-crc" target="_blank">kylin</a>.
			</p>
		</div>
	</div>
	<script type="text/javascript">
		
	</script>
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script type="text/javascript">
	var num = ${messageNum};</script>
	<script src='js/mesSpan.js'></script>
	<script src="js/message.js"></script>
</body>

</html>