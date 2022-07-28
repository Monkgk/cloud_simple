// Detail
function film_detail(detailId) {
	window.location.href = "/index/detail/"+detailId;
}

function iframe_film_detail(detailId) {
	parent.location.href = "/index/detail/"+detailId;
}

function cinema_iframe_film_detail(detailId) {
	parent.location.href = parent.location.href+"/"+detailId;
}

$('.shift').click(function() {
	var a = $(this);
	a.css("color","#dd3f3b");
	a.css("borderBottom","#dd3f3b solid 0.5rem");
	//清除其他元素的格式
	a.siblings().css("color","#666666");
	a.siblings().css("borderBottom","none");
	// page1.style.color = "#666666";
	// page1.style.borderBottom = "none";
	// page2.style.color = "#dd3f3b";
	// page2.style.borderBottom = "#dd3f3b solid 0.5rem";
});

//Data
$('.choosesession').click(function() {
	var other		= $(this).siblings();
	$(this).css("color", " #dd3f3b");
	$(this).css("borderBottom", "#dd3f3b solid 0.5rem");

	other.css("color", " #666666");
	other.css("borderBottom", "none");
});

//film
// var background = document.querySelector('.img_background');
// var pages = document.querySelector(".pages");
// var pagesW = pages.offsetWidth;
//
// var still = document.querySelectorAll(".still");
//
// for (let i = 0; i < still.length; i++) {
// 	still[i].onclick = function() {
// 		var scrollLeftNum = still[i].offsetLeft - pagesW / 2 + still[i].offsetWidth / 2;
// 		// var url = still[i].getElementsByTagName("img").src;
// 		var url = $(still[i]).attr('src');
// 		$(background).removeClass('img_background');
// 		/*
// 			position: absolute;
// 			width: 100%;
// 			height: 100%;
// 			left: 0;
// 		*/
// 		$(background).addClass("ahh");
// 		background.style.position = "relative";
// 		$(background).css("width", function() {
// 			return "100%";
// 		});
// 		$(background).css('overflow', function() {
// 			return "hidden";
// 		});
// 		background.style.height = "38rem";
// 		background.style.left = "0px";
// 		background.style.backgroundImage = `linear-gradient(rgba(0, 0, 0, .5), rgba(0, 0, 0, .5)),url(${url})`;
// 		background.style.backgroundRepeat = "no-repeat";
// 		background.style.backgroundSize = "cover";
// 		background.style.filter = "blur(25px)";
// 		background.style.zIndex = "-1";
// 		pages.scrollLeft = scrollLeftNum;
// 	}
// }


$('.choosefilm:first').find('img').css("margin-left", "37%");
// $('.choosefilm:first').attr("class",'choosefilm fade in active');
$('.choosefilm').click(function() {
	var other		= $(this).siblings();
	$(this).find("img").css("width", "25%");
	$(this).find("img").css("border", "#FFFFFF solid .3rem");
	$(this).find("img").css("transition", "all .2s");

	other.find("img").css("width", "22%");
	other.find("img").css("transition", "all .2s");
	other.find("img").css("border", "none");
});


