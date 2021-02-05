package com.example.myapplication;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Adapters.DoctorsAdapter;
import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.AreaModel;
import com.example.myapplication.Model.CityModel;
import com.example.myapplication.Model.DoctorModel;
import com.example.myapplication.Model.Result;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Doctors extends AppCompatActivity {

    ArrayList<DoctorModel> Doctor_list = new ArrayList<>();
    DoctorsAdapter doctorsAdapter;
    RecyclerView recyclerView;
    DatePickerDialog datepicker;
    Dialog dialog_new_doctor;
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
        setContentView(R.layout.activity_doctors);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color));
        }

        cityarrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, city_name);
        areaarrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, area_name);

        recyclerView = findViewById(R.id.recylerview);
        doctorsAdapter = new DoctorsAdapter(Doctor_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(Doctors.this));
        recyclerView.setAdapter(doctorsAdapter);
        get_allDoctors();
        get_citylist();
        get_arealist();
    }

    void get_allDoctors() {

        final ProgressDialog progressDialog = new ProgressDialog(Doctors.this);
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
                        Doctor_list.addAll(response.body().getDoctor_list());
                        doctorsAdapter.notifyDataSetChanged();
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

        final ProgressDialog progressDialog = new ProgressDialog(Doctors.this);
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

        final ProgressDialog progressDialog = new ProgressDialog(Doctors.this);
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
        dialog_new_doctor = new Dialog(Doctors.this, R.style.MyAlertDialogStyle);
        dialog_new_doctor.setContentView(R.layout.dialog_add_doctor);
        sp_city = dialog_new_doctor.findViewById(R.id.city_list);
        sp_area = dialog_new_doctor.findViewById(R.id.area_list);

        Button cancel = dialog_new_doctor.findViewById(R.id.cancel);
        Button submit = dialog_new_doctor.findViewById(R.id.submit);

        final EditText Name = dialog_new_doctor.findViewById(R.id.mr_name);
        final EditText Contact = dialog_new_doctor.findViewById(R.id.mr_contact);
        final EditText Email = dialog_new_doctor.findViewById(R.id.mr_email);
        final EditText hospital_name = dialog_new_doctor.findViewById(R.id.hospital_name);
        final EditText birth_date = dialog_new_doctor.findViewById(R.id.birth_date);
        final EditText Ann_date = dialog_new_doctor.findViewById(R.id.Ann_date);
        final EditText practice_date = dialog_new_doctor.findViewById(R.id.practice_date);

        sp_city.setAdapter(cityarrayAdapter);
        sp_area.setAdapter(areaarrayAdapter);
        dialog_new_doctor.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_new_doctor.dismiss();
            }
        });

        birth_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(Doctors.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                birth_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
            }
        });

        Ann_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(Doctors.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Ann_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
            }
        });


        practice_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(Doctors.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                practice_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);
                datepicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datepicker.show();
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
                } else if (hospital_name.getText().toString().length() <= 0) {
                    hospital_name.setError("Required");
                } else if (birth_date.getText().toString().length() <= 0) {
                    birth_date.setError("Required");
                } else if (Ann_date.getText().toString().length() <= 0) {
                    Ann_date.setError("Required");
                } else if (practice_date.getText().toString().length() <= 0) {
                    practice_date.setError("Required");
                }
//                 else if (Email.getText().toString().matches(email_regx)==false) {
//                    Email.setError("Enter Valid Email");
                //      }
                else if (sp_area.getSelectedItemPosition() == 0) {
                    Toast.makeText(Doctors.this, "Select Area", Toast.LENGTH_SHORT).show();
                } else if (sp_city.getSelectedItemPosition() == 0) {
                    Toast.makeText(Doctors.this, "Select City", Toast.LENGTH_SHORT).show();
                } else {
                    String city_id = all_city_list.get(sp_city.getSelectedItemPosition() - 1).getId();
                    String area_id = all_area_list.get(sp_area.getSelectedItemPosition() - 1).getId();
                    add_new_doctor(Name.getText().toString(), Contact.getText().toString(), Email.getText().toString(), hospital_name.getText().toString(), area_id, city_id, birth_date.getText().toString(), Ann_date.getText().toString(), practice_date.getText().toString());
                }

            }
        });
    }

    private void add_new_doctor(String name, String contact, String email, String medical_name, String area, String city, String bithdate, String anny, String practice) {
        final ProgressDialog progressDialog = new ProgressDialog(Doctors.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.add_Doctor(name, contact, email, medical_name, bithdate, anny, practice, area, city);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        dialog_new_doctor.dismiss();
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        get_allDoctors();
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