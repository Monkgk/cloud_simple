// 想看
var i = 2;

function like() {

	var heart = document.getElementById("heart");
	// if (i == 1){
	// 	heart.style.color = "#e0594a";
	// 	i = 0
	// } else{
	// 	heart.style.color = "#c0c0c1";
	// }
	if (parseInt(i % 2) === 0) {
		heart.style.color = "#e0594a";
		layer.msg('已标记想看');
		i = i + 1
	} else {
		heart.style.color = "#c0c0c1";
		i = 2
	}

	// if(heart.style.color === "#c0c0c1"){
	// 	heart.style.color = "#e0594a";
	// }
	// else {
	// 	heart.style.color = "#c0c0c1"
	// }
}

//评分
function rating(film_id) {
	// window.location.href = "star_rating";
	window.location.href = "/comment/"+film_id+"";
}

//图片横竖判断
var src = document.getElementById("still_img").getAttribute("src");
var pic = document.getElementById("still_img");
var timer = setInterval(takePlace(src), 50);

function takePlace(src) {
	var img = new Image();
	img.src = src;
	// if (img.width > 0 || img.height > 0) {
	if (img.width == 2500) {
		// pic.style.width = 500 + "px";
		// pic.style.height = (500 / img.width) * img.height + "px";
		// alert(pic.style.width);
		$("#still_img").css("width", "100%")
		clearInterval(timer);
	} else {
		$("#still_img").css("height", "100%")
		clearInterval(timer);
	}
}


function still_img() {
	// var imgWidth = document.getElementById("still_img").width;
	// var imgHeight = document.getElementById("still_img").height;
	// if (imgWidth == 2500) {
	// 	// imgWidth.style.width = "100%"
	// 	$("#still_img").css("width", "100%")
	// } else if (imgWidth == 1920) {
	// 	$("#still_img").css("height", "100%")
	// }
	// console.log(parseInt(imgWidth))
	// console.log(parseInt(imgHeight))
}
