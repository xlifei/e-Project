package com.xulifei.e.service;

import com.xulifei.e.entity.TotalAttendanceInfomation;

/**
 * Created by john on 2017/8/20.
 */
public interface TotalAttendanceInfomationService extends BaseService<TotalAttendanceInfomation> {

    TotalAttendanceInfomation findByClassId(String classId);
}
