package com.mh16629.onedayonepage.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.account.AccountActivity;
import com.mh16629.onedayonepage.databinding.DialogAnonymousAlertBinding;
import com.mh16629.onedayonepage.main.MainActivity;

import java.util.concurrent.Executor;

public class AnonymousAlertDialog extends Dialog implements
        View.OnClickListener {

    private static final String TAG = "AnonymousAlertDialog";

    private Context mContext;
    private FirebaseAuth mAuth;

    public AnonymousAlertDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_anonymous_alert);
        mAuth = FirebaseAuth.getInstance();

        Button anonymousNo = (Button) findViewById(R.id.anonymous_login_no);
        Button anonymousYes = (Button) findViewById(R.id.anonymous_login_yes);
        anonymousNo.setOnClickListener(this);
        anonymousYes.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.anonymous_login_no) {
            loginNo();
        } else if (i == R.id.anonymous_login_yes) {
            loginYes();
        }
    }

    /**
     * "연동할래요"
     */
    private void loginNo() {
        Log.d(TAG, "loginNo");
        dismiss();
    }

    /**
     * "이대로 할래요(익명 로그인)"
     */
    private void loginYes() {
        Log.d(TAG, "loginYes");

//        mAuth.signInAnonymously()
//                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "signInAnonymously:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        } else {
//                            Log.w(TAG, "signInAnonymously:failure", task.getException());
//                            Toast.makeText( mContext, R.string.anonymous_login_fail,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
////                    dismiss();
//                });

        // 익명 유저 생성
        AnonymousAuth mAuth = new AnonymousAuth(mContext);
        mAuth.signInAnonymously();

        //FIXME: 익명로그인 후 유저정보 설정 화면으로 ㄱㄱ
        Intent intentFirstLogin = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intentFirstLogin);

//        ((AccountActivity) mContext).finish();
    }
}
