// post
function postbtn() {
	var view = document.getElementById("view").value;
	if (view != "") {
		document.getElementById("post").disabled = false;
	} else {
		document.getElementById("post").disabled = true;
	}
}