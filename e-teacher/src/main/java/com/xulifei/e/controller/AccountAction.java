package com.xulifei.e.controller;


import com.xulifei.e.entity.User;
import com.xulifei.e.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountAction extends BaseAction {
    @Resource
    private AccountService accountService;

    @RequestMapping(value="/loginByAccountAndPwd",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object loginByAccountAndPwd(User user, HttpSession session) {
        try {
        Map  map=  new HashMap<String,Object>();

        List<User> userList= accountService.loginByAccountAndPwd(user);
        if(userList.size()>0){
            //存session
            session.setAttribute("user",userList.get(0));
            map.put("isloginSuccess",true);
            map.put("user",userList.get(0));
            return map;
        }
            map.put("isloginSuccess",false);
            return map;

        }catch (Exception e){
     throw new RuntimeException(e);
 }


    }
    @RequestMapping(value="/loginByAccount",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object loginByAccount(User user, HttpSession session) {
try {
    Map  map=  new HashMap<String,Object>();
    System.out.println("---action.account:"+user);
    System.out.println("账号"+user.getAccount());
    List<User> userList= accountService.loginByAccount(user);
    System.out.println(userList.size());
    System.out.println(userList.get(0).getAccount());
    if(userList.size()>0){
        //存session
        session.setAttribute("user",userList.get(0));
        map.put("isloginSuccess",true);
        map.put("user",userList.get(0));
        return map;
    }

    map.put("isloginSuccess",false);
    return map;

}catch (Exception e){
    throw  new RuntimeException(e);
}


    }
    //produces="text/html;charset=UTF-8",避免返回的中文数据乱码,设置的是reponse的Content-Type:application/json;charset=UTF-8即返回的数据格式
    @RequestMapping(value="/resetPwd",produces="application/json;charset=UTF-8")
    @ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
    public Object updatePwdByAccount(User user){
        try {
            System.out.println(user.getAccount());
            System.out.println(user.getPwd());
            boolean isUpdateSuccess = accountService.updatePwdByAccount(user);
            Map map = new HashMap<String,Object>();
            map.put("isUpdateSuccess",isUpdateSuccess);
            return map;
        }catch (Exception e){
             throw new RuntimeException(e);
        }

    }

    @RequestMapping(value="/registerAccountIsExist",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object registerAccountIsExist(User user) {
try {
    Boolean registerAccountIsExist = accountService.registerAccountIsExist(user);
    Map map = new HashMap<String,Object>();
    map.put("registerAccountIsExist",registerAccountIsExist);
    return map;

}catch (Exception e){
    throw  new RuntimeException(e);
}

    }
    @RequestMapping(value="/register",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object register(User user) {
        try {
            user.setUserId(user.getUserId());
            Boolean isRegisterSuccess = accountService.register(user);
            Map map = new HashMap<String,Object>();
            if (isRegisterSuccess == true){
                map.put("isRegisterSuccess",isRegisterSuccess);
                map.put("user",user);
            }
            map.put("isRegisterSuccess",isRegisterSuccess);

            return map;
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }
    @RequestMapping(value="/changePhonenumber",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object changePhonenumber(User user) {
        try {
            Map map = new HashMap<String,Object>();
            int num = accountService.update(user);

          if (num>0){
              map.put("updateSuccess",true);
           return map;
          }
         map.put("updateSuccess",false);

            return map;
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }
}
