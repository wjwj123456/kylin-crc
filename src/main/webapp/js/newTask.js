var isOK = false;
$('#createTask').on('click', function() {
	isOK=true;
	checkOK();
	if (isOK) {
		createNewTask();
	}
});
$('#doc').on('click',function(){
	$('#languageBlock').addClass('hide');
});
$('#code').on('click',function(){
	$('#languageBlock').removeClass('hide');
});
function createNewTask() {
	var type = ($('#type').get(0).selectedIndex == 1) ? 'code' : 'document';
	run_waitMe();
	jQuery.ajax({
		url : '/crc/CreateTaskServlet',
		style : 'post',
		data : 'taskName=' + $('#inputName').val().trim() + '&type='
				+ type + '&describe='
				+ $('#discription').val().trim() + '&deadline='
				+ $('#deadline').val(),
		success : function(data) {
			if (data == 'success') {
				top.location = 'My CRC.jsp';
			}
			stopWait();
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
