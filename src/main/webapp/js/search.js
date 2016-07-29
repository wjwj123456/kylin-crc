var content = '';

$(function() {
	$('#searchContent').keypress(function(e) {
		if (e.which == 13) {
			content = $('#searchContent').val();
			top.location = 'searchResult.jsp?content=' + encodeURI(encodeURI(content));
		}
	});

	$('#search').on('click', function() {
		content = $('#searchContent').val();
		top.location = 'searchResult.jsp?content=' + encodeURI(encodeURI(content));
	});
});

/**
 * search users by keyword
 * @param keyword
 * @returns [], set of UserVO, may be null
 */
function searchUser(keyword) {
	var users = [];
	
	jQuery.ajax({
		url: '/SearchServlet',
		type: 'post',
		data: 'type=searchUser&keyword=' + keyword,
		success: function(data) {
			users = jQuery.parseJSON(data)[0].users;
			displayUser(users);
		}
	});
}