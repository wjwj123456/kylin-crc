$(function() {
	$('#searchContent').keypress(function(e) {
		if (e.which == 13) {
			searchTask($('#searchContent').val().trim())
		}
	})
	$('#search').on('click', function() {
		searchTask($('#searchContent').val().trim())
	})
})

/**
 * search users by keyword
 * @param keyword
 * @returns [], set of UserVO, may be null
 */
function searchUser(keyword) {
	var users = [];
	
	jQuery.ajax({
		url: '/crc/SearchServlet',
		type: 'post',
		data: 'type=searchUser&keyword=' + keyword,
		success: function(data) {
			users = jQuery.parseJSON(data)[0].users;
			displayUser(users);
		}
	});
}