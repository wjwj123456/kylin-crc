$('#searchPeople').keyup(function (e) {
    if (e.which == 13) {
        searchPeople();
    }
});

$('#search').on("click", function () {
    searchPeople();
});

function searchPeople() {
    console.log(3)
    var users = [];
    var keyword = $('#searchPeople').text().trim();
    jQuery.ajax({
        url: '/crc/SearchServlet',
        type: 'post',
        data: 'type=searchUser&keyword=' + keyword,
        success: function (data) {
            users = jQuery.parseJSON(data)[0].users;
            alert(data);
        }
    });
}
