package com.xulifei.e.controller;

import com.xulifei.e.entity.AttendanceRecord;
import com.xulifei.e.entity.Class;
import com.xulifei.e.service.AttendanceRecordService;
import com.xulifei.e.service.ClassService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by john on 2017/8/8.
 */
@Controller
@RequestMapping("/attendanceRecord")
public class AttendanceRecordAction {

    @Resource
    private AttendanceRecordService attendanceRecordService;
    @Resource
    private ClassService classService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true));
    }


    @RequestMapping(value="/createAttendance",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object createAttendance(AttendanceRecord attendanceRecord) {
        try {
            System.out.println(attendanceRecord.getLongitude()+"    "+attendanceRecord.getLatitude());
            Map<String,Object> map = new HashMap<String, Object>();
//            System.out.println("--------------------------------->>"+attendanceRecord.getCreationTime());
            int num = attendanceRecordService.createAttendance(attendanceRecord);

           if (num>1){
                map.put("isCreateSuccess",true);
               map.put("attendanceId",attendanceRecord.getAttendanceId());
           }
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }

    @RequestMapping(value="/getAttendanceForStudent",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getAttendanceForStudent(AttendanceRecord attendanceRecord) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
//            System.out.println("------------------------------->"+attendanceRecord.getAttendanceDetailList().get(0).getDetailUserId());
            List<AttendanceRecord> allRecord = attendanceRecordService.findAllRecordForStudent(attendanceRecord);
//            System.out.println("-------------------------->"+allRecord.get(0).getCreationTime());

                map.put("attendanceRecordList",allRecord);

            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    @RequestMapping(value="/getAttendanceForStudentFinshFalse",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getAttendanceForStudentFinshFalse(AttendanceRecord attendanceRecord) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            AttendanceRecord aR = attendanceRecordService.findAllRecordForStudentFinshFalse(attendanceRecord);
            Class c = classService.findByClassId(attendanceRecord.getClassId());
//            System.out.println(aR.getAttendanceDetailList().get(0).getAttendanceDetailId());
//            System.out.println(aR.getAttendanceId());
//            System.out.println(c.getClassName());
            map.put("attendanceRecord",aR);
            map.put("class",c);

            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    @RequestMapping(value="/getAttendanceForTeacher",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getAttendanceForTeacher(AttendanceRecord attendanceRecord) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();

            List<AttendanceRecord> allRecord = attendanceRecordService.findAllRecordForTeacher(attendanceRecord);
//            System.out.println(allRecord.get(2).getAttendanceName()+" ----------------"+allRecord.get(2).getIsFinsh());
            map.put("attendanceRecordList",allRecord);

            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    @RequestMapping(value="/getAttendanceForTeacherFinshFalse",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getAttendanceForTeacherFinshFalse(AttendanceRecord attendanceRecord) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();

            AttendanceRecord aR = attendanceRecordService.findAllRecordForTeacherFinshFalse(attendanceRecord);
            map.put("attendanceRecord",aR);

            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }



    @RequestMapping(value="/saveAttendance",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object saveAttendance(AttendanceRecord attendanceRecord) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
         int num =  attendanceRecordService.updateRecorAndDetail(attendanceRecord);
       if (num > 2){
        map.put("isSaveSuccess",true);
        return map;
       }
       map.put("isSaveSuccess",false);
            return map;

        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }


    @RequestMapping(value="/deleteAttendance",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object deleteAttendance(AttendanceRecord attendanceRecord) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
//            System.out.println(attendanceRecord.getAttendanceDetailList().get(0));
            int num =  attendanceRecordService.deleteAttendance(attendanceRecord);
            if (num > 2){
                map.put("deleteSuccess",true);
                return map;
            }
            map.put("deleteSuccess",false);
            return map;

        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    @RequestMapping(value="/abandonAttendance",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object abandonAttendance(AttendanceRecord attendanceRecord) {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
//            System.out.println(attendanceRecord.getAttendanceDetailList().get(0));
            int num =  attendanceRecordService.abandonAttendance(attendanceRecord);
            if (num > 0){
                map.put("abandonSuccess",true);
                return map;
            }
            map.put("abandonSuccess",false);
            return map;

        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }
    @RequestMapping(value="/updateAttendanceName",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object updateAttendanceName(AttendanceRecord attendanceRecord) {
        try {

            Map<String,Object> map = new HashMap<String, Object>();
          int num = attendanceRecordService.updateAttendanceName(attendanceRecord);
            if (num>0){
                map.put("updateSuccess",true);
             return  map;
            }
            map.put("updateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }

}
