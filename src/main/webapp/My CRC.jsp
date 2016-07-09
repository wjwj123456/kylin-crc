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
<link href="http://v3.bootcss.com/assets/css/docs.min.css"
	rel="stylesheet">
<title>My CRC</title>
</head>
<body role="document">
	<nav class="navbar navbar-inverse navbar-fixed-top">
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
				<li><a href="#runningReview">进行中评审</a></li>
				<li><a href="#historyReview">历史评审</a></li>
				<li><a href="#personalAnalyze">个人分析</a></li>
			</ul>
			<a class="back-to-top" href="#top"> 返回顶部 </a> </nav>
		</div>
		<div class="col-md-10">
			<div class="bs-docs-section">
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
				<h2 id="runningReview">进行中评审</h2>
				<table class="table">
					<tr>
						<th>评审名</th>
						<th>描述</th>
						<th>deadline</th>
						<th>操作</th>
					</tr>
					<tr>
						<td>haha</td>
						<td>haha</td>
						<td>haha</td>
						<td><button class="btn-success" data-toggle="modal"
								data-target="#inviseModal">查看</button></td>
					</tr>
					<tr>
						<td>haha</td>
						<td>haha</td>
						<td>haha</td>
						<td><button class="btn-success">查看</button></td>
					</tr>
				</table>
				<hr>
				<h2 id="historyReview">历史评审</h2>
				<table class="table">
					<tr>
						<th>评审名</th>
						<th>描述</th>
						<th>deadline</th>
						<th>操作</th>
					</tr>
					<tr>
						<td>haha</td>
						<td>haha</td>
						<td>haha</td>
						<td><button class="btn-success">查看</button></td>
					</tr>
					<tr>
						<td>haha</td>
						<td>haha</td>
						<td>haha</td>
						<td><button class="btn-success">查看</button></td>
					</tr>
				</table>
				<hr>
				<h2 id="personalAnalyze">个人分析</h2>
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
	</div>
	<div id="inviseModal" class="modal fade">
		<div class="modal-dialog" style="width: 800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">x</button>
					<h2 class="text-left text-primary">邀请评审者</h2>
				</div>
				<div class="modal-body" style="height: 450px;overflow:auto">

					
						
							<table class="table">
							<%for(int i = 0;i<100;i++){ %>
							<tr><th>12</th><td>12</td></tr>
							<%} %>
							</table>
						
					
					

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">提交更改</button>
				</div>
			</div>
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
</body>

</html>