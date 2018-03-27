package com.example.a67371.tabtest.ui.bean;

/**
 * Created by wenjie on 2018/3/13.
 */

public class AddFood {
    private String foodName;
    private int imageId;
    private int eachKarori;
    private int foodNumber;

    public AddFood(String foodName, int imageId, int eachKarori) {
        this.foodName = foodName;
        this.imageId = imageId;
        this.eachKarori = eachKarori;
        this.foodNumber = 0;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getImageId() {
        return imageId;
    }

    public int getEachKarori() {
        return eachKarori;
    }

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }
}
