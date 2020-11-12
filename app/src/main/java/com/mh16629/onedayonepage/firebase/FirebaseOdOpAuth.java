package com.mh16629.onedayonepage.firebase;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FirebaseOdOpAuth {

    private static final String TAG = "FirebaseOdOpAuth";

    private Context mContext;
    private com.google.firebase.auth.FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public FirebaseOdOpAuth(@NonNull Context context) {
        mAuth = FirebaseAuth.getInstance();
        mContext = context;

        // 사용자 로그인 여부 확인
        currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Log.d(TAG, "유저 로그인 정보 부재");
        }
    }

    public boolean isCurrentUserNull() {
        if (currentUser == null) {
            return true;
        }else {
            return false;
        }
    }

    public String getUserName() {
        return currentUser.getDisplayName();
    }

    public String getUserEmail() {
        return currentUser.getEmail();
    }

    public String getUserUid() {
        return currentUser.getUid();
    }

    public Uri getUserPhotoUri() {
        return currentUser.getPhotoUrl();
    }

    /**
     * 익명 사용자 생성
     */
    public void createUserAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserAnonymously:success");
                            currentUser = mAuth.getCurrentUser();
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.w(TAG, "createUserAnonymously:failure", task.getException());
                        }
                    }
                });
    }

    /**
     * create FirstUser
     * 이메일을 지정해 사용자 생성, 프로필 업데이트
     */
    public void createFirstUser(String email, String password, final String userName, final Uri userPhoto) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createFirstUser:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.w(TAG, "createFirstUser:failure", task.getException());
                        }
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //firebase유저 생성 완료 후 유저 프로필 업데이트
                        updateProfile(userName, userPhoto);
                    }
        });
    }

    /**
     * 사용자 로그아웃
     */
    public void signOut() {
        mAuth.signOut();
    }

    /**
     * 기존 사용자 로그인
     * @param email
     * @param password
     */
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    /**
     * 사용자 계정 연결
     */
    public void linkAccount(String email, String password) {
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        mAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "linkWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                        } else {
                            Log.w(TAG, "linkWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    /**
     * 프로필 업데이트
     * @param userName 유저명
     * @param userPhoto 프로필사진 Uri
     */
    public void updateProfile(String userName, Uri userPhoto) {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
            .setDisplayName(userName)
                .setPhotoUri(userPhoto)
                .build();

        mAuth.getCurrentUser().updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "updateProfile: success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Log.d(TAG, "updateProfile: fail");

                        }
                    }
                });
    }
    /**
     * 프로필 업데이트
     * @param userName 유저명
     */
    public void updateProfile(String userName) {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(userName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }

    /**
     * 프로필 업데이트
     * @param userPhoto 프로필사진 Uri
     */
    public void updateProfile(Uri userPhoto) {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser user = mAuth.getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(userPhoto)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }

    /**
     * 사용자 계정 삭제
     */
    public void deleteUser() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseUser user = mAuth.getCurrentUser();

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
    }


}
