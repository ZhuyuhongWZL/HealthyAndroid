package com.example.a67371.tabtest.ui.bean;

/**
 * Created by wenjie on 2018/3/13.
 */

public class Diet {

    private int imageId;
    private String foodName;
    private int number;
    private int karori;

    public Diet(int imageId, String foodName, int number, int karori) {
        this.imageId = imageId;
        this.foodName = foodName;
        this.number = number;
        this.karori = karori;
    }

    public int getKarori() {
        return karori;
    }

    public void setKarori(int karori) {
        this.karori = karori;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getImageId() {
        return imageId;
    }
}
