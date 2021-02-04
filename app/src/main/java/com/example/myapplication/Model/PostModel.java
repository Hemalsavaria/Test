package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class PostModel {


    @SerializedName("id")
    private String id;

    @SerializedName("post_name")
    private String post_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }
}
