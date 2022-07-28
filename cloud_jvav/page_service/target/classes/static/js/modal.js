$(document).ready(function() {
	var height = window.innerHeight;
	var item_height = 784.8;
	var top = (height - item_height) / 2;
	if (top < 0) {
		top = 0;
	}
	$("#myModal").css("top", top + "px");
	$("#myModal").modal("show");
	$("#btnOpen").click(function() {
		$("#myModal").modal("show");
	});
});
