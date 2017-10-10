package com.xulifei.e.dao;

import com.xulifei.e.entity.AttendanceRecord;
import com.xulifei.e.entity.PersonalAttendanceInformationTable;

import java.util.List;

public interface AttendanceRecordMapper extends BaseMapper<AttendanceRecord> {
    int deleteByPrimaryKey(String attendanceId);

    AttendanceRecord selectByPrimaryKey(String attendanceId);

    int updateByPrimaryKeySelective(AttendanceRecord record);

    int updateByPrimaryKey(AttendanceRecord record);
    List<AttendanceRecord> findAllRecordForStudent(AttendanceRecord attendanceRecord);
    AttendanceRecord findAllRecordForStudentFinshFalse(AttendanceRecord attendanceRecord);
    List<AttendanceRecord> findAllRecordForTeacher(AttendanceRecord attendanceRecord);
    AttendanceRecord findAllRecordForTeacherFinshFalse(AttendanceRecord attendanceRecord);
    List<AttendanceRecord> findAllRecordByClassId(String classId);
    List<AttendanceRecord> findAllRecordByClassIdAndUserId(PersonalAttendanceInformationTable p);
     int updateRattendanceNumber(AttendanceRecord attendanceRecord);

    int updateRattendanceAndRleaveNumber(AttendanceRecord attendanceRecord);
    int updateRlateAndRkuangNumber(AttendanceRecord attendanceRecord);
    int updateRkuangAndRattendanceNumber(AttendanceRecord attendanceRecord);
    int updateRleaveAndRlateNumber(AttendanceRecord attendanceRecord);



   int updateIsFinshByAttendance(String attendanceId);
    int updateKuangByAttendance(AttendanceRecord attendanceRecord);
    int updateRattendanceByAttendance(AttendanceRecord attendanceRecord);
    int updateRlateByAttendance(AttendanceRecord attendanceRecord);
    int updateRleaveByAttendance(AttendanceRecord attendanceRecord);
    int updateAttendanceName(AttendanceRecord attendanceRecord);

int deleteListByArgString(List<String> ids);

    int updateChuqinList(List<AttendanceRecord> attendanceRecords);
    int updateChidaoList(List<AttendanceRecord> attendanceRecords);
    int updateKuangList(List<AttendanceRecord> attendanceRecords);
    int updateLeaveList(List<AttendanceRecord> attendanceRecords);

}