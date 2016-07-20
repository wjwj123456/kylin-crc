$(function() {
	$('#undo').on('click', function() {
		undoCommand();
	});
	$('#redo').on('click', function() {
		redoCommand();
	})
})

function undoCommand() {
	sendRequest('undo');
}

function redoCommand() {
	sendRequest('redo');
}

function sendRequest(type) {
	run_waitMe();
	jQuery.ajax({
		url: '/crc/CommandServlet',
		type: 'post',
		data: 'type=' + type,
		success: function(data) {
			location.reload(true);
			stopWait();
		}
	})
}