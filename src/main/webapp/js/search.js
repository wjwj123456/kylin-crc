/**
 * search users by keyword
 * @param keyword
 * @returns [], set of UserVO, may be null
 */
function searchUser(keyword) {
	jQuery.ajax({
		url: '/crc/SearchServlet',
		type: 'post',
		data: 'type=searchUser&keyword=' + keyword,
		success: function(data) {
			var result = jQuery.parseJSON(data);
			console.log(result);
		}
	});
}