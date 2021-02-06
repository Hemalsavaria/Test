package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.DoctorsAdapter;
import com.example.myapplication.Adapters.MRAttendanceAdapter;
import com.example.myapplication.Adapters.SeniorAttendanceAdapter;
import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.CityModel;
import com.example.myapplication.Model.MrAttendanceModel;
import com.example.myapplication.Model.Result;
import com.example.myapplication.Model.SeniorAttendanceModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Attendence extends AppCompatActivity {
    TextView attendence_date;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String TAG = "Attendence";
    String month_new, date_new;
    String select_month_data[] = {"Today", "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String filter_type_data[] = {"All", "MR", "Senior"};
    String present_absent_data[] = {"All", "Present", "Absent"};
    MaterialSpinner select_month, filter_type, present_absent;
    ImageView filter;

    RecyclerView recyclerView;

    ArrayList<MrAttendanceModel> mrAttendancelist = new ArrayList<>();
    ArrayList<SeniorAttendanceModel> seniorAttendanceModels = new ArrayList<>();
    MRAttendanceAdapter mrAttendanceAdapter;
    SeniorAttendanceAdapter seniorAttendanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        Log.d("Current_date", formattedDate);
        get_attendance(formattedDate, "3", "1");

    }

    public void init() {

        recyclerView = findViewById(R.id.attendence_recycle);

        recyclerView = findViewById(R.id.attendence_recycle);
        mrAttendanceAdapter = new MRAttendanceAdapter(mrAttendancelist, Attendence.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Attendence.this));
        recyclerView.setAdapter(mrAttendanceAdapter);
        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        filter = findViewById(R.id.filter);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_filter();
            }
        });

    }

    public void dialog_filter() {
        final Dialog dialog = new Dialog(Attendence.this, R.style.MyAlertDialogStyle);
        dialog.setContentView(R.layout.dialog_filter);


        Button filter_button = dialog.findViewById(R.id.filter_button);
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        filter_type = dialog.findViewById(R.id.select_type);
        ArrayAdapter<String> adapter_select_type = new ArrayAdapter<String>(Attendence.this, R.layout.spinner_item, filter_type_data);
        filter_type.setAdapter(adapter_select_type);

        select_month = dialog.findViewById(R.id.select_month);
        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(Attendence.this, R.layout.spinner_item, select_month_data);
        select_month.setAdapter(adapter_month);

        present_absent = dialog.findViewById(R.id.present_absent);
        ArrayAdapter<String> adapter_present_absent = new ArrayAdapter<String>(Attendence.this, R.layout.spinner_item, present_absent_data);
        present_absent.setAdapter(adapter_present_absent);


        attendence_date = dialog.findViewById(R.id.attendence_date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());
        attendence_date.setText(date);

        attendence_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Attendence.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Log.d(TAG, "month.." + monthOfYear);
                                Log.d(TAG, "mDay.." + dayOfMonth);

                                if (monthOfYear < 10) {
                                    month_new = "0" + (monthOfYear + 1);
                                } else {
                                    month_new = String.valueOf((monthOfYear + 1));
                                }

                                if (dayOfMonth < 10) {
                                    date_new = "0" + (dayOfMonth);
                                } else {
                                    date_new = String.valueOf((dayOfMonth));
                                }

                                attendence_date.setText(date_new + "-" + (month_new) + "-" + year);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
        });

        dialog.show();


    }

    public void select_date() {


    }

    void get_attendance(String date, String status, String filter_type) {

        final ProgressDialog progressDialog = new ProgressDialog(Attendence.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_attendance(date, status, filter_type);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        mrAttendancelist.clear();
                        mrAttendancelist.addAll(response.body().getMr_attendance_list());
                        mrAttendanceAdapter.notifyDataSetChanged();
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

}