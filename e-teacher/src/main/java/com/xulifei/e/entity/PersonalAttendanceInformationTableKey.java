package com.xulifei.e.entity;

import java.io.Serializable;

public class PersonalAttendanceInformationTableKey implements Serializable {

    private static final long serialVersionUID = 3850813675317432740L;
    private String classId;

    private String userId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}