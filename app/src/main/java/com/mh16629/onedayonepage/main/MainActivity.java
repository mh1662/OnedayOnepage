package com.mh16629.onedayonepage.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.bookedit.BookEditActivity;
import com.mh16629.onedayonepage.booknew.BookNewActivity;
import com.mh16629.onedayonepage.booksearch.BookSearchActivity;

public class MainActivity extends AppCompatActivity {

    private Fragment fragmentMainOld;
    private Fragment fragmentMainNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragmentMainOld = new MainOldBookFragment();
//        fragmentMainNo = new MainNoBookFragment();
//
//        setDefaultFragment();

        FloatingActionButton fab = findViewById(R.id.floatingButton_add_book);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intentEdit = new Intent(getApplicationContext(), BookSearchActivity.class);
                startActivity(intentEdit);
            }
        });
    }

    /**
     * MainActivity가 처음 실행될 때
     * 최초로 보여질 Fragment를 셋팅한다
     */
    public void setDefaultFragment(){
        //화면에 보여지는 fragment를 추가하거나 바꿀 수 있는 객체
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //TODO: 메인 화면 초기표시프레그먼트 전환로직 추가
        //기존 책 정보가 있을 경우 MainOldBookFragment를 보여준다
//        if() {
//            //책 1권 이상 : MainOldBookFragment
            transaction.add(R.id.container, fragmentMainOld);
//        }else{
//            //책 0권 : MainNoBookFragment
//            transaction.add(R.id.container, fragmentMainNo);
//        }

        //fragment의 변경사항 반영
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
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