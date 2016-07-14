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
	}
	
	storeCode();
});

$('#add-choosecode').on('click',function(){
	if(ischooseCodeItemOK()){
		$('#merged-code').append("<tr> <td>" +$('#fileName-choosecode').val()+
				"</td> <td>" +$('#lineNum-choosecode').val()+
				"</td> <td>" +$('#discription-choosecode').val()+
				"</td><td>"+username+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
		$('#chooseModal').modal('hide');
		var inputs = $('#toMerge-code').find('input');
		var length = inputs.length;
		var j = 0;
		for( i = 0;i<length;i++){
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-code').find('tr')[j+1]).remove();
				j--;
				length--;
			}
			j++;
		}
	}
	
});
$('#add-file').on('click',function(){
	if(isFileItemOK()){
		$('#docStart').append("<tr> <td>" +$('#fileName-file').val()+
				"</td> <td>" +$('#pageNum-file').val()+
				"</td> <td>" +$('#lineNum-file').val()+
				"</td> <td>" +$('#discription-file').val()+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
	}
	
	storeFile();
});
$('#add-choosefile').on('click',function(){
	if(ischooseFileItemOK()){
		$('#docStart').after("<tr> <td>" +$('#fileName-choosefile').val()+
				"</td> <td>" +$('#pageNum-choosefile').val()+
				"</td> <td>" +$('#lineNum-choosefile').val()+
				"</td> <td>" +$('#discription-choosefile').val()+
				"</td><td>"+username+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
		$('#chooseModal').modal('hide');
		var inputs = $('#toMerge-code').find('input');
		var length = inputs.length;
		var j = 0;
		for( i = 0;i<length;i++){
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-code').find('tr')[j+1]).remove();
				j--;
				length--;
			}
			j++;
		}
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
		var temp=$(this).clone();
		// TODO onclick
		$(temp).append($("<td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td>"));
		$('#merged-code').append($(temp));
		$('#chooseModal').modal('hide');
		var inputs = $('#toMerge-code').find('input');
		var length = inputs.length;
		var j = 0;
		for( i = 0;i<length;i++){
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-code tbody').find('tr')[j]).remove();
				j--;
			}
			j++;
		}
		
	});
}
$('#merge').on('click',function(){
	$('#choose-code').empty();
	$('#choose-code').append($("<tr><th>文件名</th><th>行数</th><th>描述</th><th>评审人</th></tr>"));
	codeMerge();	
	commitMerge();
});


function initFileChoode() {
	$('#choose-file').find('tr').not(':first').on('click',function(){
		var temp=$(this).clone();
		$(temp).append($("<td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td>"));
		$('#merged-file').append($(temp));
		$('#chooseModal').modal('hide');
		$('#choose-file').empty();
		$('#choose-file').append($("<tr><th>文件名</th><th>页码</th><th>行数</th><th>描述</th><th>评审人</th></tr>"));
		var inputs = $('#toMerge-file').find('input');
		var length = inputs.length;
		var j = 0;
		for( i = 0;i<length;i++){
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-file tbody').find('tr')[j]).remove();
				j--;
			}
			j++;
		}
		
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
	if($('#lineNum-code').val()=="" && !isNaN(Number($('#lineNum-code').val().trim()))){
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
	if($('#lineNum-choosecode').val()==""){
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
	if($('#lineNum-file').val()=="" && !isNaN(Number($('#lineNum-file').val().trim()))){
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
	if($('#pageNum-file').val()=="" && !isNaN(Number($('#pageNum-file').val().trim()))){
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
	if($('#lineNum-choosefile').val()==""){
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
	if($('#pageNum-choosefile').val()==""){
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
		describe: $('#discription-code').val().trim(),
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
		describe: $('#discription-file').val().trim(),
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
//				if (data == 0) {
//					alert('提交成功')
//				}
			}
		});
	}
}

// merge 操作相关
{
	function storeCodeMerge() {
		
	}
	
	function storeFileMerge() {
		
	}
	
	function storeMerge() {
		
	}
}

