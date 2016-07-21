
$(function() {
	if (num > 0) {
		$($('#mesSpan').find('a')[0]).find('span').before($(' <span class="badge" style="background-color: #ff3333">'+num+'</span>'));
		$($('#mesSpan').find('ul').find('a')[1]).append($(' <span class="badge" style="background-color: #ff3333">'+num+'</span>'));
	}

});