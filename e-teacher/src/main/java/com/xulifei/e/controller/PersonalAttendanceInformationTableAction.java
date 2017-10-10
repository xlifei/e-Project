package com.xulifei.e.controller;

import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.service.PersonalAttendanceInformationTableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/8/18.
 */
@Controller
@RequestMapping("/personalAttendanceInformationTable")
public class PersonalAttendanceInformationTableAction extends BaseAction {

    @Resource
    private PersonalAttendanceInformationTableService pinfoService;

    @RequestMapping(value="/deleteStudent",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object deleteStudent(PersonalAttendanceInformationTable p) {
        try {
            Map map=  new HashMap<String,Object>();
            int num = pinfoService.deleteStudent(p);

            if (num>1){
                map.put("deleteSuccess",true);
                return map;
            }
            map.put("deleteSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }

}
