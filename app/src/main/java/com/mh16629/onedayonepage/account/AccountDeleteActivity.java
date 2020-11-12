package com.mh16629.onedayonepage.account;

import androidx.appcompat.app.AppCompatActivity;
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.ActivityAccountDeleteBinding;
import com.mh16629.onedayonepage.firebase.FirebaseOdOpAuth;

import android.content.Context;
import android.os.Bundle;

public class AccountDeleteActivity extends AppCompatActivity {

    private static final String TAG = "AccountDeleteActivity";

    private Context mContext;
    private FirebaseOdOpAuth mAuth;
    private ActivityAccountDeleteBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_delete);
    }
}