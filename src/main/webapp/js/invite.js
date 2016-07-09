$(function() {
	$('#toInvite').find('tr').on('click', function() {
		addInvite($(this));
	});
	$('#searchName').bind('input propertychange', function() {
		searchUser($(this).val().trim());
	});
});

function addInvite(obj) {
	var temp = obj.clone();
	if (isUnique(temp))
		$('#invited').append(temp);
}

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
	alert(users[0])
}