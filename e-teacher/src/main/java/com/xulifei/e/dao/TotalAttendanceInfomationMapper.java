package com.xulifei.e.dao;

import com.xulifei.e.entity.TotalAttendanceInfomation;

public interface TotalAttendanceInfomationMapper extends  BaseMapper<TotalAttendanceInfomation> {
    int deleteByPrimaryKey(String totalAttendanceId);

    TotalAttendanceInfomation selectByPrimaryKey(String totalAttendanceId);

    int updateByPrimaryKeySelective(TotalAttendanceInfomation record);

    int updateByPrimaryKey(TotalAttendanceInfomation record);
}