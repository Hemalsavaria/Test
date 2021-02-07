package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class WorkplaceModel {


    @SerializedName("id")
    private String id;

    @SerializedName("workplace_name")
    private String workplace_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkplace_name() {
        return workplace_name;
    }

    public void setWorkplace_name(String workplace_name) {
        this.workplace_name = workplace_name;
    }
}

