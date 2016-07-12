<<<<<<< HEAD

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
	$('button:contains("接受邀请")').on('click', function() {
		accept($(this))
	});
	$('button:contains("x")').on('click', function() {
		$(this).parents('tr').remove();
	});
	$('#confirmInvite').on('click', function() {
		invite();
	});
});

/**
 * load agreed reviewers of a task
 * @param taskName
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
			if (data != 2) {
				$('#inviteModal').modal('hide')
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
 * accept invitation from others
 */
function accept(taskButton) {
	var name = $(taskButton).parent().prev().find('a').text();
	jQuery.ajax({
		url: '/crc/RefuseServlet',
		style: 'post',
		data: 'type=accept&taskName=' + name,
		success: function(data) {
			if (data == 0) {				
				$(taskButton).text('已加入')
				$(taskButton).removeClass('btn-success')
			}
		}
	});
}

/**
 * refuse invitation from others
 */
function refuse() {
	jQuery.ajax({
		url: '/crc/RefuseServlet',
		style: 'post',
		data: 'type=refuse&taskName=' + taskName,
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
}

=======

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
	$('button:contains("接受邀请")').on('click', function() {
		accept($(this))
	});
	$('button:contains("x")').on('click', function() {
		$(this).parents('tr').remove();
	});
	$('#confirmInvite').on('click', function() {
		invite();
	});
});

/**
 * load agreed reviewers of a task
 * @param taskName
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
 * accept invitation from others
 */
function accept(taskButton) {
	var name = $(taskButton).parent().prev().find('a').text();
	jQuery.ajax({
		url: '/crc/RefuseServlet',
		style: 'post',
		data: 'type=accept&taskName=' + name,
		success: function(data) {
			if (data == 0) {				
				$(taskButton).text('已加入')
				$(taskButton).removeClass('btn-success')
			}
		}
	});
}

/**
 * refuse invitation from others
 */
function refuse() {
	jQuery.ajax({
		url: '/crc/RefuseServlet',
		style: 'post',
		data: 'type=refuse&taskName=' + taskName,
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

>>>>>>> 8aff2ba32590b73d3f30675e599fcaf3c1aedc92
