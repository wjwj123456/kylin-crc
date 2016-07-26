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
<style type="text/css">
.myrow {
	padding-bottom: 10px;
}
</style>
<title>My CRC</title>


</head>
<script type="text/javascript">
<%UserInfoVO infoVO = Cast.cast(session.getAttribute("userInfo"));%>
<%Language[] languages = infoVO.getLanguages();%>


var languages = new Array();
<%for (Language lg : languages) {%>
languages.push('<%=lg.toString()%>');
<%}%>
var sex ="${userInfo.sex}";

<%String userName = (String) session.getAttribute("username");
			AchievementVO achievement = Cast.cast(session.getAttribute("achievement_" + userName));%>
<%System.out.print(achievement);%>
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
			style="text-align: center; padding-top: 80px; width: 18%;">
			<div class="myrow">
				<img alt="" src="img/glyphicons-halflings.png" width="50px"
					height="50px">
			</div>
			<div class="myrow"><%=session.getAttribute("username")%><a
					href="describe.jsp"><button class="close" style="float: none;">
						<span class="glyphicon glyphicon-edit"></span>
					</button></a>
			</div>
			<div id="message">
				<div class="myrow">${userInfo.province}${userInfo.city}</div>
				<div class="myrow">${userInfo.job}</div>
				<div class="myrow">${userInfo.description}</div>
				<%
					for (Language lg : languages) {
				%>
				<div class="myrow" style="color: #336699">
					擅长
					<%=lg%></div>
				<%
					}
				%>

			</div>
			<a href="newTask.jsp"><button class="btn btn-success">新评审</button></a>

		</div>
		<div class="col-md-9 bs-docs-section">
			<div>
				<h2 id="perAnalyze">优秀评审</h2>
				<div class="col-md-3 text-center">
					<%
						if (achievement.isEfficiency_count_5_achi()) {
					%>
					<img alt="" src="img/achievement1/achievement11.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement1/achievement12.png"
						style="width: 150px"> <br>
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
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement1/achievement22.png"
						style="width: 150px"> <br>
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
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement1/achievement32.png"
						style="width: 150px"> <br>
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
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement1/achievement42.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到50次</label>
				</div>
				<div class="col-md-3 text-center">
					<%
						if (achievement.isEfficiency_count_100_achi()) {
					%>
					<img alt="" src="img/achievement1/achievement51.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement1/achievement52.png"
						style="width: 150px"> <br>
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
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement1/achievement62.png"
						style="width: 150px"> <br>
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
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement1/achievement72.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到500次</label>
				</div>
			</div>
		<div class="row" >	<hr/></div>
			<div>
				<h2 id="perAnalyze">评审时长</h2>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_time_20_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement11.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement12.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到5次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_time_50_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement21.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement22.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到10次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_time_100_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement31.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement32.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到20次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_time_200_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement41.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement42.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到50次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_time_500_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement51.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement52.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到100次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_time_1000_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement61.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement62.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到200次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_time_2000_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement71.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement72.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到500次</label>
				</div>

				<hr>


			</div>
			
			
			<div class="row" >	<hr/></div>
			<div>
				<h2 id="perAnalyze">总计评审次数</h2>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_count_5_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement11.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement12.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到5次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_count_10_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement21.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement22.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到10次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_count_10_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement31.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement32.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到20次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_count_10_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement41.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement42.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到50次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_count_10_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement51.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement52.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到100次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_count_10_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement61.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement62.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到200次</label>
				</div>
					<div class="col-md-3 text-center">
					<%
						if (achievement.isReview_count_10_achi()) {
					%>
					<img alt="" src="img/achievement2/achievement71.png"
						style="width: 150px"> <br>
					<%
						} else {
					%>
					<img alt="" src="img/achievement2/achievement72.png"
						style="width: 150px"> <br>
					<%
						}
					%>
					<label class="text-center">优秀评审达到500次</label>
				</div>

				<hr>


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
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script type="text/javascript">
	var num = ${messageNum};</script>
	<script src='js/mesSpan.js'></script>
</body>

</html>