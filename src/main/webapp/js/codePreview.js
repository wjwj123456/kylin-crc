
var fileName = "xxx.java";
var settings = {
	trigger : 'click',
	width : 500,
	multi : false,
	closeable : true,
	style : '',
	padding : true
};
function generateSetting(i, recordList) {
	var deleteString ;
	if(recordList[i-1]==''){
		deleteString = '';
	}else {
		deleteString = "<button class = 'btn btn-new' onclick = 'deleteWithCode(this)'>删除</button>";
	}
	var settings = {
		title : '第' + i + '行',
		content : "<div class='form-group'><input class = 'form-control' value = "
				+ recordList[i - 1]
				+ ">"+deleteString+"<button class='btn pull-right' onclick='submitWithCode(this)'>提交</button></div>",
	}
	return settings;
}
function initButtons(length, recordList) {
	$('.gutter').css('cursor', 'pointer');
	for (var i = 0; i < length + 1; i++) {
		$($('.gutter').children()[i]).webuiPopover(
				$.extend({}, settings, generateSetting(i + 1, recordList)));
		if (recordList[i] == '') {
			$($('.gutter').children()[i]).css("cssText",
			"background-color:#fff!important");
			$($('.gutter').children()[i]).hover(function() {
				$(this).css("cssText", "background-color:#ddd!important");
				$(this).text('+');
			}, function() {
				$(this).css("cssText", "background-color:#fff!important");
				$(this).text($($('.gutter').children()).index($(this)) + 1);
			});
		} else {
			$($('.gutter').children()[i]).css("cssText",
					"background-color:#eee!important");
			$($('.gutter').children()[i]).hover(function() {
				$(this).css("cssText", "background-color:#ddd!important");
				$(this).text('+');
			}, function() {
				$(this).css("cssText", "background-color:#eee!important");
				$(this).text($($('.gutter').children()).index($(this)) + 1);
			});
		}
	}
}
function submitWithCode(obj) {
	if ($(obj).parent().children('input').val() == '') {
		$(obj).parent().addClass('has-error');
	} else {
		$(obj).parent().removeClass('has-error');
		storeWithCode(obj);
	}
}
function storeWithCode(obj) {
	var number;
	var numString = $(obj).parent().parent().parent().children('h3').text().trim();
	numString = numString.replace('第', '').replace('行', '');
	number = Number(numString.trim());
	var report = new Object({
		taskName : taskName,
		fileName : fileName,
		page : 0,
		location : number,
		description : $(obj).parent().children('input').val().trim(),
		state : 0,
		origin : 0
	});
	run_waitMe();
    jQuery.ajax({
        url: '/ReportServlet',
        type: 'post',
        data: 'type=store' + '&data=' + JSON.stringify(report),
        success: function (data) {
        	load();
            stopWait();
        },
        error: function () {
            alert('出错了，更改无法保存')
            stopWait();
        }
    });
}
function deleteWithCode(obj) {
	var number;
	var numString = $(obj).parent().parent().parent().children('h3').text().trim();
	numString = numString.replace('第', '').replace('行', '');
	number = Number(numString.trim());
	var report = new Object({
        taskName: taskName,
        userName: username,
        fileName: fileName,
        page: 0,
        location: number,
        description: $(obj).parent().children('input').val().trim(),
        state: 0,
        origin: 0
    });
	run_waitMe();
    jQuery.ajax({
        url: '/ReportServlet',
        type: 'post',
        data: 'type=delete' + '&data=' + JSON.stringify(report),
        success: function (data) {
        	load();
            stopWait();
        },
        error: function () {
            alert('出错了，更改无法保存')
        }
    });
}
function loadPreview(path,type) {
	var txtLike=['txt', 'css', 'html', 'java', 'c', 'cpp', 'php', 'python', 'jsp', 'matlab', 'sql', 'markdown'];
	var pdfLike=['pdf'];
	if(contains(txtLike,type)){
		handleTxt(path);
	}else if (contains(pdfLike,type)) {
		handlePdf(path);
	}else {
		handleOther();
	}
}
function contains(array,item) {
	for(var i = 0;i<array.length;i++){
		if (array[i]==item) {
			return true;
		}
	}
	return false;
}
function handleTxt(path) {
	jQuery.ajax({
		url : '/CodePreviewServlet',
		type : 'post',
		data : 'path='+path+'&type=read',
		success : function(data) {
			var tempData = jQuery.parseJSON(data);
			generatePre(tempData, path);
			registView();
		}
	});
}
function handlePdf(path) {
	$('#codePreview').before($('<a class="media" href="'+path+'"></a>'));
	$('a.media').media({width:800, height:600});
}
function handleOther() {
	
}
function registView() {
	$('.webui-popover').remove();
	SyntaxHighlighter.highlight();
	var fileName = "xxx.java";
	var length = $('.gutter').children().length;

	jQuery.ajax({
		url : '/CodePreviewServlet',
		type : 'post',
		data : 'taskName=' + taskName+'&type=temp',
		success : function(data) {
			var tempData = jQuery.parseJSON(data);
			var recordList = [];
			var has;
			for (var i = 0; i < length; i++) {
				has = false;
				for (var j = 0; j < tempData.length; j++) {
					if (tempData[j].fileName == fileName
							&& tempData[j].location == i + 1) {
						recordList.push(tempData[j].description);
						has = true;
					}
				}
				if (!has) {
					recordList.push('');
				}
			}
			initButtons(length, recordList);

		}
	});
}
function generatePre(codeData,path) {
	var type=findType(path);
	$('#codePreview').empty().append($('<pre type="syntaxhighlighter" class="brush: '+type+';"></pre>'));

	var code = String();
	for(var i = 0;i<codeData.length;i++){
		// 两次解码,不能使用decodeURI方法
		code = code+decodeURIComponent(decodeURIComponent(codeData[i]))+'\n';
	}
	$('pre').text(code);
}
function findType(path) {
	var temp = path.split('.');
	var lastName=temp[temp.length-1].toLowerCase();
	var type;
	if(lastName=='java'){
		type = 'java';
	}else if (lastName=='cs') {
		type = 'csharp';
	}else if (lastName=='js') {
		type = 'js';
	}else if (lastName=='rb') {
		type = 'ruby';
	}else if (lastName=='php') {
		type = 'php';
	}else if (lastName=='pl'||lastName=='pm') {
		type = 'perl';
	}else if (lastName=='py') {
		type = 'python';
	}else if (lastName=='groovy') {
		type = 'groovy';
	}else {
		type = 'cpp';
	}
	return type;
}