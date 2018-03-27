package com.example.a67371.tabtest.ui.bean;

/**
 * Created by wenjie on 2018/3/8.
 */

public class Chater {
    private int imageId;
    private String name;

    public Chater(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;
    }
}

