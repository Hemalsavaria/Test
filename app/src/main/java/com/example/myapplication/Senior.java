package com.example.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.example.myapplication.Model.PostModel;
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
    ImageView back, add_senior;
    Dialog dialog_new_senior;
    Spinner post;
    ArrayAdapter postAdapter;
    ArrayList<PostModel> post_list = new ArrayList<>();
    ArrayList<String> post_name = new ArrayList<>();

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

        //  add_senior = findViewById(R.id.add_senior);
        postAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, post_name);

        recyclerView = findViewById(R.id.recylerview);
        seniorAdapter = new SeniorAdapter(seniorlist, Senior.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Senior.this));
        recyclerView.setAdapter(seniorAdapter);
        get_allSenior();
        get_post();

//        add_senior.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                add_senior_dialog();
//            }
//        });
    }

    void get_allSenior() {

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

    void get_post() {

        final ProgressDialog progressDialog = new ProgressDialog(Senior.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_post("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        post_list.clear();
                        post_name.clear();
                        post_name.add("Select Post");
                        post_list.addAll(response.body().getPost_list());
                        for (int i = 0; i < post_list.size(); i++) {
                            post_name.add(post_list.get(i).getPost_name());
                        }
                        postAdapter.notifyDataSetChanged();
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
                add_senior_dialog();
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


    public void add_senior_dialog() {
        dialog_new_senior = new Dialog(Senior.this, R.style.MyAlertDialogStyle);
        dialog_new_senior.setContentView(R.layout.dialog_add_senior);

        Button cancel = dialog_new_senior.findViewById(R.id.cancel);
        Button submit = dialog_new_senior.findViewById(R.id.submit);

        final EditText Name = dialog_new_senior.findViewById(R.id.mr_name);
        final EditText Contact = dialog_new_senior.findViewById(R.id.mr_contact);
        final EditText Email = dialog_new_senior.findViewById(R.id.mr_email);

        post = dialog_new_senior.findViewById(R.id.post_list);

        post.setAdapter(postAdapter);


        dialog_new_senior.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_new_senior.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name.getText().toString().length() <= 0) {
                    Name.setError("Required");
                } else if (Contact.getText().toString().length() <= 0) {
                    Contact.setError("Required");
                } else if (Contact.getText().toString().length() < 10) {
                    Contact.setError("Enter Valid Mobile Number");
                } else if (Email.getText().toString().length() <= 0) {
                    Email.setError("Required");
                }
//                 else if (Email.getText().toString().matches(email_regx)==false) {
//                    Email.setError("Enter Valid Email");
                //      }
                else if (post.getSelectedItemPosition() == 0) {
                    Toast.makeText(Senior.this, "Select Post", Toast.LENGTH_SHORT).show();
                } else {
                    String post_id = post_list.get(post.getSelectedItemPosition()-1).getId();
                    add_new_senior(Name.getText().toString(), Contact.getText().toString(), Email.getText().toString(), post_id);
                }
            }
        });

    }

    private void add_new_senior(String name, String contact, String email, String post) {
        final ProgressDialog progressDialog = new ProgressDialog(Senior.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.add_senior(name, contact, email, post);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        dialog_new_senior.dismiss();
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        get_allSenior();
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
}