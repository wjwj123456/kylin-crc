/**
 * Created by song on 16-7-26.
 *
 * message
 */

/**
 * 接受邀请
 */
$('#accept').on('click', function () {
    run_waitMe();
    jQuery.ajax({
        url: '/crc/RefuseServlet?type=accept',
        type: 'post',
        success: function (data) {
            if (data == 0) {
                $('#accept').removeClass("btn-success").end().text("已参加");
                stopWait();
            }
        }
    });
});

/**
 * 已读消息
 */
$('#read').on('click', function () {
    run_waitMe();
    jQuery.ajax({
        url: '/crc/RefuseServlet?type=delete',
        type: 'post',
        success: function(data) {
            if (data == 0) {
                $(this).parent().parent().remove();
                stopWait();
            }
        }
    });
});
