<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2019-12-28
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="/resource/css/user-info.css"/>
    <link type="text/css" rel="stylesheet" href="/resource/css/head-search.css"></link>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        body{
            font: 15px/1.3 'Open Sans',sans-serif;
            color: #5e5b64;
            text-align: center;
            width: 100%;
        }

        #body{
            width: 1400px;
            margin-top: 10px;
        }
        .img{
            margin: 5px 10px;
            width: 250px;
            height: 400px;
            border: 1px solid transparent;
        }
        .img:hover{
            border: 1px solid #777;
        }

        .img img{
            width: 100%;
            height: auto;
        }
    /*    商品列表*/
        .desc #price{
            color: #e4393c;
            font-size: 20px;
            font-weight: 400;
            font-family: Verdana;
            text-align: left;
        }
        .desc #goodsName {
            font-size: 12px;
            text-align: left;
        }
        .desc #goodsName a{
            color: #000;
            text-decoration: none;
        }
        .desc #goodsName a:hover{
            color: #e4393c;
            text-decoration: none;
        }
        .desc #sales{
            color: #e4393c;
            font-size: 14px;
            text-align: left;
        }
        .desc #shop{
            font-size: 14px;
            text-align: left;
            color: #5c9bb7;
        }

        .condition{
            background-color: red;
            color: white;
        }

        #goodsFilter_two a:hover{
            border: 1px solid #777;
        }
    </style>
</head>
<body style="overflow-y: scroll;overflow-x:hidden;">
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
<div id="user-info">
    <a id="hello">欢迎回来！</a>
    <a class="user-info" href="#user-info"><%=username%></a>
    <a class="user-info" href="#setting">设置</a>
    <a class="user-info" href="#modify">修改</a>
    <a class="user-info" href="${pageContext.request.contextPath}/logout">退出</a>
</div>
    <div id="head">
        <form id="search" action="/GoodsList">
            <div class="bar">
                <input type="hidden" name="pageNum" value="1">
                <input type="hidden" name="pageSize" value="20">
                <input type="text" placeholder="输入内容" id="searching" name="goodsName">
                <input type="submit" id="searching-btn" value="搜索">
            </div>
        </form>
    </div>
    <div>
        <hr style="margin: 10px 0;width: 100%"/>
    </div>
    <div id="left" style="color: red;width: 250px;float: left">
        <p style="font-size: xx-large">左</p>
        <p style="font-size: xx-large">边</p>
        <p style="font-size: xx-large">广</p>
        <p style="font-size: xx-large">告</p>
        <p style="font-size: xx-large">位</p>
    </div>

    <div id="body" style="float: left">
        <c:if test="${goodsList.size() != 0}">
            <div id="goodsFilter">
                <div style="text-align: left;background-color: #5c9bb7;margin-bottom: 0px"><em style="font-size: larger">商品筛选</em>&nbsp;&nbsp;&nbsp;&nbsp;<em style="font-size: large">共有<em>${goodsList.size()}</em>个商品</em></div>
                <div style="text-align: left;line-height: 34px">
                    <div style="width:60px;height: 34px;float: left">品牌：</div>
                    <div id="brandCondition" style="width:500px;height: 34px">
                        <a href="#" style="text-decoration: none;margin:0 15px 0 0">品牌1</a>
                        <a href="#" style="text-decoration: none;margin:0 15px 0 0">品牌2</a>
                        <a href="#" style="text-decoration: none;margin:0 15px 0 0">品牌3</a>
                        <a href="#" style="text-decoration: none;margin:0 15px 0 0">品牌4</a>
                        <a href="#" style="text-decoration: none;margin:0 15px 0 0">品牌5</a>
                    </div>
                </div>
                <hr style="margin: 1px 0;width: 100%;border: 1px dashed #000000"/>
                <div style="text-align: left;line-height: 34px">
                    <div style ="width:60px;height: 34px;float: left">价格：</div>
                    <div id="priceCondition"  style="width:500px;height: 34px">
                        <a href="#" style="text-decoration: none;margin:0 20px 0 0">0-100</a>
                        <a href="#" style="text-decoration: none;margin:0 20px 0 0">101-200</a>
                        <a href="#" style="text-decoration: none;margin:0 20px 0 0">201-300</a>
                        <a href="#" style="text-decoration: none;margin:0 20px 0 0">301-400</a>
                        <a href="#" style="text-decoration: none;margin:0 20px 0 0">401以上</a>
                    </div>
                </div>
                <hr style="margin: 1px 0;width: 100%;border: 1px solid #000000"/>

                <div id="goodsFilter_two" style="height: 34px">
                    <div id="moreCondition" style="text-align: left;line-height: 34px;float: left">
                        <a class="condition" href="#" style=" text-decoration: none">综合排序</a>
                        <a href="#" style="text-decoration: none">销量</a>
                        <a href="#" style="text-decoration: none">价格</a>
                    </div>
                    <div id="page" style="float: left;height: 34px;margin-left: 80%">
                        <div style="line-height: 34px;float: left"><span>1</span>/${goodsPage}</div>
                        <div style="float: left;line-height: 34px"><a style="font-size:25px;text-decoration: none" href="#"><</a><a style="font-size:25px;text-decoration: none" href="#">></a></div>
                    </div>
                </div>
            </div>

            <c:forEach items="${goodsList}" var="goods">
                <div class="img" style="float: left">
                    <a target="_blank" href="${goods.goodsurl}">
                        <img src="${goods.goodsDetail.goodsImageUrl}">
                    </a>
                    <div class="desc">
                        <p id="price"><em>¥</em><i>${goods.currprice}</i></p>
                        <p id="goodsName"><a target="_blank" href="${goods.goodsurl}">${goods.goodsname}</a></p>
                        <p id="sales"><em>销量：</em>${goods.goodsDetail.sales}</p>
                        <p id="shop"><a href="#" style="text-decoration: none">${goods.goodsDetail.shopname}</a></p>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${goodsList.size() == 0}">
            <h1>抱歉！没有相关数据</h1>
        </c:if>

    </div>
    <div id="right" style="width: 240px;color: red;float: left">
        <p style="font-size: xx-large">右</p>
        <p style="font-size: xx-large">边</p>
        <p style="font-size: xx-large">广</p>
        <p style="font-size: xx-large">告</p>
        <p style="font-size: xx-large">位</p>
    </div>
    <div style="float: none"></div>
    <div id="foot"></div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="/resource/js/util.js"></script>
<script type="text/javascript">
$(function () {
    $("#goodsFilter_two #moreCondition a").click(function () {
        $(this).siblings("a").removeClass("condition");
        $(this).addClass("condition");

        let price = $("#priceCondition .condition").text();
        let brand = $("#brandCondition .condition").text()
        console.log(price+"==="+brand+"==="+$(this).text());
    });
    $("#priceCondition a").click(function () {
        $(this).siblings("a").removeClass("condition");
        $(this).addClass("condition");

        let moreCon = $("#moreCondition .condition").text();
        let brand = $("#brandCondition .condition").text();
        console.log(moreCon+"==="+brand+"==="+$(this).text());
    });
    $("#brandCondition a").click(function () {
        $(this).siblings("a").removeClass("condition");
        $(this).addClass("condition");

        let moreCon = $("#moreCondition .condition").text();
        let price = $("#priceCondition .condition").text();
        console.log(moreCon+"==="+price+"==="+$(this).text());
    });

})

</script>
</html>
