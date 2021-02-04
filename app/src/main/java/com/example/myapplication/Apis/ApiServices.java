package com.example.myapplication.Apis;


import com.example.myapplication.Model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("admin_login.php")
    Call<Result> Login(
            @Field("emp_id") String emp_id,
            @Field("emp_pass") String emp_pass,
            @Field("token") String token
    );


    @FormUrlEncoded
    @POST("get_city")
    Call<Result> get_city(
            @Field("test") String test
    );

    @FormUrlEncoded
    @POST("get_area")
    Call<Result> get_area(
            @Field("test") String test
    );

    @FormUrlEncoded
    @POST("get_post")
    Call<Result> get_post(
            @Field("test") String test
    );


    @FormUrlEncoded
    @POST("get_all_doctor_list")
    Call<Result> get_all_doctor_list(
            @Field("test") String test
    );

    @FormUrlEncoded
    @POST("get_all_Chemist_list")
    Call<Result> get_all_Chemist_list(
            @Field("test") String test
    );

    @FormUrlEncoded
    @POST("get_all_mr_list")
    Call<Result> get_all_mr_list(
            @Field("test") String test
    );

    @FormUrlEncoded
    @POST("get_all_senior_list")
    Call<Result> get_all_senior_list(
            @Field("test") String test
    );


    @FormUrlEncoded
    @POST("get_mr_docotor_list")
    Call<Result> get_mr_docotor_list(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("get_mr_chemist_list")
    Call<Result> get_mr_chemist_list(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("get_senior_mr_list")
    Call<Result> get_senior_mr_list(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("get_mr_report_for_doctor")
    Call<Result> get_mr_report_for_doctor(
            @Field("mr_id") String mr_id,
            @Field("dr_id") String dr_id,
            @Field("month") String month
    );

    @FormUrlEncoded
    @POST("get_mr_report_for_chemist")
    Call<Result> get_mr_report_for_chemist(
            @Field("mr_id") String mr_id,
            @Field("chemist_id") String chemist_id,
            @Field("month") String month
    );

    @FormUrlEncoded
    @POST("add_MR")
    Call<Result> add_MR(
            @Field("mr_name") String mr_name,
            @Field("contact") String contact,
            @Field("emailid") String emailid,
            @Field("area") String area,
            @Field("city") String city
    );

    @FormUrlEncoded
    @POST("add_senior")
    Call<Result> add_senior(
            @Field("senior_name") String senior_name,
            @Field("contact") String contact,
            @Field("emailid") String emailid,
            @Field("post") String post
    );

    @FormUrlEncoded
    @POST("add_Doctor")
    Call<Result> add_Doctor(
            @Field("doctor_name") String mr_name,
            @Field("contact") String contact,
            @Field("emailid") String emailid,
            @Field("medical_hospital") String medical_hospital,
            @Field("birth_date") String birth_date,
            @Field("Ann_date") String Ann_date,
            @Field("pra_date") String pra_date,
            @Field("area") String area,
            @Field("city") String city
    );


    @FormUrlEncoded
    @POST("add_Chemist")
    Call<Result> add_Chemist(
            @Field("name") String mr_name,
            @Field("contact") String contact,
            @Field("emailid") String emailid,
            @Field("medical_hospital") String medical_hospital,
            @Field("birth_date") String birth_date,
            @Field("Ann_date") String Ann_date,
            @Field("pra_date") String pra_date,
            @Field("area") String area,
            @Field("city") String city
    );

}
