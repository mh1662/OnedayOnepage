package com.mh16629.onedayonepage.account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.ActivityAccountBinding;
import com.mh16629.onedayonepage.firebase.FirebaseOdOpAuth;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class AccountActivity extends AppCompatActivity implements
        View.OnClickListener  {

    public static Context mContext;
    private FirebaseOdOpAuth mAuth;

    private static final String TAG = "AccountActivity";

    private ActivityAccountBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mContext = this;
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
        mAuth = new FirebaseOdOpAuth(this);
        if (!mAuth.isCurrentUserNull()) {
            Log.d(TAG, "set user info");
            mBinding.accountUserName.setText(mAuth.getUserName());
            mBinding.accountUserEmail.setText(mAuth.getUserEmail());
            mBinding.accountUserUid.setText(mAuth.getUserUid());
            mBinding.accountUserPhoto.setImageURI(mAuth.getUserPhotoUri());
        }

        //프로필 변경 리스너
        mBinding.accountUserPhoto.setOnClickListener(this);
        mBinding.accountIconUserPhotoUpdate.setOnClickListener(this);
        mBinding.accountIconNameUpdate.setOnClickListener(this);

        //AccountActivity 회원관리 버튼 클릭 리스너
//        mBinding.accountButtonLinkWithSignIn.setOnClickListener(this);
        mBinding.accountButtonPasswordUpdate.setOnClickListener(this);
        mBinding.accountButtonSignOut.setOnClickListener(this);
        mBinding.accountButtonCleanup.setOnClickListener(this);
        mBinding.accountButtonUserDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_userPhoto:
            case R.id.account_icon_userPhotoUpdate: {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
                break;
            }
            case R.id.account_icon_nameUpdate: {
                AccountUsernameUpdateDialog usernameUpdateDialog = new AccountUsernameUpdateDialog(mContext);
                usernameUpdateDialog.setCancelable(true);
                usernameUpdateDialog.show();
                break;
            }
                /*계정 연동 버튼 삭제
            case R.id.account_button_linkWithSignIn:

                break;
                 */
            case R.id.account_button_passwordUpdate:

                break;
            case R.id.account_button_signOut: {
                SignOutDialog signOutDialog = new SignOutDialog(this);
                signOutDialog.setCancelable(true);
                signOutDialog.show();
                break;
            }
            case R.id.account_button_cleanup:
                Intent intentCleanup = new Intent(getApplicationContext(), AccountCleanUpActivity.class);
                startActivity(intentCleanup);
                break;
            case R.id.account_button_userDelete:
                Intent intentDelete = new Intent(getApplicationContext(), AccountDeleteActivity.class);
                startActivity(intentDelete);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // CropImage result(프로필 사진 변경)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK && data !=null) {
                //프로필 업데이트
                mAuth.updateProfile(result.getUri());

                //선택된 사진 표시
                mBinding.accountUserPhoto.setImageURI(result.getUri());

                //TOAST MESSAGE
                Toast toastMessage_share = Toast.makeText(this, "프로필 사진이 변경되었어요.", Toast.LENGTH_SHORT);
                toastMessage_share.show();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void updateUserName(String name) {
        mBinding.accountUserName.setText(name);
    }
}