package com.mrjk.demo.doctor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "doctor_user", schema = "dh", catalog = "")
public class DoctorUserEntity {
    private int id;
    private String user;
    private String doctor;
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

        DoctorUserEntity that = (DoctorUserEntity) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (doctor != null ? !doctor.equals(that.doctor) : that.doctor != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
