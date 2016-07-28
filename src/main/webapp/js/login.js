var hasUser = false;
var hasPass = false;
var regOK1 = false;
var regOK2 = false;
var regOK3 = false;
var regOK4 = false;

$('#login').on('click', function() {
	if ($(this).text().trim() == '登录') {
		$('#loginModal').modal('show');
	} else {
		logout();
	}
});
$('#username').on('blur',function(){
	
	if($('#username').val()==""){
		$('#usergroup').addClass('has-error');
		hasUser=false;
	}else {
		$('#usergroup').removeClass('has-error');
		hasUser=true;
	}
});
$('#password').on('blur',function(){
	
	if($('#password').val()==""){
		$('#passgroup').addClass('has-error');
		hasPass=false;
	}else {
		$('#passgroup').removeClass('has-error');
		hasPass=true;
	}
});
$('#regUsername').on('blur',function(){
	
	if($('#regUsername').val()==""){
		$('#regUserGroup').addClass('has-error');
		regOK1=false;
	}else {
		$('#regUserGroup').removeClass('has-error');
		regOK1=true;
	}
});
$('#regPassword').on('blur',function(){
	
	if($('#regPassword').val()==""){
		$('#regPassGroup').addClass('has-error');
		regOK2=false;
	}else {
		$('#regPassGroup').removeClass('has-error');
		regOK2=true;
	}
});
$('#regConfirm').on('blur',function(){
	
	if($('#regConfirm').val()==""||$('#regConfirm').val().trim()!=$('#regPassword').val().trim()){
		$('#regConfirmGroup').addClass('has-error');
		regOK3=false;
	}else {
		$('#regConfirmGroup').removeClass('has-error');
		regOK3=true;
	}
});
$('#regMail').on('blur',function(){
	
	if($('#regMail').val()==""){
		$('#regMailGroup').addClass('has-error');
		regOK4=false;
	}else {
		$('#regMailGroup').removeClass('has-error');
		regOK4=true;
	}
});

/**
 * login operation
 */
function login() {
	isOK();
	if(hasUser&&hasPass){
		jQuery.ajax({
			url : '/login',
			style : 'post',
			data : 'type=login' + '&username=' + $('#username').val().trim()
					+ '&password=' + $('#password').val(),
			success : function(data) {
				if (data == 0) {
					location.reload(true);
				} else if (data == 1) {
					alert('用户名不存在')
				} else if (data == 2) {
					alert('密码错误')
				}
			}
		});
	}
	
}

/**
 * logout operation
 */
function logout() {
	jQuery.ajax({
		url : '/logout',
		style : 'post',
		data : 'type=logout',
		success : function(data) {
			if (data == 'success') {
				top.location = 'index.jsp';
			}
		}
	});
}

/**
 * register operation
 */
function register() {
	var userName = $('#signUpModal').find(':input[name="username"]').val().trim();
	var password = $('#signUpModal').find(':input[name="password"]').val().trim();
	var email = $('#signUpModal').find(':input[name="mail"]').val().trim();
	if(regOK1&&regOK2&&regOK3&&regOK4){
		jQuery.ajax({
			url : '/RegisterServlet',
			style : 'post',
			data : 'userName=' + userName + '&email=' + email + '&password='
					+ password,
			success : function(data) {
				if (data == '1') {
					alert('账户已存在')
				} else if (data == '2') {
					alert('未知错误')
				} else if (data == '0') {
					location.reload(true)
				}
			}
		});
		
	}
}

function isOK(){
	if($('#username').val()==""){
		$('#usergroup').addClass('has-error');
		hasUser=false;
	}else {
		$('#usergroup').removeClass('has-error');
		hasUser=true;
	}
	if($('#password').val()==""){
		$('#passgroup').addClass('has-error');
		hasPass=false;
	}else {
		$('#passgroup').removeClass('has-error');
		hasPass=true;
	}
}
