package com.xulifei.e.service.impl;

import com.xulifei.e.entity.Class;
import com.xulifei.e.entity.InviteCode;
import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.service.ClassService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/8/2.
 */
@Service("classService")
public class ClassServiceImpl extends BaseServiceImpl<Class> implements ClassService {

    @Override
    public Boolean addClass(Class c) throws Exception {
        int sum = classMapper.findSum(c.getUser().getUserId());

        int num = inviteCodeMapper.insert(c.getInviteCode());
              if (num>0){
                   c.setSerialNumber(sum+1);
                 num = classMapper.insert(c);
                if (num > 0){
                    return true;
                }
              }

        return false;
    }

    @Override
    public List<Class> findAllClassRoom(String userId) {
       List<Class> classRoomList =  classMapper.findAll(userId);
        return classRoomList;
    }

    @Override
    public List<Class> findAllArchiveClassRoom(Class c) {
        List<Class> archiveClassRoom = classMapper.findArchiveClassRoom(c);
        return archiveClassRoom;
    }

    @Override
    public int updateSerialNumber(String currentClassId, String preClassId) {
       int num =  classMapper.updateCurrentSerialNumber(currentClassId);
       int num2 =  classMapper.updatePreSerialNumber(preClassId);
        return num+num2;
    }

    @Override
    public int updateClassName(Class c ) {

        return classMapper.updateClassName(c);
    }

    @Override
    public int updateIsArchive(Class c) {
        return classMapper.updateIsArchive(c);
    }

    @Override
    public int resetCode(Map<String ,Object> map){

        return  inviteCodeMapper.resetCode(map);
    }
    @Override
    public int stopCode(Map<String ,Object> map) {

        return  inviteCodeMapper.stopCode(map);
    }
    @Override
    public int useCode(Map<String ,Object> map) {

        return  inviteCodeMapper.useCode(map);
    }

    @Override
    public List<Class>  joinClass(String userId, String code) {
        List<Class> cList = new ArrayList<Class>();

        cList =  classMapper.findByCode(code);
        if (cList.size()==1) {
//            判断当前班级是否是自己创建的
            System.out.println(userId+"------------------------------>"+cList.get(0).getUser().getUserId());
            String a = cList.get(0).getUser().getUserId();
            System.out.println(a);
            if (userId.equals( cList.get(0).getUser().getUserId())){
                return null;
            }
//            移动端成员数要手动加一
            classMapper.updateMemberByClassId(cList.get(0).getClassId());
            PersonalAttendanceInformationTable pinfo = new PersonalAttendanceInformationTable();
            pinfo.setClassId(cList.get(0).getClassId());
            pinfo.setUserId(userId);
            pinfo.setTotalNumberAttendance(cList.get(0).getAtotalCheckNumber());
            personalAttendanceInformationTableMapper.insert(pinfo);

        }
         if (cList.size() > 0) {
             for (Class cs : cList) {
                InviteCode inviteCode =  new InviteCode();
                 inviteCode.setCode(code);
                 cs.setInviteCode(inviteCode);
             }
         }
        return cList;
    }

    @Override
    public int joinClass2(String classId,int totalAttendenceNumber, String userId) throws  Exception {

        classMapper.updateMemberByClassId(classId);
        PersonalAttendanceInformationTable pinfo = new PersonalAttendanceInformationTable();
        pinfo.setClassId(classId);
        pinfo.setUserId(userId);
        pinfo.setTotalNumberAttendance(totalAttendenceNumber);
        personalAttendanceInformationTableMapper.insert(pinfo);
        return 0;
    }


}
