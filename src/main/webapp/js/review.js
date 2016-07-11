var itemOK = true;
var reportOK = true;
$('#startReview').on('click',function(){
	$('#preWord').hide();
	$(this).hide();
});
$('#add-code').on('click',function(){
	if(isCodeItemOK()){
		$('#codeStart').after("<tr> <td>" +$('#fileName-code').val()+
				"</td> <td>" +$('#lineNum-code').val()+
				"</td> <td>" +$('#discription-code').val()+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
	}
	
});
$('#add-file').on('click',function(){
	if(isFileItemOK()){
		$('#docStart').after("<tr> <td>" +$('#fileName-file').val()+
				"</td> <td>" +$('#pageNum-file').val()+
				"</td> <td>" +$('#lineNum-file').val()+
				"</td> <td>" +$('#discription-file').val()+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
	}
	
});
$('#confirmReport').on('click',function(){
	if(isReportOK()){
		
	}
});
function initCodeChoose(){
	$('#choose-code').find('tr').not(':first').on('click',function(){
		var temp=$(this).clone();
		$(temp).append($("<td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td>"));
		$('#merged-code').append($(temp));
		$('#chooseModal').modal('hide');
		$('#choose-code').empty();
		$('#choose-code').append($("<tr><th>文件名</th><th>行数</th><th>描述</th><th>评审人</th></tr>"));
		var inputs = $('#toMerge-code').find('input');
		var length = inputs.length;
		var j = 0;
		for( i = 0;i<length;i++){
			
			alert($(inputs[i]).prop('checked'))
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-code').find('tr')[j+1]).remove();
				j--;
				length--;
			}
			j++;
		}
		
	});
}
$('#merge').on('click',function(){
	codeMerge();
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
			
			alert($(inputs[i]).prop('checked'))
			if($(inputs[i]).prop('checked')==true){
				$($('#toMerge-file').find('tr')[j+1]).remove();
				j--;
				length--;
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
			for(j=1;j<length;j++){
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
			for(j=1;j<length;j++){
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

function deleteItem(obj){
	$(obj).parent().parent().remove();
}
function isCodeItemOK(){
	itemOK=true;
	if($('#fileName-code').val()==""){
		$('#fileGroup-code').addClass('has-error');
		itemOK=false;
	}else {
		$('#fileGroup-code').removeClass('has-error');
	}
	if($('#lineNum-code').val()==""){
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
function codeUnique() {
	var items=$('#codeTable').find('tr');
}
function isFileItemOK(){
	itemOK=true;
	if($('#fileName-file').val()==""){
		$('#fileGroup-file').addClass('has-error');
		itemOK=false;
	}else {
		$('#fileGroup-file').removeClass('has-error');
	}
	if($('#lineNum-file').val()==""){
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
	if($('#pageNum-file').val()==""){
		$('#pageGroup-file').addClass('has-error');
		itemOK=false;
	}else {
		$('#pageGroup-file').removeClass('has-error');
	}
	return itemOK;
}
function isReportOK(){
	reportOK=true;
	if($('#timeCost').val()==""){
		$('#timeGroup').addClass('has-error');
		reportOK=false;
	}else {
		$('#timeGroup').removeClass('has-error');
	}
	return reportOK;
}

/**
 * commit report 
 * @returns
 */
function commitReport() {
	jQuery.ajax({
		url: '/crc/ReportServlet',
		type: 'post',
		data: '',
		success: function(data) {
			if (data == 0) {
				alert('提交成功')
			}
		}
	});
}

