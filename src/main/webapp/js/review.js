var itemOK = true;
var reportOK = true;
var username;
var taskType;
var taskName;
$(function () {
    if (taskType == 'code') {
        $('#docBlock').hide();
        $('#toMerge-file').hide();
        $('#merged-file').hide();
        $('#fileDiv').hide();
    } else {
        $('#codeBlock').hide();
        $('#toMerge-code').hide();
        $('#merged-code').hide();
        $('#codeDiv').hide();
    }

});
$('#add-code')
    .on(
        'click',
        function () {
            if (isCodeItemOK()) {
                $('#codeStart')
                    .append(
                        "<tr> <td>"
                        + $('#fileName-code').val()
                        + "</td> <td>"
                        + $('#lineNum-code').val()
                        + "</td> <td>"
                        + $('#discription-code').val()
                        + "</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
                storeCode();
            }
        });

$('#add-choosecode').on('click', function () {
    if (ischooseCodeItemOK()) {
        var report = new Object({
            taskName: taskName,
            userName: $('#user-name').text().trim(),
            fileName: $('#fileName-choosecode').val().trim(),
            page: 0,
            location: Number($('#lineNum-choosecode').val().trim()),
            description: $('#discription-choosecode').val().trim(),
            state: 0,
            origin: 1
        });

        storeCodeMerge(report);
    }

});
$('#add-file')
    .on(
        'click',
        function () {
            if (isFileItemOK()) {
                $('#docStart')
                    .append(
                        "<tr> <td>"
                        + $('#fileName-file').val()
                        + "</td> <td>"
                        + $('#pageNum-file').val()
                        + "</td> <td>"
                        + $('#lineNum-file').val()
                        + "</td> <td>"
                        + $('#discription-file').val()
                        + "</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
                storeFile();
            }
        });
$('#add-choosefile').on('click', function () {
    if (ischooseFileItemOK()) {
        var report = new Object({
            taskName: taskName,
            userName: $('#user-name').text().trim(),
            fileName: $('#fileName-choosefile').val().trim(),
            page: Number($('#pageNum-choosefile').val().trim()),
            location: Number($('#lineNum-choosefile').val().trim()),
            description: $('#discription-choosefile').val().trim(),
            state: 0,
            origin: 1
        });

        storeFileMerge(report);
    }
});

function initCodeChoose() {
    $('#choose-code').find('tr').not(':first').on('dblclick', function () {
        var temp = $(this).find('td');
        var report = new Object({
            taskName: taskName,
            userName: $(temp[3]).text(),
            fileName: $(temp[0]).text(),
            page: 0,
            location: Number($(temp[1]).text()),
            description: $(temp[2]).text(),
            state: 0,
            origin: 0
        });

        storeCodeMerge(report);
    });
};

$('#merge')
    .on(
        'click',
        function () {
            $('#choose-code').empty();
            $('#choose-file').empty();
            $('#fileName-choosecode').val('');
            $('#lineNum-choosecode').val('');
            $('#discription-choosecode').val('');
            $('#fileName-choosefile').val('');
            $('#pageNum-choosefile').val('');
            $('#lineNum-choosefile').val('');
            $('#discription-choosefile').val('');
            if (taskType == 'code') {
                $('#choose-code')
                    .append(
                        $("<tr><th>文件名</th><th>行数</th><th>描述</th><th>评审人</th></tr>"));
                codeMerge();
            } else {
                $('#choose-file')
                    .append(
                        $("<tr><th>文件名</th><th>页码</th><th>行数</th><th>描述</th><th>评审人</th></tr>"));
                fileMerge();
            }
        });

function initFileChoose() {
    $('#choose-file').find('tr').not(':first').on('dblclick', function () {
        var temp = $(this).find('td');
        var report = new Object({
            taskName: taskName,
            userName: $(temp[4]).text(),
            fileName: $(temp[0]).text(),
            page: Number($(temp[1]).text()),
            location: Number($(temp[2]).text()),
            description: $(temp[3]).text(),
            state: 0,
            origin: 0
        })

        storeFileMerge(report);
    });
}

// $('#start').before('<div class='form-group'> <label for='inputName'
// class='col-sm-1 control-label'>文件名</label> <div class='col-sm-5'> <input
// type='text' class='form-control' id='inputName' placeholder='**.*'> </div>
// <label for='inputName' class='col-sm-1 control-label'>行数</label> <div
// class='col-sm-5'> <input type='text' class='form-control' id='inputName'
// placeholder=''> </div> </div> <div class='form-group'> <label for='inputName'
// class='col-sm-1 control-label'>描述</label> <div class='col-sm-11'> <textarea
// class='form-control' rows='1' id='discription'></textarea> </div> </div>');
function codeMerge() {
    var list = new Array();
    var inputs = $('#toMerge-code').children().children().not('.collapse')
        .find('input');
    for (i = 0; i < inputs.length; i++) {
        if ($(inputs[i]).prop('checked') == true) {
            var temp = $(
                $('#toMerge-code').children().children().not('.collapse')[i + 1])
                .clone();
            var temptr = $("<tr></tr>");
            var length = $(temp).children().length;
            for (j = 1; j < length - 1; j++) {
                var temptd = $(temp).children().eq(1);
                temptr.append($(temptd));
            }
            list.push(temptr);
        }
    }
    for (i in list) {
        $('#choose-code').append($(list[i]));
    }
    initCodeChoose();
    $('#chooseModal').modal('show');
}
function fileMerge() {
    var list = new Array();
    var inputs = $('#toMerge-file').children().children().not('.collapse')
        .find('input');
    for (i = 0; i < inputs.length; i++) {
        if ($(inputs[i]).prop('checked') == true) {
            var temp = $(
                $('#toMerge-file').children().children().not('.collapse')[i + 1])
                .clone();
            var temptr = $("<tr></tr>");
            var length = $(temp).children().length;
            for (j = 1; j < length - 1; j++) {
                var temptd = $(temp).children().eq(1);
                temptr.append($(temptd));
            }
            list.push(temptr);
        }
    }
    for (i in list) {
        $('#choose-file').append($(list[i]));
    }
    initFileChoose();
    $('#chooseModal').modal('show');
}

/**
 * 删除表格中记录前需删除数据库中的记录，否则无法删除
 *
 * @param obj
 */
function deleteItem(obj) {
    var data = $(obj).parent().parent().find('td');

    if (data.length == 4) { // 删除代码记录
        deleteCode(data, obj);
    } else { // 删除文档记录
        deleteFile(data, obj);
    }
}

function isCodeItemOK() {
    itemOK = true;
    if ($('#fileName-code').val() == "") {
        $('#fileGroup-code').addClass('has-error');
        itemOK = false;
    } else {
        $('#fileGroup-code').removeClass('has-error');
    }
    if ($('#lineNum-code').val() == ""
        || isNaN($('#lineNum-code').val().trim())) {
        $('#lineGroup-code').addClass('has-error');
        itemOK = false;
    } else {
        $('#lineGroup-code').removeClass('has-error');
    }
    if ($('#discription-code').val() == "") {
        $('#discripGroup-code').addClass('has-error');
        itemOK = false;
    } else {
        $('#discripGroup-code').removeClass('has-error');
    }
    if (itemOK) {
        if (!codeUnique()) {
            itemOK = false;
        }
    }
    return itemOK;
}

function ischooseCodeItemOK() {
    itemOK = true;
    if ($('#fileName-choosecode').val() == "") {
        $('#fileGroup-choosecode').addClass('has-error');
        itemOK = false;
    } else {
        $('#fileGroup-choosecode').removeClass('has-error');
    }
    if ($('#lineNum-choosecode').val() == ""
        || isNaN($('#lineNum-code').val().trim())) {
        $('#lineGroup-choosecode').addClass('has-error');
        itemOK = false;
    } else {
        $('#lineGroup-choosecode').removeClass('has-error');
    }
    if ($('#discription-choosecode').val() == "") {
        $('#discripGroup-choosecode').addClass('has-error');
        itemOK = false;
    } else {
        $('#discripGroup-choosecode').removeClass('has-error');
    }

    return itemOK;
}

/**
 * judge whether an item already existed in report table
 *
 * @returns
 */
function codeUnique() {
    var items = $('#codeTable').find('tr').filter(
        function () {
            var fileName = $(this).find('td:first');

            if ($(fileName).text() == $('#fileName-code').val().trim()
                && $(fileName).next().text() == $('#lineNum-code')
                    .val().trim()
                && $(fileName).next().next().text() == $(
                    '#discription-code').val().trim()) {
                return $(this);
            }
        });
    if (!(items.length == 0)) {
        alert("重复项目");
    }

    return items.length == 0;
}
function fileUnique() {
    var items = $('#fileTable').find('tr').filter(
        function () {
            var fileName = $(this).find('td:first');

            if ($(fileName).text() == $('#fileName-file').val().trim()
                && $(fileName).next().text() == $('#pageNum-code')
                    .val().trim()
                && $(fileName).next().text().next() == $(
                    '#lineNum-file').val().trim()
                && $(fileName).next().next().text().next() == $(
                    '#discription-file').val().trim()) {
                return $(this);
            }
        });
    if (!(items.length == 0)) {
        alert("重复项目");
    }
    return items.length == 0;
}

function isFileItemOK() {
    itemOK = true;
    if ($('#fileName-file').val() == "") {
        $('#fileGroup-file').addClass('has-error');
        itemOK = false;
    } else {
        $('#fileGroup-file').removeClass('has-error');
    }
    if ($('#lineNum-file').val() == ""
        || isNaN($('#lineNum-file').val().trim())) {
        $('#lineGroup-file').addClass('has-error');
        itemOK = false;
    } else {
        $('#lineGroup-file').removeClass('has-error');
    }
    if ($('#discription-file').val() == "") {
        $('#discripGroup-file').addClass('has-error');
        itemOK = false;
    } else {
        $('#discripGroup-file').removeClass('has-error');
    }
    if ($('#pageNum-file').val() == ""
        || isNaN($('#lineNum-file').val().trim())) {
        $('#pageGroup-file').addClass('has-error');
        itemOK = false;
    } else {
        $('#pageGroup-file').removeClass('has-error');
    }
    if (itemOK) {
        if (!fileUnique()) {
            itemOK = false;
        }
    }

    return itemOK;
}

function ischooseFileItemOK() {
    itemOK = true;
    if ($('#fileName-choosefile').val() == "") {
        $('#fileGroup-choosefile').addClass('has-error');
        itemOK = false;
    } else {
        $('#fileGroup-choosefile').removeClass('has-error');
    }
    if ($('#lineNum-choosefile').val() == ""
        || isNaN($('#lineNum-code').val().trim())) {
        $('#lineGroup-choosefile').addClass('has-error');
        itemOK = false;
    } else {
        $('#lineGroup-choosefile').removeClass('has-error');
    }
    if ($('#discription-choosefile').val() == "") {
        $('#discripGroup-choosefile').addClass('has-error');
        itemOK = false;
    } else {
        $('#discripGroup-choosefile').removeClass('has-error');
    }
    if ($('#pageNum-choosefile').val() == ""
        || isNaN($('#lineNum-code').val().trim())) {
        $('#pageGroup-choosefile').addClass('has-error');
        itemOK = false;
    } else {
        $('#pageGroup-choosefile').removeClass('has-error');
    }
    return itemOK;
}

function isReportOK() {
    reportOK = true;
    if ($('#timeCost').val() == ""
        && !isNaN(Number($('#timeCost').val().trim()))) {
        $('#timeGroup').addClass('has-error');
        reportOK = false;
    } else {
        $('#timeGroup').removeClass('has-error');
    }
    return reportOK;
}

/**
 * 每添加一条记录，就向数据库中存储一条记录
 */
function storeCode() {
    var report = new Object({
        taskName: taskName,
        fileName: $('#fileName-code').val().trim(),
        page: 0,
        location: Number($('#lineNum-code').val().trim()),
        description: $('#discription-code').val().trim(),
        state: 0,
        origin: 0
    });

    store(report);
}

/**
 * 每添加一条记录，即向数据库中添加一条记录
 */
function storeFile() {
    var report = new Object({
        taskName: taskName,
        fileName: $('#fileName-file').val().trim(),
        page: Number($('#pageNum-file').val().trim()),
        location: Number($('#lineNum-file').val().trim()),
        description: $('#discription-file').val().trim(),
        state: 0,
        origin: 0
    });

    store(report);
}

/**
 * 存储一条记录
 *
 * @param data
 *            记录数据
 */
function store(data) {
    run_waitMe();
    jQuery.ajax({
        url: '/crc/ReportServlet',
        type: 'post',
        data: 'type=store' + '&data=' + JSON.stringify(data),
        success: function (data) {
            stopWait();
        },
        error: function () {
            alert('出错了，更改无法保存')
            stopWait();
        }
    });
}

/**
 * 每删除一条记录，即从数据库删除一条记录
 */
function deleteCode(data, obj) {
    var report = new Object({
        taskName: taskName,
        userName: $(data[3]).text().trim(),
        fileName: $(data[0]).text().trim(),
        page: 0,
        location: Number($(data[1]).text().trim()),
        description: $(data[2]).text().trim(),
        state: 0,
        origin: 0
    });

    deleteCodeAndFile(report, obj);
}

/**
 * 每删除一条记录，即从数据库删除一条记录
 */
function deleteFile(data, obj) {
    var report = new Object({
        taskName: taskName,
        userName: $(data[4]).text().trim(),
        fileName: $(data[0]).text().trim(),
        page: Number($(data[1]).text().trim()),
        location: Number($(data[2]).text().trim()),
        description: $(data[3]).text().trim(),
        state: 0,
        origin: 0
    });

    deleteCodeAndFile(report, obj);
}

/**
 * 删除一条记录
 *
 * @param data
 *            记录数据
 */
function deleteCodeAndFile(data, obj) {
    run_waitMe();
    jQuery.ajax({
        url: '/crc/ReportServlet',
        type: 'post',
        data: 'type=delete' + '&data=' + JSON.stringify(data),
        success: function (data) {
            $(obj).parent().parent().remove();
            stopWait();
        },
        error: function () {
            alert('出错了，更改无法保存')
        }
    });

}

/**
 * commit report
 *
 * @returns
 */
function commitReport() {
    if (isReportOK()) {
        run_waitMe();
        jQuery.ajax({
            url: '/crc/ReportServlet',
            type: 'post',
            data: 'type=commit&taskName=' + taskName + '&time='
            + $('#timeCost').val().trim(),
            success: function (data) {
                location.reload(true);
                stopWait();
            }
        });

    }
}

// merge 操作相关
{
    function storeCodeMerge(report) {
        var data = new Array();
        data.push(report);

        var temp = $('#choose-code').find('tr').not(':first');
        for (var i = 0; i < temp.length; i++) {
            var td = $(temp[i]).find('td');
            var obj = new Object({
                taskName: taskName,
                userName: $(td[3]).text(),
                fileName: $(td[0]).text(),
                page: 0,
                location: Number($(td[1]).text()),
                description: $(td[2]).text(),
                state: 0,
                origin: 1
            });
            data.push(obj);
        }
        run_waitMe();
        console.log(JSON.stringify(data));
        jQuery.ajax({
            url: '/crc/MergeServlet',
            type: 'post',
            data: 'type=saveMerge&taskName=' + taskName + '&data='
            + JSON.stringify(data),
            success: function (data) {
                if (data == 404) {
                    alert('当前有用户正在合并，请等待')
                } else {
                    afterCodeMerge(report);
                }
                stopWait();
            }
        });
    }

    function storeFileMerge(report) {
        var data = new Array();
        data.push(report);

        var temp = $('#choose-file').find('tr').not(':first');
        for (var i = 0; i < temp.length; i++) {
            var td = $(temp[i]).find('td');
            var obj = new Object({
                taskName: taskName,
                userName: $(td[4]).text(),
                fileName: $(td[0]).text(),
                page: Number($(td[1]).text()),
                location: Number($(td[2]).text()),
                description: $(td[3]).text(),
                state: 0,
                origin: 1
            });
            data.push(obj);
        }
        run_waitMe();
        jQuery.ajax({
            url: '/crc/MergeServlet',
            type: 'post',
            data: 'type=saveMerge&taskName=' + taskName + '&data='
            + JSON.stringify(data),
            success: function (data) {
                if (data == 404) {
                    alert('当前有用户正在合并，请等待')
                } else {
                    afterFileMerge(report);
                }
                stopWait();
            }
        });
    }

    /**
     * 代码合并条目以后的操作，将合并后的条目加入合并列表，删除原始条目
     */
    function afterCodeMerge(report) {
        var inputs = $('#toMerge-code').children().children().not('.collapse')
            .find('input');
        var mark;
        var j = 0;
        for (i = 0; i < inputs.length; i++) {
            if ($(inputs[i]).prop('checked') == true) {
                mark = $('#toMerge-code tbody').children().not('.collapse')[j + 1];
                $($('#toMerge-code tbody').children().not('.collapse')[j])
                    .remove();
                j--;
            }
            j++;
        }
        $(mark)
            .before(
                '<tr><td><input type="checkbox"></td>'
                + '<td>'
                + report.fileName
                + '</td>'
                + '<td>'
                + report.location
                + '</td>'
                + '<td>'
                + report.description
                + '</td>'
                + '<td>'
                + report.userName
                + '</td>'
                + '<td><button class="btn btn-sm">拆分</button></td></tr>'
                + '<tr class="collapse fade">'
                + '<td colspan="6"></td>' + '</tr>');
        $('#chooseModal').modal('hide');
        reBind();
    }

    /**
     * 文档合并条目以后的操作，将合并后的条目加入合并列表，删除原始条目
     */
    function afterFileMerge(report) {
        var inputs = $('#toMerge-file').children().children().not('.collapse')
            .find('input');
        var j = 0;
        var mark;
        for (i = 0; i < inputs.length; i++) {
            if ($(inputs[i]).prop('checked') == true) {
                mark = $('#toMerge-file tbody').children().not('.collapse')[j + 1];
                $($('#toMerge-file tbody').children().not('.collapse')[j])
                    .remove();
                j--;
            }
            j++;
        }
        $(mark)
            .before(
                '<tr><td><input type="checkbox"></td><td>'
                + report.fileName
                + '</td>'
                + '<td>'
                + report.page
                + '</td>'
                + '<td>'
                + report.location
                + '</td>'
                + '<td>'
                + report.description
                + '</td>'
                + '<td>'
                + report.userName
                + '</td>'
                + '<td><button class="btn btn-sm">拆分</button></td></tr>'
                + '<tr class="collapse fade">'
                + '<td colspan="7"></td>' + '</tr>');
        $('#chooseModal').modal('hide');
        reBind();
    }
}
// 删除合并条目相关操作
{
    /**
     * 删除代码条目
     *
     * @param obj
     * @returns
     */
    function deleteCodeMerge(obj) {
        var temp = $(obj).parent().parent().find('td');
        var report = new Object({
            taskName: taskName,
            userName: $(temp[3]).text(),
            fileName: $(temp[0]).text(),
            page: 0,
            location: $(temp[1]).text(),
            description: $(temp[2]).text(),
            state: 1,
            origin: 0
        });

        deleteMerge(report, obj);
    }

    /**
     * 删除文档条目
     *
     * @param obj
     * @returns
     */
    function deleteFileMerge(obj) {
        var temp = $(obj).parent().parent().find('td');
        var report = new Object({
            taskName: taskName,
            userName: $(temp[4]).text(),
            fileName: $(temp[0]).text(),
            page: $(temp[1]).text(),
            location: $(temp[2]).text(),
            description: $(temp[3]).text(),
            state: 1,
            origin: 0
        });

        deleteMerge(report, obj);
    }

    /**
     * 删除条目
     *
     * @param report
     * @returns
     */
    function deleteMerge(report, obj) {
        var data = new Array();
        data.push(report);
        run_waitMe();
        jQuery.ajax({
            url: '/crc/MergeServlet',
            type: 'post',
            data: 'type=deleteMerge&data=' + JSON.stringify(data),
            success: function (data) {
                $(obj).parent().parent().addClass("drop");
                stopWait();
            },
            error: function () {
                alert('出错了，更改无法生效')
            }
        });

    }
}

// 提交合并报告
{
    $('#confirmMerge').on('click', function () {
        run_waitMe();
        jQuery.ajax({
            url: '/crc/MergeServlet',
            type: 'post',
            data: 'type=commitMerge&taskName=' + taskName,
            success: function (data) {
                // if (data == 0) {
                location.reload(true)
                // }
                stopWait();
            }
        });
    })
}

// 拆分相关操作
{
    // 选择被拆分的报告
    var report;

    $('#toMerge-code').children().children().not('.collapse').find('button')
        .on('click', function () {
            handleCollapse_code(this);
        });
    $('#toMerge-file').children().children().not('.collapse').find('button')
        .on('click', function () {
            handleCollapse_file(this);
        });
    function reBind() {
        $('#toMerge-code').children().children().not('.collapse')
            .find('button').on('click', function () {
            handleCollapse_code(this);
        });
        $('#toMerge-file').children().children().not('.collapse')
            .find('button').on('click', function () {
            handleCollapse_file(this);
        });
    }

    /**
     * 处理下拉栏
     */
    function handleCollapse_code(obj) {
        if ($(obj).parent().parent().next().hasClass('in')) {
            $(obj).parent().parent().next().collapse('hide');
        } else {
            if ($(obj).parent().parent().next().find('td').has('#divideTable').length != 0) {
                $(obj).parent().parent().next().collapse('show');
            } else {
                $(obj).parent().parent().parent().find('.in').collapse('hide');
                $(obj).parent().parent().next().find('td')
                    .prepend($('#divide'));
                var temp = $(obj).parent().parent().find('td');
                report = new Object({
                    taskName: taskName,
                    userName: $(temp[4]).text(),
                    fileName: $(temp[1]).text(),
                    page: 0,
                    location: Number($(temp[2]).text()),
                    description: $(temp[3]).text(),
                    state: 0,
                    origin: 0
                });
                getData('code', $(obj));
            }

        }
    }

    function handleCollapse_file(obj) {
        if ($(obj).parent().parent().next().hasClass('in')) {
            $(obj).parent().parent().next().collapse('hide');
        } else {
            if ($(obj).parent().parent().next().find('td').has('#divideTable').length != 0) {
                $(obj).parent().parent().next().collapse('show');
            } else {
                $(obj).parent().parent().parent().find('.in').collapse('hide');
                $(obj).parent().parent().next().find('td')
                    .prepend($('#divide'));
                var temp = $(obj).parent().parent().find('td');
                report = new Object({
                    taskName: taskName,
                    userName: $(temp[5]).text(),
                    fileName: $(temp[1]).text(),
                    page: Number($(temp[2]).text()),
                    location: Number($(temp[3]).text()),
                    description: $(temp[4]).text(),
                    state: 0,
                    origin: 0
                });
                getData('file', $(obj));
            }
        }
    }

    /**
     * 加载指定条目的组成数据，并在表格中显示
     *
     * @returns
     */
    function getData(type, obj) {
        run_waitMe();
        jQuery.ajax({
            url: '/crc/SplitServlet',
            type: 'post',
            data: 'type=getData' + '&data=' + JSON.stringify(report),
            success: function (data) {
                var result = jQuery.parseJSON(data)[0].data;
                $('#divideTable tbody').empty();

                if (type == 'code') {
                    alert(JSON.stringify(report))
                    displayCode(result);
                } else {
                    displayFile(result);
                }
                stopWait();
                $(obj).parent().parent().next().collapse('show');
            },
            error: function () {
                alert('出错了')
            }
        })

    }

    /**
     * 显示可拆分的条目（代码）
     *
     * @param data
     * @returns
     */
    function displayCode(data) {
        for (var i = 0; i < data.length; i++) {
            var temp = '<tr>' + '<td><input type="checkbox"></td>' + '<td>'
                + data[i].fileName + '</td>' + '<td>' + data[i].location
                + '</td>' + '<td>' + data[i].description + '</td>' + '<td>'
                + data[i].userName + '</td>' + '</tr>';

            $('#divideTable tbody').append(temp);
            // $('#divideModal').modal('show');
        }
    }

    /**
     * 显示可拆分的条目（文档）
     *
     * @param data
     * @returns
     */
    function displayFile(data) {
        for (var i = 0; i < data.length; i++) {
            var temp = '<tr>' + '<td><input type="checkbox"></td>' + '<td>'
                + data[i].fileName + '</td>' + '<td>' + data[i].page
                + '</td>' + '<td>' + data[i].location + '</td>' + '<td>'
                + data[i].description + '</td>' + '<td>' + data[i].userName
                + '</td>' + '</tr>';

            $('#divideTable tbody').append(temp);
            // $('#divideModal').modal('show');
        }
    }

    $(function () {
        $('#confirmDivide').on(
            'click',
            function () {
                run_waitMe();
                var data = getDivideItem();
                console.log(data);
                jQuery.ajax({
                    url: '/crc/SplitServlet',
                    type: 'post',
                    data: 'type=split' + '&origin='
                    + JSON.stringify(report) + '&data=' + data,
                    success: function (data) {
                        var result = jQuery.parseJSON(data);
                        afterSplit(result.data);
                        location.reload(true)
                    }
                })
            })
    })

    /**
     * 拆分条目后进行的操作，更新“toMerge-code”表中数据
     *
     * @param data
     * @returns
     */
    function afterSplit(data) {
        if (data[0].page == 0) {// 代码条目
            updateCode(data);
        } else {// 文档条目
            updateFile(data);
        }

        $('#divideTable tbody').empty();
        stopWait();
    }

    /**
     * 更新代码条目(toMerge-code)
     *
     * @param data
     * @returns
     */
    function updateCode(data) {
        $('#toMerge-code tbody').empty();
        for (var i = 0; i < data.length; i++) {
            var temp = '<tr>' + '<td><input type="checkbox"></td>' + '<td>'
                + data[i].fileName + '</td>' + '<td>' + data[i].location
                + '</td>' + '<td>' + data[i].description + '</td>' + '<td>'
                + data[i].userName + '</td>' + '</tr>';

            $('#toMerge-code tbody').append(temp);
        }
    }

    /**
     * 更新文档条目(toMerge-file)
     *
     * @param data
     * @returns
     */
    function updateFile(data) {
        $('#toMerge-file tbody').empty();
        for (var i = 0; i < data.length; i++) {
            var temp = '<tr>' + '<td><input type="checkbox"></td>' + '<td>'
                + data[i].fileName + '</td>' + '<td>' + data[i].page
                + '</td>' + '<td>' + data[i].location + '</td>' + '<td>'
                + data[i].description + '</td>' + '<td>' + data[i].userName
                + '</td>' + '</tr>';

            $('#toMerge-file tbody').append(temp);
        }
    }

    /**
     * 获取要拆分的条目
     *
     * @returns
     */
    function getDivideItem() {
        var inputs = $('#divideTable tbody').find('input');
        var checked = new Array();
        for (var i = 0; i < inputs.length; i++) {
            if ($(inputs[i]).prop('checked')) {
                checked.push($(inputs[i]))
            }
        }

        var result = new Array();
        for (var i = 0; i < checked.length; i++) {
            var temp = $(checked[i]).parent().parent().find('td');
            var obj = new Object({ // 默认代码拆分
                taskName: taskName,
                userName: $(temp[4]).text(),
                fileName: $(temp[1]).text(),
                page: 0,
                location: $(temp[2]).text(),
                description: $(temp[3]).text(),
                state: 0,
                origin: 0
            });

            if (temp.length % 6 == 0) { // 文档拆分
                obj.userName = $(temp[5]).text();
                obj.page = Number($(temp[2]).text());
                obj.location = $(temp[3]).text();
                obj.description = $(temp[4]).text();
            }

            result.push(obj);
        }

        return JSON.stringify(result);
    }
}
