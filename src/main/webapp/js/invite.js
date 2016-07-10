$(function() {
	$('#toInvite').find('tr').on('click', function() {
		addInvite($(this));
	});
	$('#searchName').bind('input propertychange', function() {
		if ($(this).val().trim() != '') {			
			searchUser($(this).val().trim());
		}
	});
});

function invite() {
	
	jQuery.ajax({
		url: '/crc/InviteServlet',
		type: 'post',
		data: '',
		success: function(data) {
			
		}
	});
}

/**
 * add a user into invite list
 */
function addInvite(obj) {
	if (isUnique(obj))
		$('#invited').prepend(obj.clone());
}

/**
 * judge whether a user already in invite list
 */
function isUnique(obj) {
	var username = $('#invited').find('tr');
	
	for (var i = 0; i < username.length; i++) {
		if ($(username[i]).children(':first').text().trim() == $(obj).children(':first').text().trim()) {
			return false;
		}
	}
	
	return true;
}

/**
 * display the user name after searching
 * @param users 
 * @returns
 */
function displayUser(users) {
	console.log(users)
}