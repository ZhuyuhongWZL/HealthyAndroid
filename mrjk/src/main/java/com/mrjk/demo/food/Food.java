package com.mrjk.demo.food;

import java.util.ArrayList;
import java.util.List;

public class Food {


    /**
     * success : true
     * message : 成功
     * food : [{"name":"食物名","alias":"食物别名","calorie":233}]
     */

    private boolean success;
    private String message;
    private List<FoodBean> food = new ArrayList<FoodBean>();

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

    public List<FoodBean> getFood() {
        return food;
    }

    public void setFood(List<FoodBean> food) {
        this.food = food;
    }

    public static class FoodBean {
        /**
         * name : 食物名
         * alias : 食物别名
         * calorie : 233
         */

        private String name;
        private String alias;
        private int calorie;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getCalorie() {
            return calorie;
        }

        public void setCalorie(int calorie) {
            this.calorie = calorie;
        }
    }
}
