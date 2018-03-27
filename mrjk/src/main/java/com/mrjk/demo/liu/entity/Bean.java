package com.mrjk.demo.liu.entity;

public class Bean {

    /**
     * success : true
     * message : 登录成功
     * user : {"name":"用户名","mail":"邮箱","password":"密码","birthday":"出生日期","sex":"性别","phone":"手机号","createTime":"用户创建时间","state":"账号状态"}
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

    public static class UserBean {
        /**
         * name : 用户名
         * mail : 邮箱
         * password : 密码
         * birthday : 出生日期
         * sex : 性别
         * phone : 手机号
         * createTime : 用户创建时间
         * state : 账号状态
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
