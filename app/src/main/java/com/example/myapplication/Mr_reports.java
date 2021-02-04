package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.MRChemistListAdapter;
import com.example.myapplication.Adapters.MRDoctorListAdapter;
import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.MR_chemist_Model;
import com.example.myapplication.Model.MR_doctor_Model;
import com.example.myapplication.Model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mr_reports extends AppCompatActivity {

    ArrayList<MR_doctor_Model> mr_doctor_reports = new ArrayList<>();
    ArrayList<MR_chemist_Model> mr_chemist_models = new ArrayList<>();
    MRDoctorListAdapter mrDoctorReportsAdapter;
    MRChemistListAdapter mrChemistReportsAdapter;

    RecyclerView recyclerView;
    TextView name, doctors, chemist;
    String id;


    Spinner select_month, selecyear;
    String select_month_data[] = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String selecyear_data[] = {"2021", "2020", "2019"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mr_reports);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color));
        }

        doctors = findViewById(R.id.doctors);
        chemist = findViewById(R.id.chemist);
        select_month = findViewById(R.id.select_month);
        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(Mr_reports.this, R.layout.spinner_item, select_month_data);
        select_month.setAdapter(adapter_month);

        selecyear = findViewById(R.id.selectyear);
        ArrayAdapter<String> adapter_selecyear = new ArrayAdapter<String>(Mr_reports.this, R.layout.spinner_item, selecyear_data);
        selecyear.setAdapter(adapter_selecyear);

        id = getIntent().getStringExtra("mr_id");
        String mr_name = getIntent().getStringExtra("mr_name");
        name = findViewById(R.id.mr_name);
        name.setText(mr_name);

        recyclerView = findViewById(R.id.recylerview);
        mrDoctorReportsAdapter = new MRDoctorListAdapter(mr_doctor_reports, Mr_reports.this);
        mrChemistReportsAdapter = new MRChemistListAdapter(mr_chemist_models, Mr_reports.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(Mr_reports.this));
        recyclerView.setAdapter(mrDoctorReportsAdapter);
        get_Doctors_reports(id);

        doctors.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                doctors.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
                doctors.setTextColor(getResources().getColor(R.color.white));

                chemist.setBackgroundColor(getResources().getColor(R.color.white));
                chemist.setTextColor(getResources().getColor(R.color.toolbar_color));
                recyclerView.setAdapter(mrDoctorReportsAdapter);
                get_Doctors_reports(id);
            }
        });

        chemist.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                chemist.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
                chemist.setTextColor(getResources().getColor(R.color.white));

                doctors.setBackgroundColor(getResources().getColor(R.color.white));
                doctors.setTextColor(getResources().getColor(R.color.toolbar_color));
                recyclerView.setAdapter(mrChemistReportsAdapter);
                get_Chemist_reports(id);
            }
        });


    }


    void get_Doctors_reports(String id) {

        final ProgressDialog progressDialog = new ProgressDialog(Mr_reports.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_mr_docotor_list(id);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        mr_doctor_reports.clear();
                        mr_doctor_reports.addAll(response.body().getDoctors_data());
                        mrDoctorReportsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Somethings are Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void get_Chemist_reports(String id) {

        final ProgressDialog progressDialog = new ProgressDialog(Mr_reports.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_mr_chemist_list(id);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        mr_chemist_models.clear();
                        mr_chemist_models.addAll(response.body().getChemist_data());
                        mrChemistReportsAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Somethings are Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}