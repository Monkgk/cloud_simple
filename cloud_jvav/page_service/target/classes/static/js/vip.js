// 季卡
function click_three() {
	var three = document.getElementById("three_months");
	var six = document.getElementById("six_months");
	var choice1 = document.getElementById("choice1");
	var choice2 = document.getElementById("choice2");
	var time = document.getElementById("card_time");
	var price = document.getElementById("card_price");
	var quotation = document.getElementById("final_quotation");
	three.style.background = "#fdf5ee";
	six.style.background = "#fff";
	choice1.style.display="";
	choice2.style.display="none";
	time.innerHTML="3";
	price.innerHTML="9.9";
	quotation.innerHTML="9.9"
}

// 半年卡
function click_six() {
	var three = document.getElementById("three_months");
	var six = document.getElementById("six_months");
	var choice1 = document.getElementById("choice1");
	var choice2 = document.getElementById("choice2");
	var time = document.getElementById("card_time");
	var price = document.getElementById("card_price");
	var quotation = document.getElementById("final_quotation");
	three.style.background = "#fff";
	six.style.background = "#fdf5ee";
	choice1.style.display="none";
	choice2.style.display="";
	time.innerHTML="6";
	price.innerHTML="19.8";
	quotation.innerHTML="19.8"
}
