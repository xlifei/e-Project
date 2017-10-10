package com.xulifei.e.dao;

import com.xulifei.e.entity.AttendanceDetail;
import com.xulifei.e.entity.AttendanceRecord;

import java.util.List;
import java.util.Map;

public interface AttendanceDetailMapper extends  BaseMapper<AttendanceDetail> {
    int deleteByPrimaryKey(String attendanceDetailId);

    AttendanceDetail selectByPrimaryKey(String attendanceDetailId);

    int updateByPrimaryKeySelective(AttendanceDetail record);

    int updateByPrimaryKey(AttendanceDetail record);
    int deleteByClassId(String classId);
    List<AttendanceDetail> findDetailsByAttendanceId(AttendanceRecord attendanceRecord);
    int updateList(List<String> attendanceDetailIds);
    List<AttendanceDetail> findAllByAttendanceId(AttendanceRecord attendanceRecord);
    AttendanceDetail findByAttendanceIdAndUserId(AttendanceDetail attendanceDetail);
   List<AttendanceDetail> selectList(AttendanceDetail attendanceDetail);
    int updateStatus(AttendanceDetail attendanceDetail);
    int deleteListByArgString(List<String> ids);
    int deleteListByAttendanceDetailIdArgString(List<String > ids);
    List<AttendanceDetail> findListByKeyValue(Map<String,String> map);

}