var itemOK = true;
var reportOK = true;
var username;
var taskType;
var taskName;
$(function(){
	if(taskType=='code'){
		$('#docBlock').hide();
		$('#toMerge-file').hide();
		$('#merged-file').hide();
		$('#fileDiv').hide();
	}else {
		$('#codeBlock').hide();
		$('#toMerge-code').hide();
		$('#merged-code').hide();
		$('#codeDiv').hide();
	}
});
$('#add-code').on('click', function(){
	if(isCodeItemOK()){
		$('#codeStart').append("<tr> <td>" +$('#fileName-code').val()+
				"</td> <td>" +$('#lineNum-code').val()+
				"</td> <td>" +$('#discription-code').val()+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
		run_waitMe();
		storeCode();
		stopWait();
	}
});

$('#add-choosecode').on('click',function(){
	if(ischooseCodeItemOK()){
		var report = new Object({
			taskName: taskName,
			fileName: $('#fileName-choosecode').val().trim(),
			page: 0,
			location: Number($('#lineNum-choosecode').val().trim()),
			description: $('#discription-choosecode').val().trim(),
			state: 0,
			origin: 1
		});
		run_waitMe();
		storeCodeMerge(report);
		stopWait()
	}
	
});
$('#add-file').on('click',function(){
	if(isFileItemOK()){
		$('#docStart').append("<tr> <td>" +$('#fileName-file').val()+
				"</td> <td>" +$('#pageNum-file').val()+
				"</td> <td>" +$('#lineNum-file').val()+
				"</td> <td>" +$('#discription-file').val()+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
		run_waitMe();
		storeFile();
		stopWait();
	}
	
});
$('#add-choosefile').on('click',function(){
	if(ischooseFileItemOK()) {
		var report = new Object({
			taskName: taskName,
			fileName: $('#fileName-choosefile').val().trim(),
			page: Number($('#pageNum-choosefile').val().trim()),
			location: Number($('#lineNum-choosefile').val().trim()),
			description: $('#discription-choosefile').val().trim(),
			state: 0,
			origin: 1
		});
		run_waitMe();
		storeFileMerge(report);
		stopWait();
	}
});

$('#toMerge-code').find('button').on('click',function(){
	$('#divideModal').modal('show');
});
$('#toMerge-file').find('button').on('click',function(){
	$('#divideModal').modal('show');
});

function initCodeChoose(){
	$('#choose-code').find('tr').not(':first').on('click',function(){
		var temp=$(this).find('td');
		var report = new Object({
			taskName: taskName,
			fileName: $(temp[0]).text(),
			page: 0,
			location: Number($(temp[1]).text()),
			description: $(temp[2]).text(),
			state: 0,
			origin: 1
		});
		
		storeCodeMerge(report);
	});
}
$('#merge').on('click',function(){
	$('#choose-code').empty();
	$('#choose-code').append($("<tr><th>文件名</th><th>行数</th><th>描述</th><th>评审人</th></tr>"));
	run_waitMe();
	codeMerge();
	stopWait();
});


function initFileChoose() {
	$('#choose-file').find('tr').not(':first').on('click',function(){
		var temp=$(this).find('td');
		var report = new Object({
			taskName: taskName,
			fileName: temp[0].text(),
			page: Number(temp[1].text()),
			location: Number(temp[1].text()),
			description: temp[2].text(),
			state: 0,
			origin: 1
		})
		
		storeFileMerge(report);
	});
}

//$('#start').before('<div class='form-group'> <label for='inputName' class='col-sm-1 control-label'>文件名</label> <div class='col-sm-5'> <input type='text' class='form-control' id='inputName' placeholder='**.*'> </div> <label for='inputName' class='col-sm-1 control-label'>行数</label> <div class='col-sm-5'> <input type='text' class='form-control' id='inputName' placeholder=''> </div> </div> <div class='form-group'> <label for='inputName' class='col-sm-1 control-label'>描述</label> <div class='col-sm-11'> <textarea class='form-control' rows='1' id='discription'></textarea> </div> </div>');
function codeMerge() {
	var list = new Array();
	var inputs = $('#toMerge-code').find('input');
	for( i = 0;i<inputs.length;i++){
		if($(inputs[i]).prop('checked')==true){
			var temp=$($('#toMerge-code').find('tr')[i+1]).clone();
			var temptr=$("<tr></tr>");
			var length = $(temp).children().length;
			for(j=1;j<length-1;j++){
				var temptd=$(temp).children().eq(1);
				temptr.append($(temptd));
			}
			list.push(temptr);
		}
	}
	for(i in list){
		$('#choose-code').append($(list[i]));
	}
	initCodeChoose();
	$('#chooseModal').modal('show');
}
function fileMerge() {
	var list = new Array();
	var inputs = $('#toMerge-file').find('input');
	for( i = 0;i<inputs.length;i++){
		if($(inputs[i]).prop('checked')==true){
			var temp=$($('#toMerge-file').find('tr')[i+1]).clone();
			var temptr=$("<tr></tr>");
			var length = $(temp).children().length;
			for(j=1;j<length-1;j++){
				var temptd=$(temp).children().eq(1);
				temptr.append($(temptd));
			}
			list.push(temptr);
		}
	}
	for(i in list){
		$('#choose-file').append($(list[i]));
	}
	initChoose();
	$('#chooseModal').modal('show');
}

/**
 * 删除表格中记录前需删除数据库中的记录，否则无法删除
 * @param obj
 */
function deleteItem(obj){
	var data = $(obj).parent().parent().find('td');
	
	if (data.length == 4) { // 删除代码记录
		deleteCode(data, obj);
	} else { // 删除文档记录
		deleteFile(data, obj);
	}
}

function isCodeItemOK(){
	itemOK=true;
	if($('#fileName-code').val()==""){
		$('#fileGroup-code').addClass('has-error');
		itemOK=false;
	}else {
		$('#fileGroup-code').removeClass('has-error');
	}
	if($('#lineNum-code').val()=="" || isNaN($('#lineNum-code').val().trim())){
		$('#lineGroup-code').addClass('has-error');
		itemOK=false;
	}else {
		$('#lineGroup-code').removeClass('has-error');
	}
	if($('#discription-code').val()==""){
		$('#discripGroup-code').addClass('has-error');
		itemOK=false;
	}else {
		$('#discripGroup-code').removeClass('has-error');
	}
	if(itemOK){
		if(!codeUnique()){
			itemOK=false;
		}
	}
	return itemOK;
}

function ischooseCodeItemOK(){
	itemOK=true;
	if($('#fileName-choosecode').val()==""){
		$('#fileGroup-choosecode').addClass('has-error');
		itemOK=false;
	}else {
		$('#fileGroup-choosecode').removeClass('has-error');
	}
	if($('#lineNum-choosecode').val()=="" || isNaN($('#lineNum-code').val().trim())){
		$('#lineGroup-choosecode').addClass('has-error');
		itemOK=false;
	}else {
		$('#lineGroup-choosecode').removeClass('has-error');
	}
	if($('#discription-choosecode').val()==""){
		$('#discripGroup-choosecode').addClass('has-error');
		itemOK=false;
	}else {
		$('#discripGroup-choosecode').removeClass('has-error');
	}

	return itemOK;
}

/**
 * judge whether an item already existed in report table
 * @returns
 */
function codeUnique() {
	var items = $('#codeTable').find('tr').filter(function() {
		var fileName = $(this).find('td:first');
		
		if ($(fileName).text() == $('#fileName-code').val().trim() 
				&& $(fileName).next().text() == $('#lineNum-code').val().trim()
				&& $(fileName).next().next().text() == $('#discription-code').val().trim()) {
			return $(this);
		}
	});
	
	return items.length == 0;
}

function isFileItemOK(){
	itemOK=true;
	if($('#fileName-file').val()==""){
		$('#fileGroup-file').addClass('has-error');
		itemOK=false;
	}else {
		$('#fileGroup-file').removeClass('has-error');
	}
	if($('#lineNum-file').val()==""  || isNaN($('#lineNum-code').val().trim())){
		$('#lineGroup-file').addClass('has-error');
		itemOK=false;
	}else {
		$('#lineGroup-file').removeClass('has-error');
	}
	if($('#discription-file').val()==""){
		$('#discripGroup-file').addClass('has-error');
		itemOK=false;
	}else {
		$('#discripGroup-file').removeClass('has-error');
	}
	if($('#pageNum-file').val()==""  || isNaN($('#lineNum-code').val().trim())){
		$('#pageGroup-file').addClass('has-error');
		itemOK=false;
	}else {
		$('#pageGroup-file').removeClass('has-error');
	}
	return itemOK;
}

function ischooseFileItemOK(){
	itemOK=true;
	if($('#fileName-choosefile').val()==""){
		$('#fileGroup-choosefile').addClass('has-error');
		itemOK=false;
	}else {
		$('#fileGroup-choosefile').removeClass('has-error');
	}
	if($('#lineNum-choosefile').val()==""  || isNaN($('#lineNum-code').val().trim())){
		$('#lineGroup-choosefile').addClass('has-error');
		itemOK=false;
	}else {
		$('#lineGroup-choosefile').removeClass('has-error');
	}
	if($('#discription-choosefile').val()==""){
		$('#discripGroup-choosefile').addClass('has-error');
		itemOK=false;
	}else {
		$('#discripGroup-choosefile').removeClass('has-error');
	}
	if($('#pageNum-choosefile').val()==""  || isNaN($('#lineNum-code').val().trim())){
		$('#pageGroup-choosefile').addClass('has-error');
		itemOK=false;
	}else {
		$('#pageGroup-choosefile').removeClass('has-error');
	}
	return itemOK;
}

function isReportOK(){
	reportOK=true;
	if($('#timeCost').val()=="" && !isNaN(Number($('#timeCost').val().trim()))){
		$('#timeGroup').addClass('has-error');
		reportOK=false;
	}else {
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
 * @param data 记录数据 
 */
function store(data) {
	jQuery.ajax({
		url: '/crc/ReportServlet',
		type: 'post',
		data: 'type=store' + '&data=' + JSON.stringify(data),
		success: function(data) {
			alert(data)
		},
		error: function() {
			alert('出错了，更改无法保存')
		}
	})	
}

/**
 * 每删除一条记录，即从数据库删除一条记录
 */
function deleteCode(data, obj) {
	var report = new Object({
		taskName: taskName,
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
 * @param data 记录数据
 */
function deleteCodeAndFile(data, obj) {
	jQuery.ajax({
		url: '/crc/ReportServlet',
		type: 'post',
		data: 'type=delete' + '&data=' + JSON.stringify(data),
		success: function(data) {
			$(obj).parent().parent().remove();
		},
		error: function() {
			alert('出错了，更改无法保存')
		}
	})
}

/**
 * commit report 
 * @returns
 */
function commitReport() {
	if(isReportOK()){
		jQuery.ajax({
			url: '/crc/ReportServlet',
			type: 'post',
			data: 'type=commit&taskName=' + taskName + '&time=' + $('#timeCost').val().trim(),
			success: function(data) {
				console.log(data)
				if (data == 0) {
					alert('提交成功')
				}
			}
		});
	}
}

// merge 操作相关
{
	/**
	 * 每合并一次记录，写入数据库
	 * @param report
	 * @returns
	 */
	function storeCodeMerge(report) {
		var data = new Array();
		data.push(report);
		
		var temp = $('#choose-code').find('tr').not(':first');
		for (var i = 0; i < temp.length; i++) {
			var td = $(temp[i]).find('td');
			var obj = new Object({
				taskName: taskName,
				fileName: $(td[0]).text(),
				page: 0,
				location: Number($(td[1]).text()),
				description: $(td[2]).text(),
				state: 0,
				origin: 1
			});
			data.push(obj);
		}
		
		jQuery.ajax({
			url: '/crc/MergeServlet',
			type: 'post',
			data: 'type=saveMerge&taskName=' + taskName + '&data=' + JSON.stringify(data),
			success: function(data) {
				afterCodeMerge(report);
			},
			error: function() {
				alert('出错了，更改无法生效')
			}
		});
	}
	
	function storeFileMerge() {
		var data = new Array();
		data.push(report);
		
		var temp = $('#choose-file').find('tr').not(':first');
		for (var i = 0; i < temp.length; i++) {
			var td = $(temp[i]).find('td');
			var obj = new Object({
				taskName: taskName,
				fileName: $(td[0]).text(),
				page: Number($(td[1]).text()),
				location: Number($(td[2]).text()),
				description: $(td[3]).text(),
				state: 0,
				origin: 1
			});
			data.push(obj);
		}
		
		jQuery.ajax({
			url: '/crc/MergeServlet',
			type: 'post',
			data: 'type=saveMerge&taskName=' + taskName + '&data=' + JSON.stringify(data),
			success: function(data) {
				afterFileMerge(report);
			},
			error: function() {
				alert('出错了，更改无法生效')
			}
		});
	}
	
	/**
	 * 代码合并条目以后的操作，将合并后的条目加入合并列表，删除原始条目
	 */
	function afterCodeMerge(report) {
		$('#merged-code').append(
				'<tr><td>' + report.fileName + '</td>' +
				'<td>' + report.location + '</td>' + 
				'<td>' + report.description + '</td>' + 
				'<td>' + username + '</td>' + 
				'<td><button type="button" class="close"' +
				'aria-hidden="true" id="delete" onclick="deleteCodeMerge(this)">' +
				'x</button></td>');
		var inputs = $('#toMerge-code').find('input');
		var j = 0;
		for( i = 0; i < inputs.length; i++){
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-code tbody').find('tr')[j]).remove();
				j--;
			}
			j++;
		}
		$('#chooseModal').modal('hide');
	}
	
	/**
	 * 文档合并条目以后的操作，将合并后的条目加入合并列表，删除原始条目
	 */
	function afterFileMerge(report) {
		$('#merged-file').append(
				'<tr><td>' + report.fileName + '</td>' +
				'<td>' + report.page + '</td>' +
				'<td>' + report.location + '</td>' + 
				'<td>' + report.description + '</td>' + 
				'<td>' + username + '</td>' + 
				'<td><button type="button" class="close"' +
				'aria-hidden="true" id="delete" onclick="deleteFileMerge(this)">' +
				'x</button></td>');
		var inputs = $('#toMerge-file').find('input');
		var j = 0;
		for( i = 0; i < inputs.length; i++){
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-file tbody').find('tr')[j]).remove();
				j--;
			}
			j++;
		}
		$('#chooseModal').modal('hide');
	}
}

// 删除合并条目相关操作
{
	/**
	 * 删除代码条目
	 * @param obj
	 * @returns
	 */
	function deleteCodeMerge(obj) {
		var temp = $(obj).parent().parent().find('td');
		var report = new Object({
			taskName: taskName,
			fileName: $(temp[0]).text(),
			page: 0,
			location: $(temp[1]).text(),
			description: $(temp[2]).text(),
			state: 1,
			origin: 0
		});
		concole.log(JSON.stringify(report))
//		jQuery.ajax({
//			url: '/crc/MergeServlet',
//			type: 'post',
//			data: 'type=deleteMerge&data=' + JSON.stringify(report),
//			success: function(data) {
//				
//			},
//			error: function() {
//				alert('出错了，更改无法生效')
//			}
//		})
	}
	
	function deleteFileMerge(obj) {
		
	}
}

