$(function() {
	$('#toInvite').find('tr').on('click', function() {
		addInvite($(this));
	});

});

function addInvite(obj) {
	$(obj).hide();
	$('#invited').prepend($(obj));
	$(obj).show();
}