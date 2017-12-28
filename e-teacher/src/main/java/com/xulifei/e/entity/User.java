package com.xulifei.e.entity;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private static final long serialVersionUID = -6546254384193596607L;
    private String userId;

    private String account;

    private String pwd;

    private String userName;

    private String sex;

    private String school;

    private String department;

    private String major ;

    private String sjnumber = "              ";

    private String educationBackground;

    private String professionalTitle;

    private String researchDirection;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    private String imgPath;

    private PersonalAttendanceInformationTable p;

    public PersonalAttendanceInformationTable getP() {
        return p;
    }

    public void setP(PersonalAttendanceInformationTable p) {
        this.p = p;
    }

    public String getUserId() {
        if (userId == null) {
            userId = UUID.randomUUID().toString().replaceAll("-","");
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getSjnumber() {
        return sjnumber;
    }

    public void setSjnumber(String sjnumber) {
        this.sjnumber = sjnumber == null ? null : sjnumber.trim();
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground == null ? null : educationBackground.trim();
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle == null ? null : professionalTitle.trim();
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection == null ? null : researchDirection.trim();
    }


}