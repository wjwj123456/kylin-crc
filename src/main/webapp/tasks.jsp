<%@page import="vo.State"%>
<%@page import="bl.ReviewBlImpl"%>
<%@page import="blservice.ReviewBlService"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.AssessmentVO"%>
<%@page import="vo.ReportVO"%>
<%@page import="tools.Tools"%>
<%@page import="java.util.List"%>
<%@page import="vo.Type"%>
<%@page import="tools.Cast"%>
<%@page import="vo.TaskVO"%>
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
.drop {
	text-decoration: line-through;
}
</style>
<title>CRC Task</title>
</head>
<script type="text/javascript">
username = '<%=session.getAttribute("username")%>';
taskName = '<%=request.getParameter("taskName")%>';
<%TaskVO taskVO = Cast.cast(session.getAttribute("taskVO"));%>
	
<%if (taskVO.getType() == Type.code) {%>
	taskType = 'code';
<%} else {%>
	taskType = 'file';
<%}%>
<%	ReviewBlService reviewBl = new ReviewBlImpl(); 
	State theState = reviewBl.getState((String)session.getAttribute("username"), taskVO.getTaskName());
	if(theState==State.agree){
		%>currentUserDisp=userDisp[joined];<%
	}else if(theState==State.refuse){
		%>currentUserDisp=userDisp[unJoin];<%
	}
	else if(theState==State.commit){
		%>currentUserDisp=userDisp[committed];<%
	}
	else if(theState==State.merged){
		%>currentUserDisp=userDisp[merged];<%
	}
%>
	
</script>
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

	<div id="signUpModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">x</button>
					<h1 class="text-center text-primary">注册</h1>
				</div>
				<div class="modal-body" style="height: 300px">
					<form action="/register" class="form col-md-12 center-block"
						method="get">
						<div class="form-group">
							<input type="text" name='username' class="form-control input-lg"
								placeholder="用户名">
						</div>
						<div class="form-group">
							<input type="password" name="password"
								class="form-control input-lg" placeholder="登录密码">
						</div>
						<div class="form-group">
							<input type="text" name="mail" class="form-control input-lg"
								placeholder="邮箱">
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-lg btn-block"
								onclick="register()" id="registNow">立刻注册</button>
							<span><a data-toggle="modal" data-target="#loginModal"
								data-dismiss="modal" href="" class="pull-right">已有账号，点此登陆</a></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<div class="container" id="waitArea">
		<div id="suspensionNavigation" class="col-md-2" role="complementary">
			<nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm">
			<ul class="nav bs-docs-sidenav">

				<li><a href="#title">评审名</a></li>
				<li><a href="#message">说明</a></li>
				<li><a href="#deadline">截止时间</a></li>
				<li><a href="#review">评审</a></li>
				<li><a href="#report">评审报告</a></li>
			</ul>
			<a class="back-to-top" href="#top"> 返回顶部 </a> </nav>
		</div>

		<div class="col-md-10 bs-docs-section">
			<h2 id="title"><%=taskVO.getTaskName()%></h2>
			<hr>
			<h2 id="message">说明</h2>
			<div class="row">
				<div class="col-md-10">
					<%
						if (taskVO.getType() == Type.code) {
					%>
					<p>类型：代码评审</p>
					<%
						} else {
					%>
					<p>类型：文档评审</p>
					<%
						}
					%>
					<p><%=taskVO.getDescribe()%></p>
					<div id="preJoinBlock">
						<div id="joinBlock">
							<button class="btn btn-success" id="join">参加评审</button>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<%
						List<String> joiners = Cast.cast(session.getAttribute("agree_" + taskVO.getTaskName()));
					%>
					<table class="table">
						<tr>
							<th>参与人</th>
						</tr>
						<%
							for (String s : joiners) {
						%>
						<tr>
							<td><%=s%></td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
			</div>
			<hr>
			<h2 id="deadline">截止时间</h2>
			<strong><%=Tools.dateToString(taskVO.getDeadline())%></strong>
			<hr>
			<h2 id="review">评审</h2>
			<div id="reviewBlock">
				<div id="commitBlock">
					<div id="codeBlock">
						<div
							style="height: 300px; overflow: auto; border: 1px solid #AAAAAA; border-radius: 2%; margin-bottom: 20px">
							<table class="table" id="codeTable">
								<thead>
									<tr>
										<th width=120px>文件名</th>
										<th width=50px>行数</th>
										<th>描述</th>
										<th width=10px></th>
									</tr>
								</thead>
								<tbody id="codeStart">

								</tbody>
							</table>
						</div>

						<form action="">
							<div class="row">
								<div class="form-group col-sm-6" id="fileGroup-code">
									<label for="fileName-code" class="col-sm-2 control-label">文件名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="fileName-code"
											placeholder="**.*">
									</div>
								</div>
								<div class="form-group col-sm-6" id="lineGroup-code">
									<label for="lineNum-code" class="col-sm-2 control-label">行数</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="lineNum-code"
											placeholder="">
									</div>
								</div>
							</div>

							<div class="form-group" id="discripGroup-code">
								<label for="discription-code" class="col-sm-1 control-label">描述</label>
								<div class="col-sm-11">
									<textarea class="form-control" rows="1" id="discription-code"></textarea>
								</div>
							</div>
						</form>
						<button class="btn btn-success" id="add-code">添加</button>
					</div>
					<div id="docBlock">
						<div
							style="height: 300px; overflow: auto; border: 1px solid #AAAAAA; border-radius: 2%; margin-bottom: 20px">
							<table class="table" id="docTable">
								<thead>
									<tr>
										<th width=120px>文件名</th>
										<th width=50px>页码</th>
										<th width=50px>行数</th>
										<th>描述</th>
										<th width=10px></th>
									</tr>
								</thead>
								<tbody id="docStart">

								</tbody>
							</table>
						</div>

						<form action="">
							<div class="row">
								<div class="form-group col-sm-4" id="fileGroup-file">
									<label for="fileName-file" class="col-sm-4 control-label">文件名</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="fileName-file"
											placeholder="**.*">
									</div>
								</div>
								<div class="form-group col-sm-4" id="pageGroup-file">
									<label for="pageNum-file" class="col-sm-3 control-label">页码</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="pageNum-file"
											placeholder="">
									</div>
								</div>
								<div class="form-group col-sm-4" id="lineGroup-file">
									<label for="lineNum-file" class="col-sm-3 control-label">行数</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="lineNum-file"
											placeholder="">
									</div>
								</div>
							</div>
							<div class="form-group" id="discripGroup-file">
								<label for="discription-file" class="col-sm-1 control-label">描述</label>
								<div class="col-sm-11">
									<textarea class="form-control" rows="1" id="discription-file"></textarea>
								</div>
							</div>

						</form>
						<button class="btn btn-success" id="add-file">添加</button>

					</div>
					<div class="row" style="padding-top: 20px; text-align: right;">
						<div class="col-sm-5"></div>
						<div class="form-group col-sm-5" id="timeGroup">
							<label for="timeCost" class="col-sm-4 control-label">评审时间</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="timeCost"
									placeholder="小时">
							</div>
						</div>
						<button class="btn btn-success" id="confirmReport"
							onclick="commitReport()">确认评审结果</button>
					</div>
				</div>
				<div id="mergeBlock">
					<p>请从下方表格中选择要合并的项目，点击“合并”，如要废弃条目，请单独合并后在合并结果中删除</p>
					<div class="row" style="min-height:10px; max-height:400px; overflow: auto;">
						<table class="table" id="toMerge-code">
							<thead>
								<tr>
									<th width=10px></th>
									<th>文件名</th>
									<th>行数</th>
									<th>描述</th>
									<th>评审人</th>
									<th width=20px></th>
								</tr>
							</thead>
							<tbody>
								<%
									List<ReportVO> toMergeVOs = Cast.cast(session.getAttribute("toMerge_" + taskVO.getTaskName()));
								%>
								<%
									for (ReportVO reportVO : toMergeVOs) {
								%>
								<tr>
									<td><input type="checkbox"></td>
									<td><%=reportVO.getFileName()%></td>
									<td><%=reportVO.getLocation()%></td>
									<td><%=reportVO.getDescription()%></td>
									<td><%=reportVO.getUserName()%></td>
									<td><%
											if (reportVO.getIsMerged() == 1) {
										%><button
											class="btn btn-warning">拆分</button>
										<%
											}
										%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<table class="table" id="toMerge-file">
							<thead>
								<tr>
									<th></th>
									<th>文件名</th>
									<th>页码</th>
									<th>行数</th>
									<th>描述</th>
									<th>评审人</th>
									<th width=20px></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (ReportVO reportVO : toMergeVOs) {
								%>
								<tr>
									<td><input type="checkbox"></td>
									<td><%=reportVO.getFileName()%></td>
									<td><%=reportVO.getPage()%></td>
									<td><%=reportVO.getLocation()%></td>
									<td><%=reportVO.getDescription()%></td>
									<td><%=reportVO.getUserName()%></td>
									<td>
										<%
											if (reportVO.getIsMerged() == 1) {
										%><button
											class="btn btn-warning">拆分</button>
										<%
											}
										%>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>

					<div class="row" style="text-align: center; padding-bottom: 20px">
						<button class="btn btn-success" id="merge">合并</button>
					</div>
					<div class="row" style="min-height:10px; max-height:400px; overflow: auto;">
						<table class="table" id="merged-code">
							<tr>

								<th>文件名</th>
								<th>行数</th>
								<th>描述</th>
								<th>评审人</th>
								<th></th>
							</tr>
						</table>
						<table class="table" id="merged-file">
							<tr>

								<th>文件名</th>
								<th>页码</th>
								<th>行数</th>
								<th>描述</th>
								<th>评审人</th>
								<th></th>
							</tr>
						</table>
					</div>
					<div class="row" style="text-align: right;">
						<button class="btn btn-success" id="confirmMerge">确认合并</button>
					</div>
				</div>
				<div id="chooseModal" class="modal fade">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">x</button>
								<h2 class="text-left text-primary">选择保留项</h2>
							</div>
							<div class="modal-body" style="height: 250px; overflow: auto">
								<div id="codeDiv">
									<table class="table" id="choose-code">
										<tr>
											<th>文件名</th>
											<th>行数</th>
											<th>描述</th>
											<th>评审人</th>
										</tr>
									</table>
									<form action="">
										<div class="row">
											<div class="form-group col-sm-6" id="fileGroup-choosecode">
												<label for="fileName-choosecode"
													class="col-sm-2 control-label">文件名</label>
												<div class="col-sm-10">
													<input type="text" class="form-control"
														id="fileName-choosecode" placeholder="**.*">
												</div>
											</div>
											<div class="form-group col-sm-6" id="lineGroup-choosecode">
												<label for="lineNum-choosecode"
													class="col-sm-2 control-label">行数</label>
												<div class="col-sm-10">
													<input type="text" class="form-control"
														id="lineNum-choosecode" placeholder="">
												</div>
											</div>
										</div>

										<div class="form-group" id="discripGroup-choosecode">
											<label for="discription-choosecode"
												class="col-sm-1 control-label">描述</label>
											<div class="col-sm-11">
												<textarea class="form-control" rows="1"
													id="discription-choosecode"></textarea>
											</div>
										</div>
									</form>
									<button class="btn btn-success" id="add-choosecode">添加</button>
								</div>
								<div id="fileDiv">
									<table class="table" id="choose-file">
										<tr>
											<th>文件名</th>
											<th>页码</th>
											<th>行数</th>
											<th>描述</th>
											<th>评审人</th>
										</tr>
									</table>
									<form action="">
										<div class="row">
											<div class="form-group col-sm-4" id="fileGroup-choosefile">
												<label for="fileName-choosefile"
													class="col-sm-4 control-label">文件名</label>
												<div class="col-sm-8">
													<input type="text" class="form-control"
														id="fileName-choosefile" placeholder="**.*">
												</div>
											</div>
											<div class="form-group col-sm-4" id="pageGroup-choosefile">
												<label for="pageNum-choosefile"
													class="col-sm-3 control-label">页码</label>
												<div class="col-sm-9">
													<input type="text" class="form-control"
														id="pageNum-choosefile" placeholder="">
												</div>
											</div>
											<div class="form-group col-sm-4" id="lineGroup-choosefile">
												<label for="lineNum-choosefile"
													class="col-sm-3 control-label">行数</label>
												<div class="col-sm-9">
													<input type="text" class="form-control"
														id="lineNum-choosefile" placeholder="">
												</div>
											</div>
										</div>
										<div class="form-group" id="discripGroup-choosefile">
											<label for="discription-choosefile"
												class="col-sm-1 control-label">描述</label>
											<div class="col-sm-11">
												<textarea class="form-control" rows="1"
													id="discription-choosefile"></textarea>
											</div>
										</div>

									</form>
									<button class="btn btn-success" id="add-choosefile">添加</button>
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="overBlock">
				<p>评审已截止，您可以查看评审报告</p>
			</div>
			<div id="divideModal" class="modal fade">
				<div class="modal-dialog" style="width: 800px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">x</button>
							<h2 class="text-left text-primary">选择保留项</h2>
						</div>
						<div class="modal-body" style="height: 250px; overflow: auto">
							<table class="table" id="divideTable">
								<thead>
									<tr>
										<th width=10px></th>
										<th>文件名</th>
										<%
											if (taskVO.getType() == Type.document) {
										%>
										<th>页码</th>
										<%
											}
										%>
										<th>行数</th>
										<th>描述</th>
										<th>评审人</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" id="confirmDivide">确认</button>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<h2 id="report">评审报告</h2>
			<div class="col-md-7" id="resultGraph" style="height: 300px"></div>
			<%
				int[][] taskHis = Cast.cast(session.getAttribute("taskHis_" + taskVO.getTaskName()));
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
					List<AssessmentVO> userHis = Cast.cast(session.getAttribute("userHis_" + taskVO.getTaskName()));
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
							if(assessmentVO.getAssessfaults()!=0){
								res = (assessmentVO.getFindedfaults() + 0.0) / assessmentVO.getAssessfaults();
							}else{
								res = 0;
							}
						%>
						<tr>
							<td><%=assessmentVO.getReviewerName()%></td>
							<td><%=assessmentVO.getAssessfaults()%></td>
							<td><%=assessmentVO.getFindedfaults()%></td>
							<td><%=ddf1.format( res*100)%>%</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
			<hr>
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
	<script src="js/review.js"></script>
	<script src="js/stateControl.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
</body>

</html>