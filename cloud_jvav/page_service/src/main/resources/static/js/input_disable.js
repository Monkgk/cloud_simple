// post
function postbtn() {
    var flag = 0;
    var length = document.getElementsByClassName("info_summit").length;
    $(".info_summit").each(function (i,e) {
        if($(this).val()!=""){
            flag = flag+1;
        }
    });
    if (flag==length){
        document.getElementsByClassName("post")[0].disabled = false;
    } else {
        document.getElementsByClassName("post")[0].disabled = true;
    }
}

function postbtn_seat() {
    var length = $(".info_summit li").length;
    console.log(length)
    if (length!=0){
        document.getElementsByClassName("post")[0].disabled = false;
    } else {
        document.getElementsByClassName("post")[0].disabled = true;
    }
}