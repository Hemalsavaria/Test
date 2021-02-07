package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.myapplication.Adapters.DoctorsAdapter;
import com.example.myapplication.Adapters.WorkPlaceAdapter;
import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.DoctorModel;
import com.example.myapplication.Model.Result;
import com.example.myapplication.Model.WorkplaceModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Workplace extends AppCompatActivity implements WorkPlaceAdapter.WorkplaceInterface {
    ArrayList<WorkplaceModel> workplaceList = new ArrayList<>();
    WorkPlaceAdapter workPlaceAdapter;
    RecyclerView recyclerView;
    Button add;
    EditText workplace_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplace);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        get_workPlaceList();
    }

    void init() {

        recyclerView = findViewById(R.id.recylerview);
        workPlaceAdapter = new WorkPlaceAdapter(workplaceList, Workplace.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Workplace.this));
        recyclerView.setAdapter(workPlaceAdapter);
        workplace_name = findViewById(R.id.workplace_name);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workplace_name.getText().equals("") || workplace_name.getText().toString().length() <= 0) {
                    workplace_name.setError("Required");
                } else {
                    add_workplace(workplace_name.getText().toString());
                }
            }
        });
    }

    void get_workPlaceList() {

        final ProgressDialog progressDialog = new ProgressDialog(Workplace.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_work_place("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        workplaceList.clear();
                        workplaceList.addAll(response.body().getWork_place());
                        workPlaceAdapter.notifyDataSetChanged();
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
    public void delete_workplace(String id) {
        final ProgressDialog progressDialog = new ProgressDialog(Workplace.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.delete_workplace(id);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        //  Toast.makeText(Workplace.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        get_workPlaceList();
                    } else {
                        Toast.makeText(Workplace.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Workplace.this, "Somethings are Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void add_workplace(String name) {
        final ProgressDialog progressDialog = new ProgressDialog(Workplace.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.add_workplace(name);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        workplace_name.setText("");
                        //  Toast.makeText(Workplace.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        get_workPlaceList();
                    } else {
                        Toast.makeText(Workplace.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Workplace.this, "Somethings are Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}