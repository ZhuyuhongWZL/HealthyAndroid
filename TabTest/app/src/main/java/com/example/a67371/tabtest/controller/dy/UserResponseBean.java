package com.example.a67371.tabtest.controller.dy;

import java.io.Serializable;

public class UserResponseBean implements Serializable {

    /**
     * success : false
     * message : 注册成功，立即验证
     * user : {"name":"waz","mail":"134697980@qq.com","password":"123456","createTime":"2018-03-18 13:07:24.386","state":"未验证"}
     */

    private boolean success;
    private String message;
    private UserBean user;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Serializable {
        /**
         * name : dy
         * mail : 1048585782@qq.com
         * password : 123456
         * birthday : 2018-03-14
         * sex : 男
         * phone : 13759893595
         * createTime : 2018-03-18 16:05:33.0
         * state : 已验证
         */

        private String name;
        private String mail;
        private String password;
        private String birthday;
        private String sex;
        private String phone;
        private String createTime;
        private String state;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
