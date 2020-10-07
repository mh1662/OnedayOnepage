package com.mh16629.onedayonepage.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.booksearch.BookSearchActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.mh16629.onedayonepage.account.AccountActivity;
import com.mh16629.onedayonepage.login.LoginActivity;

import java.util.List;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Fragment fragmentMainOld;
    private Fragment fragmentMainNo;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        // 유저 로그인 체크
//        checkCurrentUser();
//        Log.d(TAG, "onCreate -> loginOK");

        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);

//        fragmentMainOld = new MainOldBookFragment();
//        fragmentMainNo = new MainNoBookFragment();
//
//        setDefaultFragment();

        FloatingActionButton fab = findViewById(R.id.floatingButton_add_book);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intentAddBook = new Intent(getApplicationContext(), BookSearchActivity.class);
                startActivity(intentAddBook);
            }
        });

        // 네비게이션 드로어 오픈 이벤트
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ImageButton naviButton = (ImageButton) findViewById(R.id.main_contents_naviButton);
        naviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.main_navi_menu_library:
                        Log.d(TAG, "네비게이션 드로어 클릭: "+menuItem.getTitle());
                        break;
                    case R.id.main_navi_menu_calender:
                        Log.d(TAG, "네비게이션 드로어 클릭: "+menuItem.getTitle());
                        break;
                    case R.id.main_navi_menu_addBook:
                        Intent intentAddBook = new Intent(getApplicationContext(), BookSearchActivity.class);
                        startActivity(intentAddBook);
                        break;
                    case R.id.main_navi_menu_account:
                        Intent intentAccount = new Intent(getApplicationContext(), AccountActivity.class);
                        startActivity(intentAccount);
                        break;
                    case R.id.main_navi_menu_accountReset:
                        Log.d(TAG, "네비게이션 드로어 클릭: "+menuItem.getTitle());
                        break;
                    case R.id.main_navi_menu_evaluation:
                        Log.d(TAG, "네비게이션 드로어 클릭: "+menuItem.getTitle());
                        break;
                    case R.id.main_navi_menu_infoDesk:
                        Log.d(TAG, "네비게이션 드로어 클릭: "+menuItem.getTitle());
                        break;
                    case R.id.main_navi_menu_signOut:
                        Log.d(TAG, "네비게이션 드로어 클릭: "+menuItem.getTitle());
                        break;
                }

                return true;
            }
        });

        // Firebase storage 사용예
//        FirebaseStorage storage = FirebaseStorage.getInstance();
    }

    /**
     * 현재 유저 체크
     */
    public void checkCurrentUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // 유저 로그인ok
            Log.d(TAG, "checkCurrentUser: login OK");
        } else {
            // 유저 로그인 정보 X -> 로그인화면으로 전이
            Log.d(TAG, "checkCurrentUser: login");
            Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }
    }

//    /**
//     * 유저 프로필 취득
//     */
//    public void getUserProfile() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            boolean emailVerified = user.isEmailVerified();
//
//            String uid = user.getUid();
//        }
//    }
//
//    /**
//     * 제공업체의 사용자 프로필 정보 가져오기
//     */
//    public void getProviderData() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            for (UserInfo profile : user.getProviderData()) {
//                String providerId = profile.getProviderId();
//                String  uid = profile.getUid();
//                String name = profile.getDisplayName();
//                String email = profile.getEmail();
//                Uri photoUrl = profile.getPhotoUrl();
//            }
//        }
//    }
//
//    /**
//     * 프로필 업데이트
//     */
//    public void updateProfile() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        //FIXME: 유저 프로필 업데이트
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//            .setDisplayName("Jane Q. User")
//                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
//                .build();
//
//        user.updateProfile(profileUpdates)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User profile updated.");
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 이메일 주소 설정
//     */
//    public void updateEmail() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        //FIXME: 유저 이메일 업데이트
//        user.updateEmail("user@example.com")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User email address updated.");
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 비밀번호 업데이트
//     */
//    public void updatePassword() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        //FIXME: 패스워드 업데이트
//        String newPassword = "SOME-SECURE-PASSWORD";
//
//        user.updatePassword(newPassword)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User password updated.");
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 사용자에게 확인 메일 보내기
//     */
//    public void sendEmailVerification() {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//
//        user.sendEmailVerification()
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Email sent.");
//                        }
//                    }
//                });
//    }
//
//    /**
//     *
//     */
//    public void sendEmailVerificationWithContinueUrl() {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//
//        String url = "http://www.example.com/verify?uid=" + user.getUid();
//        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
//                .setUrl(url)
//                .setAndroidPackageName("com.mh16629.onedayonepage", false, null)
//                .build();
//
//        user.sendEmailVerification(actionCodeSettings)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Email sent.");
//                        }
//                    }
//                });
//
//        auth.setLanguageCode("kr");
//    }
//
//    /**
//     * 비밀번호 리셋
//     */
//    public void sendPasswordReset() {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        String emailAddress = "user@example.com";
//
//        auth.sendPasswordResetEmail(emailAddress)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Email sent.");
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 유저 삭제
//     */
//    public void deleteUser() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        user.delete()
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User account deleted.");
//                        }
//                    }
//                });
//    }
//
//    public void reauthenticate() {
//        // [START reauthenticate]
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        // Get auth credentials from the user for re-authentication. The example below shows
//        // email and password credentials but there are multiple possible providers,
//        // such as GoogleAuthProvider or FacebookAuthProvider.
//        AuthCredential credential = EmailAuthProvider
//                .getCredential("user@example.com", "password1234");
//
//        // Prompt the user to re-provide their sign-in credentials
//        user.reauthenticate(credential)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Log.d(TAG, "User re-authenticated.");
//                    }
//                });
//        // [END reauthenticate]
//    }
//
//    public void authWithGithub() {
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//        // [START auth_with_github]
//        String token = "<GITHUB-ACCESS-TOKEN>";
//        AuthCredential credential = GithubAuthProvider.getCredential(token);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "signInWithCredential", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
//        // [END auth_with_github]
//    }
//
//
//    /**
//     * 사용자 연결실패시(다중연결) 인증정보 병합처리
//     * @param credential
//     */
//    public void linkAndMerge(AuthCredential credential) {
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//        // [START auth_link_and_merge]
//        FirebaseUser prevUser = FirebaseAuth.getInstance().getCurrentUser();
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        FirebaseUser currentUser = task.getResult().getUser();
//                        // Merge prevUser and currentUser accounts and data
//                        // ...
//                    }
//                });
//        // [END auth_link_and_merge]
//    }
//
//    /**
//     * 사용자 정보 연결 해제
//     * @param providerId
//     */
//    public void unlink(String providerId) {
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//        // [START auth_unlink]
//        mAuth.getCurrentUser().unlink(providerId)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Auth provider unlinked from account
//                            // ...
//                        }
//                    }
//                });
//        // [END auth_unlink]
//    }
//
//    public void buildActionCodeSettings() {
//        // [START auth_build_action_code_settings]
//        ActionCodeSettings actionCodeSettings =
//                ActionCodeSettings.newBuilder()
//                        // URL you want to redirect back to. The domain (www.example.com) for this
//                        // URL must be whitelisted in the Firebase Console.
//                        .setUrl("https://www.example.com/finishSignUp?cartId=1234")
//                        // This must be true
//                        .setHandleCodeInApp(true)
//                        .setIOSBundleId("com.example.ios")
//                        .setAndroidPackageName(
//                                "com.example.android",
//                                true, /* installIfNotAvailable */
//                                "12"    /* minimumVersion */)
//                        .build();
//        // [END auth_build_action_code_settings]
//    }
//    public void sendSignInLink(String email, ActionCodeSettings actionCodeSettings) {
//        // [START auth_send_sign_in_link]
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        auth.sendSignInLinkToEmail(email, actionCodeSettings)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Email sent.");
//                        }
//                    }
//                });
//        // [END auth_send_sign_in_link]
//    }
//
//    public void verifySignInLink() {
//        // [START auth_verify_sign_in_link]
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        Intent intent = getIntent();
//        String emailLink = intent.getData().toString();
//
//        // Confirm the link is a sign-in with email link.
//        if (auth.isSignInWithEmailLink(emailLink)) {
//            // Retrieve this from wherever you stored it
//            String email = "someemail@domain.com";
//
//            // The client SDK will parse the code from the link for you.
//            auth.signInWithEmailLink(email, emailLink)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Log.d(TAG, "Successfully signed in with email link!");
//                                AuthResult result = task.getResult();
//                                // You can access the new user via result.getUser()
//                                // Additional user info profile *not* available via:
//                                // result.getAdditionalUserInfo().getProfile() == null
//                                // You can check if the user is new or existing:
//                                // result.getAdditionalUserInfo().isNewUser()
//                            } else {
//                                Log.e(TAG, "Error signing in with email link", task.getException());
//                            }
//                        }
//                    });
//        }
//        // [END auth_verify_sign_in_link]
//    }
//
//    public void linkWithSignInLink(String email, String emailLink) {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//
//        // [START auth_link_with_link]
//        // Construct the email link credential from the current URL.
//        AuthCredential credential =
//                EmailAuthProvider.getCredentialWithLink(email, emailLink);
//
//        // Link the credential to the current user.
//        auth.getCurrentUser().linkWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Successfully linked emailLink credential!");
//                            AuthResult result = task.getResult();
//                            // You can access the new user via result.getUser()
//                            // Additional user info profile *not* available via:
//                            // result.getAdditionalUserInfo().getProfile() == null
//                            // You can check if the user is new or existing:
//                            // result.getAdditionalUserInfo().isNewUser()
//                        } else {
//                            Log.e(TAG, "Error linking emailLink credential", task.getException());
//                        }
//                    }
//                });
//        // [END auth_link_with_link]
//    }
//
//    public void reauthWithLink(String email, String emailLink) {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//
//        // [START auth_reauth_with_link]
//        // Construct the email link credential from the current URL.
//        AuthCredential credential =
//                EmailAuthProvider.getCredentialWithLink(email, emailLink);
//
//        // Re-authenticate the user with this credential.
//        auth.getCurrentUser().reauthenticateAndRetrieveData(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // User is now successfully reauthenticated
//                        } else {
//                            Log.e(TAG, "Error reauthenticating", task.getException());
//                        }
//                    }
//                });
//        // [END auth_reauth_with_link]
//    }
//
//    public void differentiateLink(String email) {
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//
//        // [START auth_differentiate_link]
//        auth.fetchSignInMethodsForEmail(email)
//                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//                        if (task.isSuccessful()) {
//                            SignInMethodQueryResult result = task.getResult();
//                            List<String> signInMethods = result.getSignInMethods();
//                            if (signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
//                                // User can sign in with email/password
//                            } else if (signInMethods.contains(EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD)) {
//                                // User can sign in with email/link
//                            }
//                        } else {
//                            Log.e(TAG, "Error getting sign in methods for user", task.getException());
//                        }
//                    }
//                });
//        // [END auth_differentiate_link]
//    }
//
//    /**
//     * 구글 아이디 AuthCredential 취득
//     */
//    public void getGoogleCredentials() {
//        String googleIdToken = "";
//        AuthCredential credential = GoogleAuthProvider.getCredential(googleIdToken, null);
//    }
//
//    public void signOut() {
//        FirebaseAuth.getInstance().signOut();
//    }


















    /**
     * MainActivity가 처음 실행될 때
     * 최초로 보여질 Fragment를 셋팅한다
     */
    public void setMainContentFragment(){
        //화면에 보여지는 fragment를 추가하거나 바꿀 수 있는 객체
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //FIXME: 기존 책 정보가 있을 경우 MainOldBookFragment를 보여주도록
        if(false) {
            //책 1권 이상 : MainOldBookFragment
//            fragmentMainOld = new MainOldBookFragment();
//            fragmentMainNo = new MainNoBookFragment();
            transaction.add(R.id.container, new MainOldBookFragment());
        }else{
            //책 0권 : MainNoBookFragment
            transaction.add(R.id.container, new MainNoBookFragment());
        }

        //fragment의 변경사항 반영
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkCurrentUser();
        setMainContentFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}