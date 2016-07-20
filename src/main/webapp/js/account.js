$(function() {
	$('#confirm').on('click', function() {
		confirm();
	})
})

/**
 * 确认更改
 * @returns
 */
function confirm() {
	run_waitMe();
	jQeury.ajax({
		url: '/crc/AccountServlet',
		type: 'post',
		data: 'type=update' + '&data=' + data,
		success: function(data) {
			stopWait();
		}
	});
}

/**
 * 获得用户信息
 * @returns
 */
function getData() {
	
}
