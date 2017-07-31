package com.xulifei.e.entity;

import java.io.Serializable;
import java.util.UUID;

public class TotalAttendanceInfomation implements Serializable {

    private static final long serialVersionUID = -6823313179962164358L;
    private String totalAttendanceId;

    private Integer atotalCheckNumber;

    private Integer totalAttendanceNumber;

    private Integer totalAbsenteeism;

    private Integer totalLateNumber;

    private Integer totalKuangNumber;

    private Integer totalLeaveNumber;

    public String getTotalAttendanceId() {
        if (totalAttendanceId != null) {
            totalAttendanceId = UUID.randomUUID().toString().replaceAll("-","");
        }
        return totalAttendanceId;
    }

    public void setTotalAttendanceId(String totalAttendanceId) {
        this.totalAttendanceId = totalAttendanceId == null ? null : totalAttendanceId.trim();
    }

    public Integer getAtotalCheckNumber() {
        return atotalCheckNumber;
    }

    public void setAtotalCheckNumber(Integer atotalCheckNumber) {
        this.atotalCheckNumber = atotalCheckNumber;
    }

    public Integer getTotalAttendanceNumber() {
        return totalAttendanceNumber;
    }

    public void setTotalAttendanceNumber(Integer totalAttendanceNumber) {
        this.totalAttendanceNumber = totalAttendanceNumber;
    }

    public Integer getTotalAbsenteeism() {
        return totalAbsenteeism;
    }

    public void setTotalAbsenteeism(Integer totalAbsenteeism) {
        this.totalAbsenteeism = totalAbsenteeism;
    }

    public Integer getTotalLateNumber() {
        return totalLateNumber;
    }

    public void setTotalLateNumber(Integer totalLateNumber) {
        this.totalLateNumber = totalLateNumber;
    }

    public Integer getTotalKuangNumber() {
        return totalKuangNumber;
    }

    public void setTotalKuangNumber(Integer totalKuangNumber) {
        this.totalKuangNumber = totalKuangNumber;
    }

    public Integer getTotalLeaveNumber() {
        return totalLeaveNumber;
    }

    public void setTotalLeaveNumber(Integer totalLeaveNumber) {
        this.totalLeaveNumber = totalLeaveNumber;
    }
}