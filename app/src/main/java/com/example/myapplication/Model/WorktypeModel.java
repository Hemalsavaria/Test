package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class WorktypeModel {


    @SerializedName("id")
    private String id;

    @SerializedName("work_type")
    private String work_type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWork_type() {
        return work_type;
    }

    public void setWork_type(String work_type) {
        this.work_type = work_type;
    }
}

