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
        url: '/crc/RefuseServlet',
        type: 'post',
        data: 'type=accept&' + 'taskName=' + $('#accept').parent().prev().children().text(),
        success: function () {
            $('#accept').removeClass("btn-success").text("已参加");
            stopWait();
        }
    });
});

/**
 * 已读消息
 */
$('#read').on('click', function () {
    run_waitMe();
    jQuery.ajax({
        url: '/crc/RefuseServlet',
        type: 'post',
        data: 'type=delete&' + 'taskName=' + $('#read').parent().prev().prev().children().text(),
        success: function (data) {
            $(this).parent().parent().remove();
            stopWait();
        }
    });
});
