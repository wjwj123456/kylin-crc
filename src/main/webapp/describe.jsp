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

.no-border {
	border: 1px solid transparent !important;
}

.no-part-border {
	border-right: 1px solid transparent !important;
}
</style>
<title>资料填写</title>
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
		<h2>个人资料填写</h2>
		<form class="form-horizontal">
			<div class="form-group row">
				<label for="name" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="name" value="${userInfo.userName}">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class=" col-sm-4 radio">
					<label> <input type="radio" name="optionsRadios"
						id="optionsRadios1" value="option1" checked> 男
					</label> <label> <input type="radio" name="optionsRadios"
						id="optionsRadios1" value="option1" checked> 女
					</label>
				</div>
			</div>
			<div class="form-group">
				<label for="area" class="col-sm-2 control-label">擅长领域</label>
				<div class=" col-sm-8">
					<table class="table">
						<tr >
							<td class="no-border no-part-border"><input type="checkbox"> C</td>
							<td class="no-border no-part-border"><input type="checkbox"> Java</td>
							<td class="no-border no-part-border"><input type="checkbox"> Objective_C</td>
							<td class="no-border no-part-border"><input type="checkbox"> C++</td>
							<td class="no-border no-part-border"><input type="checkbox"> C#</td>
							<td class="no-border no-part-border"><input type="checkbox"> JavaScript</td>
						</tr>
						<tr>
							<td class="no-border no-part-border"><input type="checkbox"> Pointnet</td>
							<td class="no-border no-part-border"><input type="checkbox"> Ruby</td>
							<td class="no-border no-part-border"><input type="checkbox"> PHP</td>
							<td class="no-border no-part-border"><input type="checkbox"> Swift</td>
							<td class="no-border no-part-border"><input type="checkbox"> D</td>
							<td class="no-border no-part-border"><input type="checkbox"> R</td>
						</tr>
						<tr>
							<td class="no-border no-part-border"><input type="checkbox"> MATLAB</td>
							<td class="no-border no-part-border"><input type="checkbox"> Perl</td>
							<td class="no-border no-part-border"><input type="checkbox"> python</td>
							<td class="no-border no-part-border"><input type="checkbox"> other</td>
							<td class="no-border no-part-border"><input type="checkbox"> Groovy</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="form-group row">
				<label for="city" class="col-sm-2 control-label">城市</label>
				<div class="col-sm-5 form-inline" id="city">
					<select class="province cxselect form-control" data-value="江苏省"
						data-first-title="选择省"></select> <select
						class="city cxselect form-control" data-value="南京市"
						data-first-title="选择市"></select>
				</div>
			</div>
			<div class="form-group row">
				<label for="job" class="col-sm-2 control-label">职业</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="job" value="${userInfo.job}">
				</div>
			</div>
			<div class="form-group row">
				<label for="describe" class="col-sm-2 control-label">个人描述</label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="describe"  rows="3"
						>${userInfo.description}</textarea>
				</div>
			</div>
		</form>
		<div class="row" style="text-align: right;">
		<button class="btn btn-success" id="confirm">确认提交</button>
		</div>
	</div>
	<script>
		$.cxSelect.defaults.url = 'js/cityData.min.json';
		$('#city').cxSelect({
			selects : [ 'province', 'city' ],
			nodata : 'none'
		});
	</script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script src="js/account.js"></script>
</body>

</html>