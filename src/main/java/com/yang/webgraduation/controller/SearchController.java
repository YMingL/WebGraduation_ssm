package com.yang.webgraduation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yang.webgraduation.dto.SqlParams;
import com.yang.webgraduation.dto.UserGoldInfo;
import com.yang.webgraduation.entity.Goods;
import com.yang.webgraduation.entity.User;
import com.yang.webgraduation.entity.ZdCategory;
import com.yang.webgraduation.service.GoodsService;
import com.yang.webgraduation.service.UserService;
import com.yang.webgraduation.service.ZdCategoryService;
import com.yang.webgraduation.util.DateUtil.DateUtil;
import com.yang.webgraduation.util.RedisUtil;
import com.yang.webgraduation.util.UserRedisTask;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by acer on 2019-12-23.
 */

@Controller
public class SearchController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ZdCategoryService zdCategoryService;
    @Autowired
    private UserRedisTask userRedisTask;

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = Logger.getLogger(SearchController.class);

    /**
     * @param pageNum
     * @param pageSize
     * @param goodsName
     * @return
     */
    @RequestMapping("/GoodsList")
//    @ResponseBody
    public String getGoodsPageByName(@RequestParam(value = "pageNum",required = false) int pageNum,
                                           @RequestParam(value = "pageSize",required = false) int pageSize,
                                           @RequestParam(value = "goodsName",required = false) String goodsName,
                                           @RequestParam(value = "brand",required = false) String brand,
                                           @RequestParam(value = "price",required = false) String price,Model model
                                     ){
        List<Goods> goodsList = goodsService.getPageList(pageNum,pageSize,goodsName);
        int goodsPage =  goodsService.getGoodsNum(goodsName)/20;
        model.addAttribute("goodsList",goodsList);
        model.addAttribute("goodsPage",goodsPage);
        return "Admin/goodsPage";
    }
//
//    @RequestMapping("/GoodsPage")
//    public String goodsPage(Model model){
//        return "Admin/goodsPage";
//    }
    /**
     * 查找界面
     * @return
     */
    @RequestMapping("/search")
    public  String searching(Model model){
        return "Admin/search";
    }

    /**
     * 返回分类列表
     * @return
     */
    @RequestMapping("/getCategory")
    @ResponseBody
    public List<ZdCategory> getCategoryList(){
        List<ZdCategory> zdCategories = zdCategoryService.getCategoryList();
        return  zdCategories;
    }

    /**
     * 添加用户金币赠送功能
     * 优化重复代码
     * @param
     * @return
     */
    @RequestMapping(value = "/getGoldInfo",method = RequestMethod.POST,produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String getUserGoldInfo(@RequestParam("userName") String userName){
/*        利用数组存储用户每日获得金币数，登陆时，若当天金币数为0，则表示首次登陆，反之则不是首次，
        若上一个数组元素为零，则表示前一天未登录，赠送金币数应重新计数，反之金币数 +1
        金币数量存入mysql数据库，数组存入redis，每天更新一次数据库，显示则用redis累加*/
        Calendar calendar= Calendar.getInstance();
        //1、从redis中获取数据，若没有则创建
        UserGoldInfo userGold = (UserGoldInfo) redisUtil.getObj("userGold",userName+"_Gold");
        Calendar calendar_old = Calendar.getInstance();
        if (userGold != null) {
            calendar_old.setTimeInMillis(userGold.getMillis());//获取上次登陆时间
        }

        if (userGold  == null){
//首次登陆
            UserGoldInfo userGoldInfo = new UserGoldInfo();
            //取得当前时间
            userGoldInfo.setMillis(calendar.getTimeInMillis());
            if(calendar.get(Calendar.DAY_OF_WEEK)-1 == 0){
                userGoldInfo.setGoldNum(7,1);
            }else {
                userGoldInfo.setGoldNum(calendar.get(Calendar.DAY_OF_WEEK)-1,1);
            }
            //更新redis
            redisUtil.addObj("userGold",userName+"_Gold",userGoldInfo);

            //更新数据库
            userService.updateUserGold(new SqlParams(userName,1));
            //添加异常处理添加失败，
            return new JSONObject(userGoldInfo).toString();
        }else if (calendar.get(Calendar.YEAR) == calendar_old.get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == calendar_old.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_MONTH) == calendar_old.get(Calendar.DAY_OF_MONTH)){
//            return null;//同一天登陆
//            JSONArray array = new JSONArray(userGold.getGoldNums());
//            return array.toString();
            return new JSONObject(userGold).toString();
        }else if(calendar.get(Calendar.DAY_OF_WEEK)-1 == 1) {  //判断当前登陆时间是否为周一
            userGold.empty();//清除以前的数据
            //设置新数据
            userGold.setMillis(calendar.getTimeInMillis());
            userGold.setGoldNum(1,1);
            //更新redis
            redisUtil.updateObj("userGold",userName+"_Gold",userGold);
            userService.updateUserGold(new SqlParams(userName,1));
            return  new JSONObject(userGold).toString();
        } else if(DateUtil.isInWeek(calendar_old.getTimeInMillis(),calendar.getTimeInMillis())){ //周内连续
            userGold.setMillis(calendar.getTimeInMillis());
            if(calendar.get(Calendar.DAY_OF_WEEK) -1 == 0){
                userGold.setGoldNum(7,userGold.getGoldNum(6)+1);
                redisUtil.updateObj("userGold",userName+"_Gold",userGold);
                userService.updateUserGold(new SqlParams(userName,userGold.getGoldNum(7)));
            }else {
                userGold.setGoldNum(calendar.get(Calendar.DAY_OF_WEEK)-1,userGold.getGoldNum(calendar.get(Calendar.DAY_OF_WEEK)-1-1)+1);
                redisUtil.updateObj("userGold",userName+"_Gold",userGold);
                userService.updateUserGold(new SqlParams(userName,userGold.getGoldNum(calendar.get(Calendar.DAY_OF_WEEK)-1)));
            }
            return new JSONObject(userGold).toString();
        } else if(!DateUtil.isInWeek(calendar_old.getTimeInMillis(),calendar.getTimeInMillis())){//不连续周
            userGold.empty();
            userGold.setMillis(calendar.getTimeInMillis());
            if (calendar.get(Calendar.DAY_OF_WEEK)-1 == 0){
                userGold.setGoldNum(7,1);
            }else {
                userGold.setGoldNum(calendar.get(Calendar.DAY_OF_WEEK)-1,1);
            }
            redisUtil.updateObj("userGold",userName+"_Gold",userGold);
            userService.updateUserGold(new SqlParams(userName,1));
            return new JSONObject(userGold).toString();
        }
        return null;
//        return new JSONObject(userGold).toString();
    }
    /*
    * 显示登陆页面
    * */
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value="/loginVerify",method = RequestMethod.POST,produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        Map<String ,Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        logger.warn("111");
        User user = (User) userRedisTask.getObj("userInfo",username);
        logger.warn("222");
        if (user == null){
            user = userService.getUserByName(username);
            userRedisTask.addObj("userInfo",username,user);
        }else{
            System.out.println("redis----"+user.getUsername()+"::"+user.getPassword()+"----"+"user_"+username);
        }
        try {
             if (user == null) {
                map.put("code", 0);
                map.put("msg", "用户名无效");
            } else if (!user.getPassword().equals(password)) {
                map.put("code", 0);
                map.put("msg", "密码错误");
            } else {
                map.put("code", 1);
                map.put("msg", "");
                //添加session
                request.getSession().setAttribute("user", user);
                //如果有记住密码，则添加cookie
                if (remember != null) {
                    Cookie nameCookie = new Cookie("username", URLEncoder.encode(username, "utf-8"));
                    nameCookie.setMaxAge(60*60*24*3);
                    Cookie passCookie = new Cookie("password", URLEncoder.encode(password, "utf-8"));
                    passCookie.setMaxAge(60*60*24*3);
                    response.addCookie(nameCookie);
                    response.addCookie(passCookie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = new JSONObject(map).toString();
        return result;
    }

    //退出登陆
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();//session失效
        return "redirect:/login";
    }

}
