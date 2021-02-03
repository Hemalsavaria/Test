package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button login_button;
    EditText login_number, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.toolbar_color));
        }

        inti();
    }

    void inti() {
        login_button = findViewById(R.id.login_button);
        login_number = findViewById(R.id.login_number);
        login_password = findViewById(R.id.login_password);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (login_number.getText().toString().length() == 0) {
//                    login_number.setError("Enter Mobile Number");
//                } else if (login_number.getText().toString().length() != 10) {
//                    login_number.setError("Enter Valid Mobile Number");
//                } else if (login_password.getText().toString().equals("")) {
//                    login_password.setError("Enter Password");
//                } else {
//                    finish();
//                    startActivity(new Intent(MainActivity.this, DashBoard.class));
//                }

                finish();
                startActivity(new Intent(MainActivity.this, DashBoard.class));
            }
        });
    }
}
