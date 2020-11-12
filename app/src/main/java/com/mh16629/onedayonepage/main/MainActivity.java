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
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.booksearch.BookSearchActivity;
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
            Log.d(TAG, "checkCurrentUser: login success");
        } else {
            // 유저 로그인 정보 X -> 로그인화면으로 전이
            Log.d(TAG, "checkCurrentUser: login");
            Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }
    }

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

}