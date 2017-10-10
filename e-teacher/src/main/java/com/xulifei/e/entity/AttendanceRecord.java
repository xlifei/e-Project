package com.xulifei.e.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AttendanceRecord implements Serializable{

    private static final long serialVersionUID = -5936180052714356946L;
    private String attendanceId;

    private String classId;

    private String attendanceName;

    private Date creationTime ;

    private Integer rattendanceNumber = 0;

    private Integer rlateNumber = 0;

    private Integer rkuangNumber = 0;

    private Integer rleaveNumber = 0;

    private String attendanceCode ;

    private List<AttendanceDetail> attendanceDetailList = new ArrayList<AttendanceDetail>();

    private Boolean isOpen = false;

    private Boolean isFinsh = false;

    private BigDecimal longitude;

    private  BigDecimal latitude;



    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public List<AttendanceDetail> getAttendanceDetailList() {
        return attendanceDetailList;
    }

    public void setAttendanceDetailList(List<AttendanceDetail> attendanceDetailList) {
        this.attendanceDetailList = attendanceDetailList;
    }

    public Boolean getIsFinsh() {
        return isFinsh;
    }

    public void setIsFinsh(Boolean isFinsh) {
        this.isFinsh = isFinsh;
    }

    public String getAttendanceId() {
        if (attendanceId == null) {
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