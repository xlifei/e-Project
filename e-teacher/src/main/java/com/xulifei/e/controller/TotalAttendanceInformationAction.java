package com.xulifei.e.controller;

import com.xulifei.e.entity.TotalAttendanceInfomation;
import com.xulifei.e.service.TotalAttendanceInfomationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/8/7.
 */
@Controller
@RequestMapping("/totalAttendanceInfomation")
public class TotalAttendanceInformationAction extends BaseAction {

     @Resource
    private TotalAttendanceInfomationService totalAttendanceInfomationService;

    @RequestMapping(value="/getTotalAttendanceInfomation",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getTotalAttendanceInfomation(String classId) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationService.findByClassId(classId);
            map.put("totalAttendanceInfomation",totalAttendanceInfomation);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }


}
