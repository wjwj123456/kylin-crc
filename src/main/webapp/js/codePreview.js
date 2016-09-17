SyntaxHighlighter.highlight();
var settings = {
	trigger : 'click',
	width : 500,
	multi : false,
	closeable : true,
	style : '',
	padding : true
};
function generateSetting(i,recordList) {
	var settings = {
		title : '第' + i + '行',
		content : "<input class = 'form-control' value = "+recordList[i-1]+"><button class='btn pull-right'>提交</button>",
	}
	return settings;
}
function initButtons(length,recordList) {
	$('.gutter').css('cursor', 'pointer');
	for (var i = 0; i < length + 1; i++) {
		$($('.gutter').children()[i]).webuiPopover(
				$.extend({}, settings, generateSetting(i+1,recordList)));
		if(recordList[i]==''){
			$($('.gutter').children()[i]).hover(function() {
				$(this).css("cssText", "background-color:#ddd!important");
				$(this).text('+');
			}, function() {
				$(this).css("cssText", "background-color:#fff!important");
				$(this).text($($('.gutter').children()).index($(this)) + 1);
			});
		}else {
			$($('.gutter').children()[i]).css("cssText", "background-color:#eee!important");
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
function load() {
	var fileName = "xxx.java";
	var length = $('.gutter').children().length;
	jQuery.ajax({
		url : '/CodePreviewServlet',
		type : 'post',
		data : 'taskName=' + taskName,
		success : function(data) {
			var tempData = jQuery.parseJSON(data);
			var recordList = [];
			var has;
			for (var i = 0; i < length; i++) {
				has = false;
				for (var j = 0; j < tempData.length; j++) {	
					if (tempData[j].fileName == fileName && tempData[j].location == i+1) {
						recordList.push(tempData[j].description);
						has = true;
					}
				}
				if (!has) {
					recordList.push('');
				}
			}
			initButtons(length,recordList);
			
		}
	});
	
	


}
load();