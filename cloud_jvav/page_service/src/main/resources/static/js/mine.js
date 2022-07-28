// skip
function jump() {
	window.location.href = "/mine/login";
}
function info(){
	window.location.href = "/mine/info";
}

// sign
function signbtn() {
	var name = document.getElementById("name").value;
	var password = document.getElementById("password").value;
	if (name != "" & password != "") {
		document.getElementById("sign").disabled = false;
	} else {
		document.getElementById("sign").disabled = true;
	}
}

// skip
function city_jump() {
	window.location.href = "#";
}