var itemOK = true;
var reportOK = true;
$('#startReview').on('click',function(){
	$('#preWord').hide();
	$(this).hide();
});
$('#add').on('click',function(){
	if(isItemOK()){
		$('#start').after("<tr> <td>" +$('#fileName').val()+
				"</td> <td>" +$('#lineNum').val()+
				"</td> <td>" +$('#discription').val()+
				"</td> <td><button type='button' class='close' aria-hidden='true' id='delete' onclick='deleteItem(this)'>x</button></td> </tr>");
	}
	
});
$('#confirmReport').on('click',function(){
	if(isReportOK()){
		
	}
});
$('#merge').on('click',function(){
	var list = new Array();
	var inputs = $('#toMerge-code').find('input');
	for( i = 0;i<inputs.length;i++){
		if($(inputs[i]).prop('checked')==true){
			var temp=$($('#toMerge-code').find('tr')[i+1]).clone();
			list.push(temp);
		}
	}
	for(i in list){
		$('#choose-code').append($(list[i]));
	}
	
	$('#chooseModal').modal('show');
});
//$('#start').before('<div class='form-group'> <label for='inputName' class='col-sm-1 control-label'>文件名</label> <div class='col-sm-5'> <input type='text' class='form-control' id='inputName' placeholder='**.*'> </div> <label for='inputName' class='col-sm-1 control-label'>行数</label> <div class='col-sm-5'> <input type='text' class='form-control' id='inputName' placeholder=''> </div> </div> <div class='form-group'> <label for='inputName' class='col-sm-1 control-label'>描述</label> <div class='col-sm-11'> <textarea class='form-control' rows='1' id='discription'></textarea> </div> </div>');
function deleteItem(obj){
	$(obj).parent().parent().remove();
}
function isItemOK(){
	itemOK=true;
	if($('#fileName').val()==""){
		$('#fileGroup').addClass('has-error');
		itemOK=false;
	}else {
		$('#fileGroup').removeClass('has-error');
	}
	if($('#lineNum').val()==""){
		$('#lineGroup').addClass('has-error');
		itemOK=false;
	}else {
		$('#lineGroup').removeClass('has-error');
	}
	if($('#discription').val()==""){
		$('#discripGroup').addClass('has-error');
		itemOK=false;
	}else {
		$('#discripGroup').removeClass('has-error');
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