var isOK = false;

$('#inputName').on('change', function () {
    var text = $('#inputName').val().trim();

    $('#form_file').attr('action', '/FileServlet?type=upload&taskName=' + text);
    $('#form_folder').attr('action', '/FileServlet?type=uploadFolder&taskName=' + text);
});

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
                alert("项目名称已存在");
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

    task.language = task.type == 'document' ? 'none' : $('#language').find('option:selected').val();

    console.log(JSON.stringify(task));
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
    $('#file').fadeIn('slow');
}
/**
 * 上传类型
 */
$('.selectOne').change(function () {
    if ($(this).children('option:selected').text() == "文件") {
        $('#fileUp').removeClass('hidden');
        $('#folderUp').addClass('hidden');
    } else {
        $('#fileUp').addClass('hidden');
        $('#folderUp').removeClass('hidden');
    }
});

// wait for the DOM to be loaded
$(document).ready(function () {
    $('#upload_file').on('click', function () {
        if ($('#upload_file').hasClass('disabled')) {
            return;
        }

        run_waitMe();
    });

    $('#upload_folder').on('click', function() {
        if ($('#upload_folder').hasClass('disabled')) {
            return;
        }

        run_waitMe();
    });

    // 添加文件上传结束后的回调函数
    $('#form_file').ajaxForm(function () {
        alert("文件上传成功!");
        stopWait();

        $('#upload_file').addClass('disabled');
    });

    $('#form_folder').ajaxForm(function () {
        alert("文件夹上传成功!");
        stopWait();

        $('#upload_folder').addClass('disabled');
    })
});