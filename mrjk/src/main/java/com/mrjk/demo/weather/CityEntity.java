package com.mrjk.demo.weather;

import javax.persistence.*;

@Entity
@Table(name = "city", schema = "dh", catalog = "")
public class CityEntity {
    private int id;
    private String cityName;
    private String cityEn;
    private String townId;
    private String townName;
    private String townEn;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cityName")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "cityEN")
    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    @Basic
    @Column(name = "townID")
    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    @Basic
    @Column(name = "townName")
    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @Basic
    @Column(name = "townEN")
    public String getTownEn() {
        return townEn;
    }

    public void setTownEn(String townEn) {
        this.townEn = townEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        if (id != that.id) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (cityEn != null ? !cityEn.equals(that.cityEn) : that.cityEn != null) return false;
        if (townId != null ? !townId.equals(that.townId) : that.townId != null) return false;
        if (townName != null ? !townName.equals(that.townName) : that.townName != null) return false;
        if (townEn != null ? !townEn.equals(that.townEn) : that.townEn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (cityEn != null ? cityEn.hashCode() : 0);
        result = 31 * result + (townId != null ? townId.hashCode() : 0);
        result = 31 * result + (townName != null ? townName.hashCode() : 0);
        result = 31 * result + (townEn != null ? townEn.hashCode() : 0);
        return result;
    }
}
