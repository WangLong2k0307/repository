$(function () {
    $.get("newbase.html",function (data) {
        $("#newbase").html(data);
    });
});