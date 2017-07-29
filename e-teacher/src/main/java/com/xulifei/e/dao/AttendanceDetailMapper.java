package com.xulifei.e.dao;

import com.xulifei.e.entity.AttendanceDetail;

public interface AttendanceDetailMapper extends  BaseMapper<AttendanceDetail> {
    int deleteByPrimaryKey(String attendanceDetailId);

    AttendanceDetail selectByPrimaryKey(String attendanceDetailId);

    int updateByPrimaryKeySelective(AttendanceDetail record);

    int updateByPrimaryKey(AttendanceDetail record);
}