package com.xulifei.e.service;

import com.xulifei.e.entity.AttendanceDetail;
import com.xulifei.e.entity.AttendanceRecord;

import java.util.List;

/**
 * Created by john on 2017/8/11.
 */
public interface AttendanceDetailService extends BaseService<AttendanceDetail> {

    int updateAttendanceDetailAndRecord(AttendanceRecord attendanceRecord, AttendanceDetail attendanceDetail) throws Exception;
     List<AttendanceDetail> selectList(AttendanceDetail attendanceDetail) throws Exception;
    int changeStatus(AttendanceRecord attendanceRecord) throws  Exception;
    List<AttendanceDetail> findListByKeyValue(String attendanceId,String searchKeyValue) throws  Exception;

}
