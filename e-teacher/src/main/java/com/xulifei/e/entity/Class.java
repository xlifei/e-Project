package com.xulifei.e.entity;

import java.io.Serializable;

public class Class  implements Serializable{

    private static final long serialVersionUID = 5002547115976322693L;
    private String classId;

    private String codeId;

    private String totalAttendanceId;

    private String userId;

    private String className;

    private String serialNumber;

    private Integer atotalCheckNumber;

    private Integer memberNumber;

    private Boolean isArchive;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId == null ? null : codeId.trim();
    }

    public String getTotalAttendanceId() {
        return totalAttendanceId;
    }

    public void setTotalAttendanceId(String totalAttendanceId) {
        this.totalAttendanceId = totalAttendanceId == null ? null : totalAttendanceId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
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
}