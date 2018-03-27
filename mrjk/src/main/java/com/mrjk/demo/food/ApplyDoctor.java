package com.mrjk.demo.food;

import java.util.List;

public class ApplyDoctor {


    /**
     * success : true
     * message : 查询所求医生成功
     *
     */

    private boolean success;
    private String message;
    private List<ApplyDoctorEntity> doctors;

    /**
     * applysuccess : true
     * applymessage : 已发出申请
     */

    private boolean applysuccess;
    private String applymessage;

    /**
     * querysuccess : true
     * querymessage : 查询成功
     * state : 申请状态
     */

    private boolean querysuccess;
    private String querymessage;
    private String state;

    /**
     * logOutsuccess : true
     * logOutmessage : 注销成功
     */

    private boolean logOutsuccess;
    private String logOutmessage;
    /**
     * HandleSuccess : true
     * HandleMessage : 处理成功
     */

    private boolean HandleSuccess;
    private String HandleMessage;

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

    public List<ApplyDoctorEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<ApplyDoctorEntity> doctors) {
        this.doctors = doctors;
    }

    public boolean isApplysuccess() {
        return applysuccess;
    }

    public void setApplysuccess(boolean applysuccess) {
        this.applysuccess = applysuccess;
    }

    public String getApplymessage() {
        return applymessage;
    }

    public void setApplymessage(String applymessage) {
        this.applymessage = applymessage;
    }

    public boolean isQuerysuccess() {
        return querysuccess;
    }

    public void setQuerysuccess(boolean querysuccess) {
        this.querysuccess = querysuccess;
    }

    public String getQuerymessage() {
        return querymessage;
    }

    public void setQuerymessage(String querymessage) {
        this.querymessage = querymessage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLogOutsuccess() {
        return logOutsuccess;
    }

    public void setLogOutsuccess(boolean logOutsuccess) {
        this.logOutsuccess = logOutsuccess;
    }

    public String getLogOutmessage() {
        return logOutmessage;
    }

    public void setLogOutmessage(String logOutmessage) {
        this.logOutmessage = logOutmessage;
    }

    public boolean isHandleSuccess() {
        return HandleSuccess;
    }

    public void setHandleSuccess(boolean HandleSuccess) {
        this.HandleSuccess = HandleSuccess;
    }

    public String getHandleMessage() {
        return HandleMessage;
    }

    public void setHandleMessage(String HandleMessage) {
        this.HandleMessage = HandleMessage;
    }
}
