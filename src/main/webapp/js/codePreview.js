
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
function load() {
	jQuery.ajax({
		url : '/CodePreviewServlet',
		type : 'post',
		data : 'path=D:/CRC/src/main/java/bl/FileBlImpl.java&type=read',
		success : function(data) {
			var tempData = jQuery.parseJSON(data);
			$('#codePreview').empty();
			$('#codePreview').append($('<pre type="syntaxhighlighter" class="brush: java;"></pre>'));
			var code = new String();
			for(var i = 0;i<tempData.length;i++){
				code = code+tempData[i]+'\n';
			}
			$('pre').text(code);
			registView();
		}
	});
	
}
function registView() {
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
load();