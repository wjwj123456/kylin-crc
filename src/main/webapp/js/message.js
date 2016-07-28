/**
 * Created by song on 16-7-26.
 *
 * message
 */

/**
 * 接受邀请
 */
$('#messageTable tbody').find('button').not('.btn-new').on('click', function () {
    run_waitMe();
    var temp = $(this);
    jQuery.ajax({
        url: '/crc/RefuseServlet',
        type: 'post',
        data: 'type=accept&' + 'taskName=' + temp.parent().prev().children().text(),
        success: function () {
            temp.text("已参加");
            stopWait();
        }
    });
});

/**
 * 已读消息
 */
$('#messageTable tbody').find('button').filter('.btn-new').on('click', function () {
    run_waitMe();
    var temp = $(this);
    jQuery.ajax({
        url: '/crc/RefuseServlet',
        type: 'post',
        data: 'type=delete&' + 'taskName=' + temp.parent().prev().prev().children().text(),
        success: function (data) {
            temp.parent().parent().remove();
            stopWait();
        }
    });
});
