package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class DoctorReportModel {


    @SerializedName("remarks")
    private String remarks;

    @SerializedName("visit_status")
    private String visit_status;

    @SerializedName("visit_date")
    private String visit_date;

    @SerializedName("senior_name")
    private String senior_name;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVisit_status() {
        return visit_status;
    }

    public void setVisit_status(String visit_status) {
        this.visit_status = visit_status;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getSenior_name() {
        return senior_name;
    }

    public void setSenior_name(String senior_name) {
        this.senior_name = senior_name;
    }
}
