package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class SeniorModel {


    @SerializedName("id")
    private String id;

    @SerializedName("senior_name")
    private String senior_name;

    @SerializedName("contact_no")
    private String contact_no;

    @SerializedName("password")
    private String password;



    @SerializedName("email")
    private String email;

    @SerializedName("post_name")
    private String post_name;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenior_name() {
        return senior_name;
    }

    public void setSenior_name(String senior_name) {
        this.senior_name = senior_name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }
}
