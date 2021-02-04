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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Adapters.MRAdapter;
import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.AreaModel;
import com.example.myapplication.Model.CityModel;
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
    Spinner sp_city, sp_area;
    ArrayList<CityModel> all_city_list = new ArrayList<>();
    ArrayList<String> city_name = new ArrayList<>();
    ArrayAdapter cityarrayAdapter;

    ArrayList<AreaModel> all_area_list = new ArrayList<>();
    ArrayList<String> area_name = new ArrayList<>();
    ArrayAdapter areaarrayAdapter;
    String email_regx = "^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";

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

        cityarrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, city_name);
        areaarrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, area_name);

        get_allMR();
        get_citylist();
        get_arealist();
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

    void get_citylist() {

        final ProgressDialog progressDialog = new ProgressDialog(MR.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_city("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        all_city_list.clear();
                        city_name.clear();
                        all_city_list.addAll(response.body().getCity_list());
                        city_name.add("Select Area");
                        for (int i = 0; i < all_city_list.size(); i++) {
                            city_name.add(response.body().getCity_list().get(i).getCity_name());
                        }
                        cityarrayAdapter.notifyDataSetChanged();
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

    void get_arealist() {

        final ProgressDialog progressDialog = new ProgressDialog(MR.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_area("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        all_area_list.clear();
                        area_name.clear();
                        all_area_list.addAll(response.body().getGet_area());
                        area_name.add("Select Area");
                        for (int i = 0; i < all_area_list.size(); i++) {
                            area_name.add(response.body().getGet_area().get(i).getArea_name());
                        }
                        areaarrayAdapter.notifyDataSetChanged();
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
                open_dialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void open_dialog() {
        dialog_new_mr = new Dialog(MR.this, R.style.MyAlertDialogStyle);
        dialog_new_mr.setContentView(R.layout.dialog_add_mr);
        sp_city = dialog_new_mr.findViewById(R.id.city_list);
        sp_area = dialog_new_mr.findViewById(R.id.area_list);

        Button cancel = dialog_new_mr.findViewById(R.id.cancel);
        Button submit = dialog_new_mr.findViewById(R.id.submit);

        final EditText Name = dialog_new_mr.findViewById(R.id.mr_name);
        final EditText Contact = dialog_new_mr.findViewById(R.id.mr_contact);
        final EditText Email = dialog_new_mr.findViewById(R.id.mr_email);

        sp_city.setAdapter(cityarrayAdapter);
        sp_area.setAdapter(areaarrayAdapter);
        dialog_new_mr.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_new_mr.dismiss();
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
                else if (sp_area.getSelectedItemPosition() == 0) {
                    Toast.makeText(MR.this, "Select Area", Toast.LENGTH_SHORT).show();
                } else if (sp_city.getSelectedItemPosition() == 0) {
                    Toast.makeText(MR.this, "Select City", Toast.LENGTH_SHORT).show();
                } else {
                    String city_id = all_city_list.get(sp_city.getSelectedItemPosition()-1).getId();
                    String area_id = all_area_list.get(sp_area.getSelectedItemPosition()-1).getId();
                    add_new_mr(Name.getText().toString(), Contact.getText().toString(), Email.getText().toString(), area_id, city_id);
                }

            }
        });
    }

    private void add_new_mr(String name, String contact, String email, String area, String city) {
        final ProgressDialog progressDialog = new ProgressDialog(MR.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.add_MR(name, contact, email, area, city);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        dialog_new_mr.dismiss();
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        get_allMR();
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