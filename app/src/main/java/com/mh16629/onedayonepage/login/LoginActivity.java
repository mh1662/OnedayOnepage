package com.mh16629.onedayonepage.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.firebase.ui.auth.AuthUI;
//import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.firebase.storage.FirebaseStorage;
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.account.SignOutDialog;
import com.mh16629.onedayonepage.booksearch.BookSearchActivity;
import com.mh16629.onedayonepage.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity implements
        View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // Button listeners
        mBinding.loginButtonGoogle.setOnClickListener(this);
        mBinding.loginButtonAnonymous.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.login_button_google) {
            signInGoogle();
        } else if (i == R.id.login_button_anonymous) {
            signInAnonymous();
        }
    }

    /**
     * Google 로그인
     */
    private void signInGoogle() {
        Log.d(TAG, "signInGoogle");
        Intent intentGoogleLogin = new Intent(getApplicationContext(), GoogleSignInActivity.class);
        startActivity(intentGoogleLogin);
    }

    /**
     * 익명 로그인
     */
    private void signInAnonymous() {
        Log.d(TAG, "signInAnonymous");

        AnonymousAlertDialog anonymousAlertDialog = new AnonymousAlertDialog(this);
        anonymousAlertDialog.setCancelable(true);
        anonymousAlertDialog.show();

//        Intent intentAnonymousLogin = new Intent(getApplicationContext(), AnonymousAuthActivity.class);
//        startActivity(intentAnonymousLogin);
    }

}
