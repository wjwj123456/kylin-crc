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
<%String taskName = Encode.transfer((String) request.getParameter("taskName"));%>
	
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
	<div class="container" style="text-align: center;" id="waitArea">
		<%
			int[][] taskHis = Cast.cast(session.getAttribute("taskHis_" + taskName));
		%>
		<script type="text/javascript">
			var dataMt = new Array();
			var dataMh = new Array();
			var assessmenMt = new Array();
			var assessmenMh = new Array();
			var fault = new Array();
			var res1;
			var res2;
		<%for (int i = 0; i < taskHis[0].length; i++) {%>
			<%if(taskHis[1][i]==0){%>
				res1=0;
			<%}else{%>
				res1 = <%=(taskHis[0][i] + 0.0) / taskHis[1][i] * 100%>;
				res1 = res1.toFixed(2);
			<%}%>
			<%if(taskHis[2][i]==0){%>
				res2=0;
			<%}else{%>
				res2 = <%=(taskHis[0][i] + 0.0) / taskHis[2][i] * 100%>;
				res2 = res2.toFixed(2);
			<%}%>
			dataMt.push(res1);
			dataMh.push(res2);
			assessmenMt.push(
		<%=taskHis[1][i]%>
			);
			assessmenMh.push(
		<%=taskHis[2][i]%>
			);
			fault.push(
		<%=taskHis[0][i]%>
			);
		<%}%>
			$(function() {
				if (fault.length == 0) {
					$('#reportZone').hide();
					$('#noReportZone').show();
				} else {
					$('#reportZone').show();
					$('#noReportZone').hide();
				}
			});
		</script>
		<h1 id="report">评审报告</h1>
		<hr>
		<hr>
		<div id="reportZone">
			<div class="row" id="resultGraph" style="height: 400px"></div>
			<script type="text/javascript">
				var myChart = echarts.init(document
						.getElementById('resultGraph'));
				var option = {
					title : {
						text : '总体评审效率'
					},
					tooltip : {
						trigger : 'axis',
						
						axisPointer : {
							animation : true
						}
					},
					legend : {
						data : [ 'mt效率', 'mh效率', '总体缺陷发现', 'mt估值', 'mh估值' ]
					},
					xAxis : {
						name : '合并次数',
						type : 'category',
						data : [ '合并一', '合并二', '合并三', '合并四', '合并五', '合并六',
								'合并七' ]
					},
					yAxis : [ {
						name : '数量',
						type : 'value',
						splitLine : {
							show : false
						}
					}, {
						name : '效率',
						type : 'value',
						splitLine : {
							show : false
						}
					} ],
					series : [ {
						name : '总体缺陷发现',
						type : 'bar',
						barWidth : '15%',
						barCategoryGap : '5%',
						data : fault
					}, {
						name : 'mt估值',
						type : 'bar',
						barWidth : '15%',
						barCategoryGap : '5%',
						data : assessmenMt
					}, {
						name : 'mh估值',
						type : 'bar',
						barWidth : '15%',
						barCategoryGap : '5%',
						data : assessmenMh
					}, {
						name : 'mt效率',
						type : 'line',
						yAxisIndex : 1,
						data : dataMt
					}, {
						name : 'mh效率',
						type : 'line',
						yAxisIndex : 1,
						data : dataMh
					} ]
				};
				myChart.setOption(option);
			</script>
			<%
				List<AssessmentVO> userHis = Cast.cast(session.getAttribute("userHis_" + taskName));
			%>
			<h2>
				最新Mt预估缺陷值：<%=userHis.get(0).getAssessfaults_mt()%></h2>
			<h2>
				最新Mh预估缺陷值：<%=userHis.get(0).getAssessfaults_mh()%></h2>
			<div class="row">

				<table class="table">
					<thead>
						<tr>
							<th>评审人</th>
							<th>发现缺陷数</th>
							<th>独特缺陷发现数</th>
							<th>mt评审效率</th>
							<th>mh评审效率</th>
							<th>花费时间</th>
							<th>每小时缺陷发现数</th>
						</tr>
					</thead>
					<tbody>
						<%
							NumberFormat ddf1 = NumberFormat.getNumberInstance();
							ddf1.setMaximumFractionDigits(2);
							double resMt;
							double resMh;
							for (AssessmentVO assessmentVO : userHis) {
								if (assessmentVO.getAssessfaults_mt() != 0) {
									resMt = (assessmentVO.getFindedfaults() + 0.0) / assessmentVO.getAssessfaults_mt();
								} else {
									resMt = 0;
								}
								if (assessmentVO.getAssessfaults_mh() != 0) {
									resMh = (assessmentVO.getFindedfaults() + 0.0) / assessmentVO.getAssessfaults_mh();
								} else {
									resMh = 0;
								}
						%>
						<tr>
							<td><%=assessmentVO.getReviewerName()%></td>
							<td><%=assessmentVO.getFindedfaults()%></td>
							<td><%=assessmentVO.getUniquefaults()%></td>
							<td><%=ddf1.format(resMt * 100)%>%</td>
							<td><%=ddf1.format(resMh * 100)%>%</td>
							<td><%=assessmentVO.getTime()%></td>
							<td><%=assessmentVO.getFaultsperhour()%></td>
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
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script src='js/countDown.js'></script>
	<script type="text/javascript">
	var num = ${messangNum};</script>
	<script src='js/mesSpan.js'></script>
</body>

</html>