<%@page import="vo.Language"%>
<%@page import="vo.UserInfoVO"%>
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
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/portraitStyle.css" type="text/css" />
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
<script src="js/jquery.cxselect.min.js"></script>
<script type="text/javascript">
<%UserInfoVO infoVO = Cast.cast(session.getAttribute("userInfo"));%>
<%Language[] languages = infoVO.getLanguages();%>
var languages = new Array();
<%for (Language lg : languages) {%>
languages.push('<%=lg.toString()%>');
<%}%>
var sex ="${userInfo.sex}";
</script>
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
	<div class="container" id="waitArea">
		<h2>个人资料填写</h2>
		<form class="form-horizontal">
			<div class="form-group row">
				<label for="name" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="name"
						value="${userInfo.userName}">
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-2 control-label">性别</label>
				<div class=" col-sm-4 radio">
					<label> <input type="radio" name="optionsRadios" id="male"
						value="male"> 男
					</label> <label> <input type="radio" name="optionsRadios"
						id="female" value="female"> 女
					</label> <label> <input type="radio" name="optionsRadios"
						id="unknown" value="secret" checked> 保密
					</label>
				</div>
			</div>
			<div class="form-group row">
				<label for="fileinput" class="col-sm-2 control-label">头像</label>
				<div class="col-sm-10" style="transform: scale(0.6);transform-origin:0% 0%;margin-bottom: -180px;">
					<div class="imageBox">
						<div class="thumbBox"></div>
						<div class="spinner" style="display: none"></div>
					</div>
					<div class="action">
						<!-- <input type="file" id="file" style=" width: 200px">-->
						<div class="new-contentarea tc">
							<a href="javascript:void(0)" class="upload-img btn">请先选择图片...
							 <input type="file" class="" name="upload-file" id="upload-file" /></a>
						</div>
						<input type="button" id="uploadPic" class="Btnsty_peyton btn"
							value="OK"> <input type="button" id="btnZoomIn"
							class="Btnsty_peyton btn" value="+"> <input type="button"
							id="btnZoomOut" class="Btnsty_peyton btn" value="-">
					</div>
					<div class="cropped"></div>
				</div>
			</div>
			<div class="form-group">
				<label for="area" class="col-sm-2 control-label">擅长领域</label>
				<div class=" col-sm-8">
					<table class="table" id="goodAt">
						<tr>
							<td class="no-border no-part-border"><input type="checkbox"
								value="c"> C</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="java"> Java</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="Objective_C"> Objective_C</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="cpp"> C++</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="csharp"> C#</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="JavaScript"> JavaScript</td>
						</tr>
						<tr>
							<td class="no-border no-part-border"><input type="checkbox"
								value="pointnet"> Pointnet</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="Ruby"> Ruby</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="PHP"> PHP</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="Swift"> Swift</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="D"> D</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="R"> R</td>
						</tr>
						<tr>
							<td class="no-border no-part-border"><input type="checkbox"
								value="MATLAB"> MATLAB</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="Perl"> Perl</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="python"> python</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="Groovy"> Groovy</td>
							<td class="no-border no-part-border"><input type="checkbox"
								value="other"> other</td>

						</tr>
					</table>
				</div>
			</div>
			<div class="form-group row">
				<label for="city" class="col-sm-2 control-label">城市</label>
				<div class="col-sm-5 form-inline" id="city">
					<select class="province cxselect form-control"
						data-value="${userInfo.province}" data-first-title="选择省"></select>
					<select class="city cxselect form-control"
						data-value="${userInfo.city}" data-first-title="选择市"></select>
				</div>
			</div>
			<div class="form-group row">
				<label for="job" class="col-sm-2 control-label">职业</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="job"
						value="${userInfo.job}">
				</div>
			</div>
			<div class="form-group row">
				<label for="describe" class="col-sm-2 control-label">个人描述</label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="describe" rows="3">${userInfo.description}</textarea>
				</div>
			</div>
		</form>
		<div class="row" style="text-align: right;">
			<button class="btn" id="confirm">确认提交</button>
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
	<script src="js/fileUpload.js"></script>
	<script type="text/javascript">
		initFileInput('fileinput', '/file');
	</script>
	<script type="text/javascript">
	var num = ${messageNum};</script>
	<script src='js/mesSpan.js'></script>
	<script type="text/javascript" src="js/portrait.js"></script>
</body>

</html>