/*获取cookie*/
//传入key
function getUserNameCookie(name) {
    //    js遍历获取cookie
    var allcookies = document.cookie;
    var arrcookies = allcookies.split("; ");
    for(var i=0;i<arrcookies.length;i++){
        var usercookie = arrcookies[i].split("=");
        if (usercookie[0] = name){
            return usercookie[1];
        }
    }
    return "";
}

/*表单输入框获取焦点*/
//传入标签id
function getFocus(attr) {
    setTimeout(function(){
            var d=document.getElementById(attr);
            d.focus();
    },2)
    return;
}