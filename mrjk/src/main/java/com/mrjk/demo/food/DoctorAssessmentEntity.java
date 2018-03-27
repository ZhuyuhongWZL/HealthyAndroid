package com.mrjk.demo.food;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "doctor_assessment", schema = "dh", catalog = "")
public class DoctorAssessmentEntity {
    private int id;
    private String user;
    private String doctor;
    private Double star;
    private Timestamp createTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "doctor")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAssessmentEntity that = (DoctorAssessmentEntity) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(star, that.star) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, doctor, star, createTime);
    }
}
