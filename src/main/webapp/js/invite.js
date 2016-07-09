$(function() {
	$('#toInvite').find('tr').on('click', function() {
		addInvite($(this));
	});

});

function addInvite(obj) {
	$(obj).hide();
	$('#invited').append($(obj));
	$(obj).show();
}