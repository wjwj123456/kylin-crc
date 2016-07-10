var hasUser = false;
var hasPass = false;

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

/**
 * login operation
 */
function login() {
	isOK();
	if(hasUser&&hasPass){
		jQuery.ajax({
			url : '/crc/login',
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
		url : '/crc/logout',
		style : 'post',
		data : 'type=logout',
		success : function(data) {
			if (data == 'success') {
				$('#login').text('登录')
				$('#user-name').text('');
			}
		}
	});
}

/**
 * register operation
 */
function register() {
	jQuery.ajax({
		url: '/crc/RegisterServlet',
		style: 'post',
		data: 'userName=' + '&email=' + '&password=',
		success: function(data) {
			alert(data);
		}
	});
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
