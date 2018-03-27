package com.example.a67371.tabtest.ui.bean;

import java.util.List;

public class Food {

    /**
     * success : true
     * message : 成功
     * food : [{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278},{"name":"自制吐司","alias":"吐司 面包","calorie":278}]
     */

    private boolean success;
    private String message;
    private List<FoodBean> food;

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
         * name : 自制吐司
         * alias : 吐司 面包
         * calorie : 278
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
