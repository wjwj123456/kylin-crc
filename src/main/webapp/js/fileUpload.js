$(window).load(function() {
	//$('#btnCrop').click();$("#idName").css("cssText","background-color:red!important");
	//$(".imageBox").css("cssText","background-position:88px 88px!important");$(".imageBox").css("cssText","background-size:222px 222px!important");
	var options = {
		thumbBox: '.thumbBox',
		spinner: '.spinner',
		imgSrc: ''
	}

	var cropper = $('.imageBox').cropbox(options);
	var img="";
	$('#upload-file').on('change', function(){
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
			getImg();
		}
		reader.readAsDataURL(this.files[0]);
		this.files = [];
		//getImg();
	})
	$('#btnCrop').on('click', function(){
		alert("图片上传喽");
	})
	function getImg(){
		img = cropper.getDataURL();
		$('.cropped').html('');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
	}

	$(".imageBox").on("mouseup",function(){
 		getImg();
  	});

	$('#btnZoomIn').on('click', function(){
		cropper.zoomIn();
	})

	$('#btnZoomOut').on('click', function(){
		cropper.zoomOut();
	})
	
	$("#uploadPic").click(function() {
		console.log($('#headerPic').attr('background-image'))
    	$.ajax({
        	type: "post",
        	url: "ImageServlet",
        	data: {
            	picFile: $("#headerPic").attr("background-image")
        	},
        	success: function(data) {
	            alert("you header pic upload success !")
    	    },
        	error: function(e) {
            	alert("you file upload error , error is " + e);
        	}
    	});
    });
});
