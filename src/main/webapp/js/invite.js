$(function() {
	$('#toInvite').find('tr').on('click', function() {
		addInvite($(this));
	});

});

function addInvite(obj) {
	// $(obj).hide();
	var temp = obj.clone();
	if (isUnique(temp))
		$('#invited').append(temp);
	// $(obj).show();
}
function isUnique(obj) {
	
	return true;
}