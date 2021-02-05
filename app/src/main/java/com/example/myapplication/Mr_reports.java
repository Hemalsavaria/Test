package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.myapplication.Model.ChemistModel;
import com.example.myapplication.Model.DoctorModel;
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

    ArrayList<ChemistModel> chemist_list = new ArrayList<>();
    ArrayList<DoctorModel> Doctor_list = new ArrayList<>();
    ArrayList<String> doctor_chemist_name_list = new ArrayList<>();

    ArrayAdapter arrayAdapter;
    RecyclerView recyclerView;
    TextView name, doctors, chemist;
    String MR_id;
    Dialog dialog_new_allocation;
    int selected_type = 0;
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

        MR_id = getIntent().getStringExtra("mr_id");
        String mr_name = getIntent().getStringExtra("mr_name");
        name = findViewById(R.id.mr_name);
        name.setText(mr_name);

        recyclerView = findViewById(R.id.recylerview);
        mrDoctorReportsAdapter = new MRDoctorListAdapter(mr_doctor_reports, Mr_reports.this);
        mrChemistReportsAdapter = new MRChemistListAdapter(mr_chemist_models, Mr_reports.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(Mr_reports.this));
        recyclerView.setAdapter(mrDoctorReportsAdapter);
        get_Doctors_reports(MR_id);

        doctors.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                doctors.setBackgroundColor(getResources().getColor(R.color.toolbar_color));
                doctors.setTextColor(getResources().getColor(R.color.white));

                chemist.setBackgroundColor(getResources().getColor(R.color.white));
                chemist.setTextColor(getResources().getColor(R.color.toolbar_color));
                recyclerView.setAdapter(mrDoctorReportsAdapter);
                get_Doctors_reports(MR_id);
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
                get_Chemist_reports(MR_id);
            }
        });


    }

    void get_allDoctors() {

        final ProgressDialog progressDialog = new ProgressDialog(Mr_reports.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_all_doctor_list("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        Doctor_list.clear();
                        doctor_chemist_name_list.clear();
                        Doctor_list.addAll(response.body().getDoctor_list());
                        for (int i = 0; i < Doctor_list.size(); i++) {
                            doctor_chemist_name_list.add(Doctor_list.get(i).getDoctor_name());
                        }
                        arrayAdapter.notifyDataSetChanged();
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

    void get_allChemist() {

        final ProgressDialog progressDialog = new ProgressDialog(Mr_reports.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_all_Chemist_list("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        chemist_list.clear();
                        doctor_chemist_name_list.clear();
                        chemist_list.addAll(response.body().getChemist_list());
                        for (int i = 0; i < chemist_list.size(); i++) {
                            doctor_chemist_name_list.add(chemist_list.get(i).getChemist_name());
                        }
                        arrayAdapter.notifyDataSetChanged();
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

    void allocate_doctor_to_mr() {

        final ProgressDialog progressDialog = new ProgressDialog(Mr_reports.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_all_doctor_list("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        Doctor_list.clear();
                        doctor_chemist_name_list.clear();
                        Doctor_list.addAll(response.body().getDoctor_list());
                        for (int i = 0; i < Doctor_list.size(); i++) {
                            doctor_chemist_name_list.add(Doctor_list.get(i).getDoctor_name());
                        }
                        arrayAdapter.notifyDataSetChanged();
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

    void allocate_chemist_to_mr() {

        final ProgressDialog progressDialog = new ProgressDialog(Mr_reports.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_all_doctor_list("");

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        Doctor_list.clear();
                        doctor_chemist_name_list.clear();
                        Doctor_list.addAll(response.body().getDoctor_list());
                        for (int i = 0; i < Doctor_list.size(); i++) {
                            doctor_chemist_name_list.add(Doctor_list.get(i).getDoctor_name());
                        }
                        arrayAdapter.notifyDataSetChanged();
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



    private void open_dialog() {


        dialog_new_allocation = new Dialog(Mr_reports.this, R.style.MyAlertDialogStyle);
        dialog_new_allocation.setContentView(R.layout.dialog_allocate_doctor_to_mr);

        get_allDoctors();

        Spinner spn_selection_type = dialog_new_allocation.findViewById(R.id.select_type);
        Spinner spn_doctor_chemist_names = dialog_new_allocation.findViewById(R.id.names_list);
        Spinner visit_count = dialog_new_allocation.findViewById(R.id.visit_count);

        ArrayAdapter spn_type_adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, getResources().getTextArray(R.array.select_type_mr));
        ArrayAdapter spn_visit_adapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, getResources().getTextArray(R.array.count));
        arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, doctor_chemist_name_list);

        spn_selection_type.setAdapter(spn_type_adapter);
        spn_doctor_chemist_names.setAdapter(arrayAdapter);
        visit_count.setAdapter(spn_visit_adapter);

        Button cancel = dialog_new_allocation.findViewById(R.id.cancel);
        Button submit = dialog_new_allocation.findViewById(R.id.submit);

        spn_selection_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    selected_type = position;
                    get_allDoctors();
                } else {
                    selected_type = position;
                    get_allChemist();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_new_allocation.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected_type == 0) {
                    allocate_doctor_to_mr();
                } else {
                    allocate_chemist_to_mr();
                }
            }
        });

        dialog_new_allocation.show();
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