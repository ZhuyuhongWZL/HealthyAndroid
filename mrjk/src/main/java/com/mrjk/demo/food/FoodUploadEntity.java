package com.mrjk.demo.food;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "food_upload", schema = "dh", catalog = "")
public class FoodUploadEntity {
    private int id;
    private String name;
    private String alias;
    private String uploader;
    private Timestamp uploadTime;
    private Integer calorie;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Basic
    @Column(name = "uploader")
    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    @Basic
    @Column(name = "upload_time")
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "calorie")
    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodUploadEntity that = (FoodUploadEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(uploader, that.uploader) &&
                Objects.equals(uploadTime, that.uploadTime) &&
                Objects.equals(calorie, that.calorie);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, alias, uploader, uploadTime, calorie);
    }
}
