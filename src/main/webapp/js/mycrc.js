$('#searchPeople').keyup(function (e) {
    if (e.which == 13) {
        searchPeople();
    }
});

$('#search').click(function () {
    alert("dsfsdf....x.cvxcv.x.cv.x.cv")
});
$('#search').on("click", function () {
    searchPeople();
});
console.log(2)

function searchPeople() {
    console.log(3)
    var users = [];
    var keyword = $('#searchPeople').text().trim();
    jQuery.ajax({
        url: '/SearchServlet',
        type: 'post',
        data: 'type=searchUser&keyword=' + keyword,
        success: function (data) {
            users = jQuery.parseJSON(data)[0].users;
            alert(data);
        }
    });
}
