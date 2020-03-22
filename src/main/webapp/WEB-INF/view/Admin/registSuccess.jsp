<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020-03-19
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: center">
    <h1>成功！</h1>
    <p><em style="font-size: larger"></em>&nbsp;S后跳转至登陆页面</p>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="/resource/js/util.js"></script>
<script type="text/javascript">
    var time = 5;
    function showTime() {
        $("em").html(time);
        time -= 1;
        if (time == 0){
            window.location.href = "/login";
        }
        setTimeout("showTime()",1000);
    }
    showTime();


</script>
</html>
