package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.DoctorReportAdapter;
import com.example.myapplication.Adapters.MRChemistListAdapter;
import com.example.myapplication.Adapters.MRDoctorListAdapter;
import com.example.myapplication.Adapters.SeniorAdapter;
import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.DoctorReportModel;
import com.example.myapplication.Model.MR_chemist_Model;
import com.example.myapplication.Model.MR_doctor_Model;
import com.example.myapplication.Model.Result;
import com.example.myapplication.Model.SeniorModel;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Senior extends AppCompatActivity {

    ArrayList<SeniorModel> seniorlist = new ArrayList<>();
    SeniorAdapter seniorAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color));
        }

        recyclerView = findViewById(R.id.recylerview);
        seniorAdapter = new SeniorAdapter(seniorlist, Senior.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Senior.this));
        recyclerView.setAdapter(seniorAdapter);
        get_allDoctors();

    }

    void get_allDoctors() {

        final ProgressDialog progressDialog = new ProgressDialog(Senior.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_all_senior_list("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        seniorlist.clear();
                        seniorlist.addAll(response.body().getSenior_list());
                        seniorAdapter.notifyDataSetChanged();
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