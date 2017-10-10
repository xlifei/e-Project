package com.xulifei.e.service.impl;

import com.xulifei.e.entity.AttendanceDetail;
import com.xulifei.e.entity.AttendanceRecord;
import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.entity.TotalAttendanceInfomation;
import com.xulifei.e.service.AttendanceRecordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/8/8.
 */
@Service("attendanceRecordService")
public class AttendanceRecordServiceImpl extends BaseServiceImpl<AttendanceRecord> implements AttendanceRecordService {

    @Override
    public List<AttendanceRecord> findAllRecordForStudent(AttendanceRecord attendanceRecord) throws Exception {

       List<AttendanceRecord> attendanceRecordList = attendanceRecordMapper.findAllRecordForStudent(attendanceRecord);
        return attendanceRecordList;
    }

    @Override
    public List<AttendanceRecord> findAllRecordForTeacher(AttendanceRecord attendanceRecord) throws  Exception {
        List<AttendanceRecord> attendanceRecordList = attendanceRecordMapper.findAllRecordForTeacher(attendanceRecord);
        return attendanceRecordList;
    }

    @Override
    public AttendanceRecord findAllRecordForStudentFinshFalse(AttendanceRecord attendanceRecord)throws Exception {
        return attendanceRecordMapper.findAllRecordForStudentFinshFalse(attendanceRecord);
    }

    @Override
    public AttendanceRecord findAllRecordForTeacherFinshFalse(AttendanceRecord attendanceRecord) throws  Exception{
        return attendanceRecordMapper.findAllRecordForTeacherFinshFalse(attendanceRecord);
    }


//这里更新考勤详细需要更新个人考勤统计和总考勤统计两个表的数据。同时
//    老师进行手动更改考勤状态时也要实时更改个人考勤统计和总考勤统计两个表的数据。
//
    @Override
    public int updateRecorAndDetail(AttendanceRecord attendanceRecord) throws Exception {
//        更新当前考勤已经完成
        int num = 0;
        num = attendanceRecordMapper.updateIsFinshByAttendance(attendanceRecord.getAttendanceId());

        int num2 = 0;
//        查询出所有此次考勤的考勤详细记录
        List<AttendanceDetail> attendanceDetailList = attendanceDetailMapper.findDetailsByAttendanceId(attendanceRecord);
//        封装未完成此次考勤的详细记录id
        List<String> attendanceDetailIdsUnFinsh = new ArrayList<String>();
//封装完成此次考勤的详细记录id
        List<String > attendanceDetailIdsFinsh = new ArrayList<String>();
//        封装未完成此次考勤的user_id
        List<String > detailUserIdUnFinsh = new ArrayList<String>();

//        封装完成此次考勤的user_id
        List<String> detailUserIdFinsh = new ArrayList<String>();

        for (AttendanceDetail attendanceDetail : attendanceDetailList) {
            if (attendanceDetail.isFinsh()){
                attendanceDetailIdsFinsh.add(attendanceDetail.getAttendanceDetailId());
                detailUserIdFinsh.add(attendanceDetail.getDetailUserId());
            }else {
                attendanceDetailIdsUnFinsh.add(attendanceDetail.getAttendanceDetailId());
                detailUserIdUnFinsh.add(attendanceDetail.getDetailUserId());
            }
    }

        if (detailUserIdUnFinsh.size()>0) {
//            更新考勤详细sis_fish为true，考勤状态status默认为旷课，不用改
            attendanceDetailMapper.updateList(attendanceDetailIdsUnFinsh);
            //        更新考勤记录的旷到人数以及旷到的详细记录的sis_finsh为true
            attendanceRecord.setRkuangNumber(attendanceDetailIdsUnFinsh.size());
             attendanceRecordMapper.updateKuangByAttendance(attendanceRecord);

            //        查询所有未完成的个人统计并加一（总考勤数和旷到数）
            Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put("classId",attendanceRecord.getClassId());
            map2.put("userIds",detailUserIdUnFinsh);
            List<PersonalAttendanceInformationTable> psUnFinsh = personalAttendanceInformationTableMapper.selectList(map2);
            if (psUnFinsh.size()>0){
                for (PersonalAttendanceInformationTable pai: psUnFinsh) {
                    pai.setTotalNumberAttendance(pai.getTotalNumberAttendance()+1);
                    pai.setKuangNumber(pai.getKuangNumber()+1);
                    personalAttendanceInformationTableMapper.updateUnFinshByPersonAttendanceInformationId(pai);
                }
            }

        }



//        更新所有完成的考勤信息的个人统计信息
//        更新个人考勤统计,完成加一

        //        查询所有完成的个人统计并加一（总考勤数和出勤数）
if (detailUserIdFinsh.size()>0) {

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("classId", attendanceRecord.getClassId());
    map.put("userIds", detailUserIdFinsh);
    List<PersonalAttendanceInformationTable> psFinsh = personalAttendanceInformationTableMapper.selectList(map);
    if (psFinsh.size() > 0) {
        for (PersonalAttendanceInformationTable pai : psFinsh) {
            pai.setTotalNumberAttendance(pai.getTotalNumberAttendance() + 1);
            pai.setAttendanceNumber(pai.getAttendanceNumber() + 1);
            personalAttendanceInformationTableMapper.updateFinshByPersonAttendanceInformationId(pai);

        }
    }
}

//        更新班级的总考勤记录
       int num3 = classMapper.updateAtotalCheckNumberPlusByClassId(attendanceRecord.getClassId());

//        更新总考勤统计

//         查询记录，在原有基础上加，总考勤记录早在课堂创建时就已经创建。
        List<TotalAttendanceInfomation> totalAttendanceInfomations = totalAttendanceInfomationMapper.selectList();
       if (totalAttendanceInfomations.size()>0){
          TotalAttendanceInfomation totalAttendanceInfomation = new TotalAttendanceInfomation();
           totalAttendanceInfomation.setTotalAttendanceId(totalAttendanceInfomations.get(0).getTotalAttendanceId());
           totalAttendanceInfomation.setAtotalCheckNumber(totalAttendanceInfomations.get(0).getAtotalCheckNumber()+1);
           totalAttendanceInfomation.setTotalAttendanceNumber(totalAttendanceInfomations.get(0).getTotalAttendanceNumber()+detailUserIdFinsh.size());
           totalAttendanceInfomation.setTotalKuangNumber(totalAttendanceInfomations.get(0).getTotalKuangNumber()+detailUserIdUnFinsh.size());

           num2 = totalAttendanceInfomationMapper.update(totalAttendanceInfomation);

       }


        return num+num2+num3;
    }

    @Override
    public int deleteAttendance(AttendanceRecord attendanceRecord) throws Exception {


//        查询出所有此次考勤的考勤详细记录
        List<AttendanceDetail> attendanceDetailIds =  attendanceDetailMapper.findAllByAttendanceId(attendanceRecord);
        int num = 0;
   if (attendanceDetailIds.size() > 0) {
//      按id批量删除考勤详细记录
     num = attendanceDetailMapper.deleteList(attendanceDetailIds);
       System.out.println("------" + attendanceDetailIds.get(0) + "----");
   }
//        删除考勤记录
        int num2 = attendanceRecordMapper.delete(attendanceRecord);
       if (attendanceDetailIds.size() > 0) {
           //        更改个人考勤统计

           for (AttendanceDetail a : attendanceDetailIds) {
               if (a.getAttendanceStatus().equals("出勤")) {
                   //        获取本次考勤状态为出勤的记录的用户id

                   PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
                   p.setClassId(attendanceRecord.getClassId());
                   p.setUserId(a.getDetailUserId());
                   p = personalAttendanceInformationTableMapper.selectByPrimaryKey(p);
                   p.setTotalNumberAttendance(p.getTotalNumberAttendance() - 1);
                   p.setAttendanceNumber(p.getAttendanceNumber() - 1);
                   personalAttendanceInformationTableMapper.updateByIdForchuqin(p);
               } else if (a.getAttendanceStatus().equals("迟到")) {
//        获取本次考勤状态为迟到的记录的用户id
                   PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
                   p.setClassId(attendanceRecord.getClassId());
                   p.setUserId(a.getDetailUserId());
                   p = personalAttendanceInformationTableMapper.selectByPrimaryKey(p);
                   p.setTotalNumberAttendance(p.getTotalNumberAttendance() - 1);
                   p.setLateNumber(p.getLateNumber() - 1);
                   personalAttendanceInformationTableMapper.updateByIdForchidao(p);
               } else if (a.getAttendanceStatus().equals("旷课")) {
                   //        获取本次考勤状态为旷课的记录的用户id
                   PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
                   p.setClassId(attendanceRecord.getClassId());
                   p.setUserId(a.getDetailUserId());
                   p = personalAttendanceInformationTableMapper.selectByPrimaryKey(p);
                   p.setTotalNumberAttendance(p.getTotalNumberAttendance() - 1);
                   p.setKuangNumber(p.getKuangNumber() - 1);
                   personalAttendanceInformationTableMapper.updateByIdForkuang(p);
               } else if (a.getAttendanceStatus().equals("请假")) {
                   //        获取本次考勤状态为请假的记录的用户id
                   PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
                   p.setClassId(attendanceRecord.getClassId());
                   p.setUserId(a.getDetailUserId());
                   p = personalAttendanceInformationTableMapper.selectByPrimaryKey(p);
                   p.setTotalNumberAttendance(p.getTotalNumberAttendance() - 1);
                   p.setLeaveNumber(p.getLeaveNumber());
                   personalAttendanceInformationTableMapper.updateByIdForqingjia(p);
               }


           }
       }
//      更改总考勤统计
        TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectByPrimaryKey(classMapper.selectTotalAttendanceIdByClassId(attendanceRecord.getClassId()));
        totalAttendanceInfomation.setAtotalCheckNumber(totalAttendanceInfomation.getAtotalCheckNumber()-1);
        totalAttendanceInfomation.setTotalAttendanceNumber(totalAttendanceInfomation.getTotalAttendanceNumber()-attendanceRecord.getRattendanceNumber());
        totalAttendanceInfomation.setTotalLateNumber(totalAttendanceInfomation.getTotalLateNumber()-attendanceRecord.getRlateNumber());
        totalAttendanceInfomation.setTotalKuangNumber(totalAttendanceInfomation.getTotalKuangNumber()-attendanceRecord.getRkuangNumber());
        totalAttendanceInfomation.setTotalLeaveNumber(totalAttendanceInfomation.getTotalLeaveNumber()-attendanceRecord.getRleaveNumber());

      int num3 =   totalAttendanceInfomationMapper.updateByPrimaryKey(totalAttendanceInfomation);

//更新班级的考勤总数
      int num4 =  classMapper.updateAtotalCheckNumberByClassId(attendanceRecord.getClassId());


        return num+num2+num3+num4;
    }

    @Override
    public int abandonAttendance(AttendanceRecord attendanceRecord) throws Exception {


//        查询出所有此次考勤的考勤详细记录
        List<AttendanceDetail> attendanceDetailIds =  attendanceDetailMapper.findAllByAttendanceId(attendanceRecord);
        int num = 0;
        if (attendanceDetailIds.size() > 0) {
//      按id批量删除考勤详细记录
            num = attendanceDetailMapper.deleteList(attendanceDetailIds);
            System.out.println("------" + attendanceDetailIds.get(0) + "----");
        }
//        删除考勤记录
        int num2 = attendanceRecordMapper.delete(attendanceRecord);


        return num+num2;
    }
    @Override
    public int createAttendance(AttendanceRecord attendanceRecord) throws Exception {
         List<String> userIds = personalAttendanceInformationTableMapper.findAllByClassId(attendanceRecord.getClassId());
        attendanceRecord.setAttendanceId(attendanceRecord.getAttendanceId());
       int num = attendanceRecordMapper.insert(attendanceRecord);

         if (userIds.size()>0) {
             for (String id : userIds) {
                 AttendanceDetail attendanceDetail = new AttendanceDetail();
                 attendanceDetail.setAttendanceId(attendanceRecord.getAttendanceId());
                 attendanceDetail.setDetailUserId(id);
                 int num2 =attendanceDetailMapper.insert(attendanceDetail);
               num = num+num2;
             }
         }

        return num;
    }

    @Override
    public int updateAttendanceName(AttendanceRecord attendanceRecord) throws Exception {
        return attendanceRecordMapper.updateAttendanceName(attendanceRecord);
    }
}
