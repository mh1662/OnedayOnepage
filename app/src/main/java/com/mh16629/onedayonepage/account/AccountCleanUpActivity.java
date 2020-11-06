package com.mh16629.onedayonepage.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.ActivityAccountCleanUpBinding;
import com.mh16629.onedayonepage.util.FirebaseOdOpAuth;

public class AccountCleanUpActivity extends AppCompatActivity {

    private static final String TAG = "AccountCleanUpActivity";

    private Context mContext;
    private FirebaseOdOpAuth mAuth;
    private ActivityAccountCleanUpBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_clean_up);
    }
}