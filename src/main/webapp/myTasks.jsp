<%@page import="tools.Tools"%>
<%@page import="tools.Encode"%>
<%@page import="vo.State"%>
<%@page import="bl.ReviewBlImpl"%>
<%@page import="blservice.ReviewBlService"%>
<%@page import="vo.Type"%>
<%@page import="vo.TaskVO"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="tools.Cast"%>
<%@page import="vo.AssessmentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	response.setContentType("text/html;charset=utf-8");
	request.setCharacterEncoding("utf-8");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/otherTheme.css" rel="stylesheet">
<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/waitMe.min.css">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
.drop {
	text-decoration: line-through;
}
</style>
<style type="text/css">
.myrow {
	padding-bottom: 10px;
}
</style>
<title>我的评审</title>
</head>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="js/jquery.cxselect.min.js"></script>
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
			<a class="navbar-brand" href="index.jsp">CRC在线评审系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
			</ul>
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
						<li><a style="cursor: pointer;" href="describe.jsp">我的资料</a></li>
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
				<img alt="" src="img/glyphicons-halflings.png" width="50px"
					height="50px">
			</div>
			<div class="myrow"><%=session.getAttribute("username")%><a
					href="describe.jsp"><button class="close" style="float: none;">
						<span class="glyphicon glyphicon-edit"></span>
					</button></a>
			</div>

			<a href="newTask.jsp"><button class="btn btn-success">新评审</button></a>

		</div>
		<div class="col-md-10">
			<ul id="myTab" class="nav nav-tabs" style="font-size: 150%;text-align: center;">
				<li class="active"><a href="#my" data-toggle="tab"> 我发布的评审</a></li>
				<li><a href="#join" data-toggle="tab">我参与的评审</a></li>
				<li><a href="#his" data-toggle="tab">历史评审</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="my">
					<%
						List<TaskVO> running = Cast.cast(session.getAttribute("runningTask"));
					%>
					<%
						if (running.size() == 0) {
					%>
					<h2>暂无</h2>
					<%
						} else {
					%>
					<%
						for (TaskVO vo : running) {
					%>
					<div class="well well-lg">
						<h2 title="<%=vo.getTaskName()%>">
							<%=vo.getTaskName()%>
							<a href="tasks.jsp?taskName=<%=vo.getTaskName()%>">
								<button class="btn btn-default" style="float: right;">
									<span class="glyphicon glyphicon-hand-up"></span> Check
								</button>
							</a>
						</h2>
						<p><%=vo.getDescribe()%></p>
						<p>
							<strong>截止时间：<%=Tools.dateToString(vo.getDeadline())%></strong>
						</p>
						<p>
							参与者：XXXXXXXXXXX
							<button class="btn btn-success" data-toggle="modal"
								data-target="#inviteModal" onclick="initInvite(this)">邀请</button>
						</p>
					</div>
					<%
						}
					%>
					<%
						}
					%>


					<hr>
				</div>
				<div class="tab-pane fade " id="join">

					<%
						List<TaskVO> joiningTasks = Cast.cast(session.getAttribute("participantTask"));
					%>
					<%
						if (joiningTasks.size() == 0) {
					%>
					<h2>暂无</h2>
					<%
						} else {
					%>
					<%
						for (TaskVO vo : joiningTasks) {
					%>
					<div class="well well-lg">
						<h2 title="<%=vo.getTaskName()%>">
							<%=vo.getTaskName()%>
							<a href="tasks.jsp?taskName=<%=vo.getTaskName()%>">
								<button class="btn btn-default">
									<span class="glyphicon glyphicon-hand-up"></span> Check
								</button>
							</a>
						</h2>
						<p><%=vo.getDescribe()%></p>
						<p>
							<strong>截止时间：<%=Tools.dateToString(vo.getDeadline())%></strong>
						</p>
						<p>
							参与者：XXXXXXXXXXX
							<button class="btn btn-success" data-toggle="modal"
								data-target="#inviteModal" onclick="initInvite(this)">邀请</button>
						</p>
					</div>
					<%
						}
					%>
					<%
						}
					%>

					<hr>
				</div>
				<div class="tab-pane fade " id="his">
					<%
						List<TaskVO> history = Cast.cast(session.getAttribute("historyTask"));
					%>
					<%
						if (history.size() == 0) {
					%>
					<h2>暂无</h2>
					<%
						} else {
					%>
					<%
						for (TaskVO vo : history) {
					%>
					<div class="well well-lg">
						<h2 title="<%=vo.getTaskName()%>">
							<%=vo.getTaskName()%>
							<a href="tasks.jsp?taskName=<%=vo.getTaskName()%>">
								<button class="btn btn-default">
									<span class="glyphicon glyphicon-hand-up"></span> Check
								</button>
							</a>
						</h2>
						<p><%=vo.getDescribe()%></p>
						<p>
							<strong>截止时间：<%=Tools.dateToString(vo.getDeadline())%></strong>
						</p>
						<p>
							参与者：XXXXXXXXXXX
							<button class="btn btn-success" data-toggle="modal"
								data-target="#inviteModal" onclick="initInvite(this)">邀请</button>
						</p>
					</div>
					<%
						}
					%>
					<%
						}
					%>

					<hr>
				</div>



			</div>
			<div id="inviteModal" class="modal fade">
				<div class="modal-dialog" style="width: 800px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">x</button>
							<h2 class="text-left text-primary">邀请评审者</h2>
						</div>
						<div class="modal-body" style="height: 450px; overflow: auto">

							<div class="row form-group">
								<div class="col-md-8">
									<input size="40" type="text" class="form-control typeahead"
										id="searchName" placeholder="用户名关键字">
								</div>
								<button type="button" class="btn btn-success" id="search">搜索</button>
							</div>
							<div class="col-md-4">
								<p>可邀请</p>
								<div style="height: 250px; overflow: auto">

									<table class="table" id="toInvite">
										<thead>
											<tr>
												<th>用户名</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
							<div class="col-md-4">
								<p>将邀请</p>
								<div style="height: 250px; overflow: auto">

									<table class="table" id="invited">
										<thead>
											<tr>
												<th>用户名</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<div class="col-md-4">
								<p>已参加</p>
								<div style="height: 250px; overflow: auto">

									<table class="table" id="agreed">
										<thead>
											<tr>
												<th>用户名</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="confirmInvite">确认邀请</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
	<script src="js/search.js"></script>
	<script src="js/invite.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script type="text/javascript">
	var num = ${messageNum};</script>
	<script src='js/mesSpan.js'></script>
</body>

</html>