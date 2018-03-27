package com.mrjk.demo.liu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LiuHaoiang
 * @date 2018/1/31 13:57
 */
@Entity
@Table(name = "student")
public class Student  {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer state;
    private String name;
    private String password;
    private String mail;

    public Student(){}

    public Integer getId() {
        return id;
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

