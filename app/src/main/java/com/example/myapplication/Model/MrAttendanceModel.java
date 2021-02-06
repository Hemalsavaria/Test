package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class MrAttendanceModel {


    @SerializedName("mr_id")
    private String mr_id;

    @SerializedName("work_place")
    private String work_place;

    @SerializedName("work_type")
    private String work_type;

    @SerializedName("city")
    private String city;

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("status")
    private String status;

    @SerializedName("mr_name")
    private String mr_name;

    public String getMr_id() {
        return mr_id;
    }

    public void setMr_id(String mr_id) {
        this.mr_id = mr_id;
    }

    public String getWork_place() {
        return work_place;
    }

    public void setWork_place(String work_place) {
        this.work_place = work_place;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMr_name() {
        return mr_name;
    }

    public void setMr_name(String mr_name) {
        this.mr_name = mr_name;
    }
}
