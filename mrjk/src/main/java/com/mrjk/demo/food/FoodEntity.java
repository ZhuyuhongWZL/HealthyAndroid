package com.mrjk.demo.food;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "food", schema = "dh", catalog = "")
public class FoodEntity {
    private int id;
    private String name;
    private String alias;
    private Integer calorie;
    private String detailUrl;
    private Integer index;
    private Timestamp addTime;

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
    @Column(name = "calorie")
    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    @Basic
    @Column(name = "detail_url")
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Basic
    @Column(name = "index")
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Basic
    @Column(name = "add_time")
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodEntity that = (FoodEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(calorie, that.calorie) &&
                Objects.equals(detailUrl, that.detailUrl) &&
                Objects.equals(index, that.index) &&
                Objects.equals(addTime, that.addTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, alias, calorie, detailUrl, index, addTime);
    }
}
