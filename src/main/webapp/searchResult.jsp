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
<link rel="stylesheet" href="css/avenir.css">
<link rel="stylesheet" href="css/searchTab.css">
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
			<a class="navbar-brand" href="index.jsp" style="font-size: 130%;">CRC
				ReviewSystem</a>
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
	<div class="container" id="waitArea"
		style="background-image: url('img/nav.png') no-repeat">
		<div id="suspensionNavigation" class="col-md-3" role="complementary"
			style="padding-top: 20px; padding-right: 50px;">
			<div class="myrow">
				<h3>languages</h3>
			</div>
			<hr>
			<div>
				<ul id="myTab" class="nav nav-tabs">
					<li class="active"><a href="#doc" data-toggle="tab">
							文档 </a></li>
					<li><a href="#C" data-toggle="tab">C</a></li>
					<li><a href="#Java" data-toggle="tab">Java </a></li>
					<li><a href="#Objective_c" data-toggle="tab">Objective_c</a></li>
					<li><a href="#C++" data-toggle="tab">C++ </a></li>
					<li><a href="#Ruby" data-toggle="tab">Ruby</a></li>
					<li><a href="#Csharp" data-toggle="tab">C# </a></li>
					<li><a href="#JavaScript" data-toggle="tab">JavaScript</a></li>
					<li><a href="#net" data-toggle="tab">.net </a></li>
					<li><a href="#php" data-toggle="tab">php</a></li>
					<li><a href="#swift" data-toggle="tab">swift </a></li>
					<li><a href="#groovy" data-toggle="tab">groovy</a></li>
					<li><a href="#matlab" data-toggle="tab">matlab </a></li>
					<li><a href="#D" data-toggle="tab">D</a></li>
					<li><a href="#R" data-toggle="tab">R </a></li>
					<li><a href="#Perl" data-toggle="tab">Perl</a></li>
					<li><a href="#Python" data-toggle="tab">Python </a></li>
					<li><a href="#other" data-toggle="tab">other</a></li>
				</ul>
			</div>

		</div>
		<div class="col-md-9 bs-docs-section">
			<div class=row>
				<div class="col-md-10 "
					style="padding-left: 50px; padding-top: 27px; padding-right: 0px;">

					<input
						style="position: relative; vertical-align: top; background-color: transparent;"
						name="term" placeholder="Search review..." required=""
						class="store-search typeahead form-control tt-input" type="text">

				</div>
				<div class="col-md-2 col-md-offest-0" style="padding-top: 26px;">
					<button type="submit" class="btn-primary btn" style="height: 36px;">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
				</div>




			</div>
			<h3>We’ve found 4,293,725 repository results</h3>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="doc">
					<div class="avn-price-table avn-style14 avn-hover">
						<div class="row">
							<div class="col-md-1 header">
								<p style="margin-left: -11px;">D</p>
							</div>
							<div class="col-md-11">
								<h2 title="评审测试">评审测试</h2>
								<p>司法哈高科发挥砂锅饭技术股份卡公司罚款是高科技发生噶发生口角光刻技术飞洒股份杰弗森阿康恢复</p>
								<p>
									<strong>截止时间：XXXX/XX/XX XX:XX:XX</strong>
								</p>
							</div>
						</div>
					</div>
					<div class="avn-price-table avn-style14 avn-hover">
						<div class="row">
							<div class="col-md-1 header">
								<p style="margin-left: -11px;">C++</p>
							</div>
							<div class="col-md-11">
								<h2 title="评审测试">评审测试</h2>
								<p>司法哈高科发挥砂锅饭技术股份卡公司罚款是高科技发生噶发生口角光刻技术飞洒股份杰弗森阿康恢复</p>
								<p>
									<strong>截止时间：XXXX/XX/XX XX:XX:XX</strong>
								</p>
							</div>
						</div>
					</div>
					<div class="avn-price-table avn-style14 avn-hover">
						<div class="row">
							<div class="col-md-1 header">
								<p style="margin-left: -11px;">Java</p>
							</div>
							<div class="col-md-11">
								<h2 title="评审测试">评审测试</h2>
								<p>司法哈高科发挥砂锅饭技术股份卡公司罚款是高科技发生噶发生口角光刻技术飞洒股份杰弗森阿康恢复</p>
								<p>
									<strong>截止时间：XXXX/XX/XX XX:XX:XX</strong>
								</p>
							</div>
						</div>
					</div>
					<hr>
				</div>
				<div class="tab-pane fade" id="C">
					<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和
						Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS
						是苹果的移动版本。</p>
				</div>
				<div class="tab-pane fade" id="Java">
					<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
				</div>
				<div class="tab-pane fade" id="Objective_c">
					<p>Enterprise Java
						Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web
						Logic 等）的 J2EE 上。</p>
				</div>
				<div class="tab-pane fade" id="C++">
					<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和
						Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS
						是苹果的移动版本。</p>
				</div>
				<div class="tab-pane fade" id="Ruby">
					<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
				</div>
				<div class="tab-pane fade" id="Csharp">
					<p>Enterprise Java
						Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web
						Logic 等）的 J2EE 上。</p>
				</div>
				<div class="tab-pane fade" id="JavaScript">
					<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和
						Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS
						是苹果的移动版本。</p>
				</div>
				<div class="tab-pane fade" id="net">
					<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
				</div>
				<div class="tab-pane fade" id="php">
					<p>Enterprise Java
						Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web
						Logic 等）的 J2EE 上。</p>
				</div>
				<div class="tab-pane fade" id="swift">
					<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和
						Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS
						是苹果的移动版本。</p>
				</div>
				<div class="tab-pane fade" id="groovy">
					<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
				</div>
				<div class="tab-pane fade" id="matlab">
					<p>Enterprise Java
						Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web
						Logic 等）的 J2EE 上。</p>
				</div>
				<div class="tab-pane fade" id="D">
					<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和
						Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS
						是苹果的移动版本。</p>
				</div>
				<div class="tab-pane fade" id="R">
					<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
				</div><div class="tab-pane fade" id="Perl">
					<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和
						Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS
						是苹果的移动版本。</p>
				</div>
				<div class="tab-pane fade" id="Python">
					<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
				</div>
				<div class="tab-pane fade" id="other">
					<p>Enterprise Java
						Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web
						Logic 等）的 J2EE 上。</p>
				</div>
				
			</div>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/login.js"></script>
	<script src="js/search.js"></script>
	<script src='js/waitFunction.js'></script>
	<script src='js/waitMe.min.js'></script>
	<script type="text/javascript">
		var num = ${messageNum};
	</script>
	<script src='js/mesSpan.js'></script>
	<script src='js/searchResult.js'></script>
</body>

</html>