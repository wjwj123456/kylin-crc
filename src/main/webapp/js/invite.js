
// store the task to operate thanks to 
// user can only operate a task at one time
var taskName;

$(function() {
	$('#toInvite').find('tr').on('click', function() {
		addInvite($(this));
	});
	$('#searchName').bind('input propertychange', function() {
		if ($(this).val().trim() != '') {			
			searchUser($(this).val().trim());
		}
	});
	$('button[data-target="#inviteModal"]').on('click', function() {
		taskName = $(this).parent().parent().children().first().text().trim();
	});
	$('#confirmInvite').on('click', function() {
		invite();
	});
});

/**
 * invite 
 * @returns
 */
function invite() {
	var temp = $('#invited').find('td');
	var users = '';
	for (var i = 0; i < temp.length; i++) {
		users += $(temp[i]).text().trim() + ' ';
	}
	
	// TODO taskName
	jQuery.ajax({
		url: '/crc/InviteServlet',
		type: 'post',
		data: 'taskName=' + taskName + '&userNumber=' + temp.length +'&users=' + users,
		success: function(data) {
			if (data == 0) {
				alert('已发出邀请')
			}
		}
	});
}

/**
 * add a user into invite list
 */
function addInvite(obj) {
	if (isUnique(obj))
		$('#invited').append(obj.clone());
}

/**
 * judge whether a user already in invite list
 */
function isUnique(obj) {
	var username = $('#invited').find('tr');

	for (var i = 0; i < username.length; i++) {
		if ($(username[i]).children(':first').text().trim() == $(obj).children(
				':first').text().trim()) {
			return false;
		}
	}

	return true;
}

/**
 * display the result of searching
 * 
 * @param users
 * @returns
 */
function displayUser(users) {
//	console.log(users)
//	$('#searchName').val(users[0].name)
}

/**
 * refuse invitation from others
 * @returns
 */
function refuse() {
	
}

