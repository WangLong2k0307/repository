package cn.pslab.domain;

import java.io.Serializable;

/*
* 用户实体类
* */
public class User implements Serializable{
    private int uid;
    private String name;
    private String username;
    private String password;
    private String fusername;
    private String location;
    private String contact;
    private String contacttel;
    private String description;
    private String date;
    private int rid;

    /*无参构造方法*/
    public User(){}
    /*有参构造方法*/
    public User(int uid,String name,String username,String password,String fusername,String location,String contact,String contacttel,String description,String date,int rid){
        this.uid=uid;
        this.name=name;
        this.username=username;
        this.password=password;
        this.fusername=fusername;
        this.location=location;
        this.contact=contact;
        this.contacttel=contacttel;
        this.description=description;
        this.date=date;
        this.rid=rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFusername() {
        return fusername;
    }

    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
