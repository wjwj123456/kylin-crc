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
        data: $("form").serialize(),
        success: function (data) {
            if (data == 'success') {
                self.location = ''
            } else {

            }
        }
    });
}
