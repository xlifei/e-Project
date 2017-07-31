package com.xulifei.e.controller;


import com.xulifei.e.entity.User;
import com.xulifei.e.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountAction extends BaseAction {
    @Resource
    private AccountService accountService;

    @RequestMapping(value="/login",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object login(User user, HttpSession session) {

        Map  map=  new HashMap<String,User>();
        System.out.println("---action.account:"+user);
        System.out.println("账号"+user.getAccount()+"密码"+user.getPwd());
        User acc= accountService.login(user);
        if(acc!=null){
            //存session
            session.setAttribute("user", acc);
            map.put("isLogin",true);
            map.put("user",acc);
            return map;
        }

       map.put("isLogin",false);
        map.put("user",acc);
            return map;


    }
    //produces="text/html;charset=UTF-8",避免返回的中文数据乱码,设置的是reponse的Content-Type:application/json;charset=UTF-8即返回的数据格式
    @RequestMapping(value="/resetPwd",produces="application/json;charset=UTF-8")
    @ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
    public Object updatePwdByAccount(User user){
        System.out.println(user.getAccount());
        System.out.println(user.getPwd());
        boolean isUpdateSuccess = accountService.updatePwdByAccount(user);
       Map map = new HashMap<String,Object>();
       map.put("isUpdateSuccess",isUpdateSuccess);
        return map;
    }

    @RequestMapping(value="/registerAccountIsExist",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object registerAccountIsExist(User user) {

        Boolean registerAccountIsExist = accountService.registerAccountIsExist(user);
        Map map = new HashMap<String,Object>();
        map.put("registerAccountIsExist",registerAccountIsExist);
        return map;

    }
    @RequestMapping(value="/register",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object register(User user) {
        user.setUserId(user.getUserId());
        Boolean isRegisterSuccess = accountService.register(user);
        Map map = new HashMap<String,Object>();
        if (isRegisterSuccess == true){
            map.put("isRegisterSuccess",isRegisterSuccess);
            map.put("user",user);
        }
         map.put("isRegisterSuccess",isRegisterSuccess);

        return map;

    }
}
