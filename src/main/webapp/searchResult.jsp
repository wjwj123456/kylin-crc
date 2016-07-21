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
<link rel="stylesheet" href="css/waitMe.min.css">
<style type="text/css">
.myrow {
	padding-bottom: 10px;
}

.item {
	cursor: pointer;
}

.item>p {
	padding: 4px;
}

.item:hover {
	background: rgb(220, 220, 220);
}
</style>
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
			<a class="navbar-brand" href="index.jsp">CRC在线评审系统</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
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
		<div id="suspensionNavigation" class="col-md-3" role="complementary"
			style="padding-top: 80px; padding-right: 50px;">
			<div class="myrow">
				<h3>languages</h3>
			</div>
			<hr>
			<div>
				<div class="item">
					<p>
						c++<span style="float: right;">haha</span>
					</p>
				</div>
				<div class="item">
					<p>
						java<span style="float: right;">haha</span>
					</p>
				</div>
				<div class="item">
					<p>
						c#<span style="float: right;">haha</span>
					</p>
				</div>
				<div class="item">
					<p>
						Ruby<span style="float: right;">haha</span>
					</p>
				</div>
			</div>

		</div>
		<div class="col-md-9 bs-docs-section">
			<div class=row>
				<div class="col-md-10 "
					style="padding-left: 98px; padding-top: 27px; padding-right: 0px;">

					<input
						style="position: relative; vertical-align: top; background-color: transparent;"
						name="term" placeholder="Search review..." required=""
						class="store-search typeahead form-control tt-input" type="text">

				</div>
				<div class="col-md-2 col-md-offest-0" style="padding-top: 26px;">
					<button type="submit" class="btn-primary btn"
						style="height: 36px; margin-left: -71px;">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
				</div>




			</div>
			<h2 id="basicInfo">个人资料</h2>
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
			<h2 id="perAnalyze">个人成就</h2>
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
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script type="text/javascript">
		var num = $
		{
			messageNum
		};
	</script>
	<script src='js/mesSpan.js'></script>
</body>

</html>