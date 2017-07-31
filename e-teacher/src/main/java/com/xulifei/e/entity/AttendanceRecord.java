package com.xulifei.e.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class AttendanceRecord implements Serializable{

    private static final long serialVersionUID = -5936180052714356946L;
    private String attendanceId;

    private String classId;

    private String attendanceName;

    private Date creationTime;

    private Integer rattendanceNumber;

    private Integer rlateNumber;

    private Integer rkuangNumber;

    private Integer rleaveNumber;

    private String attendanceCode;

    private Boolean isOpen;

    public String getAttendanceId() {
        if (attendanceId != null) {
            attendanceId = UUID.randomUUID().toString().replaceAll("-","");
        }
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId == null ? null : attendanceId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getAttendanceName() {
        return attendanceName;
    }

    public void setAttendanceName(String attendanceName) {
        this.attendanceName = attendanceName == null ? null : attendanceName.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getRattendanceNumber() {
        return rattendanceNumber;
    }

    public void setRattendanceNumber(Integer rattendanceNumber) {
        this.rattendanceNumber = rattendanceNumber;
    }

    public Integer getRlateNumber() {
        return rlateNumber;
    }

    public void setRlateNumber(Integer rlateNumber) {
        this.rlateNumber = rlateNumber;
    }

    public Integer getRkuangNumber() {
        return rkuangNumber;
    }

    public void setRkuangNumber(Integer rkuangNumber) {
        this.rkuangNumber = rkuangNumber;
    }

    public Integer getRleaveNumber() {
        return rleaveNumber;
    }

    public void setRleaveNumber(Integer rleaveNumber) {
        this.rleaveNumber = rleaveNumber;
    }

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode == null ? null : attendanceCode.trim();
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }
}