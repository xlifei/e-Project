package com.xulifei.e.controller;

import com.xulifei.e.entity.AttendanceDetail;
import com.xulifei.e.entity.AttendanceRecord;
import com.xulifei.e.service.AttendanceDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/8/11.
 */
@Controller
@RequestMapping("/attendanceDetail")
public class AttendanceDetailAction extends  BaseAction{
    @Resource
    private AttendanceDetailService attendanceDetailService;

    @RequestMapping(value="/updateAttendanceDetailAndRecord",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object updateAttendanceDetailAndRecord(String attendanceId,String attendanceDetailId,String attendanceStatus,Boolean isFinsh) {
        try {

            Map<String,Object> map = new HashMap<String, Object>();
            AttendanceRecord attendanceRecord =  new AttendanceRecord();
            attendanceRecord.setAttendanceId(attendanceId);
            AttendanceDetail attendanceDetail = new AttendanceDetail();
            attendanceDetail.setAttendanceDetailId(attendanceDetailId);
            attendanceDetail.setAttendanceStatus(attendanceStatus);
            attendanceDetail.setFinsh(isFinsh);
            int num = attendanceDetailService.updateAttendanceDetailAndRecord(attendanceRecord,attendanceDetail);
        if (num>1){
             map.put("updateSuccess",true);
         return map;
        }
       map.put("updateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    @RequestMapping(value="/getAttendanceDetailList",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getAttendanceDetailList(AttendanceDetail attendanceDetail) {
        try {

            Map<String,Object> map = new HashMap<String, Object>();
            List<AttendanceDetail> attendanceDetails = attendanceDetailService.selectList(attendanceDetail);
            map.put("attendanceDetails",attendanceDetails);
          return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }

    @RequestMapping(value="/changeStatus",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object changeStatus(AttendanceRecord attendanceRecord) {
        try {

            Map<String,Object> map = new HashMap<String, Object>();
            int num = attendanceDetailService.changeStatus(attendanceRecord);
            if (num>3){
                map.put("updateSuccess",true);
          return map;
            }
            map.put("updateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }


    @RequestMapping(value="/searchAttendanceDetail",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object  searchAttendanceDetail(String attendanceId,String searchKeyValue) {
        try {

            Map<String,Object> map = new HashMap<String, Object>();

            List<AttendanceDetail> attendanceDetails  = attendanceDetailService.findListByKeyValue(attendanceId,searchKeyValue);
            map.put("attendanceDetails",attendanceDetails);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }

}
