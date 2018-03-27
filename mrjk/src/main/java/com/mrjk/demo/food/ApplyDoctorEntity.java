package com.mrjk.demo.food;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "apply_doctor", schema = "dh", catalog = "")
public class ApplyDoctorEntity {
    private int applyId;
    private String name;
    private Timestamp createDate;
    private String state;
    private Double star;
    private Integer criticCount;
    private String introduction;

    @Id
    @Column(name = "apply_id")
    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "star")
    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    @Basic
    @Column(name = "critic_count")
    public Integer getCriticCount() {
        return criticCount;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setCriticCount(Integer criticCount) {
        this.criticCount = criticCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplyDoctorEntity that = (ApplyDoctorEntity) o;
        return applyId == that.applyId &&
                Objects.equals(name, that.name) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(state, that.state) &&
                Objects.equals(star, that.star) &&
                Objects.equals(criticCount, that.criticCount);
    }



    @Override
    public int hashCode() {

        return Objects.hash(applyId, name, createDate, state, star, criticCount);
    }
}
