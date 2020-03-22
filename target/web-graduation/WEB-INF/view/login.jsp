<%@ page import="java.net.URLDecoder" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020-01-29
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body{
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            width:100%;
            height:100%;
        }

        #head{
            color: beige;
            width: 100%;
            height:10%;
        }

        #head p {
            font-size: 25px;
            color: red;
            width:100%;
            height: 100%;
            padding-left: 15%;
            line-height: 350%;

        }
        #body{
            color: blue;
            width:100%;
            height: 50%;
        }
        #foot{
            color: beige;
            width: 100%;
            height: 40%;
        }


    </style>
</head>
<body>

<div id="head">
    <p>学习实例</p>
</div>
<div id="body">
<%--    通过脚本片段取值--%>
    <%
        String username = "";
        String password = "";
        //获取当前站点的所有Cookie
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
            if ("username".equals(cookies[i].getName())) {
                username = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
            } else if ("password".equals(cookies[i].getName())) {
                password = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
            }
        }
    %>
    <form name = "loginForm" id="loginForm" method="post">
        <p>
            <label for="user_login">用户名
                <input type="text" name="username" id="user_login"  size="20" required/>
            </label>
        </p>
        <p>
            <label for="user_pass">密&#12288;码
                <input type="password" name="password" id="user_pass"  size="20" required/>
            </label>
        </p>
        <p>
            <label>
                <input type="checkbox" name="remember" id="remember" value="1" checked/>记住密码
            </label>
        </p>
        <p>
            <input type="button" name="submit-btn" id="submit-btn" value="登陆"/>
            <input type="button" name="regist-btn" id="regist-btn" value="注册"/>
        </p>

    </form>
</div>
<div id="foot"></div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="/resource/js/util.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        getFocus("user_login");
        var username = getUserNameCookie($("#user_login").attr("name"));
        $("#user_login").attr("value",username);
    })

        <%--登陆验证--%>
    $("#submit-btn").click(function(){
        var user = $("#user_login").val();
        var pass = $("#user_pass").val();
        if(user == ""){
            alert("用户名不可为空")
        }else if(pass == ""){
            alert("密码不可为空")
        }else {
            $.ajax({
                async: false,
                type: "POST",
                url: "/loginVerify",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: $("#loginForm").serialize(),
                dataType: JSON,
                success: function (data) {
                    data = eval("("+data+")");
                    if(data.code == 0){
                        alert(data.msg);
                    }else{
                        window.location.href="/search"
                    }
                },
                error: function () {
                    alert("获取数据失败")
                }
            })
        }
    })

        /*注册功能*/
    $("#regist-btn").click(function () {
        window.location.href= "/regist";})

</script>



</html>
