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
<link href="http://v3.bootcss.com/assets/css/docs.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/waitMe.min.css">
<style type="text/css">
.drop {
	text-decoration: line-through;
}
</style>
<title>CRC Task</title>
</head>
<script type="text/javascript">
username = '<%=session.getAttribute("username")%>';
taskName = '<%=request.getParameter("taskName")%>';
<%String taskName = Encode.transfer((String)request.getParameter("taskName")) ;%>
	
</script>
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://echarts.baidu.com/dist/echarts.min.js"></script>
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
	<div class="container" id="waitArea">
		<%
			int[][] taskHis = Cast.cast(session.getAttribute("taskHis_" + taskName));
		%>
		<script type="text/javascript">
			var data = new Array();
			var assessmen = new Array();
			var fault = new Array();
		<%for (int i = 0; i < taskHis[0].length; i++) {%>
			data.push(
		<%=(taskHis[0][i] + 0.0) / taskHis[1][i] * 100%>
			);
			assessmen.push(
		<%=taskHis[1][i]%>
			);
			fault.push(
		<%=taskHis[0][i]%>
			);
		<%}%>
		$(function(){
			if(assessmen.length==0){
				$('#reportZone').hide();
				$('#noReportZone').show();
			}else {
				$('#reportZone').show();
				$('#noReportZone').hide();
			}
		});
		
		</script>
		<div id="reportZone">
			<h2 id="report">评审报告</h2>
			<div class="col-md-7" id="resultGraph" style="height: 300px"></div>

			<script type="text/javascript">
				var myChart = echarts.init(document
						.getElementById('resultGraph'));
				var option = {
					title : {
						text : '总体评审效率'
					},
					tooltip : {
						trigger : 'axis',
						formatter : function(params) {
							params = params[0];

							return "有效率：" + params.data.toFixed(2)
									+ "<br/>找出缺陷数：" + fault[params.dataIndex]
									+ "<br/>估计缺陷数："
									+ assessmen[params.dataIndex];
						},
						axisPointer : {
							animation : false
						}
					},
					xAxis : {
						name : '合并次数',
						type : 'category',
						data : [ '合并一', '合并二', '合并三', '合并四', '合并五', '合并六',
								'合并七' ]
					},
					yAxis : {
						name : '效率',
						type : 'value',
						boundaryGap : [ 0, '100%' ],
						splitLine : {
							show : false
						}
					},
					series : [ {
						name : '模拟数据',
						type : 'line',
						showSymbol : false,
						hoverAnimation : false,
						data : data
					} ]
				};
				myChart.setOption(option);
			</script>
			<div class="col-md-5">
				<%
					List<AssessmentVO> userHis = Cast.cast(session.getAttribute("userHis_" + taskName));
				%>
				<table class="table">
					<thead>
						<tr>
							<th>评审人</th>
							<th>预估缺陷总数</th>
							<th>找出缺陷数</th>
							<th>评审效率</th>
						</tr>
					</thead>
					<tbody>
						<%
							NumberFormat ddf1 = NumberFormat.getNumberInstance();
							ddf1.setMaximumFractionDigits(2);
							double res;
							for (AssessmentVO assessmentVO : userHis) {
								if (assessmentVO.getAssessfaults() != 0) {
									res = (assessmentVO.getFindedfaults() + 0.0) / assessmentVO.getAssessfaults();
								} else {
									res = 0;
								}
						%>
						<tr>
							<td><%=assessmentVO.getReviewerName()%></td>
							<td><%=assessmentVO.getAssessfaults()%></td>
							<td><%=assessmentVO.getFindedfaults()%></td>
							<td><%=ddf1.format(res * 100)%>%</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<hr>
		</div>
		<div id="noReportZone">
		<p>暂无可显示的报告数据</p>
		</div>
	</div>

	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
	<script src="js/review.js"></script>
	<script src="js/stateControl.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script src='js/countDown.js'></script>
</body>

</html>