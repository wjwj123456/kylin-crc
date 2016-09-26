<%@page import="bl.ReportBlImpl"%>
<%@page import="blservice.ReportBlService"%>
<%@page import="bl.UserInfoBlImpl"%>
<%@page import="tools.Encode"%>
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
<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="css/otherTheme.css" rel="stylesheet">
<link href="http://v3.bootcss.com/assets/css/docs.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/waitMe.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/fileTree.css">
<link rel="stylesheet" href="css/shCore.css">
<link rel="stylesheet" href="css/shCoreDefault.css">
<link rel="stylesheet" href="css/jquery.webui-popover.min.css">
<link rel='icon' href='img/icon.ico ' type=‘image/x-ico’ />
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<style type="text/css">
.drop {
	text-decoration: line-through;
}

.hideBlock {
	display: none;
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
<%ReviewBlService reviewBl = new ReviewBlImpl();
			State theState = reviewBl.getUserState((String) session.getAttribute("username"), taskVO.getTaskName());
			State taskState = reviewBl.getTaskState(taskVO.getTaskName());
			boolean isOwner = reviewBl.isOwner((String) session.getAttribute("username"), taskVO.getTaskName());%>
var isOwner = <%=isOwner%>;
</script>
<body role="document">
	<nav class="navbar ">
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
					aria-haspopup="true" aria-expanded="false"><img alt=""
						src="${userInfo.picture} " async width="27px" class="img-circle"
						height="27px"> <%=session.getAttribute("username")%><span
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
				<li><a href="#deadline">截止时间</a></li>
				<li><a href="#review">评审</a></li>
			</ul>
			<a class="back-to-top" href="#top"> 返回顶部 </a> </nav>
		</div>
		<div style="position: fixed; width: 90px; height: 90px; top: 300px;">
		</div>

		<div class="col-md-10 bs-docs-section">
			<h2 id="title"><%=taskVO.getTaskName()%>
				<div style="float: right;" id="fnTimeCountDown"
					data-end="2018/07/08 18:45:13">
					<span class="day">00</span>天 <span class="hour">00</span>时 <span
						class="mini">00</span>分 <span class="sec">00</span>秒
				</div>
			</h2>
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
					<%
						List<String> taskFile = Cast.cast(session.getAttribute("taskFile"));
					%>
					<p>评审附件：</p>
					<ul>
						<%
							if (taskFile.size() == 0) {
						%>
						<li>无</li>
						<%
							} else {
						%>
						<%
							for (String s : taskFile) {
						%>
						<li><a
							href="/FileServlet?type=download&taskName=<%=taskVO.getTaskName()%>&fileName=<%=s%>"><%=s%></a></li>
						<%
							}
						%>
						<%
							}
						%>
					</ul>
					<%
						int[][] taskHis = Cast.cast(session.getAttribute("taskHis_" + request.getParameter("taskName")));
						if (taskHis[0].length == 0) {
					%>
					<a>
						<button class="btn disabled">查看已生成报告</button>
					</a>
					<%
						} else {
					%>
					<a
						href="report.jsp?taskName=<%=Encode.transfer(request.getParameter("taskName"))%>">
						<button class="btn ">查看已生成报告</button>
					</a>
					<%
						}
					%>

					<div class="row">
						<p>参与者：</p>
						<%
							List<String> joiners = Cast.cast(session.getAttribute("agree_" + taskVO.getTaskName()));
							UserInfoBlImpl userInfo = new UserInfoBlImpl();
						%>
						<%
							for (String s : joiners) {
						%>

						<div class="col-md-1" style="text-align: center;">
							<a data-toggle="tooltip" title="<%=s%>"
								href="friend.jsp?friend=<%=s%>"> <img alt=""
								src="<%=userInfo.get(s).getPicture()%>" async width="40px"
								class="img-circle scaleable" height="40px">
							</a>

						</div>

						<%
							}
						%>
					</div>
					<div id="preJoinBlock" class="hideBlock">
						<div id="joinBlock">
							<button class="btn " id="join">参加评审</button>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<h2 id="deadline">截止时间</h2>
			<div id="master" class="row hide">
				<div class="col-sm-4">
					<link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
					<div class='input-group date' id='datetimepicker1'>
						<input type='text' class="form-control" id="deadlineZone" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
				<button id="changeTime" class="btn">确认修改</button>
				<button id="changeTime" class="btn"
					onclick="$('#master').addClass('hide');$('#normal').removeClass('hide');">取消</button>
			</div>
			<div id="normal">
				<strong><%=Tools.dateToString(taskVO.getDeadline())%></strong>
				<%
					if (isOwner && theState != State.merged) {
				%>
				<button class="btn"
					onclick="$('#normal').addClass('hide');$('#master').removeClass('hide');">修改</button>
				<%
					}
				%>
			</div>
			<script type="text/javascript">
				time = "<%=Tools.dateToString(taskVO.getDeadline())%>";
			</script>

			<%--文件目录结构--%>
			<hr/>
			<div class="file-tree panel panel-default">
				<div class="panel-heading">
					<ul class="breadcrumb" id="dir-path">
						<li>
							<a href="#">Home</a>
						</li>
						<li>
							<a href="#">Library</a>
						</li>
						<li class="active">
                            data
						</li>
					</ul>
				</div>
                <div class="panel-body">
					<table class="table">
						<tbody id="file-list">
						</tbody>
					</table>
				</div>
			</div>
			<%--文件目录结构End--%>
			<hr>
			<h2 id="review">
				评审
				<div id="taskUndoRedo" style="float: right; display: none">
					<div id="undoredo" style="float: right; display: none;">

						<button type="button" class="btn  btn-sm" id="undo">
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							Undo
						</button>
						<button type="button" class="btn  btn-sm" id="redo">
							Redo <span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span>
						</button>
					</div>
				</div>
			</h2>
			<div id="codePreview">
				<pre type="syntaxhighlighter" class="brush: java;">
package blservice;

import vo.AchievementVO;

public interface AchievementBlService {

	/**
	 * ldk get one's achievement
	 * 
	 * @param userName
	 * @return
	 */
	public AchievementVO getAchievement(String userName);
}
				</pre>
			</div>

			<div id="reviewBlock" class="hideBlock">
				<%
					ReportBlService reportBl = new ReportBlImpl();
				%>
				<%
					List<ReportVO> tempVOs = reportBl.getTempReport(taskVO.getTaskName(),
							(String) session.getAttribute("username"));
				%>
				<div id="commitBlock" class="hideBlock">
					<div id="codeBlock">
						<div
							style="height: 300px; width: 100%; overflow: auto; border: 1px solid #AAAAAA; border-radius: 10px; margin-bottom: 20px">
							<table class="table" id="codeTable">
								<thead>
									<tr>
										<th width=120px>文件名</th>
										<th width=50px>位置</th>
										<th>描述</th>
										<th width=10px></th>
									</tr>
								</thead>
								<tbody id="codeStart">
									<%
										if (taskVO.getType() == Type.code) {
									%>
									<%
										for (ReportVO vo : tempVOs) {
									%>
									<tr>
										<td><%=vo.getFileName()%></td>
										<td><%=vo.getLocation()%></td>
										<td><%=vo.getDescription()%></td>
										<td><button type='button' class='close'
												aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td>
									</tr>
									<%
										}
									%>
									<%
										}
									%>
								</tbody>
							</table>
						</div>

						<form>
							<div class="row">
								<div class="form-group col-sm-6" id="fileGroup-code">
									<label for="fileName-code" class="col-sm-2 control-label">文件名</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="fileName-code"
											placeholder="**.*">
									</div>
								</div>
								<div class="form-group col-sm-6" id="lineGroup-code">
									<label for="lineNum-code" class="col-sm-2 control-label">位置</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="lineNum-code"
											placeholder="">
									</div>
								</div>
							</div>
						</form>

						<div class="form-group" id="discripGroup-code"
							style="padding-bottom: 30px;">
							<label for="discription-code" class="col-sm-1 control-label">描述</label>
							<div class="col-sm-11 form-inline">
								<textarea class="form-control" rows="1" id="discription-code"
									style="width: 80%"></textarea>
								<button class="btn " id="add-code"
									style="margin-left: 20px; float: right;">添加</button>
							</div>
						</div>

					</div>
					<div id="docBlock">
						<div
							style="height: 300px; overflow: auto; width: 100%; border: 1px solid #AAAAAA; border-radius: 10px; margin-bottom: 20px">
							<table class="table" id="docTable">
								<thead>
									<tr>
										<th width=120px>文件名</th>
										<th width=50px>页码</th>
										<th width=50px>位置</th>
										<th>描述</th>
										<th width=10px></th>
									</tr>
								</thead>
								<tbody id="docStart">
									<%
										if (taskVO.getType() == Type.document) {
									%>
									<%
										for (ReportVO vo : tempVOs) {
									%>
									<tr>
										<td><%=vo.getFileName()%></td>
										<td><%=vo.getPage()%></td>
										<td><%=vo.getLocation()%></td>
										<td><%=vo.getDescription()%></td>
										<td><button type='button' class='close'
												aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td>
									</tr>
									<%
										}
									%>
									<%
										}
									%>
								</tbody>
							</table>
						</div>

						<form>
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
									<label for="lineNum-file" class="col-sm-3 control-label">位置</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="lineNum-file"
											placeholder="">
									</div>
								</div>
							</div>
						</form>
						<div class="form-group" id="discripGroup-file"
							style="padding-bottom: 30px;">
							<label for="discription-file" class="col-sm-1 control-label">描述</label>
							<div class="col-sm-11 form-inline">
								<textarea class="form-control" rows="1" id="discription-file"
									style="width: 80%"></textarea>
								<button class="btn " id="add-file"
									style="margin-left: 20px; float: right;">添加</button>
							</div>
						</div>

					</div>
					<div class="row"
						style="margin-top: 50px; padding-top: 20px; text-align: right; background-color: #eee; border-radius: 10px">
						<div class="col-sm-5"></div>
						<div class="form-group col-sm-5" id="timeGroup">
							<label for="timeCost" class="col-sm-4 control-label">评审时间</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="timeCost">小时
							</div>

						</div>
						<div class="col-sm-2">
							<button class="btn" data-toggle="modal" href="#reportModal">提交报告</button>
						</div>
						<div id="reportModal" class="modal fade">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header" style="height: 50px;">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">x</button>
									</div>
									<div class="modal-body" style="height: 50px">
										<p>提交后将无法修改，确认提交吗？</p>
									</div>
									<div class="modal-footer">
										<button class="btn" id="confirmReport"
											onclick="commitReport()">确认</button>
										<a href="#" class="btn" data-dismiss="modal">取消</a>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div id="mergeBlock" class="hideBlock">
					<p>请从下方表格中选择要合并的项目，点击“合并”</p>
					<div class="row" style="">
						<table class="table table-hover table-expandable"
							id="toMerge-code">
							<thead>
								<tr>
									<th width=10px></th>
									<th>文件名</th>
									<th>位置</th>
									<th>描述</th>
									<th>评审人</th>
									<th width=20px></th>
									<th width="10px"></th>
								</tr>
							</thead>
							<tbody>
								<%--<tr onclick="$('.collapse').collapse('toggle');">--%>
								<%--<td><input type="checkbox"></td>--%>
								<%--<td>asdad</td>--%>
								<%--<td>asdasd</td>--%>
								<%--<td>asda</td>--%>
								<%--<td>asdasd</td>--%>
								<%--<td></td>--%>
								<%--</tr>--%>

								<%
									List<ReportVO> toMergeVOs = Cast.cast(session.getAttribute("toMerge_" + taskVO.getTaskName()));
								%>
								<%
									for (ReportVO reportVO : toMergeVOs) {
								%>
								<%
									if (reportVO.getOperator().equals((String) session.getAttribute("username"))) {
								%>
								<tr style="background-color: rgb(255, 249, 223)">
									<%
										} else {
									%>
								
								<tr>
									<%
										}
									%>
									<td><input type="checkbox"></td>
									<td><%=reportVO.getFileName()%></td>
									<td><%=reportVO.getLocation()%></td>
									<td><%=reportVO.getDescription()%></td>
									<td><%=reportVO.getUserName()%></td>
									<td>
										<%
											if (reportVO.getIsMerged() == 1) {
										%><button class="btn btn-sm">拆分</button> <%
 	}
 %>
									</td>
								</tr>
								<tr class="collapse fade">
									<td colspan="6"></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<table class="table table-hover" id="toMerge-file">
							<thead>
								<tr>
									<th></th>
									<th>文件名</th>
									<th>页码</th>
									<th>位置</th>
									<th>描述</th>
									<th>评审人</th>
									<th width=20px></th>
									<th width="10px"></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (ReportVO reportVO : toMergeVOs) {
								%>
								<%
									if (reportVO.getOperator().equals((String) session.getAttribute("username"))) {
								%>
								<tr style="background-color: rgb(255, 249, 223)">
									<%
										} else {
									%>
								
								<tr>
									<%
										}
									%>
									<td><input type="checkbox"></td>
									<td><%=reportVO.getFileName()%></td>
									<td><%=reportVO.getPage()%></td>
									<td><%=reportVO.getLocation()%></td>
									<td><%=reportVO.getDescription()%></td>
									<td><%=reportVO.getUserName()%></td>
									<td>
										<%
											if (reportVO.getIsMerged() == 1) {
										%><button class="btn btn-sm">拆分</button> <%
 	}
 %>
									</td>

								</tr>
								<tr class="collapse fade">
									<td colspan="7"></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>

					<div class="row pull-right"padding-bottom: 20px">
						<button class="btn " id="merge">合并</button>
						<button class="btn btn-new" data-toggle="modal" href="#mergeModal">提交</button>
					</div>

					<div id="mergeModal" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header" style="height: 50px;">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">x</button>
								</div>
								<div class="modal-body" style="height: 50px">
									<p>提交后将无法修改，确认提交吗？</p>
								</div>
								<div class="modal-footer">
									<button class="btn btn-new" id="confirmMerge">确认</button>
									<a href="#" class="btn" data-dismiss="modal">取消</a>
								</div>
							</div>
						</div>
					</div>
					<div class="row hidden"
						style="min-height: 10px; max-height: 400px; overflow: auto;">
						<table class="table" id="merged-code">
							<tr>

								<th>文件名</th>
								<th>位置</th>
								<th>描述</th>
								<th>评审人</th>
								<th></th>
							</tr>
						</table>
						<table class="table" id="merged-file">
							<tr>

								<th>文件名</th>
								<th>页码</th>
								<th>位置</th>
								<th>描述</th>
								<th>评审人</th>
								<th></th>
							</tr>
						</table>
					</div>
					<div class="row" style="text-align: right;"></div>
				</div>
				<div id="chooseModal" class="modal fade">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">x</button>
								<h2 class="text-left text-primary">选择保留项</h2>
								<p>双击选择已有项或自己填写新描述</p>
							</div>
							<div class="modal-body" style="height: 300px; overflow: auto">
								<div id="codeDiv">
									<table class="table table-hover" id="choose-code">
										<tr>
											<th>文件名</th>
											<th>位置</th>
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
													class="col-sm-2 control-label">位置</label>
												<div class="col-sm-10">
													<input type="text" class="form-control"
														id="lineNum-choosecode" placeholder="">
												</div>
											</div>
										</div>
									</form>

									<div class="form-group" id="discripGroup-choosecode"
										style="padding-bottom: 30px;">
										<label for="discription-choosecode"
											class="col-sm-1 control-label">描述</label>
										<div class="col-sm-11 form-inline">
											<textarea class="form-control" rows="1"
												id="discription-choosecode" style="width: 80%"></textarea>
											<button class="btn " id="add-choosecode"
												style="margin-left: 20px; float: right;">添加</button>
										</div>
									</div>

								</div>
								<div id="fileDiv">
									<table class="table table-hover" id="choose-file">
										<tr>
											<th>文件名</th>
											<th>页码</th>
											<th>位置</th>
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
													class="col-sm-3 control-label">位置</label>
												<div class="col-sm-9">
													<input type="text" class="form-control"
														id="lineNum-choosefile" placeholder="">
												</div>
											</div>
										</div>
									</form>
									<div class="form-group" id="discripGroup-choosefile"
										style="padding-bottom: 30px;">
										<label for="discription-choosefile"
											class="col-sm-1 control-label">描述</label>
										<div class="col-sm-11 form-inline">
											<textarea class="form-control" rows="1"
												id="discription-choosefile" style="width: 80%"></textarea>
											<button class="btn " id="add-choosefile"
												style="margin-left: 20px; float: right;">添加</button>
										</div>
									</div>


								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn " data-dismiss="modal">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="overBlock" class="hideBlock">
				<p>评审已结束，您可以查看评审报告</p>
			</div>
			<div id="divideModal" class="modal fade">
				<div class="modal-dialog" style="width: 800px;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">x</button>
							<h2 class="text-left text-primary">选择拆分项</h2>
						</div>
						<div class="modal-body" style="height: 250px; overflow: auto">
							<div id="divide">
								<table class="table table-hover" id="divideTable">
									<tbody>
									</tbody>
								</table>
								<button type="button" class="btn btn-sm" data-dismiss="modal"
									id="confirmDivide" style="float: right;">确认</button>
							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn " data-dismiss="modal">取消</button>

						</div>
					</div>
				</div>
			</div>
			<hr>

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





	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="http://v3.bootcss.com/assets/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
	<script src="js/review.js"></script>
	<script src="js/stateControl.js"></script>
	<script type="text/javascript">
	<%if (taskState == State.ownerfinish || (taskState == State.timefinish && !isOwner)) {%>
	currentTaskDisp = taskDisp[finished];
	<%} else {%>
	currentTaskDisp = taskDisp[running];
	<%}%>		
	<%if (theState == State.agree) {%>
	currentUserDisp = userDisp[joined];
<%} else if (theState == State.refuse) {%>
	currentUserDisp = userDisp[unJoin];
<%} else if (theState == State.commit) {%>
	currentUserDisp = userDisp[committed];
	
<%} else {%>
	currentUserDisp = userDisp[merged];
	currentTaskDisp = taskDisp[finished];
<%}%>
	</script>
</body>
	<script src='js/moment-with-locales.js'></script>
	<script src='js/bootstrap-datetimepicker.js'></script>
	<script src="js/fileTree.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker1').datetimepicker({
				format : 'YYYY-MM-DD HH:mm',
				minDate : new Date(),
				locale : 'zh-cn',
			});
		});
	</script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script src='js/countDown.js'></script>
	<script src='js/command.js'></script>
	<script type="text/javascript">
	var num = ${messageNum};</script>
	<script src='js/mesSpan.js'></script>
	<script src="js/codeScriptImporter.js"></script>
	<script src="js/codePreview.js"></script>

</html>