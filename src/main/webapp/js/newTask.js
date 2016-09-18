var isOK = false;

$('#next').on('click', function () {
    isOK = true;
    checkOK();
    if (isOK) {
        $(this).fadeOut();
        window.scrollTo(0, document.body.scrollHeight);

        uploadFile();
        $('#createTask').on('click', function () {
            createNewTask();
        }).fadeIn();
    }
});

$('#type').change(function () {
    if ($(this).children('option:selected').val() == "文档评审") {
        $('#languageBlock').css('display', 'none');
    } else {
        $('#languageBlock').css('display', 'block');
    }
});

/**
 * 创建新项目
 * @returns
 */
function createNewTask() {
    run_waitMe();
    jQuery.ajax({
        url: '/CreateTaskServlet',
        style: 'post',
        data: 'type=createNewTask' + '&data=' + getData(),
        success: function (data) {
            if (data == 0) {
                top.location = 'myTasks.jsp';
            } else if (data == 1) {
                alert("项目已存在");
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
    var power = $('#privacy').get(0).selectedIndex == 0 ? 'PUBLIC' : 'PRIVATE';
    var task = new Object({
        taskName: $('#inputName').val().trim(),
        type: type,
        project: '',
        describe: $('#discription').val().trim(),
        deadline: $('#deadline').val(),
        state: 0,
        power: power
    });

    task.language = task.type == '文档评审' ? 'none' : $('#language option:selected').val();

    console.log(JSON.stringify(task))
    return JSON.stringify(task);
}

function checkOK() {
    if ($('#inputName').val() == "") {
        $('#nameGroup').addClass('has-error');
        isOK = false;
    } else {
        $('#nameGroup').removeClass('has-error');
    }
    if ($('#discription').val() == "") {
        $('#discripGroup').addClass('has-error');
        isOK = false;
    } else {
        $('#discripGroup').removeClass('has-error');
    }
    if ($('#deadline').val() == "") {
        $('#deadGroup').addClass('has-error');
        isOK = false;
    } else {
        $('#deadGroup').removeClass('has-error');
    }
}

/**
 * 创建任务成功后，可选择上传文件
 */
function uploadFile() {
    $('#file').fadeIn('slow').attr('action',
        '/FileServlet?type=upload&taskName=' + $('#inputName').val().trim());
}
/**
 * 上传类型
 */
$('.selectOne').change(function(){
	if ($(this).children('option:selected').text() == "文件") {
        $('#fileUp').removeClass('hidden');
        $('#folderUp').addClass('hidden');
    } else {
    	$('#fileUp').addClass('hidden');
        $('#folderUp').removeClass('hidden');
    }
});