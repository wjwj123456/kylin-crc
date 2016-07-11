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
<link href="css/otherTheme.css" rel="stylesheet">
<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="http://v3.bootcss.com/assets/css/docs.min.css"
	rel="stylesheet">
<title>My CRC</title>
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

		</div>
	</div>
	</nav>
	<div class="container">
		<div id="suspensionNavigation" class="col-md-2" role="complementary">
			<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm">
			<ul class="nav bs-docs-sidenav">

				<li><a href="#basicInfo">基本信息</a></li>
				<li><a href="#message">消息</a></li>
				<li><a href="#runningReview">进行中评审</a></li>
				<li><a href="#joiningReview">参与中评审</a></li>
				<li><a href="#hisReview">历史评审</a></li>
				<li><a href="#perAnalyze">个人分析</a></li>
			</ul>
			<a class="back-to-top" href="#top"> 返回顶部 </a> </nav>
		</div>
		<div class="col-md-10 bs-docs-section">

			<h2 id="basicInfo">基本信息</h2>
			<table class="table">
				<tr>
					<th>haha</th>
					<td>haha</td>
					<th>haha</th>
					<td>haha</td>
				</tr>
				<tr>
					<th>haha</th>
					<td>haha</td>
					<th>haha</th>
					<td>haha</td>
				</tr>
				<tr>
					<th>haha</th>
					<td>haha</td>
					<th>haha</th>
					<td>haha</td>
				</tr>
			</table>
			<hr>
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
						<td><button class="btn btn-success">接收邀请</button></td>
						<td><button type="button" class="close" aria-hidden="true"
								id="delete">x</button></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<hr>
			<h2 id="runningReview">进行中评审</h2>
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
					<td><%=vo.getDeadline()%></td>
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
				<tr>
					<td>haha</td>
					<td>haha</td>
					<td>haha</td>
				</tr>
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
					<td><%=vo.getDeadline()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<hr>
			<h2 id="perAnalyze">个人分析</h2>
			<table class="table">
				<tr>
					<th>haha</th>
					<td>haha</td>
					<th>haha</th>
					<td>haha</td>
				</tr>
				<tr>
					<th>haha</th>
					<td>haha</td>
					<th>haha</th>
					<td>haha</td>
				</tr>
				<tr>
					<th>haha</th>
					<td>haha</td>
					<th>haha</th>
					<td>haha</td>
				</tr>
			</table>
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
								<tr>
									<th>用户名</th>
								</tr>
								<%
									for (int i = 0; i < 10; i++) {
								%>
								<tr>
									<td><%=i%></td>
								</tr>
								<%
									}
								%>
							</table>
						</div>
					</div>
					<div class="col-md-4">
						<p>将邀请</p>
						<div style="height: 250px; overflow: auto">

							<table class="table" id="invited">
								<tr>
									<th>用户名</th>
								</tr>
							</table>
						</div>
					</div>
					<div class="col-md-4">
						<p>已参加</p>
						<div style="height: 250px; overflow: auto">

							<table class="table" id="agreed">
								<tr>
									<th>用户名</th>
								</tr>
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
	<script src="js/search.js"></script>
	<script src="js/invite.js"></script>
	<script src="js/typeHead.js"></script>
</body>

</html>