package com.xulifei.e.dao;

import com.xulifei.e.entity.TotalAttendanceInfomation;

import java.util.List;

public interface TotalAttendanceInfomationMapper extends  BaseMapper<TotalAttendanceInfomation> {
    int deleteByPrimaryKey(String totalAttendanceId);

    TotalAttendanceInfomation selectByPrimaryKey(String totalAttendanceId);

    int updateByPrimaryKeySelective(TotalAttendanceInfomation record);

    int updateByPrimaryKey(TotalAttendanceInfomation record);
    List<TotalAttendanceInfomation> selectList();
    TotalAttendanceInfomation selectKuangNumberById(String totalAttendanceId);
    TotalAttendanceInfomation selectTotalAttendanceByClassId(String totalId);



   int  updateTotalAttendanceAndLeaveNumber(String id);
    int  updateTotalLateAndKuangNumber(String id);
    int  updateTotalKuangAndAttendanceNumber(String id);
    int  updateTotalLeaveAndLateNumber(String id);

    int deleteByClassId(String classId);

    TotalAttendanceInfomation findByClassId(String classId);



}