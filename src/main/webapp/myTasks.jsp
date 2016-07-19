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
<style type="text/css">
.drop {
	text-decoration: line-through;
}
</style>
<title>我的评审</title>
</head>
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://echarts.baidu.com/dist/echarts.min.js"></script>
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
	<div class="container" id="waitArea">

		<h2 id="runningReview">进行中评审</h2>
		<div class="row container">
			<div class="well well-lg">
				<h2>代码合并评审 <button class="btn btn-default"><span class="glyphicon glyphicon-hand-up"></span> Check</button></h2>
				<p>ReportPO [taskName=合并与拆分测试, userName=lq, fileName=h, page=0,
					location=300, description=ad, state=0, origin=0, isMerged=0]
					</p>
				<p><strong>截止时间：XXXX/XX/XX XX:XX:XX</strong></p>
				<p>参与者：XXXXXXXXXXX <button class="btn btn-success" data-toggle="modal"
						data-target="#inviteModal" onclick="initInvite(this)">邀请</button></p>
			</div>
			<div class="well well-lg">
				<h2>代码合并评审 <button class="btn btn-default"><span class="glyphicon glyphicon-hand-up"></span> Check</button></h2>
				<p>ReportPO [taskName=合并与拆分测试, userName=lq, fileName=h, page=0,
					location=300, description=ad, state=0, origin=0, isMerged=0]
					</p>
				<p><strong>截止时间：XXXX/XX/XX XX:XX:XX</strong></p>
				<p>参与者：XXXXXXXXXXX <button class="btn btn-success" data-toggle="modal"
						data-target="#inviteModal" onclick="initInvite(this)">邀请</button></p>
			</div>
			<div class="well well-lg">
				<h2>代码合并评审 <button class="btn btn-default"><span class="glyphicon glyphicon-hand-up"></span> Check</button></h2>
				<p>ReportPO [taskName=合并与拆分测试, userName=lq, fileName=h, page=0,
					location=300, description=ad, state=0, origin=0, isMerged=0]
					</p>
				<p><strong>截止时间：XXXX/XX/XX XX:XX:XX</strong></p>
				<p>参与者：XXXXXXXXXXX <button class="btn btn-success" data-toggle="modal"
						data-target="#inviteModal" onclick="initInvite(this)">邀请</button></p>
			</div>
		</div>
		<table class="table">
			<tr>
				<th>评审名</th>
				<th>描述</th>
				<th>deadline</th>
				<th>操作</th>
			</tr>
			<%
				List<TaskVO> running = Cast.cast(session.getAttribute("runningTask"));
			%>
			<%
				for (TaskVO vo : running) {
			%>
			<tr>
				<td><a href="tasks.jsp?taskName=<%=vo.getTaskName()%>"><%=vo.getTaskName()%></a></td>
				<td><%=vo.getDescribe()%></td>
				<td><%=Tools.dateToString(vo.getDeadline())%></td>
				<td><button class="btn btn-success" data-toggle="modal"
						data-target="#inviteModal" onclick="initInvite(this)">邀请</button></td>
			</tr>
			<%
				}
			%>
		</table>
		<hr>
		<h2 id="joiningReview">参与中评审</h2>
		<table class="table">
			<tr>
				<th>评审名</th>
				<th>描述</th>
				<th>deadline</th>
			</tr>

			<%
				List<TaskVO> joiningTasks = Cast.cast(session.getAttribute("participantTask"));
			%>
			<%
				for (TaskVO vo : joiningTasks) {
			%>
			<tr>
				<td><a href="tasks.jsp?taskName=<%=vo.getTaskName()%>"><%=vo.getTaskName()%></a></td>
				<td><%=vo.getDescribe()%></td>
				<td><%=Tools.dateToString(vo.getDeadline())%></td>
			</tr>
			<%
				}
			%>
		</table>
		<hr>
		<h2 id="hisReview">历史评审</h2>
		<table class="table">
			<tr>
				<th>评审名</th>
				<th>描述</th>
				<th>deadline</th>
			</tr>
			<%
				List<TaskVO> history = Cast.cast(session.getAttribute("historyTask"));
			%>
			<%
				for (TaskVO vo : history) {
			%>
			<tr>
				<td><a href="tasks.jsp?taskName=<%=vo.getTaskName()%>"><%=vo.getTaskName()%></a></td>
				<td><%=vo.getDescribe()%></td>
				<td><%=Tools.dateToString(vo.getDeadline())%></td>
			</tr>
			<%
				}
			%>
		</table>
		<hr>
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
						<button type="button" class="btn btn-default" data-dismiss="modal">取消
						</button>
						<button type="button" class="btn btn-primary" id="confirmInvite">确认邀请</button>
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
</body>

</html>