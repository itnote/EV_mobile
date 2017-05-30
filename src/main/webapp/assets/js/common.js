$(document).ready(function () {

    $("#header .menu").click(function () {
        $("#sub-menu").addClass("active");
        $("body").css("overflow", "hidden");
    });

    $("#sub-menu .close").click(function () {
        $("#sub-menu").removeClass("active");
        $("body").removeAttr("style");
    });


});
