<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020-01-30
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/resource/js/vue.js"></script>
    <link type="text/css" rel="stylesheet" href="/resource/css/user-info.css"></link>
    <link type="text/css" rel="stylesheet" href="/resource/css/head-search.css"></link>
    <link type="text/css" rel="stylesheet" href="/resource/css/search-body.css"/>
    <style type="text/css">
        /*[v-clock]{*/
            /*display: none;*/
        /*}*/
        *{
         margin: 0;
         padding: 0;
        }
        body{
            font: 15px/1.3 'Open Sans',sans-serif;
            color: #5e5b64;
            text-align: center;
        }






    </style>
</head>
<body>
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
    <div id="gold_info" style="display:none;position: absolute;z-index:999;left:40%;top:40%" title="点击表格关闭">
        <table id="table_gold" border="1" cellspacing="0" cellpadding="0" width="400">
            <tr style="text-align: center;color: white">
            </tr>
            <tr style="text-align: center;color: white">
            </tr>
        </table>
    </div>
    <div id="user-info">
        <a id="hello">欢迎回来！</a>
        <a class="user-info" href="#user-info" title="个人主页。。还在开发"><%=username%></a>
        <a class="user-info" href="#setting" title="功能还在开发中">设置</a>
        <a class="user-info" href="#modify" title="功能还在开发中">帮助</a>
        <a class="user-info" href="${pageContext.request.contextPath}/logout">退出</a>
    </div>
    <div id="head">
        <form id="search" method="post" action="/GoodsList">
            <div class="bar">
                <input type="hidden" name="pageNum" value="1">
                <input type="hidden" name="pageSize" value="20">
                <input type="text" placeholder="输入内容" id="searching" name="goodsName">
                <input type="submit" id="searching-btn" value="搜索">
            </div>

        </form>
    </div>
    <div id="body">
        <%--制作下拉菜单--%>
        <div id="menu">
            <ul>
                <li class="menu-1">
                    <a href="#cloths" class="drop-btn">衣</a>
                    <ul class="menu-2">
                        <li><a href="#coat">上衣</a></li>
                        <li><a href="#pant">裤子</a></li>
                        <li><a href="#shoes">鞋子</a></li>
                        <li><a href="#underwear">内衣</a></li>
                        <li><a href="#others">其它</a></li>
                    </ul>
                </li>
                <li class="menu-1">
                    <a href="#food" class="drop-btn">食</a>
                    <ul class="menu-2">
                        <li><a href="#meat">肉类</a></li>
                        <li><a href="#vegetables">蔬菜</a></li>
                        <li><a href="#fruit">水果</a></li>
                        <li><a href="#others">其它</a></li>
                    </ul>
                </li>
                <li class="menu-1">
                    <a href="#live" class="drop-btn">住</a>
                    <ul class="menu-2">
                        <li><a href="#daily">日用</a></li>
                        <li><a href="#furniture">家具</a></li>
                        <li><a href="#kitchen">餐厨</a></li>
                        <li><a href="#others">其它</a></li>
                    </ul>
                </li>
                <li class="menu-1">
                    <a href="#traffic" class="drop-btn">行</a>
                    <ul class="menu-2">
                        <li><a href="#car">汽车</a></li>
                        <li><a href="#motorcycle">摩托车</a></li>
                        <li><a href="#bicycle">自行车</a></li>
                        <li><a href="#others">其它</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div id="foot"></div>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
    <script type="text/javascript" src="/resource/js/util.js"></script>

<script>
    $(document).ready(function () {
        var username = getUserNameCookie("username");
        $.ajax({
            async:false,
            type:"POST",
            url:"/getGoldInfo",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data:{
                "userName":username
            },
            dataType:JSON,
            success:function (data) {
                data = eval("("+data+")");
                var $table = $("#table_gold").find("tr")
                var $tr = $("#table_gold").find("tr").first();
                let week1 = ["周一","周二","周三","周日"];
                let week2 = ["周四","周五","周六"];

                // var $td = $("<td rowspan=\"2\"><span>"+week1[1]+"</span><br/><span>金币:&nbsp;<em>"+data.goldNums[1]+"</em></span></td>")
                // $tr.append($td);
                for (let i=0,len = $table.length;i<len;i++){
                    let t;
                    let week;
                    if (i==0){
                        t = 4;
                        week = week1;
                    }else {
                        t=3;
                        week = week2;
                    }
                    for (let x=0;x<t;x++){
                        let td;
                        let y;
                        if(x==3){
                            if (data.goldNums[6] == 0){
                                td = $("<td rowspan=\"2\" style='background-color: white;color: red'><span>"+week[x]+"</span><br/><span>金币:&nbsp;<em>"+data.goldNums[6]+"</em></span></td>")
                            }else {
                                td = $("<td rowspan=\"2\" style='background-color: red'><span>"+week[x]+"</span><br/><span>金币:&nbsp;<em>"+data.goldNums[6]+"</em></span></td>")
                            }

                        }else {
                            y = x;
                            if(i==1){
                                y = x+3;
                            }
                            if (data.goldNums[y] == 0){
                                td = $("<td style='background-color: white;color: red'><span>"+week[x]+"</span><br/><span>金币:&nbsp;<em>"+data.goldNums[y]+"</em></span></td>")
                            }else {
                                td = $("<td style='background-color: red'><span>"+week[x]+"</span><br/><span>金币:&nbsp;<em>"+data.goldNums[y]+"</em></span></td>")
                            }

                        }

                        $tr.append(td);
                    }
                    $tr = $tr.next("tr");
                }

                //根据金币数量决定色块颜色  改成根据条件添加类属性
                $("#gold_info").css("display","block");
            },
            error:function () {
                alert("获取数据失败-gold！")
            }
        })
        $("#searching").focus();
        $("li").has("ul").mouseover(function () {
            $(this).children("ul").css("display","block");
        }).mouseout(function () {
            $(this).children("ul").css("display","none");
        });

    })
    $("#gold_info").click(function () {
        $("#gold_info").css("display","none")
    })

    // $("#searching-btn").click(
    //     function(){
    //         window.location.href = "/GoodsPage?pageNum=1&pageSize=20&goodsName="+$("#searching").val()
    //     }
    // )


</script>
</body>
</html>