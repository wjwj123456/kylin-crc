$('#createTask').on('click', function() {
	createNewTask();
});

function createNewTask() {
	jQuery.ajax({
		url : '/crc/CreateTaskServlet',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		style : 'post',
		data : 'taskName=' + $('#inputName').val().trim() + '&type=' + $('#type').val()
				+ '&describe=' + $('#discription').val().trim() + '&deadline=' + $('#deadline').val(),
		success : function(data) {
			if (data == 'success') {
				top.location = 'My CRC.jsp';
			}
		}
	});
}