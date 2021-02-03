package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class SeniorMrListModel {

    @SerializedName("senior_id")
    private String senior_id;

    @SerializedName("mr_id")
    private String mr_id;

    @SerializedName("senior_name")
    private String senior_name;

    @SerializedName("mr_name")
    private String mr_name;

    @SerializedName("city_name")
    private String city_name;

    @SerializedName("area_name")
    private String area_name;

    public String getMr_name() {
        return mr_name;
    }

    public void setMr_name(String mr_name) {
        this.mr_name = mr_name;
    }

    public String getSenior_id() {
        return senior_id;
    }

    public void setSenior_id(String senior_id) {
        this.senior_id = senior_id;
    }

    public String getMr_id() {
        return mr_id;
    }

    public void setMr_id(String mr_id) {
        this.mr_id = mr_id;
    }

    public String getSenior_name() {
        return senior_name;
    }

    public void setSenior_name(String senior_name) {
        this.senior_name = senior_name;
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
