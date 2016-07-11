
// store the task to operate thanks to 
// user can only operate a task at one time
var taskName;

$(function() {
	$('#searchName').bind('input propertychange', function() {
		if ($(this).val().trim() != '') {			
			searchUser($(this).val().trim());
		}
	});
	$('button[data-target="#inviteModal"]').on('click', function() {
		taskName = $(this).parent().parent().children().first().text().trim();
		loadAgreedReviewer(taskName);
	});
	$('#confirmInvite').on('click', function() {
		invite();
	});
});

/**
 * load agreed reviewers of a task
 * @param taskName
 * @returns
 */
function loadAgreedReviewer(taskName) {
	jQuery.ajax({
		url: '/crc/TaskServlet',
		type: 'post',
		data: 'type=agree' + '&taskName=' + taskName,
		success: function(data) {
			var users = jQuery.parseJSON(data)[0].users;
			for (var i = 0; i < users.length; i++) {
				$('#agreed').find('tbody').append('<tr><td>' + users[i] + '</td></tr>');
			}
		}		
	});
}

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
 * display the result of searching in left table
 * 
 * @param users
 * @returns
 */
function displayUser(users) {
	$('#toInvite').find('tbody').empty();
	
	for (var i = 0; i < users.length; i++) {
		$('#toInvite').find('tbody').append('<tr><td>' + users[i].name + '</td></tr>');
	}
	
	$('#toInvite').find('tbody>tr').on('click', function() {
		addInvite($(this));
	});
}

/**
 * refuse invitation from others
 * @returns
 */
function refuse() {
	jQuery.ajax({
		url: '/crc/RefuseServlet',
		style: 'post',
		data: 'taskName=',
		success: function(data) {
			if (data == 0) {
				alert('拒绝')
			}
		}
	});
}
var theTaskName;
function initInvite(obj) {
	theTaskName = $(obj).parent().parent().find('a').text();
	alert(theTaskName);
}

