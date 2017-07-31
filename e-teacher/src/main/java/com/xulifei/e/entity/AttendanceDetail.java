package com.xulifei.e.entity;

import java.io.Serializable;
import java.util.UUID;

public class AttendanceDetail implements Serializable {
    private static final long serialVersionUID = 8625469676909874524L;
    private String attendanceDetailId;

    private String attendanceId;

    private String attendanceStatus;

    private String detailUserId;


    public String getAttendanceDetailId() {
        if (attendanceDetailId != null) {
            attendanceDetailId = UUID.randomUUID().toString().replaceAll("-","");
        }
        return attendanceDetailId;
    }

    public void setAttendanceDetailId(String attendanceDetailId) {
        this.attendanceDetailId = attendanceDetailId == null ? null : attendanceDetailId.trim();
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId == null ? null : attendanceId.trim();
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus == null ? null : attendanceStatus.trim();
    }

    public String getDetailUserId() {
        return detailUserId;
    }

    public void setDetailUserId(String detailUserId) {
        this.detailUserId = detailUserId == null ? null : detailUserId.trim();
    }
}