package com.xulifei.e.service;

import com.xulifei.e.entity.AttendanceRecord;

import java.util.List;

/**
 * Created by john on 2017/8/8.
 */
public interface AttendanceRecordService extends BaseService<AttendanceRecord>{
   List<AttendanceRecord> findAllRecordForStudent(AttendanceRecord attendanceRecord) throws Exception;
   List<AttendanceRecord> findAllRecordForTeacher(AttendanceRecord attendanceRecord)throws Exception;
   AttendanceRecord findAllRecordForStudentFinshFalse(AttendanceRecord attendanceRecord) throws  Exception;
   AttendanceRecord findAllRecordForTeacherFinshFalse(AttendanceRecord attendanceRecord) throws  Exception;
   int updateRecorAndDetail(AttendanceRecord attendanceRecord)throws  Exception;
   int deleteAttendance(AttendanceRecord attendanceRecord) throws  Exception;
   int createAttendance(AttendanceRecord attendanceRecord)throws  Exception;
   int updateAttendanceName(AttendanceRecord attendanceRecord) throws  Exception;
   int abandonAttendance(AttendanceRecord attendanceRecord) throws  Exception;
}
