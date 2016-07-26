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
});


/**
 * search task by keyword
 * @param keyword
 * @returns
 */
function searchTask(keyword) {
    run_waitMe();
    jQuery.ajax({
        url: '/crc/SearchServlet',
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
    for (var property in result) {
        $('#myTab').append(
            '<li>' +
            '<a href="#' + property + '" data-toggle="tab">' + language[property] +
            '<span class="badge pull-right">' + result[property].length + '</span>' + '</a>' +
            '</li>');

        $('#myTabContent').append(
            '<div class="tab-pane fade in active" id="' + language[property] + '">' +
            '<hr></div>'
        )
    }
}

/**
 * 添加语言对应的任务列表
 * @param language
 * @param taskList
 */
function addTask(language, taskList) {
    for (var i = 0; i < taskList.length; i++) {
        $('#' + language).append(
            '<div class="avn-price-table avn-style14 avn-hover">' +
            '<div class="row">' +
            '<div class="col-md-1 header"><p style="margin-left: -11px;">' + '</p></div>' +
            '<div class="col-md-11"><h2 title="评审测试">评审测试</h2>' +
            '<p>司法哈高科发挥砂锅饭技术股份卡公司罚款是高科技发生噶发生口角光刻技术飞洒股份杰弗森阿康恢复</p>' +
            '<p><strong>截止时间：XXXX/XX/XX XX:XX:XX</strong></p>' +
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
