package com.mrjk.demo.userdata;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_data", schema = "dh", catalog = "")
public class UserDataEntity {
    private int dataId;
    private String name;
    private Date date;
    private Integer walk;
    private Double distance;
    private Double weight;
    private Double height;
    private Double calorie;
    private String locationCity;
    private String locationTown;

    @Id
    @Column(name = "data_id", nullable = false)
    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "walk", nullable = true)
    public Integer getWalk() {
        return walk;
    }

    public void setWalk(Integer walk) {
        this.walk = walk;
    }

    @Basic
    @Column(name = "distance", nullable = true, precision = 0)
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name = "weight", nullable = true, precision = 0)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "height", nullable = true, precision = 0)
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Basic
    @Column(name = "calorie", nullable = true, precision = 0)
    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    @Basic
    @Column(name = "location_city", nullable = true, length = 10)
    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    @Basic
    @Column(name = "location_town", nullable = true, length = 10)
    public String getLocationTown() {
        return locationTown;
    }

    public void setLocationTown(String locationTown) {
        this.locationTown = locationTown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataEntity that = (UserDataEntity) o;
        return dataId == that.dataId &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(walk, that.walk) &&
                Objects.equals(distance, that.distance) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(height, that.height) &&
                Objects.equals(calorie, that.calorie) &&
                Objects.equals(locationCity, that.locationCity) &&
                Objects.equals(locationTown, that.locationTown);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dataId, name, date, walk, distance, weight, height, calorie, locationCity, locationTown);
    }
}
