package com.example.a67371.tabtest.ui.bean;

/**
 * Created by wenjie on 2018/3/9.
 */

public class ChatContent {
    private int imageId;
    private String content;
    private String time;
    private boolean isI;

    public ChatContent(String content, int imageId, String time, boolean isI) {
        this.content = content;
        this.imageId = imageId;
        this.time = time;
        this.isI = isI;
    }
    public String getContent() {
        return content;
    }
    public int getImageId() {
        return imageId;
    }

    public boolean getIsI() { return isI; }

    public String getTime() {
        return time;
    }
}
