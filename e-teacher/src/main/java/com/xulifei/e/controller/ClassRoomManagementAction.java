package com.xulifei.e.controller;

import com.xulifei.e.entity.Class;
import com.xulifei.e.entity.InviteCode;
import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.service.ClassService;
import com.xulifei.e.service.InviteCodeService;
import com.xulifei.e.service.PersonalAttendanceInformationTableService;
import com.xulifei.e.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/8/2.
 */
@Controller
@RequestMapping("/Class")
public class ClassRoomManagementAction extends  BaseAction {
    @Resource
    private ClassService classService;
    @Resource
    private InviteCodeService inviteCodeService;
 @Resource
  private UserService userService;
    @Resource
    private PersonalAttendanceInformationTableService pinfoService;

    @RequestMapping(value="/addClass",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object addClass(Class c) {
        try {
            Map map=  new HashMap<String,Object>();
            System.out.println("yes----------------------------------------->");
            System.out.println(c.getClassName());
            System.out.println("codeidprint"+c.getInviteCode());
            System.out.println("codeprint"+c.getInviteCode().getCode());
            System.out.println("statusprint"+c.getInviteCode().getStatus());
            System.out.println("yes----------------------------------------->");
            Boolean isCreateSuccess = classService.addClass(c);


      if (isCreateSuccess){

          map.put("isCreateSuccess",isCreateSuccess);
//          c.setClassId(c.getClassId());

          map.put("class",c);
          return map;
      }
            map.put("isCreateSuccess",isCreateSuccess);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }



    }
    @RequestMapping(value="/removeClass",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object removeClass(Class c) {
        try {
            Map map = new HashMap<String, Object>();
            int num = classService.delete(c);
            if (num > 0) {
                map.put("isDeleteSuccess", true);
                return map;
            }
              map.put("isDeleteSuccess",false);
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping(value="/quitClass",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object removeClass(PersonalAttendanceInformationTable pinfo) {
        try {
            Map map = new HashMap<String, Object>();
            int num = pinfoService.delete(pinfo);
            if (num > 0) {
                map.put("isQuitSuccess", true);
                return map;
            }
            map.put("isQuitSuccess",false);
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        @RequestMapping(value="/initClassRoomList",produces="application/json;charset=UTF-8")
        @ResponseBody
        public Object initClassRoomList(String userId) {
            try {
                Map map=  new HashMap<String,Object>();
              List<Class> classRoomList = classService.findAllClassRoom(userId);
               map.put("classRoomList",classRoomList);
                return map;
            } catch (Exception e) {
                throw  new RuntimeException(e);
            }


    }

    @RequestMapping(value="/initClassArchive",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object initClassArchive(Class c) {
        try {
            Map map=  new HashMap<String,Object>();
            List<Class> classArchiveList = classService.findAllArchiveClassRoom(c);
            map.put("classArchiveList",classArchiveList);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }
    @RequestMapping(value="/editClass",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object editClass(Class c) {
        try {
            Map map=  new HashMap<String,Object>();
            int num = classService.updateClassName(c);
            if (num>0){
                map.put("isEditSuccess",true);
                return map;
            }
            map.put("isEditSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }
    @RequestMapping(value="/updateIsArchive",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object updateIsArchive(Class c) {
        try {
            Map map=  new HashMap<String,Object>();
            int num = classService.updateIsArchive(c);
            if (num>0){
                map.put("isUpdateSuccess",true);
                return map;
            }
            map.put("isUpdateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }

    @RequestMapping(value="/updateSerialNumber",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object updateSerialNumber(String currentClassId,String preClassId) {
        try {
            Map map=  new HashMap<String,Object>();
            int num = classService.updateSerialNumber(currentClassId,preClassId);
            if (num>1){
                map.put("isUpdateSuccess",true);
                return map;
            }
            map.put("isUpdateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }

    @RequestMapping(value="/resetCode",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object resetCode(String classId,String newCode) {
        try {
            Map map=  new HashMap<String,Object>();
            map.put("newCode",newCode);
            map.put("classId",classId);
            int num = classService.resetCode(map);
            System.out.println("resetCode---------------->"+num);
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
    @RequestMapping(value="/stopCode",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object stopCode(String classId) {
        try {
            Map map=  new HashMap<String,Object>();
            map.put("status","STOP_USING");
            map.put("classId",classId);
            int num = classService.stopCode(map);
            System.out.println("stopCode---------------->"+num);
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
    @RequestMapping(value="/useCode",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object useCode(String classId) {
        try {
            Map map=  new HashMap<String,Object>();
            map.put("status","START_USING");
            map.put("classId",classId);
            int num = classService.useCode(map);
            System.out.println("useCode---------------->"+num);
            if (num>0){
               InviteCode inviteCode = inviteCodeService.findCodeByClassId(classId);
                map.put("updateSuccess",true);
                map.put("inviteCode",inviteCode);
                return map;
            }
            map.put("updateSuccess",false);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }





    @RequestMapping(value="/joinClass",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object joinClass(String userId,String code) {
        try {
            Map map=  new HashMap<String,Object>();
              List<Class> cList = classService.joinClass(userId,code);
             map.put("classList",cList);
            return map;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }
    @RequestMapping(value="/joinClassTwo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public void joinClassTwo(String classId,int totalAttendenceNumber,String userId) {
        try {
            classService.joinClass2(classId,totalAttendenceNumber,userId);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }


    }



}
