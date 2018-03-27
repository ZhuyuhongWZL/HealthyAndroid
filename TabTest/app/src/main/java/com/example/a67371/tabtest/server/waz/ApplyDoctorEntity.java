package com.example.a67371.tabtest.server.waz;

import java.sql.Timestamp;

public class ApplyDoctorEntity {
    private int applyId;
    private String name;
    private Timestamp createDate;
    private String state;
    private Double star;
    private Integer criticCount;
    private String introduction;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Integer getCriticCount() {
        return criticCount;
    }

    public void setCriticCount(Integer criticCount) {
        this.criticCount = criticCount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

