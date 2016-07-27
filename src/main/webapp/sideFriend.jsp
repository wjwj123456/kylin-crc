<!doctype html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>side</title>
	<!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.rawgit.com/AndreaLombardo/BootSideMenu/master/css/BootSideMenu.css">
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<!-- HTML5 shim e Respond.js per rendere compatibile l'HTML 5 in IE8 -->
	  <!-- ATTENZIONE: Respond.js non funziona se la pagina viene visualizzata in locale file:// -->
	  <!--[if lt IE 9]>
	  <script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js"></script>
	  <script src="js/respond.min.js"></script>
	  <![endif]-->
	<style type="text/css">
	  .user{
	    padding:5px;
	    margin-bottom: 5px;
	  }
	  a:hover{text-decoration: none;}
	</style>
</head>
<body>

	  <!--Test 2 -->
	  <div id="test2">
	   <div class="user">
	      <img src="img/face_japan_01.gif" alt="Esempio" class="img-thumbnail">
	      <a href="http://www.htmleaf.com" target="_blank" class="navbar-link">UserName</a>
	    </div>
	    <div class="list-group">
	      <a href="#" class="list-group-item active">Cras justo odio</a>
	      <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
	      <a href="#" class="list-group-item">Morbi leo risus</a>
	      <a href="#" class="list-group-item">Porta ac consectetur ac</a>
	      <a href="#" class="list-group-item">Vestibulum at eros</a>
	      <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
	      <a href="#" class="list-group-item">Morbi leo risus</a>
	      <a href="#" class="list-group-item">Porta ac consectetur ac</a>
	      <a href="#" class="list-group-item">Vestibulum at eros</a>
	      <a href="#subRight" class="list-group-item">Sub Menu</a>
	      <a href="#" class="list-group-item">Vestibulum at eros</a>
	    </div>

	    <div class="list-group submenu" id="subRight">
	      <a href="#" class="list-group-item">a destra</a>
	      <a href="#subRight2" class="list-group-item">Sub right 2</a>
	    </div> 

	        <div class="list-group submenu" id="subRight2">
	      <a href="#" class="list-group-item">Porta ac consectetur ac</a>
	      <a href="#" class="list-group-item">Vestibulum at eros</a>
	      <a href="#" class="list-group-item">Dapibus ac facilisis in</a>
	      <a href="#" class="list-group-item">Morbi leo risus</a>
	      <a href="#" class="list-group-item">Porta ac consectetur ac</a>
	      <a href="#" class="list-group-item">Vestibulum at eros</a>
	    </div> 
	  </div>
	  <!--/Test 2-->
	  <!--Normale contenuto di pagina-->
	
	  <!--Normale contenuto di pagina-->
	
	<script src="http://libs.useso.com/js/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.rawgit.com/AndreaLombardo/BootSideMenu/master/js/BootSideMenu.js"></script>
    <script type="text/javascript">
	  $(document).ready(function(){
	      $('#test').BootSideMenu({side:"left", autoClose:false});
	      $('#test2').BootSideMenu({side:"right"});
	  });
	</script>
</body>
</html>