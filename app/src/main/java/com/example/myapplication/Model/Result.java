package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {


    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private String msg;

    @SerializedName("doctor_list")
    private ArrayList<DoctorModel> doctor_list;

    @SerializedName("chemist_list")
    private ArrayList<ChemistModel> chemist_list;

    @SerializedName("mr_list")
    private ArrayList<MRModel> mr_list;

    @SerializedName("doctors_data")
    private ArrayList<MR_doctor_Model> doctors_data;

    @SerializedName("chemist_data")
    private ArrayList<MR_chemist_Model> chemist_data;

    @SerializedName("senior_list")
    private ArrayList<SeniorModel> senior_list;


    public ArrayList<SeniorMrListModel> getSeniorMrList() {
        return SeniorMrList;
    }

    public void setSeniorMrList(ArrayList<SeniorMrListModel> seniorMrList) {
        SeniorMrList = seniorMrList;
    }

    @SerializedName("mr_data")
    private ArrayList<SeniorMrListModel> SeniorMrList;



    public ArrayList<SeniorModel> getSenior_list() {
        return senior_list;
    }

    public void setSenior_list(ArrayList<SeniorModel> senior_list) {
        this.senior_list = senior_list;
    }

    public ArrayList<MR_chemist_Model> getChemist_data() {
        return chemist_data;
    }

    public void setChemist_data(ArrayList<MR_chemist_Model> chemist_data) {
        this.chemist_data = chemist_data;
    }

    @SerializedName("doctor_reports")
    private ArrayList<DoctorReportModel> doctor_reports;

    @SerializedName("chemist_reports")
    private ArrayList<DoctorReportModel> chemist_reports;

    public ArrayList<DoctorReportModel> getChemist_reports() {
        return chemist_reports;
    }

    public void setChemist_reports(ArrayList<DoctorReportModel> chemist_reports) {
        this.chemist_reports = chemist_reports;
    }

    public ArrayList<DoctorReportModel> getDoctor_reports() {
        return doctor_reports;
    }

    public void setDoctor_reports(ArrayList<DoctorReportModel> doctor_reports) {
        this.doctor_reports = doctor_reports;
    }

    public ArrayList<MR_doctor_Model> getDoctors_data() {
        return doctors_data;
    }

    public void setDoctors_data(ArrayList<MR_doctor_Model> doctors_data) {
        this.doctors_data = doctors_data;
    }

    public ArrayList<MRModel> getMr_list() {
        return mr_list;
    }

    public void setMr_list(ArrayList<MRModel> mr_list) {
        this.mr_list = mr_list;
    }

    public ArrayList<ChemistModel> getChemist_list() {
        return chemist_list;
    }

    public void setChemist_list(ArrayList<ChemistModel> chemist_list) {
        this.chemist_list = chemist_list;
    }

    public ArrayList<DoctorModel> getDoctor_list() {
        return doctor_list;
    }

    public void setDoctor_list(ArrayList<DoctorModel> doctor_list) {
        this.doctor_list = doctor_list;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
