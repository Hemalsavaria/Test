package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class ChemistModel {


    @SerializedName("id")
    private String id;

    @SerializedName("chemist_name")
    private String chemist_name;

    @SerializedName("email_id")
    private String email_id;

    @SerializedName("medical_hostpital_name")
    private String medical_hostpital_name;

    @SerializedName("birth_date")
    private String birth_date;

    @SerializedName("Ann_date")
    private String Ann_date;

    @SerializedName("pra_date")
    private String pra_date;

    @SerializedName("city_name")
    private String city_name;

    @SerializedName("area_name")
    private String area_name;

    @SerializedName("contact_no")
    private String contact_no;

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChemist_name() {
        return chemist_name;
    }

    public void setChemist_name(String chemist_name) {
        this.chemist_name = chemist_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getMedical_hostpital_name() {
        return medical_hostpital_name;
    }

    public void setMedical_hostpital_name(String medical_hostpital_name) {
        this.medical_hostpital_name = medical_hostpital_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getAnn_date() {
        return Ann_date;
    }

    public void setAnn_date(String ann_date) {
        Ann_date = ann_date;
    }

    public String getPra_date() {
        return pra_date;
    }

    public void setPra_date(String pra_date) {
        this.pra_date = pra_date;
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
