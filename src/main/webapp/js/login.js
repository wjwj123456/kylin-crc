/**
 * Created by song on 16-7-8.
 * 登录
 */

/**
 * 提交表单信息
 */
function submit() {
    jQuery.ajax({
        url: '/login',
        style: 'post',
        data: 'username=' + $('#username').text().trim() + '&password=' + $('#password').text(),
        success: function (data) {
            if (data == 'success') {
                console.log('test');
                $('#loginModal').modal('hide');
                $('#login').text('登出');
            } else {
                console.log($('#login').text())
            }
        }
    });
}
