package com.xulifei.e.service.impl;

import com.xulifei.e.entity.AttendanceDetail;
import com.xulifei.e.entity.AttendanceRecord;
import com.xulifei.e.entity.PersonalAttendanceInformationTable;
import com.xulifei.e.entity.TotalAttendanceInfomation;
import com.xulifei.e.service.AttendanceDetailService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2017/8/11.
 */
@Service("attendanceDetailService")
public class AttendanceDetailServiceImpl extends BaseServiceImpl<AttendanceDetail> implements AttendanceDetailService {


    @Override
    public int updateAttendanceDetailAndRecord(AttendanceRecord attendanceRecord, AttendanceDetail attendanceDetail) throws Exception {
        int num = attendanceDetailMapper.update(attendanceDetail);
        System.out.println("----------------------->"+num);
        System.out.println(attendanceDetail.getAttendanceDetailId());
        System.out.println(attendanceDetail.getAttendanceStatus());
        System.out.println(attendanceDetail.isFinsh());
        int num2 = attendanceRecordMapper.updateRattendanceNumber(attendanceRecord);
        System.out.println("----------------------->"+num2);
        System.out.println(attendanceRecord.getAttendanceId());
        System.out.println(attendanceDetail.getAttendanceStatus());
        System.out.println(attendanceDetail.isFinsh());
        return num+num2;
    }

    @Override
    public List<AttendanceDetail> selectList(AttendanceDetail attendanceDetail) {
        return attendanceDetailMapper.selectList(attendanceDetail);
    }

    @Override
    public int changeStatus(AttendanceRecord attendanceRecord) throws Exception {
        int num = attendanceDetailMapper.updateStatus(attendanceRecord.getAttendanceDetailList().get(0));
//更新考勤记录
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        if (attendanceRecord.getAttendanceDetailList().get(0).getAttendanceStatus().equals("出勤")) {

            num2 = attendanceRecordMapper.updateRattendanceAndRleaveNumber(attendanceRecord);
//更新个人考勤
            PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
            p.setClassId(attendanceRecord.getClassId());
            p.setUserId(attendanceRecord.getAttendanceDetailList().get(0).getDetailUserId());
            num3 = personalAttendanceInformationTableMapper.updateAttendanceAndLeaveNumber(p);
//        更新总考勤

            TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectTotalAttendanceByClassId(attendanceRecord.getClassId());
            num4 = totalAttendanceInfomationMapper.updateTotalAttendanceAndLeaveNumber(totalAttendanceInfomation.getTotalAttendanceId());


        } else if (attendanceRecord.getAttendanceDetailList().get(0).getAttendanceStatus().equals("迟到")) {
            num2 = attendanceRecordMapper.updateRlateAndRkuangNumber(attendanceRecord);

            //更新个人考勤
            PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
            p.setClassId(attendanceRecord.getClassId());
            p.setUserId(attendanceRecord.getAttendanceDetailList().get(0).getDetailUserId());
            num3 = personalAttendanceInformationTableMapper.updateLateAndKuangNumber(p);
//        更新总考勤

            TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectTotalAttendanceByClassId(attendanceRecord.getClassId());
            num4 = totalAttendanceInfomationMapper.updateTotalLateAndKuangNumber(totalAttendanceInfomation.getTotalAttendanceId());


        } else if (attendanceRecord.getAttendanceDetailList().get(0).getAttendanceStatus().equals("旷课")) {
            num2 = attendanceRecordMapper.updateRkuangAndRattendanceNumber(attendanceRecord);

            //更新个人考勤
            PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
            p.setClassId(attendanceRecord.getClassId());
            p.setUserId(attendanceRecord.getAttendanceDetailList().get(0).getDetailUserId());
            num3 =personalAttendanceInformationTableMapper.updateKuangAndAttendanceNumber(p);
//        更新总考勤

            TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectTotalAttendanceByClassId(attendanceRecord.getClassId());
            num4 = totalAttendanceInfomationMapper.updateTotalKuangAndAttendanceNumber(totalAttendanceInfomation.getTotalAttendanceId());

        } else if (attendanceRecord.getAttendanceDetailList().get(0).getAttendanceStatus().equals("请假")) {
            num2 = attendanceRecordMapper.updateRleaveAndRlateNumber(attendanceRecord);

            //更新个人考勤
            PersonalAttendanceInformationTable p = new PersonalAttendanceInformationTable();
            p.setClassId(attendanceRecord.getClassId());
            p.setUserId(attendanceRecord.getAttendanceDetailList().get(0).getDetailUserId());
            num3 = personalAttendanceInformationTableMapper.updateLeaveAndLateNumber(p);
//        更新总考勤

            TotalAttendanceInfomation totalAttendanceInfomation = totalAttendanceInfomationMapper.selectTotalAttendanceByClassId(attendanceRecord.getClassId());
            num4 = totalAttendanceInfomationMapper.updateTotalLeaveAndLateNumber(totalAttendanceInfomation.getTotalAttendanceId());

        }

return num+num2+num3+num4;
    }

    @Override
    public List<AttendanceDetail> findListByKeyValue(String attendanceId, String searchKeyValue) {
        Map<String,String> map = new HashMap<String,String>();
         map.put("attendanceId",attendanceId);
        map.put("searchKeyValue","%"+searchKeyValue+"%");
       List<AttendanceDetail> attendanceDetails = attendanceDetailMapper.findListByKeyValue(map);
        return attendanceDetails;
    }
}
