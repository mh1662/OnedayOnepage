package com.mh16629.onedayonepage.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.firebase.ui.auth.AuthUI;
//import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.firebase.storage.FirebaseStorage;
import com.mh16629.onedayonepage.R;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

//    public void createSignInIntent() {
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.GoogleBuilder().build(),
//                new AuthUI.IdpConfig.AnonymousBuilder().build());
//
//        startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .build(),
//                RC_SIGN_IN);
//    }

//    /**
//     * 로그인 과정 완료시 데이터가 전달되는 메소드
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//
//            if (resultCode == RESULT_OK) {
//                // TODO: 로그인 성공
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            } else {
//                // TODO: 로그인 실패
//
//            }
//        }
//    }

//    /**
//     * 로그아웃
//     */
//    public void signOut() {
//        AuthUI.getInstance()
//                .signOut(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // TODO: 로그아웃 완료
//
//
//                    }
//                });
//    }
//
//    /**
//     * 사용자 정보 삭제
//     */
//    public void delete() {
//        AuthUI.getInstance()
//                .delete(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // TODO: 유저 삭제 완료
//
//                    }
//                });
//    }
//
//    /**
//     * 로그인화면 설정(FirebaseUI사용)
//     */
//    public void themeAndLogo() {
//        List<AuthUI.IdpConfig> providers = Collections.emptyList();
//
//        startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//    // TODO: 로그인 화면 로고, 테마 설정
//    //                .setLogo(R.drawable.logo)
//    //                .setTheme(R.style.MySuperAppTheme)
//                    .build(),
//                RC_SIGN_IN);
//    }
//
//    /**
//     * 개인정보 처리방침 및 서비스 약관
//     */
//    public void privacyAndTerms() {
//        List<AuthUI.IdpConfig> providers = Collections.emptyList();
//
//        startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .setTosAndPrivacyPolicyUrls(
//                            "https://example.com/terms.html",
//                            "https://example.com/privacy.html")
//                    .build(),
//                RC_SIGN_IN);
//    }
}
