package com.example.a67371.tabtest.ui.bean.lxb;
import java.io.Serializable;
import java.util.List;

public class ApplyDoctor implements Serializable {


    /**
     * success : true
     * message : 成功
     * doctors : [{"applyId":2,"name":"hy2","createDate":"Mar 13, 2018 6:46:06 PM","state":"审核中","star":3.72727,"criticCount":11,"introduction":"hello贺勇23999999999999999999999999999\n999999999999999gghgkjgl h"}]
     * applysuccess : false
     * querysuccess : false
     * logOutsuccess : false
     * HandleSuccess : false
     */

    private boolean success;
    private String message;
    private boolean applysuccess;
    private boolean querysuccess;
    private boolean logOutsuccess;
    private boolean HandleSuccess;
    private List<DoctorsBean> doctors;

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

    public boolean isApplysuccess() {
        return applysuccess;
    }

    public void setApplysuccess(boolean applysuccess) {
        this.applysuccess = applysuccess;
    }

    public boolean isQuerysuccess() {
        return querysuccess;
    }

    public void setQuerysuccess(boolean querysuccess) {
        this.querysuccess = querysuccess;
    }

    public boolean isLogOutsuccess() {
        return logOutsuccess;
    }

    public void setLogOutsuccess(boolean logOutsuccess) {
        this.logOutsuccess = logOutsuccess;
    }

    public boolean isHandleSuccess() {
        return HandleSuccess;
    }

    public void setHandleSuccess(boolean HandleSuccess) {
        this.HandleSuccess = HandleSuccess;
    }

    public List<DoctorsBean> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorsBean> doctors) {
        this.doctors = doctors;
    }

    public static class DoctorsBean implements Serializable{
        /**
         * applyId : 2
         * name : hy2
         * createDate : Mar 13, 2018 6:46:06 PM
         * state : 审核中
         * star : 3.72727
         * criticCount : 11
         * introduction : hello贺勇23999999999999999999999999999
         999999999999999gghgkjgl h
         */

        private int applyId;
        private String name;
        private String createDate;
        private String state;
        private double star;
        private int criticCount;
        private String introduction;

        public int getApplyId() {
            return applyId;
        }

        public void setApplyId(int applyId) {
            this.applyId = applyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public double getStar() {
            return star;
        }

        public void setStar(double star) {
            this.star = star;
        }

        public int getCriticCount() {
            return criticCount;
        }

        public void setCriticCount(int criticCount) {
            this.criticCount = criticCount;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}