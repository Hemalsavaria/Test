package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class MR_chemist_Model {


    @SerializedName("mr_id")
    private String mr_id;

    @SerializedName("chemist_id")
    private String chemist_id;

    @SerializedName("total_visit")
    private String total_visit;

    @SerializedName("remain_visit")
    private String remain_visit;

    @SerializedName("chemist_name")
    private String chemist_name;

    @SerializedName("medical_hostpital_name")
    private String medical_hostpital_name;

    @SerializedName("city_name")
    private String password;

    @SerializedName("area_name")
    private String area_name;

    public String getMr_id() {
        return mr_id;
    }

    public void setMr_id(String mr_id) {
        this.mr_id = mr_id;
    }


    public String getTotal_visit() {
        return total_visit;
    }

    public void setTotal_visit(String total_visit) {
        this.total_visit = total_visit;
    }

    public String getRemain_visit() {
        return remain_visit;
    }

    public void setRemain_visit(String remain_visit) {
        this.remain_visit = remain_visit;
    }

    public String getChemist_id() {
        return chemist_id;
    }

    public void setChemist_id(String chemist_id) {
        this.chemist_id = chemist_id;
    }

    public String getChemist_name() {
        return chemist_name;
    }

    public void setChemist_name(String chemist_name) {
        this.chemist_name = chemist_name;
    }

    public String getMedical_hostpital_name() {
        return medical_hostpital_name;
    }

    public void setMedical_hostpital_name(String medical_hostpital_name) {
        this.medical_hostpital_name = medical_hostpital_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
}
