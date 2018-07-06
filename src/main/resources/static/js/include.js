$(function () {
    $.get("header.html",function (data) {
        $("#hd").html(data);
    });
    // $.get("footer.html",function (data) {
    //     $("#footer").html(data);
    // });
});