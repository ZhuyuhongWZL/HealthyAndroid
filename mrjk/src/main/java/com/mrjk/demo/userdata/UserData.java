package com.mrjk.demo.userdata;

public class UserData {


    /**
     * success : true
     * message : 成功
     * data : {"data_id":0,"name":"名字","walk":0,"distance":0,"weight":0,"height":0,"calorie":0,"locationCity":"地理位置","locationTown":"地理位置"}
     */

    private boolean success;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * data_id : 0
         * name : 名字
         * walk : 0
         * distance : 0
         * weight : 0
         * height : 0
         * calorie : 0
         * locationCity : 地理位置
         * locationTown : 地理位置
         */

        private int walk;
        private double distance;
        private double weight;
        private double height;
        private double calorie;
        private String locationCity;
        private String locationTown;


        public int getWalk() {
            return walk;
        }

        public void setWalk(int walk) {
            this.walk = walk;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getCalorie() {
            return calorie;
        }

        public void setCalorie(double calorie) {
            this.calorie = calorie;
        }

        public String getLocationCity() {
            return locationCity;
        }

        public void setLocationCity(String locationCity) {
            this.locationCity = locationCity;
        }

        public String getLocationTown() {
            return locationTown;
        }

        public void setLocationTown(String locationTown) {
            this.locationTown = locationTown;
        }
    }
}
