var isOK = false;
$('#createTask').on('click', function() {
	isOK=true;
	checkOK();
	if (isOK) {
		createNewTask();
	}
});
$('#type').change(function(){
	if($(this).children('option:selected').val()=="文档评审"){
		$('#languageBlock').css('display','none');
	}else {
		$('#languageBlock').css('display','block');
	}
});

/**
 * 创建新项目
 * @returns
 */
function createNewTask() {
	run_waitMe();
	jQuery.ajax({
		url: '/crc/CreateTaskServlet',
		style: 'post',
		data: 'type=createNewTask' + '&data=' + getData(), 
		success: function(data) {
			if (data == 0) {
				top.location = 'My CRC.jsp';
			} else if (data == 1) {
				alert("项目已存在")
			}
			stopWait();
		}
	});	
}

/**
 * 获取数据
 * @returns
 */
function getData() {
	var type = ($('#type').get(0).selectedIndex == 1) ? 'code' : 'document';
	
	var task = new Object({
		taskName: $('#inputName').val().trim(),
		type: type, 
		project: '',
		describe: $('#discription').val().trim(),
		deadline: $('#deadline').val(),
		state: 0
	});
	
	task.language = task.type == '文档评审' ? 'none' : $('#language option:selected').val();
	
	console.log(JSON.stringify(task))
	return JSON.stringify(task);
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
