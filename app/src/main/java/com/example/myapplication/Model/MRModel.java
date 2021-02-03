package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class MRModel {


    @SerializedName("id")
    private String id;

    @SerializedName("mr_name")
    private String mr_name;

    @SerializedName("contact_no")
    private String contact_no;

    @SerializedName("email_id")
    private String email_id;

    @SerializedName("city_name")
    private String city_name;

    @SerializedName("area_name")
    private String area_name;

    @SerializedName("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMr_name() {
        return mr_name;
    }

    public void setMr_name(String mr_name) {
        this.mr_name = mr_name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
}
