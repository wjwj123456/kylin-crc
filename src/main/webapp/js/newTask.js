var isOK = false;

$('#createTask').on('click', function() {
	checkOK();
	if (isOK) {
		createNewTask();
	}
});

function createNewTask() {
	jQuery.ajax({
		url : '/crc/CreateTaskServlet',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		style : 'post',
		data : 'taskName=' + $('#inputName').val().trim() + '&type='
				+ $('#type').val() + '&describe='
				+ $('#discription').val().trim() + '&deadline='
				+ $('#deadline').val(),
		success : function(data) {
			if (data == 'success') {
				top.location = 'My CRC.jsp';
			}
		}
	});
}
function checkOK() {
	if($('#inputName').val()==""){
		$('#nameGroup').addClass('has-error');
		isOK=false;
		return
	}else {
		$('#nameGroup').removeClass('has-error');
	}
	if($('#discription').val()==""){
		$('#discripGroup').addClass('has-error');
		isOK=false;
		return
	}else {
		$('#discripGroup').removeClass('has-error');
	}
	if($('#deadline').val()==""){
		$('#deadGroup').addClass('has-error');
		isOK=false;
		return
	}else {
		$('#deadGroup').removeClass('has-error');
	}
	isOK=true;
}