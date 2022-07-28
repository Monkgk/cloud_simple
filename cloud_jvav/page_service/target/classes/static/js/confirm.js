// //Countdown
// var oBox = document.getElementById('timer');
// var maxtime = [[${"orderInfo.create_time"}]];
//
// function CountDown() {
// 	if (maxtime >= 0) {
// 		minutes = Math.floor(maxtime / 60);
// 		seconds = Math.floor(maxtime % 60);
// 		msg = "支付剩余时间：" + minutes + ":" + seconds;
// 		oBox.innerHTML = msg;
// 		--maxtime;
// 	} else {
// 		clearInterval(timer);
// 		// alert("时间到，结束!");
// 		window.location.href = "javascript:history.go(-1)";
// 	}
// }
// timer = setInterval("CountDown()", 1000);

// Noted
var i = 2;

function noted_change() {
	var noted = document.getElementById("noted_intro");
	console.log(parseInt(i))
	if (parseInt(i % 2) === 0) {
		noted.style.height = "48rem";
		noted.style.transition = "all .3s ease-in-out";
		i = i + 1
	} else {
		noted.style.height = "21rem";
		noted.style.transition = "all .3s ease-in-out";
		i = 2
	}
}
