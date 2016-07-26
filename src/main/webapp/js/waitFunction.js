function run_waitMe(){
    $('#waitArea').waitMe({

    //none, rotateplane, stretch, orbit, roundBounce, win8, 
    //win8_linear, ios, facebook, rotation, timer, pulse, 
    //progressBar, bouncePulse or img
    effect: 'bounce',

    //place text under the effect (string).
    text: '',

    //background for container (string).
    bg: 'rgba(255,255,255,0.7)',

    //color for background animation and text (string).
    color: '#000',

    //change width for elem animation (string).
    sizeW: '',

    //change height for elem animation (string).
    sizeH: '',

    // url to image
    source: ''

    });
  }
function stopWait() {
	$('#waitArea').waitMe("hide");
	
}
$(function () { $("[data-toggle='tooltip']").tooltip(); });