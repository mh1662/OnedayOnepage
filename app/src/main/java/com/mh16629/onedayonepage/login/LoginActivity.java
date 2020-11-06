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
import com.mh16629.onedayonepage.account.AccountCleanUpActivity;
import com.mh16629.onedayonepage.account.SignOutDialog;
import com.mh16629.onedayonepage.booksearch.BookSearchActivity;
import com.mh16629.onedayonepage.databinding.ActivityLoginBinding;
import com.mh16629.onedayonepage.main.MainActivity;
import com.mh16629.onedayonepage.util.FirebaseOdOpAuth;

public class LoginActivity extends BaseActivity implements
        View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private FirebaseOdOpAuth mAuth;
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mAuth = new FirebaseOdOpAuth(this);

        // Button listeners
        mBinding.loginButtonLogin.setOnClickListener(this);
        mBinding.loginButtonGoogle.setOnClickListener(this);
        mBinding.loginButtonAnonymous.setOnClickListener(this);
        mBinding.loginSignInButton.setOnClickListener(this);
        mBinding.loginSignInBack.setOnClickListener(this);
        mBinding.loginSignInFindPassword.setOnClickListener(this);


        //레이아웃 초기화
        mBinding.loginChooseSignUpLayout.setVisibility(View.VISIBLE);
        mBinding.loginSignInLayout.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button_login: { changeSignInLayout(); break; }
            case R.id.login_button_google: { signInGoogle(); break; }
            case R.id.login_button_anonymous: { signInAnonymous(); break; }

            case R.id.login_signIn_button: { signIn(); break; }
            case R.id.login_signIn_back: {
                //로그인 방식 선택 레이아웃 표시
                mBinding.loginChooseSignUpLayout.setVisibility(View.VISIBLE);
                mBinding.loginSignInLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.login_signIn_find_password: { findPassword(); break;}
        }
    }

    /**
     * 기존 계정으로 로그인
     */
    private void signIn() {
        Log.d(TAG, "signIn");

        //FIXME: 이메일, 패스워드 입력체크


        mAuth.signIn(mBinding.loginSignInEmail.getText().toString(), mBinding.loginSignInPassword.getText().toString());

        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentMain);
    }

    private void findPassword() {

    }

    /**
     * 기존 계정으로 로그인(레이아웃 Visibility변경)
     */
    private void changeSignInLayout() {
        mBinding.loginChooseSignUpLayout.setVisibility(View.GONE);
        mBinding.loginSignInLayout.setVisibility(View.VISIBLE);
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
    }

}
