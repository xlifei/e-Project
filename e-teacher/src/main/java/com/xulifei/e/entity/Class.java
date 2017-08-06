package com.xulifei.e.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Class  implements Serializable{

    private static final long serialVersionUID = 5002547115976322693L;
    private String classId;
    private InviteCode inviteCode;

    private TotalAttendanceInfomation totalAttendanceInfomation;

    private User user;

    private List<AttendanceRecord> attendanceRecordList = new ArrayList<AttendanceRecord>();

    private  List<User> userList = new ArrayList<User>();

    private String className;

    private Integer serialNumber = 1;

    private Integer atotalCheckNumber = 0;

    private Integer memberNumber = 0;

    private Boolean isArchive = false;
    private String bgc;



    public String getClassId() {
        if (classId == null) {
            classId = UUID.randomUUID().toString().replaceAll("-","");
        }
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }



    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber ;
    }

    public Integer getAtotalCheckNumber() {
        return atotalCheckNumber;
    }

    public void setAtotalCheckNumber(Integer atotalCheckNumber) {
        this.atotalCheckNumber = atotalCheckNumber;
    }

    public Integer getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Integer memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }
    public String getBgc() {
        return bgc;
    }

    public void setBgc(String bgc) {
        this.bgc = bgc == null ? null : bgc.trim();
    }


    public InviteCode getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(InviteCode inviteCode) {
        this.inviteCode = inviteCode;
    }

    public TotalAttendanceInfomation getTotalAttendanceInfomation() {
        return totalAttendanceInfomation;
    }

    public void setTotalAttendanceInfomation(TotalAttendanceInfomation totalAttendanceInfomation) {
        this.totalAttendanceInfomation = totalAttendanceInfomation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AttendanceRecord> getAttendanceRecordList() {
        return attendanceRecordList;
    }

    public void setAttendanceRecordList(List<AttendanceRecord> attendanceRecordList) {
        this.attendanceRecordList = attendanceRecordList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}