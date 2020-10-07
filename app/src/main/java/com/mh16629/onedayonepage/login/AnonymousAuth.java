package com.mh16629.onedayonepage.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.ActivityAnonymousAuthBinding;

import java.util.concurrent.Executor;

public class AnonymousAuth {

    private static final String TAG = "AnonymousAuth";

    private Context mContext;
    private FirebaseAuth mAuth;

    public AnonymousAuth(@NonNull Context context) {
        mAuth = FirebaseAuth.getInstance();
        mContext = context;
        // 사용자 로그인 여부 확인
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

//    private ActivityAnonymousAuthBinding mBinding;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mBinding = ActivityAnonymousAuthBinding.inflate(getLayoutInflater());
//        setContentView(mBinding.getRoot());
//
//        mAuth = FirebaseAuth.getInstance();
//
//        setmProgressBar(mBinding.progressBar);
//
//        mBinding.buttonAnonymousSignIn.setOnClickListener(this);
//        mBinding.buttonAnonymousSignOut.setOnClickListener(this);
//        mBinding.buttonLinkAccount.setOnClickListener(this);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // 사용자 로그인 여부 확인
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    public void signInAnonymously() {
//        showProgressBar();

        // 익명 사용자로 로그인
        mAuth.signInAnonymously()
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
//                            Toast.makeText(AnonymousAuth.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
//                        hideProgressBar();
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
//        updateUI(null);
    }

    /**
     * 사용자 계정 연결
     */
    public void linkAccount(String email, String password) {
//        if (!validateLinkForm()) {
//            return;
//        }

//        String email = mBinding.fieldEmail.getText().toString();
//        String password = mBinding.fieldPassword.getText().toString();

        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

//        showProgressBar();

        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "linkWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
//                            updateUI(user);
                        } else {
                            Log.w(TAG, "linkWithCredential:failure", task.getException());
//                            Toast.makeText(AnonymousAuth.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
//                        hideProgressBar();
                    }
                });
    }

//    private boolean validateLinkForm() {
//        boolean valid = true;
//
//        String email = mBinding.fieldEmail.getText().toString();
//        if (TextUtils.isEmpty(email)) {
//            mBinding.fieldEmail.setError("Required.");
//            valid = false;
//        } else {
//            mBinding.fieldEmail.setError(null);
//        }
//
//        String password = mBinding.fieldPassword.getText().toString();
//        if (TextUtils.isEmpty(password)) {
//            mBinding.fieldPassword.setError("Required.");
//            valid = false;
//        } else {
//            mBinding.fieldPassword.setError(null);
//        }
//
//        return valid;
//    }

//    private void updateUI(FirebaseUser user) {
//        hideProgressBar();
//
//        TextView idView = findViewById(R.id.anonymousStatusId);
//        TextView emailView = findViewById(R.id.anonymousStatusEmail);
//        boolean isSignedIn = (user != null);
//
//        //Status text
//        if (isSignedIn) {
//            idView.setText(getString(R.string.id_fmt, user.getUid()));
//            emailView.setText(getString(R.string.email_fmt, user.getEmail()));
//        } else {
//            idView.setText(R.string.signed_out);
//            emailView.setText(null);
//        }
//
//        //Button visibility
//        findViewById(R.id.buttonAnonymousSignIn).setEnabled(!isSignedIn);
//        findViewById(R.id.buttonAnonymousSignOut).setEnabled(isSignedIn);
//        findViewById(R.id.buttonLinkAccount).setEnabled(isSignedIn);
//    }

//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
//        if (i == R.id.buttonAnonymousSignIn) {
//            signInAnonymously();
//        } else if (i == R.id.buttonAnonymousSignOut) {
//            signOut();
//        } else if (i == R.id.buttonLinkAccount) {
//            linkAccount();
//        }
//    }
}