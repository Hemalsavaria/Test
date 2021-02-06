package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class SeniorAttendanceModel {


    @SerializedName("senior_id")
    private String senior_id;

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

    @SerializedName("senior_name")
    private String senior_name;

    public String getSenior_id() {
        return senior_id;
    }

    public void setSenior_id(String senior_id) {
        this.senior_id = senior_id;
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

    public String getSenior_name() {
        return senior_name;
    }

    public void setSenior_name(String senior_name) {
        this.senior_name = senior_name;
    }
}
