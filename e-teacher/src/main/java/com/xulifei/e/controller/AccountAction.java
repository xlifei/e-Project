package com.xulifei.e.controller;


import com.xulifei.e.entity.User;
import com.xulifei.e.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountAction extends BaseAction {
    @Resource
    private AccountService accountService;

    @RequestMapping(value="/login")
    public String login(User user, HttpServletRequest request, HttpSession session){
        System.out.println("---action.account:"+user);
        User acc= accountService.login(user);
        if(acc!=null){
            //存session
            session.setAttribute("user", acc);
            return "forward:/WEB-INF/main.jsp";
        }else{
            request.setAttribute("msg", "用户名或密码错误！");
            return "redirect:/login.jsp";
        }


    }

    @RequestMapping("/doAjax")
    @ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
    public Object doAjax(User user){
        System.out.println("---doAjax.user:"+user);
        user.setUserName(user.getUserName());
        return user;
    }


}
