<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/Main/Register.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/Main/styles.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">

</head>
<body>

 <!-- Titled -->
<nav class="navbar navbar-default"  role="navigation" style=" height:52px;postion:fixed;">
   <div class="navbar-header"  >
      <a class="navbar-brand" href="#" style="margin-left:80px;font-size:20px;color:white;">GitMining</a>
   </div>
   <div style="position:fixed;left:210px;"style="position:fixed;" >
      <ul class="nav-menu clearfix unstyled" >
        <li><a href="" class="three-d home"> Home <span class="three-d-box"><span class="front">Home</span><span class="back">Home</span></span> </a></li>
        <li><a href="" class="three-d"> Repository <span class="three-d-box"><span class="front">Repository</span><span class="back">Repository</span></span> </a>
           <ul class="clearfix unstyled drop-menu">
           <li><a href="<%=request.getContextPath()%>/FindReposByTag" class="three-d"> Html5 <span class="three-d-box"><span class="front">Repository</span><span class="back">Repository</span></span> </a></li>
           <li><a href="<%=request.getContextPath()%>/RepoStatistic" class="three-d"> Css3 <span class="three-d-box"><span class="front">Statistic</span><span class="back">Statistic</span></span> </a></li>
           </ul>
        </li>
        <li><a href="" class="three-d"> User <span class="three-d-box"><span class="front">User</span><span class="back">User</span></span> </a>
           <ul class="clearfix unstyled drop-menu" style="width:103px;">
           <li><a href="<%=request.getContextPath()%>/UserDetail" class="three-d" > Html5 <span class="three-d-box"><span class="front">User</span><span class="back">User</span></span> </a></li>
           <li><a href="<%=request.getContextPath()%>/UserStatistic" class="three-d"> Css3 <span class="three-d-box"><span style="padding-left: 20px;" class="front">Statistic</span><span style="padding-left: 20px;" class="back">Statistic</span></span> </a></li>
           </ul>
        </li>
        <li><a href="<%=request.getContextPath()%>/Recommend" class="three-d home"> Recommend <span class="three-d-box"><span class="front">Recommend</span><span class="back">Recommend</span></span> </a></li>        
        
       <%
        if(session.getAttribute("UserName")!=null){
        %>
        <li><a href="<%=request.getContextPath()%>/PersonInfo" class="login three-d"> <%= session.getAttribute("UserName") %><span class="three-d-box"><span class="front"> <%= session.getAttribute("UserName") %></span><span class="back"> <%= session.getAttribute("UserName") %></span></span> </a>
        </li> 
        <li><a href="<%=request.getContextPath()%>/Logout" class="three-d" style="width:120px;position:fixed;"> Logout <span class="three-d-box"><span class="front">Logout</span><span class="back">Logout</span></span> </a>
        </li> 
        <%
        }else{
        %>
        <li><a href="<%=request.getContextPath()%>/jsp/Main/Login.jsp" class="login three-d" style="position:fixed;left:750px;"> Login <span class="three-d-box"><span class="front">Login</span><span class="back">Login</span></span> </a>
        </li>
        <li id="register"><a href="<%=request.getContextPath()%>/jsp/Main/Register.jsp" class="three-d" style="width:120px;position:fixed;left:1210px;" > Register <span class="three-d-box"><span class="front">Register</span><span class="back">Register</span></span> </a>
        </li>  
        <%} %>
      </ul>
   </div>
</nav>


<div class="kePublic">
<!--效果html开始-->
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1 style="margin-top:-45px;">Welcome Register</h1>
			
			<form class="form" method="post" action="<%=request.getContextPath()%>/Person/Register.do">
				<input type="text" placeholder="Username" name="UserName">
				<input type="password" placeholder="Password" name="Password">
				<input type="text"  placeholder="Not Necessary" name="Githublogin">
				<select class="form-control" style="width:222px;height:40px;" name="language">
                    <option>Java</option>
                    <option>C</option>
                    <option>C++</option>
                    <option>Python</option>
                    <option>JavaScript</option>
                    <option>Ruby</option>
                    <option>others</option>
                </select>
                <select class="form-control" style="width:222px;height:40px;" name="Location">
                    <option>China</option><option>United States</option><option>the United Kingdom</option>
                    <option>Germany</option><option>Japan</option><option>France</option>
                    <option>Italy</option><option>Australia</option><option>Spain</option>
                    <option>Portugal</option><option>Denmark</option><option>Singapore</option>
                    <option>South Korea</option><option>New Zealand</option><option>Thailand</option>
                    <option>India</option><option>Malaysia</option><option>Canada</option>
                    <option>Russia</option><option>others</option>
                </select>
                <select class="form-control" style="width:222px;height:40px;" name="RepoSize">
                    <option>Big</option>
                    <option>Middle</option>
                    <option>Small</option>
                    <option>Whatever</option>
   
                </select>
                <select class="form-control" style="width:222px;height:40px;" name="RepoTime">
                    <option>0~2 years</option>
                    <option>2~4 years</option>
                    <option>5~7 years</option>
                    <option>8~10 years</option>
                    <option>over 10 years</option>
                </select>
                             
                <select class="form-control" style="width:222px;height:40px;" name="RepoType">
                    <option>API</option><option>APP</option><option>JSON</option><option>Library</option>
                    <option>Linux</option><option>MAC</option><option>OS</option><option>Plugin</option>
                    <option>Server</option><option>Source</option><option>Web</option><option>others</option>
                </select>
				<button type="submit" id="login-button">Register</button>
			  
			</form>
			<div class="tips">
			    <div class="tip"><span class="font">UserName:</span></div>
			    <div class="tip1"><span class="font">PassWord:</span></div>
			    <div class="tip11"><span class="font">GitHub Account:</span></div>
			    <div class="tip2"><span class="font">First Language:</span></div>
			    <div class="tip3"><span class="font">Living:</span></div>
			    <div class="tip4"><span class="font">Preferred Repository Size:</span></div>
			    <div class="tip5"><span class="font">Preferred Repository Time:</span></div>
			    <div class="tip6"><span class="font">Preferred Repository Category:</span></div>
			</div>
			
		</div>
		<!-- <div class="tipDetail" style="margin-top:-10px;" ><span class="font2">If you don't have a GitHub account,you can leave it</span></div> -->
		<%        
            String message = (String)request.getAttribute("message");
            if(message != null){
        %>
            <label class="alert-warning" name="warning"><%=message%></label>
        <%
            }
        %>
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
</div>

<script src="http://pagead2.googlesyndication.com/pub-config/r20160212/ca-pub-2416180516640221.js"></script><script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script>
$('#login-button').click(function (event) {
	event.preventDefault();
	$('form').fadeOut(500);
	$('.wrapper').addClass('form-success');
});

</script>
<!--效果html结束-->
<div class="clear"></div>
</div>


</body>
</html>