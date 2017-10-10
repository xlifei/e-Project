package com.xulifei.e.entity;

import java.util.UUID;

public class PersonalAttendanceInformationTable extends PersonalAttendanceInformationTableKey {
    private static final long serialVersionUID = -857619790822353952L;
    private String personAttendanceInformationId;
    private Integer totalNumberAttendance;

    private Integer attendanceNumber = 0;

    private Integer lateNumber =0;

    private Integer kuangNumber =0;

    private Integer leaveNumber =0;

    public String getPersonAttendanceInformationId() {
        if (personAttendanceInformationId == null) {
            personAttendanceInformationId = UUID.randomUUID().toString().replaceAll("-","");
        }
        return personAttendanceInformationId;
    }

    public void setPersonAttendanceInformationId(String personAttendanceInformationId) {
        this.personAttendanceInformationId = personAttendanceInformationId == null ? null : personAttendanceInformationId.trim();
    }

    public Integer getTotalNumberAttendance() {
        return totalNumberAttendance;
    }

    public void setTotalNumberAttendance(Integer totalNumberAttendance) {
        this.totalNumberAttendance = totalNumberAttendance;
    }

    public Integer getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(Integer attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }


    public Integer getLateNumber() {
        return lateNumber;
    }

    public void setLateNumber(Integer lateNumber) {
        this.lateNumber = lateNumber;
    }

    public Integer getKuangNumber() {
        return kuangNumber;
    }

    public void setKuangNumber(Integer kuangNumber) {
        this.kuangNumber = kuangNumber;
    }

    public Integer getLeaveNumber() {
        return leaveNumber;
    }

    public void setLeaveNumber(Integer leaveNumber) {
        this.leaveNumber = leaveNumber;
    }
}