
$('#login').on('click', function () {
    if ($(this).text().trim() == '登录') {
        $('#loginModal').modal('show');
    } else {
        logout();
    }
});

// /**
//  * the operation after clicking login/logout button
//  */
// function submit() {
//     if ($('#login').text().trim() == '登录') {
//         login();
//     } else {
//         logout();
//     }
// }

/**
 * login operation
 */
function login() {
    jQuery.ajax({
        url: '/login',
        style: 'post',
        data: 'type=login' + '&username=' + $('#username').val().trim() + '&password=' + $('#password').val(),
        success: function (data) {
            if (data == 0) {
                $('#loginModal').modal('hide');
                $('#user-name').text($('#username').val().trim());
                $('#login').text('登出');
            } else if (data == 1) {
                alert('用户名不存在')
            } else if (data == 2) {
                alert('密码错误')
            }
        }
    });
}

/**
 * logout operation
 */
function logout() {
    $('#user-name').text();

    jQuery.ajax({
        url: '/logout',
        style: 'post',
        data: 'type=logout',
        success: function (data) {
            if (data == 'success') {
                $('#login').text('登录')
            }
        }
    });
}


