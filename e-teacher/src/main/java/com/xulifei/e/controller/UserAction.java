package com.xulifei.e.controller;

import com.xulifei.e.entity.User;
import com.xulifei.e.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/8/7.
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

     @Resource
    private UserService userService;

    @RequestMapping(value="/getTeacherList",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getTeacherList(String classId) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            User u = userService.findTeacherByClassId(classId);
            map.put("u",u);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    @RequestMapping(value="/getStudentList",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getStudentList(String classId) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
             List<User> userList = new ArrayList<User>();
              userList= userService.findStudentByClassId(classId);
            map.put("userList",userList);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }

    @RequestMapping(value="/getStudentListForCloudStiatistics",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getStudentListForCloudStiatistics(String classId) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            List<User> userList = new ArrayList<User>();
            userList= userService.findStudentByClassIdForCloudStatistics(classId);
            map.put("userList",userList);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }
    @RequestMapping(value="/updateUser",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object updateUser(User user) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();

            int num = userService.update(user);
  if (num>0){
               map.put("updateSuccess",true);
      return map;
           }
            map.put("updateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }
    @RequestMapping(value="/updateNumber",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object  updateNumber(User user) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            System.out.println("--------------->"+user.getUserId());
            System.out.println("--------------->"+user.getSjnumber());
            int num = userService.updateNumber(user);
            if (num>0){
                map.put("updateSuccess",true);
                return map;
            }
            map.put("updateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }
    @RequestMapping(value="/findUser",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object  findUser(String userId) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            System.out.println(userId);
           User u = userService.findById(userId);

                map.put("user",u);

            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }

}
