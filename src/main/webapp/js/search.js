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

/**
 * search task by keyword 
 * @param keyword
 * @returns
 */
function searchTask(keyword) {
	jQuery.ajax({
		url: '/crc/SearchServlet',
		type: 'post',
		data: 'type=searchTask&keyword=' + keyword,
		success: function(data) {
			
		}
	});
}