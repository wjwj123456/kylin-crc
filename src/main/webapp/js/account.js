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
        url: '/crc/AccountServlet',
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
    var array = new Array();

    for (var i = 0; i < language.length; i++) {
        array.push($(language[i]).val())
    }
    var userInfo = new Object({
        name: $('#name').val().trim(),
        sex: $('.radio').find('input:checked').val(),
        job: $('#job').val().trim(),
        province: "",
        city: "",
        description: $('#describe').val().trim(),
        picture: '',
        language: array
    });

    userInfo.province = $($('#city option:selected')[0]).val();
    userInfo.city = $($('#city option:selected')[1]).val();
    console.log($($('#city option:selected')[0]).val());

    return JSON.stringify(userInfo);
}
