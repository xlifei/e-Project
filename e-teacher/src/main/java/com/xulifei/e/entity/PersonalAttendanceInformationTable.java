package com.xulifei.e.entity;

public class PersonalAttendanceInformationTable extends PersonalAttendanceInformationTableKey {
    private static final long serialVersionUID = -857619790822353952L;
    private Integer totalNumberAttendance;

    private Integer attendanceNumber;

    private Integer absenteeism;

    private Integer lateNumber;

    private Integer kuangNumber;

    private Integer leaveNumber;

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

    public Integer getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(Integer absenteeism) {
        this.absenteeism = absenteeism;
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