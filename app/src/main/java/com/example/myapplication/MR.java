package com.example.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Adapters.MRAdapter;
import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.MRModel;
import com.example.myapplication.Model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MR extends AppCompatActivity {

    ArrayList<MRModel> mr_list = new ArrayList<>();
    MRAdapter mrAdapter;
    RecyclerView recyclerView;
    LinearLayout layout_new_mr;
    boolean isvisible = false;
    Dialog dialog_new_mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_r);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color));
        }
        layout_new_mr = findViewById(R.id.layout_new_mr);

        recyclerView = findViewById(R.id.recylerview);
        mrAdapter = new MRAdapter(mr_list, MR.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MR.this));
        recyclerView.setAdapter(mrAdapter);
        get_allMR();

    }


    void get_allMR() {

        final ProgressDialog progressDialog = new ProgressDialog(MR.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_all_mr_list("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        mr_list.clear();
                        mr_list.addAll(response.body().getMr_list());
                        mrAdapter.notifyDataSetChanged();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.new_details:
                dialog_new_mr = new Dialog(MR.this, R.style.MyAlertDialogStyle);
                dialog_new_mr.setContentView(R.layout.dialog_add_mr);
                dialog_new_mr.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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