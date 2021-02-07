package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jaredrummler.materialspinner.MaterialSpinner;

public class MR_expense extends AppCompatActivity {
    MaterialSpinner mr_expense_moth;
    String mr_expense_moth_data[] = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_r_expense);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    public void init() {
        mr_expense_moth = findViewById(R.id.mr_expense_moth);
        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(MR_expense.this, R.layout.spinner_item, mr_expense_moth_data);
        mr_expense_moth.setAdapter(adapter_month);
    }
}