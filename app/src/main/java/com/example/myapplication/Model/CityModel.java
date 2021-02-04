package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class CityModel {


    @SerializedName("id")
    private String id;

    @SerializedName("city_name")
    private String city_name;

    @SerializedName("city_visit_charge")
    private String city_visit_charge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_visit_charge() {
        return city_visit_charge;
    }

    public void setCity_visit_charge(String city_visit_charge) {
        this.city_visit_charge = city_visit_charge;
    }
}
