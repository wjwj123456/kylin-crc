// 查询结果
var result = new Object({
    c: '',
    java: '',
    Objective_C: '',
    cpp: '',
    Ruby: '',
    csharp: '',
    JavaScript: '',
    pointnet: '',
    PHP: '',
    Swift: '',
    Groovy: '',
    MATLAB: '',
    D: '',
    R: '',
    Perl: '',
    python: '',
    other: '',
    none: ''
});

// 语言对应列表
var language = new Object({
    c: 'C',
    java: 'Java',
    Objective_C: 'Objective_c',
    cpp: 'C++',
    Ruby: 'Ruby',
    csharp: 'C#',
    JavaScript: 'JavaScript',
    pointnet: '.net',
    PHP: 'PHP',
    Swift: 'Swift',
    Groovy: 'Groovy',
    MATLAB: 'MATLAB',
    D: 'D',
    R: 'R',
    Perl: 'Perl',
    python: 'python',
    other: 'other',
    none: '文档'
});

$(function () {
    searchTask(getUrlParam("content"));
    
    $('#search').unbind('click').on('click', function () {
        searchTask($('#search-content').val().trim())
    });

    $('#search-content').keypress(function(e) {
		if (e.which == 13) {
            searchTask($('#search-content').val().trim());
		}
	});
});

/**
 * search task by keyword
 * @param keyword
 * @returns
 */
function searchTask(keyword) {
    if (keyword == '') {
        return;
    }

    run_waitMe();
    jQuery.ajax({
        url: '/SearchServlet',
        type: 'post',
        data: 'type=searchTask&keyword=' + keyword,
        success: function (data) {
            result = jQuery.parseJSON(data);
            displayResult(result);
            
            stopWait();
        }
    });
}

function displayResult(result) {
    // 清空原有列表
    $('#myTab').empty();
    $('#myTabContent').empty();

    var number = 0;
    // 显示左侧标签页
    for (var property in result) {
        $('#myTab').append(
            '<li>' +
            '<a href="#' + property + '" data-toggle="tab">' + language[property] +
            '<span class="badge pull-right">' + result[property].length + '</span>' + '</a>' +
            '</li>'
        );

        $('#myTabContent').append(
            '<div class="tab-pane fade in active" id="' + property + '"><hr></div>'
        );

        addTask(property, result[property]);

        number = number + result[property].length;
    }

    // 显示查询结果数目
    $('#result').text('We have searched ' + number + ' tasks');
}

/**
 * 添加语言对应的任务列表
 * @param tab
 * @param taskList
 */
function addTask(tab, taskList) {
    for (var i = 0; i < taskList.length; i++) {
        $('#' + tab).append(
            '<div class="avn-price-table avn-style14 avn-hover">' +
            '<div class="row">' +
            '<div class="col-md-1 header"><p style="margin-left: -11px;">' + language[tab] + '</p></div>' +
            '<div class="col-md-11"><a href="tasks.jsp?taskName=' + taskList[i].taskName + '">' +
            '<h2 title="' + taskList[i].taskName + '">' + taskList[i].taskName + '</h2></a>' +
            '<p>' + taskList[i].describe + '</p>' +
            '<p><strong>截止时间：' + taskList[i].deadline.substr(0, taskList[i].deadline.length - 5) + '</strong></p>' +
            '</div></div></div>'
        );
    }
}

/**
 * 获取url中的参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}
