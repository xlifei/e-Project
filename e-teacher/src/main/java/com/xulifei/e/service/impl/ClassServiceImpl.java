package com.xulifei.e.service.impl;

import com.xulifei.e.entity.*;
import com.xulifei.e.entity.Class;
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
        int num2 = 0;
        int num3 = 0;
                   c.setSerialNumber(sum+1);

                  TotalAttendanceInfomation totalAttendanceInfomation = new TotalAttendanceInfomation();
                  totalAttendanceInfomation.setAtotalCheckNumber(0);
                  totalAttendanceInfomation.setTotalAttendanceNumber(0);
                  totalAttendanceInfomation.setTotalKuangNumber(0);
                  totalAttendanceInfomation.setTotalLateNumber(0);
                  totalAttendanceInfomation.setTotalLeaveNumber(0);
                  num3 = totalAttendanceInfomationMapper.insert(totalAttendanceInfomation);
                 c.setTotalAttendanceInfomation(totalAttendanceInfomation);
                 num2 = classMapper.insert(c);

         if ((num+num2+num3)>2){
             return true;
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
//            添加个人考勤信息表
            classMapper.updateMemberByClassId(cList.get(0).getClassId());
            PersonalAttendanceInformationTable pinfo = new PersonalAttendanceInformationTable();
            String psId = pinfo.getPersonAttendanceInformationId();
            pinfo.setPersonAttendanceInformationId(psId);
            pinfo.setClassId(cList.get(0).getClassId());
            pinfo.setUserId(userId);
            pinfo.setTotalNumberAttendance(cList.get(0).getAtotalCheckNumber());
            personalAttendanceInformationTableMapper.insert(pinfo);
//            统计老师已经完成考勤的新加入用户的旷到次数
            int kuangNum = 0;
//如果用户加进来之前attendance_record已经有了记录，那么将这些记录的id和用户id插入attendance_detail表中
            List<AttendanceRecord> attendanceRecordList = attendanceRecordMapper.findAllRecordByClassId(cList.get(0).getClassId());
            if (attendanceRecordList.size()>0) {

                for (AttendanceRecord attendanceRecord : attendanceRecordList) {
                    if (attendanceRecord.getIsFinsh()) {
                        AttendanceDetail attendanceDetail = new AttendanceDetail();
                        attendanceDetail.setAttendanceId(attendanceRecord.getAttendanceId());
                        attendanceDetail.setDetailUserId(userId);
                       attendanceDetail.setFinsh(true);
                        attendanceDetailMapper.insert(attendanceDetail);
                        kuangNum++;
//                        更新考勤每天考勤记录的旷到数加一
                        attendanceRecord.setRkuangNumber(attendanceRecord.getRkuangNumber()+1);
                        attendanceRecordMapper.updateKuangByAttendance(attendanceRecord);
                    }else{
                        AttendanceDetail attendanceDetail = new AttendanceDetail();
                        attendanceDetail.setAttendanceId(attendanceRecord.getAttendanceId());
                        attendanceDetail.setDetailUserId(userId);
                        attendanceDetailMapper.insert(attendanceDetail);
                    }

                }
                //            最后修改新加入的学生的旷到次数。
                PersonalAttendanceInformationTable p =   new PersonalAttendanceInformationTable();
                p.setPersonAttendanceInformationId(psId);
                p.setKuangNumber(kuangNum);
                personalAttendanceInformationTableMapper.updateUnFinshByPersonAttendanceInformationId(p);

//            还要修改总考勤统计表中的旷到次数
                TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectKuangNumberById(cList.get(0).getTotalAttendanceInfomation().getTotalAttendanceId());

                totalAttendanceInfomation.setTotalKuangNumber(totalAttendanceInfomation.getTotalKuangNumber()+kuangNum);
                totalAttendanceInfomationMapper.update(totalAttendanceInfomation);

            }


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
        String psId = pinfo.getPersonAttendanceInformationId();
        pinfo.setPersonAttendanceInformationId(psId);
        pinfo.setClassId(classId);
        pinfo.setUserId(userId);
        pinfo.setTotalNumberAttendance(totalAttendenceNumber);
        personalAttendanceInformationTableMapper.insert(pinfo);
        //            统计老师已经完成考勤的新加入用户的旷到次数
        int kuangNum = 0;
        //如果用户加进来之前attendance_record已经有了记录，那么将这些记录的id和用户id插入attendance_detail表中
        List<AttendanceRecord> attendanceRecordList = attendanceRecordMapper.findAllRecordByClassId(classId);
        if (attendanceRecordList.size()>0) {
            for (AttendanceRecord attendanceRecord : attendanceRecordList) {
                if (attendanceRecord.getIsFinsh()) {
                    AttendanceDetail attendanceDetail = new AttendanceDetail();
                    attendanceDetail.setAttendanceId(attendanceRecord.getAttendanceId());
                    attendanceDetail.setDetailUserId(userId);
                    attendanceDetail.setFinsh(true);
                    attendanceDetailMapper.insert(attendanceDetail);
                    kuangNum++;
                }else {
                    AttendanceDetail attendanceDetail = new AttendanceDetail();
                    attendanceDetail.setAttendanceId(attendanceRecord.getAttendanceId());
                    attendanceDetail.setDetailUserId(userId);
                    attendanceDetailMapper.insert(attendanceDetail);
                }
            }
            //            最后修改新加入的学生的在本班级的旷到次数。
            PersonalAttendanceInformationTable p =   new PersonalAttendanceInformationTable();
            p.setPersonAttendanceInformationId(psId);
            p.setKuangNumber(kuangNum);
            personalAttendanceInformationTableMapper.updateUnFinshByPersonAttendanceInformationId(p);

//            还要修改总考勤统计表中的旷到次数
            String totalAttendanceId = classMapper.selectTotalAttendanceIdByClassId(classId);
            TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectKuangNumberById(totalAttendanceId);

            totalAttendanceInfomation.setTotalKuangNumber(totalAttendanceInfomation.getTotalKuangNumber()+kuangNum);
            totalAttendanceInfomationMapper.update(totalAttendanceInfomation);


        }
        return 0;
    }

    @Override
    public int quitClass(PersonalAttendanceInformationTable personalAttendanceInformationTable) throws Exception {
        PersonalAttendanceInformationTable p = personalAttendanceInformationTableMapper.findByClassIdAndUserId(personalAttendanceInformationTable);
        //             更新班级成员数
         int num =  classMapper.updateMemberRedByClassId(personalAttendanceInformationTable.getClassId());
        int num2 =  personalAttendanceInformationTableMapper.delete(personalAttendanceInformationTable);

        List<AttendanceRecord> allRecordByClassId = attendanceRecordMapper.findAllRecordByClassId(personalAttendanceInformationTable.getClassId());
         if (allRecordByClassId.size()>0) {
             for (AttendanceRecord attendanceRecord : allRecordByClassId) {
//更新每一条考勤记录
            updateRecord(attendanceRecord,personalAttendanceInformationTable.getUserId());

//                 删除考勤详细
                 AttendanceDetail attendanceDetail = new AttendanceDetail();
               attendanceDetail.setAttendanceId(attendanceRecord.getAttendanceId());
                 attendanceDetail.setDetailUserId(personalAttendanceInformationTable.getUserId());
               attendanceDetailMapper.delete(attendanceDetail);


             }

//             更新总考勤记录
             String pId = classMapper.selectTotalAttendanceIdByClassId(personalAttendanceInformationTable.getClassId());
             TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectByPrimaryKey(pId);
            totalAttendanceInfomation.setTotalAttendanceNumber(totalAttendanceInfomation.getTotalAttendanceNumber()-p.getAttendanceNumber());
             totalAttendanceInfomation.setTotalKuangNumber(totalAttendanceInfomation.getTotalKuangNumber()-p.getKuangNumber());
           totalAttendanceInfomation.setTotalLateNumber(totalAttendanceInfomation.getTotalLateNumber()-p.getLateNumber());
             totalAttendanceInfomation.setTotalLeaveNumber(totalAttendanceInfomation.getTotalLeaveNumber()-p.getLeaveNumber());
             totalAttendanceInfomationMapper.updateByPrimaryKey(totalAttendanceInfomation);

         }
        return num+num2;
    }

    public void updateRecord(AttendanceRecord attend,String userId){

        AttendanceDetail attendanceDetail =   new AttendanceDetail();
       attendanceDetail.setAttendanceId(attend.getAttendanceId());
       attendanceDetail.setDetailUserId(userId);
      AttendanceDetail a =  attendanceDetailMapper.findByAttendanceIdAndUserId(attendanceDetail);
       if (a.getAttendanceStatus().equals("旷到")){
          AttendanceRecord attendanceRecord = new AttendanceRecord();
                 attendanceRecord.setAttendanceId(attend.getAttendanceId());
                 attendanceRecord.setRkuangNumber(attend.getRkuangNumber()-1);
               attendanceRecordMapper.updateKuangByAttendance(attendanceRecord);
       }else if (a.getAttendanceStatus().equals("出勤")){
           AttendanceRecord attendanceRecord = new AttendanceRecord();
           attendanceRecord.setAttendanceId(attend.getAttendanceId());
           attendanceRecord.setRattendanceNumber(attend.getRattendanceNumber()-1);
           attendanceRecordMapper.updateRattendanceByAttendance(attendanceRecord);
       }else if (a.getAttendanceStatus().equals("迟到")){
           AttendanceRecord attendanceRecord = new AttendanceRecord();
           attendanceRecord.setAttendanceId(attend.getAttendanceId());
           attendanceRecord.setRlateNumber(attend.getRlateNumber()-1);
           attendanceRecordMapper.updateRlateByAttendance(attendanceRecord);
       }else if (a.getAttendanceStatus().equals("请假")){
           AttendanceRecord attendanceRecord = new AttendanceRecord();
          attendanceRecord.setAttendanceId(attend.getAttendanceId());
           attendanceRecord.setRleaveNumber(attend.getRleaveNumber()-1);
           attendanceRecordMapper.updateRleaveByAttendance(attendanceRecord);
       }


    }
    @Override
    public int removeClass(Class c) {

        int num = personalAttendanceInformationTableMapper.deleteAllByClassId(c.getClassId());
        int  num2 =0 ;
        int num3 = 0;
        int num4 = 0;
        int num5 = 0;
        List<AttendanceRecord> allRecordByClassId = attendanceRecordMapper.findAllRecordByClassId(c.getClassId());
        List<String> ids = new ArrayList<String>();
        if (allRecordByClassId.size()>0) {

            for (AttendanceRecord attendanceRecord : allRecordByClassId) {
            ids.add(attendanceRecord.getAttendanceId());
            }
            num2 = attendanceDetailMapper.deleteListByArgString(ids);
            num3 =  attendanceRecordMapper.deleteListByArgString(ids);

        }
        String totalAttendanceId = classMapper.selectTotalAttendanceIdByClassId(c.getClassId());
        //        删除班级
        num5 = classMapper.delete(c);

        //删除总考勤记录
        num4 = totalAttendanceInfomationMapper.deleteByPrimaryKey(totalAttendanceId);

        return num+num2+num3+num4+num5;
    }

    @Override
    public Class findByClassId(String classId) {
        return classMapper.selectByPrimaryKey(classId);
    }

    @Override
    public Class findAttendanceNumber(Class c) throws Exception {
        return classMapper.findAttendanceNumber(c);
    }

    @Override
    public int findMemberByClassId(String classId) throws Exception {
        return classMapper.findMemberByClassId(classId);
    }

}
