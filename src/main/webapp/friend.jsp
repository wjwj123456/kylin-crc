<%@page import="blservice.UserInfoBlService"%>
<%@page import="bl.UserBlImpl"%>
<%@page import="tools.Tools"%>
<%@page import="java.util.Date"%>
<%@page import="bl.UserInfoBlImpl"%>
<%@page import="blservice.UserBlService"%>
<%@page import="bl.InviteBlImpl"%>
<%@page import="blservice.InviteBlService"%>
<%@page import="vo.AchievementVO"%>
<%@page import="tools.Cast"%>
<%@page import="vo.TaskVO"%>
<%@page import="java.util.List"%>
<%@page import="vo.Language"%>
<%@page import="vo.UserInfoVO"%>
<%@page import="tools.Encode"%>
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
<link href="http://v3.bootcss.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="css/otherTheme.css" rel="stylesheet">
<link href="http://v3.bootcss.com/assets/css/docs.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/waitMe.min.css">
<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet" href="css/avenir2.css">
<style type="text/css">
.myrow {
	padding-bottom: 10px;
}

.user {
	padding: 5px;
	margin-bottom: 5px;
}

a:hover {
	text-decoration: none;
}
</style>

<title>ReviewCircle</title>


</head>
<script type="text/javascript">

<% String userName= (String) session.getAttribute("username");%>
<%String friendName = (String) request.getParameter("friend");%>
			<%AchievementVO achievement = Cast.cast(session.getAttribute("achievement_" + friendName));%>
<%UserInfoVO userInfo = Cast.cast(session.getAttribute("userInfo_" + friendName));%>
<%List<TaskVO> running = Cast.cast(session.getAttribute("doingTaskList_" + friendName));%>
<%List<TaskVO> endList = Cast.cast(session.getAttribute("endingTaskList_" + friendName));%>
<%boolean isFriend = Cast.cast(session.getAttribute("isFriend_" + userName+"_"+friendName));%>
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
			<a class="navbar-brand" href="index.jsp">CRC Review</a>
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
		<div id="suspensionNavigation" class="col-md-3" role="complementary"
			style="text-align: center; padding-top: 102px; width: 18%;">
			<div class="myrow">
				<img alt="" src="<%=userInfo.getPicture()%>" width="50px"
					class="img-circle" height="50px">
			</div>
			<div class="myrow"><%=userInfo.getUserName()%>
			</div>
			<div id="message">
				<div class="myrow"><%=userInfo.getProvince()%><%=userInfo.getCity()%></div>
				<div class="myrow"><%=userInfo.getJob()%></div>
				<div class="myrow"><%=userInfo.getDescription()%></div>
				<%
					for (Language lg : userInfo.getLanguages()) {
				%>
				<div class="myrow" style="color: #336699">
					擅长
					<%=lg%></div>
				<%
					}
				%>

			</div>
			<a href="#"><button class="btn" id="addfriend" onclick="deleteBtn()"><%if(isFriend){ %>取消关注<%}else{ %>关注<%} %></button></a>
		</div>
		<div class="col-md-9 bs-docs-section">
			<ul id="myTab" class="nav nav-tabs"
				style="font-size: 150%; text-align: center;">
				<li ><a href="#hisTask" data-toggle="tab">他的动态</a></li>
				<li class="active"><a href="#myAchievement" data-toggle="tab">他的成就</a></li>
				<li><a href="#wall" data-toggle="tab">他的勋章</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade" id="hisTask">
					<%
						InviteBlService invite = new InviteBlImpl();
						UserInfoBlService userBl = new UserInfoBlImpl();
					%>
					<%
						Date now = new Date();
					%>
					<%
						if (running.size() == 0) {
					%>
					<h2>暂无</h2>
					<%
						} else {
					%>
					<%
						for (TaskVO vo : running) {
								List<String> users = invite.getAgreeUser(vo.getTaskName());
					%>
					<div class="avn-price-table avn-style14 avn-hover"">
						<div class="row row-eq-height">
							<div class="col-md-2">
								<div class="header">
									<h4 class="package">剩余</h4>
									<div class="price">
										<span class="amount"><%=(int) ((vo.getDeadline().getTime() - now.getTime()) / 86400000)%></span>
										<span class="currency">天</span>

									</div>
								</div>
							</div>
							<div class="col-md-10">
								<a href="tasks.jsp?taskName=<%=vo.getTaskName()%>">
									<h2 title="<%=vo.getTaskName()%>" style="cursor: pointer;"><%=vo.getTaskName()%>
										<button class="close" style="float: none;">
											<span class="glyphicon glyphicon-hand-left"> </span>
										</button>
									</h2>
								</a>
								<p><%=vo.getDescribe()%></p>
								<p>
									<strong>截止时间：<%=Tools.dateToString(vo.getDeadline())%></strong>
								</p>

								<div>
									<%
										for (String s : users) {
									%>
									<div class="col-md-1" style="text-align: center;">
										<a data-toggle="tooltip" title="<%=s%>" href="friend.jsp?friend=<%=s%>"> <img alt=""
											src="<%=userBl.get(s).getPicture()%>" async width="40px"
											class="img-circle scaleable" height="40px">
										</a>
									</div>
									<%
										}
									%>
								</div>


							</div>
						</div>
					</div>
					<%
						}
					%>
					<%
						}
					%>


					<hr>
				</div>
				<div class="tab-pane fade  in active" id="myAchievement">
			<div class="row"  style="padding-top:89px">

</div>
					<ul style="font-size:25px ">
						<li >当前经验值为 <label style="font-size:38px ;color:#336699"
							class="timer count-title text-center" id="count-number"
							data-to="<%=achievement.getExperience()%>" data-speed="1500"><b></b></label></li>
						<li>已参与评审<label style="font-size:38px ;color:#336699"
							class="timer count-title text-center" id="count-number"
							data-to="<%=achievement.getReview_count()%>" data-speed="1500"><b></b></label>次</li>
						<li>累计评审时长 <label style="font-size:38px ;color:#336699"
							class="timer count-title text-center" id="count-number"
							data-to="<%=achievement.getReview_time()%>" data-speed="1500"><b></b></label>小时</li>
						<li>效率超过50%的评审累计<label style="font-size:38px ;color:#336699"
							class="timer count-title text-center" id="count-number"
							data-to="<%=achievement.getEfficiency_count()%>" data-speed="1500"><b></b></label>次</li>
					</ul>
				

				</div>

				<div class="tab-pane fade" id="wall">
					<h2 id="perAnalyze">
						优秀评审
						<hr />
					</h2>
					<div class="col-md-3 text-center">
						<%
							if (achievement.isEfficiency_count_5_achi()) {
						%>
						<img alt="" src="img/achievement1/achievement11.png"
							style="width: 102px"> <br>
						<%
							} else {
						%>
						<img alt="" src="img/achievement1/achievement12.png"
							style="width: 102px"> <br>
						<%
							}
						%>
						<label class="text-center">优秀评审达到5次</label>
					</div>
					<div class="col-md-3 text-center">
						<%
							if (achievement.isEfficiency_count_10_achi()) {
						%>
						<img alt="" src="img/achievement1/achievement21.png"
							style="width: 102px"> <br>
						<%
							} else {
						%>
						<img alt="" src="img/achievement1/achievement22.png"
							style="width: 102px"> <br>
						<%
							}
						%>
						<label class="text-center">优秀评审达到10次</label>
					</div>
					<div class="col-md-3 text-center">
						<%
							if (achievement.isEfficiency_count_20_achi()) {
						%>
						<img alt="" src="img/achievement1/achievement31.png"
							style="width: 102px"> <br>
						<%
							} else {
						%>
						<img alt="" src="img/achievement1/achievement32.png"
							style="width: 102px"> <br>
						<%
							}
						%>
						<label class="text-center">优秀评审达到20次</label>
					</div>
					<div class="col-md-3 text-center">
						<%
							if (achievement.isEfficiency_count_50_achi()) {
						%>
						<img alt="" src="img/achievement1/achievement41.png"
							style="width: 102px"> <br>
						<%
							} else {
						%>
						<img alt="" src="img/achievement1/achievement42.png"
							style="width: 102px"> <br>
						<%
							}
						%>
						<label class="text-center">优秀评审达到50次</label>
					</div>
					<div class="row" style="padding-top: 200px">
						<div class="col-md-2" style="width: 100px"></div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isEfficiency_count_100_achi()) {
							%>
							<img alt="" src="img/achievement1/achievement51.png"
								style="width: 102px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement1/achievement52.png"
								style="width: 102px"> <br>
							<%
								}
							%>
							<label class="text-center">优秀评审达到100次</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isEfficiency_count_200_achi()) {
							%>
							<img alt="" src="img/achievement1/achievement61.png"
								style="width: 102px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement1/achievement62.png"
								style="width: 102px"> <br>
							<%
								}
							%>
							<label class="text-center">优秀评审达到200次</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isEfficiency_count_500_achi()) {
							%>
							<img alt="" src="img/achievement1/achievement71.png"
								style="width: 102px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement1/achievement72.png"
								style="width: 102px"> <br>
							<%
								}
							%>
							<label class="text-center">优秀评审达到500次</label>
						</div>
					</div>





					<div>
						<h2 id="perAnalyze">
							评审时长
							<hr>
						</h2>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_time_20_achi()) {
							%>
							<img alt="" src="img/achievement2/achievement11.png"
								style="width: 112px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement2/achievement12.png"
								style="width: 112px"> <br>
							<%
								}
							%>
							<label class="text-center">评审时长达到20小时</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_time_50_achi()) {
							%>
							<img alt="" src="img/achievement2/achievement21.png"
								style="width: 112px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement2/achievement22.png"
								style="width: 112px"> <br>
							<%
								}
							%>
							<label class="text-center">评审时长达到50小时</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_time_100_achi()) {
							%>
							<img alt="" src="img/achievement2/achievement31.png"
								style="width: 112px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement2/achievement32.png"
								style="width: 112px"> <br>
							<%
								}
							%>
							<label class="text-center">评审时长达到100小时</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_time_200_achi()) {
							%>
							<img alt="" src="img/achievement2/achievement41.png"
								style="width: 112px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement2/achievement42.png"
								style="width: 112px"> <br>
							<%
								}
							%>
							<label class="text-center">评审时长达到200小时</label>
						</div>

						<div class="row" style="padding-top: 200px">
							<div class="col-md-2" style="width: 100px"></div>
							<div class="col-md-3 text-center">
								<%
									if (achievement.isReview_time_500_achi()) {
								%>
								<img alt="" src="img/achievement2/achievement51.png"
									style="width: 112px"> <br>
								<%
									} else {
								%>
								<img alt="" src="img/achievement2/achievement52.png"
									style="width: 112px"> <br>
								<%
									}
								%>
								<label class="text-center">评审时长达到500小时</label>
							</div>
							<div class="col-md-3 text-center">
								<%
									if (achievement.isReview_time_1000_achi()) {
								%>
								<img alt="" src="img/achievement2/achievement61.png"
									style="width: 112px"> <br>
								<%
									} else {
								%>
								<img alt="" src="img/achievement2/achievement62.png"
									style="width: 112px"> <br>
								<%
									}
								%>
								<label class="text-center">评审时长达到1000小时</label>
							</div>
							<div class="col-md-3 text-center">
								<%
									if (achievement.isReview_time_2000_achi()) {
								%>
								<img alt="" src="img/achievement2/achievement71.png"
									style="width: 112px"> <br>
								<%
									} else {
								%>
								<img alt="" src="img/achievement2/achievement72.png"
									style="width: 112px"> <br>
								<%
									}
								%>
								<label class="text-center">评审时长达到2000小时</label>
							</div>
						</div>


					</div>


					<div class="row">
						<hr />
					</div>
					<div>
						<h2 id="perAnalyze">总计评审次数</h2>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_count_5_achi()) {
							%>
							<img alt="" src="img/achievement3/achievement11.png"
								style="width: 102px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement3/achievement12.png"
								style="width: 102px"> <br>
							<%
								}
							%>
							<label class="text-center">评审累计达到5次</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_count_10_achi()) {
							%>
							<img alt="" src="img/achievement3/achievement21.png"
								style="width: 102px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement3/achievement22.png"
								style="width: 102px"> <br>
							<%
								}
							%>
							<label class="text-center">评审累计达到10次</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_count_20_achi()) {
							%>
							<img alt="" src="img/achievement3/achievement31.png"
								style="width: 102px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement3/achievement32.png"
								style="width: 102px"> <br>
							<%
								}
							%>
							<label class="text-center">评审累计达到20次</label>
						</div>
						<div class="col-md-3 text-center">
							<%
								if (achievement.isReview_count_50_achi()) {
							%>
							<img alt="" src="img/achievement3/achievement41.png"
								style="width: 102px"> <br>
							<%
								} else {
							%>
							<img alt="" src="img/achievement3/achievement42.png"
								style="width: 102px"> <br>
							<%
								}
							%>
							<label class="text-center">评审累计达到50次</label>
						</div>
						<div class="row" style="padding-top: 200px">
							<div class="col-md-2" style="width: 100px"></div>
							<div class="col-md-3 text-center">
								<%
									if (achievement.isReview_count_100_achi()) {
								%>
								<img alt="" src="img/achievement3/achievement51.png"
									style="width: 102px"> <br>
								<%
									} else {
								%>
								<img alt="" src="img/achievement3/achievement52.png"
									style="width: 102px"> <br>
								<%
									}
								%>
								<label class="text-center">评审累计达到100次</label>
							</div>


							<div class="col-md-3 text-center">
								<%
									if (achievement.isReview_count_200_achi()) {
								%>
								<img alt="" src="img/achievement3/achievement61.png"
									style="width: 102px"> <br>
								<%
									} else {
								%>
								<img alt="" src="img/achievement3/achievement62.png"
									style="width: 102px"> <br>
								<%
									}
								%>
								<label class="text-center">评审累计达到200次</label>
							</div>
							<div class="col-md-3 text-center">
								<%
									if (achievement.isReview_count_500_achi()) {
								%>
								<img alt="" src="img/achievement3/achievement71.png"
									style="width: 102px"> <br>
								<%
									} else {
								%>
								<img alt="" src="img/achievement3/achievement72.png"
									style="width: 102px"> <br>
								<%
									}
								%>
								<label class="text-center">评审累计达到500次</label>
							</div>
						</div>
						<hr>


					</div>

				</div>
			</div>



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
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script type="text/javascript">
	var num = ${messageNum};</script>
	<script src='js/mesSpan.js'></script>
<script src="js/number.js"></script>

<script type="text/javascript">
function deleteBtn(){
	var friendName='<%= (String) request.getParameter("friend")%>';
	var userName='<%= (String) session.getAttribute("username")%>';
	run_waitMe();

	if($('#addfriend').text().trim()=="取消关注"){
	jQuery.ajax({
		url : '/FriendsServlet',
		type : 'post',
		data : 'type=delete' + '&userName=' + userName+'&friendName='+friendName,
		success : function(data) {
				$('#addfriend').text("关注");
			stopWait();
		}
	});
	}else{
		jQuery.ajax({
			url : '/FriendsServlet',
			type : 'post',
			data : 'type=add' + '&userName=' + userName+'&friendName='+friendName,
			success : function(data) {
					$('#addfriend').text("取消关注");
				stopWait();
			}
		});
	}

}




</script>

</body>

</html>