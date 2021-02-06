package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Analysis_dr_chemist_list extends AppCompatActivity {
    String select_month_data[] = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String filter_type_data[] = {"Dr", "Chemist"};

    Spinner select_month, filter_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_dr_chemist_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        filter_type = findViewById(R.id.dr_chemist);
        ArrayAdapter<String> adapter_select_type = new ArrayAdapter<String>(Analysis_dr_chemist_list.this, R.layout.spinner_item, filter_type_data);
        filter_type.setAdapter(adapter_select_type);

        select_month = findViewById(R.id.month);
        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(Analysis_dr_chemist_list.this, R.layout.spinner_item, select_month_data);
        select_month.setAdapter(adapter_month);

    }
}