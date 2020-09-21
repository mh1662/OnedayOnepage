package com.mh16629.onedayonepage.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mh16629.onedayonepage.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setTitle(R.string.activity_login);

    }
}
