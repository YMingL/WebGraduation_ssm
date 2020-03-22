<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020-02-23
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>标题</title>
    <style type="text/css">
        *{margin: 1px;padding: 0;}
        form{margin: 0 auto;padding:15px; width: 350px;height:300px;text-align: left;}
        #submit{padding: 10px}
        #submit input{width: 50px;height: 24px;}
    </style>
</head>
<body>
<div class="wrapper">
    <h2 style="margin: 20px;text-align: center">新用户注册</h2>
    <form method="post" id="userInfoForm">
        <label>用户名:</label>
        <input type="text" name="userName" id="userName"/><span id = "verifyName" ></span><br>
        <label>密&nbsp;码：</label>
        <input type="password" name="password" id="password"/><span id="verifyPass"></span><br>
        <label>电&nbsp;话：</label>
        <input type="text" name="phone" id="phone"/><span id="verifyPhone"></span><br>
        <label>地&nbsp;址：</label>
        <input type="text" name="address"id="address"/><br>
        <div id="submit">
            <input type="button" id= "submit-btn" onclick="submitForm()" disabled="disabled" value="保存"/>
            <input type="button" id="empty-btn" value="重填">
        </div>
    </form>

</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="/resource/js/util.js"></script>
<script type="text/javascript">
   var flag_name = 0;
   var flag_pass = 0;
   var flag_phone = 0;
 $(document).ready(function () {
    getFocus("userName");
     setInterval(verifySubmit,1000);
 })
    function verifySubmit(){
        if( flag_name == 0 ||  flag_pass == 0 ||  flag_phone == 0){
            $("#submit-btn").attr("disabled","disabled");
        }else if( flag_name == 1 &&  flag_pass == 1 &&  flag_phone == 1) {
            $("#submit-btn").removeAttr("disabled")
        }
    }
// 为每个校验单独设置flag 当失去焦点时进行校验，不合格反馈信息并置flag为0，反之置flag为1 当保存提交时 判断flag值，只有当所有flag为1时才能提交

//    姓名校验 姓名唯一
 $("#userName").blur(function () {
     if($("#userName").attr("value") != "" && $("#userName").attr("value").length <= 10){
        $.ajax({
            async:false,
            url:"/hasUserName",
            type:"POST",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data:$("#userName"),
            dataType:JSON,
            success:function (data) {
               if (data >= 1){
                   $("#verifyName").text("用户名已存在");
                   flag_name = 0;
               }else {
                   $("#verifyName").text("");
                   flag_name = 1;
               }
            },
            error:function () {
                flag_name = 0;
                alert("获取数据失败")
            }

        })
     }else if ($("#userName").attr("value") == ""){
         $("#verifyName").text("用户名为空");
         flag_name = 0;
     }else if ($("#userName").attr("value").length > 10){
         $("#verifyName").text("用户名过长");
         flag_name = 0;
     }
 })
//    密码校验
 $("#password").blur(function () {
     //类型为password，无需此校验
     // if($("#password").attr("value") != ""){
     //     var reg = new RegExp("[\\u2E80-\\u9FFF]+","g");
     //     if(reg.test($("#password").attr("value") )){alert("包含汉字！"); }
     // }
     //失去焦点正则判断是否合格
     if($("#password").attr("value") == ""){
         $("#verifyPass").text("不能为空");
         flag_pass = 0;
     }else if($("#password").attr("value").length > 16 || $("#password").attr("value").length < 6){
         $("#verifyPass").text("密码长度为6-16");
         flag_pass = 0;
     }else {
         $("#verifyPass").text("");
         flag_pass = 1;
     }
 })
//    电话校验
$("#phone").blur(function () {
    if ($("#phone").attr("value") != ""){
        if(!(/^1[0-9]{10}$/.test($("#phone").attr("value")))){
            $("#verifyPhone").text("号码有误请重填");
            flag_phone = 0;
        }else {
            $("#verifyPhone").text("");
            flag_phone = 1;
        }
    }else if ($("#phone").attr("value") == ""){
        $("#verifyPhone").text("号码不能为空");
        flag_phone = 0;
    }
})
//暂时不做地址校验，添加省市联动
//    保存校验
//    提交注册表单
    function submitForm() {
        $.ajax({
            async:false,
            type:"POST",
            url:"/savingUsesr",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            dataType: JSON,
            data:$("#userInfoForm").serialize(),
            success:function (data) {
                if (data == 1){
                    window.location.href="/registSuccess"
                }else {
                    alert("注册失败！");
                }
            },
            error:function () {
                alert("错了!")
            }
        })
    }

//    重填按钮
</script>
</html>
