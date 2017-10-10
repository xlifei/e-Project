package com.xulifei.e.service.impl;

import com.xulifei.e.entity.AttendanceRecord;
import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.entity.TotalAttendanceInfomation;
import com.xulifei.e.service.PersonalAttendanceInformationTableService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2017/8/7.
 */
@Service("pinfoService")
public class PersonalAttendanceInformationTableServiceImpl extends BaseServiceImpl<PersonalAttendanceInformationTable> implements PersonalAttendanceInformationTableService {


    @Override
    public int deleteStudent(PersonalAttendanceInformationTable p) throws Exception {

         int num = 0;
         int num2 = 0;

        List<AttendanceRecord> records = attendanceRecordMapper.findAllRecordByClassIdAndUserId(p);
        if (records.size()>0) {

//查询当前班级下的个人考勤记录并修改总考勤记录
            PersonalAttendanceInformationTable personAttend = personalAttendanceInformationTableMapper.findByClassIdAndUserId(p);
            TotalAttendanceInfomation t = totalAttendanceInfomationMapper.findByClassId(p.getClassId());
            t.setTotalAttendanceNumber(t.getTotalAttendanceNumber() - personAttend.getAttendanceNumber());
            t.setTotalLateNumber(t.getTotalLateNumber() - personAttend.getLateNumber());
            t.setTotalKuangNumber(t.getTotalKuangNumber() - personAttend.getKuangNumber());
            t.setTotalLeaveNumber(t.getTotalLeaveNumber() - personAttend.getLeaveNumber());
           totalAttendanceInfomationMapper.update(t);

//        删除当前班级下的个人考勤记录
         num =   personalAttendanceInformationTableMapper.deleteByClassIdAndUserId(p);


//        修改当前班级下的每条考勤记录的考勤情况


            List<String> ids = new ArrayList<String>();
            for (AttendanceRecord a : records) {
                ids.add(a.getAttendanceDetailList().get(0).getAttendanceDetailId());
                if (a.getAttendanceDetailList().get(0).getAttendanceStatus().equals("出勤")) {
                    a.setRattendanceNumber(a.getRattendanceNumber() - 1);
                    attendanceRecordMapper.updateRattendanceByAttendance(a);
                } else if (a.getAttendanceDetailList().get(0).getAttendanceStatus().equals("迟到")) {
                    a.setRlateNumber(a.getRlateNumber() - 1);
                    attendanceRecordMapper.updateRlateByAttendance(a);
                } else if (a.getAttendanceDetailList().get(0).getAttendanceStatus().equals("旷课")) {
                    a.setRkuangNumber(a.getRkuangNumber() - 1);
                    attendanceRecordMapper.updateKuangByAttendance(a);
                } else if (a.getAttendanceDetailList().get(0).getAttendanceStatus().equals("请假")) {
                    a.setRleaveNumber(a.getRleaveNumber() - 1);
                    attendanceRecordMapper.updateRleaveByAttendance(a);
                }


            }


//        删除当前班级下每条考勤记录下的该学生的考勤详细
            attendanceDetailMapper.deleteListByAttendanceDetailIdArgString(ids);
//            修改当前班级的成员数
            num2 = classMapper.updateMemberRedByClassId(p.getClassId());
        }else{
           num = personalAttendanceInformationTableMapper.deleteByClassIdAndUserId(p);
            //            修改当前班级的成员数
          num2 =   classMapper.updateMemberRedByClassId(p.getClassId());
        }
        return num+num2;
    }
}
