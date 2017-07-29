package com.xulifei.e.dao;

import com.xulifei.e.entity.AttendanceRecord;

public interface AttendanceRecordMapper extends BaseMapper<AttendanceRecord> {
    int deleteByPrimaryKey(String attendanceId);

    AttendanceRecord selectByPrimaryKey(String attendanceId);

    int updateByPrimaryKeySelective(AttendanceRecord record);

    int updateByPrimaryKey(AttendanceRecord record);
}