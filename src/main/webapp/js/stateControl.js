var taskDisp = [['inline','none'],['none','inline']];
var userDisp = [['inline','none','none'],['none','inline','none'],['none','none','inline'],['none','none','none']];
var userState = [0,1,2,3];
var unJoin = 0;
var joined = 1;
var committed = 2;
var merged = 3;
var running = 0;
var finished = 1;
var currentUserDisp;
$(function(){
	$('#preJoinBlock').css('display',currentTaskDisp[0]);
	$('#reviewBlock').css('display',currentTaskDisp[0]);
	$('#overBlock').css('display',currentTaskDisp[1]);
	$('#joinBlock').css('display',currentUserDisp[0]);
	$('#commitBlock').css('display',currentUserDisp[1]);
	$('#mergeBlock').css('display',currentUserDisp[2]);
});