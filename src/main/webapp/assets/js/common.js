$(document).ready(function() {

$("#header .menu").click(function() {
  $("#sub-menu").addClass("active");
  $("body").css("overflow", "hidden");
});

$("#sub-menu .close").click(function() {
  $("#sub-menu").removeClass("active");
  $("body").removeAttr("style");
});

$(".pop-btn").click(function() {
  var popon = $(this).attr("data");

  $("div#"+ popon).show();

});

$(".filter a").click(function() {
  $(".filter").addClass("active");
});

$(".filter button").click(function() {
  $(".filter").removeClass("active");
});



});
