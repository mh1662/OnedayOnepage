package com.mh16629.onedayonepage.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.booksearch.BookSearchActivity;
import com.mh16629.onedayonepage.databinding.ActivityAccountBinding;
import com.mh16629.onedayonepage.login.AnonymousAlertDialog;
import com.mh16629.onedayonepage.main.MainActivity;

import org.w3c.dom.Text;

import java.net.URI;

public class AccountActivity extends AppCompatActivity implements
        View.OnClickListener  {

    private static final String TAG = "AccountActivity";

    private ActivityAccountBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_account);
        mBinding.setAccount(this);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.account_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김

        // 유저 정보 설정
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(TAG, "set user info");
            mBinding.accountUserName.setText(user.getDisplayName());
            mBinding.accountUserEmail.setText(user.getEmail());
            mBinding.accountUserUid.setText(user.getUid());
            Uri userPhotoUrl = user.getPhotoUrl();
            if (userPhotoUrl != null) {
                mBinding.accountUserPhoto.setImageURI(user.getPhotoUrl());
            }
        }

//        //사용자 삭제
//        Button buttonUserDelete = (Button) findViewById(R.id.account_button_userDelete);
//        buttonUserDelete.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                deleteUser();
//            }
//        });

        mBinding.accountButtonLinkWithSignIn.setOnClickListener(this);
        mBinding.accountButtonPasswordUpdate.setOnClickListener(this);
        mBinding.accountButtonSignOut.setOnClickListener(this);
        mBinding.accountButtonCleanup.setOnClickListener(this);
        mBinding.accountButtonUserDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_button_linkWithSignIn:

                break;
            case R.id.account_button_passwordUpdate:

                break;
            case R.id.account_button_signOut:
                signOut();
                break;
            case R.id.account_button_cleanup:

                break;
            case R.id.account_button_userDelete:
                deleteUser();
                break;
        }
    }

    /**
     * 로그아웃
     */
    public void signOut() {
        SignOutDialog signOutDialog = new SignOutDialog(this);
        signOutDialog.setCancelable(true);
        signOutDialog.show();
    }


    /**
     * 회원 탈퇴
     */
    public void deleteUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                        }
                    }
                });
        // TODO: 유저 삭제 전 기존 책, 노트데이터 삭제(논리)해야? 그냥 냅둠??(유지보수..)

        // FIXME: 유저 삭제 후 첫 화면으로 전이 (로그인 화면으로 전이되도록)
//        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intentMain);

        ((AccountActivity) this).finish();
    }

}