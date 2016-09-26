$(function () {
    $('.radio').find('input[value=' + sex + ']').attr('checked', 'checked');

    for (var i = 0; i < languages.length; i++) {
        $('#goodAt tbody').find('input[value=' + languages[i] + ']').attr('checked', 'checked');
    }
});

$('#confirm').on('click', function () {
    confirm();
});

/**
 * 确认更改
 * @returns
 */
function confirm() {
    run_waitMe();
    console.log(getData());
    jQuery.ajax({
        url: '/AccountServlet',
        type: 'post',
        data: 'type=update' + '&data=' + getData(),
        success: function () {
            stopWait();
            top.location = 'My CRC.jsp';
        }
    });
}

/**
 * 获得用户信息
 * @returns
 */
function getData() {
    var language = $('#goodAt tbody').find('input:checked');
    var array = [];

    for (var i = 0; i < language.length; i++) {
        array.push($(language[i]).val())
    }
    var userInfo = new Object({
        name: encodeURI($('#name').val().trim()),
        sex: encodeURI($('.radio').find('input:checked').val()),
        job: encodeURI($('#job').val().trim()),
        province: "",
        city: "",
        description: encodeURI($('#describe').val().trim()),
        picture: '',
        language: array
    });

    var city = $('#city');
    userInfo.province = encodeURI($($(city).find('option:selected')[0]).val());
    userInfo.city = encodeURI($($(city).find('option:selected')[1]).val());
    if( userInfo.city=='undefined'||userInfo.city==0){
    	userInfo.city=encodeURI('-');
    }
    if( userInfo.province=='undefined'||userInfo.province==0){
    	userInfo.province=encodeURI('-');
    }
    return JSON.stringify(userInfo);
}
