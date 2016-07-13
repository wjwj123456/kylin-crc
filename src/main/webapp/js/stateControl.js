var taskDisp = [['inline','none'],['none','inline']];
var userDisp = [['inline','none','none'],['none','inline','none'],['none','none','inline'],['none','none','none']];
var userState = [0,1,2,3];
var unJoin = 0;
var joined = 1;
var committed = 2;
var merged = 3;
var currentUserDisp;
var cuerrentTaskDisp;
$(function(){
	$('#reviewBlock').attr('display',currentUserDisp[0]);
	$('#overBlock').attr('display',currentUserDisp[1]);
});