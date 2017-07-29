package com.xulifei.e.controller;

/**
 * Created by john on 2017/7/28.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

@Controller
@RequestMapping("/base")
public class BaseAction {
    @Resource
    ServletContext application;

//    这样你要请求login.jsp页面, 就访问goURL/login/login.action（你mvc的后缀），就会转到login.jsp，
//    这就是根据你路径来返回页面的
    //方法参数folder通过@PathVariable指定其值可以从@RequestMapping的{folder}获取，同理file也一样
    @RequestMapping("/goURL/{folder}/{file}")
    public String goURL(@PathVariable String folder,@PathVariable String file) {
        System.out.println("goURL.folder|file===" + folder+"/"+file);
        return "forward:/WEB-INF/"+folder+"/"+file+".jsp";
    }

}
