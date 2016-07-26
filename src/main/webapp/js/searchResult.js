$(function(){
	searchTask("");
});


/**
 * search task by keyword 
 * @param keyword
 * @returns
 */
function searchTask(keyword) {
	run_waitMe();
	jQuery.ajax({
		url: '/crc/SearchServlet',
		type: 'post',
		data: 'type=searchTask&keyword=' + keyword + '&language=""',
		success: function(data) {
			stopWait();
			var result = jQuery.parseJSON(data);
			displayResult(result);
		}
	});
}
function displayResult(result) {
	alert(result.cpp[0].describe);
}