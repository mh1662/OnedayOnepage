package com.mh16629.onedayonepage.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.ActivityLoginBinding;
import com.mh16629.onedayonepage.main.MainActivity;
import com.mh16629.onedayonepage.firebase.FirebaseOdOpAuth;
import com.mh16629.onedayonepage.util.InputChecker;

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

        String email = mBinding.loginSignInEmail.getText().toString();
        String password = mBinding.loginSignInPassword.getText().toString();

        //FIXME: 이메일, 패스워드 입력체크
        if (InputChecker.isNullorEmpty(email) && InputChecker.isEmailValid(email)) {
            Toast toastMessage_share = Toast.makeText(this, "올바른 이메일을 입력해 주세요", Toast.LENGTH_SHORT);
            toastMessage_share.show();

            return;
        } else if (InputChecker.isNullorEmpty(password) && InputChecker.isPassValid(password)) {
            Toast toastMessage_share = Toast.makeText(this, "올바른 비밀번호를 입력해 주세요", Toast.LENGTH_SHORT);
            toastMessage_share.show();

            return;
        }

        //로그인
        mAuth.signIn(email, password);

        //TODO: Firebase보안관련 exception처리 추가

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
