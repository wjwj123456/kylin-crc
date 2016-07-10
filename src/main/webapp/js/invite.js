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

$(function() {
	// 初始化typeahead
	$('#searchName').typeahead({
		source : function(query, process) {
			// query是输入值
			jQuery.getJSON('/Search/GetHotSearchItems', {
				"query" : query
			}, function(data) {
				process(data);
			});
		},
		updater : function(item) {
			return item.replace(/<a(.+?)<\/a>/, ""); // 这里一定要return，否则选中不显示
		},
		afterSelect : function(item) {
			// 选择项之后的时间，item是当前选中的项
			alert(item);
		},
		items : 8, // 显示8条
		delay : 500
	// 延迟时间
	});
});

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
		if ($(username[i]).children(':first').text().trim() == $(obj).children(
				':first').text().trim()) {
			return false;
		}
	}

	return true;
}

/**
 * display the user name after searching
 * 
 * @param users
 * @returns
 */
function displayUser(users) {
	console.log(users)
}