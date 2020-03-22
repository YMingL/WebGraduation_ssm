package com.yang.webgraduation.controller;

import com.yang.webgraduation.entity.User;
import com.yang.webgraduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistController {

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public String registUser(){
        return "Admin/regist";
    }

    @RequestMapping("/savingUsesr")
    @ResponseBody
    public Integer addUser(HttpServletRequest request, HttpServletResponse response){
//        校验通过后保存，此处省略检验
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userId = String.format("%06d",userService.getMaxUserId()+1);
        System.out.println(userId);
        User user = new User(userId,userName,password,phone,address);
        //先判断用户是否存在再保存，此处省略判断
        int flag = userService.addUser(user);
        //添加异常
        return flag;
    }

    @RequestMapping("/registSuccess")
    public String registSuccess(){
        return "Admin/registSuccess";
    }
}
