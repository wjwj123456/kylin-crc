var isOK = false;

$('#createTask').on('click', function() {
	isOK=true;
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
	}else {
		$('#nameGroup').removeClass('has-error');
	}
	if($('#discription').val()==""){
		$('#discripGroup').addClass('has-error');
		isOK=false;
	}else {
		$('#discripGroup').removeClass('has-error');
	}
	if($('#deadline').val()==""){
		$('#deadGroup').addClass('has-error');
		isOK=false;
	}else {
		$('#deadGroup').removeClass('has-error');
	}
}