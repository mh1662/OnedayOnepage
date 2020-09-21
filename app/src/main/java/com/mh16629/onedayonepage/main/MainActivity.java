package com.mh16629.onedayonepage.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.booksearch.BookSearchActivity;

public class MainActivity extends AppCompatActivity {

    private Fragment fragmentMainOld;
    private Fragment fragmentMainNo;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
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
    //FIXME: AndroidX migration
    public void setDefaultFragment(){
        //화면에 보여지는 fragment를 추가하거나 바꿀 수 있는 객체
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //TODO: 메인 화면 초기표시프레그먼트 전환로직 추가
        //기존 책 정보가 있을 경우 MainOldBookFragment를 보여준다
//        if() {
//            //책 1권 이상 : MainOldBookFragment
//            transaction.add(R.id.container, fragmentMainOld);
//        }else{
//            //책 0권 : MainNoBookFragment
//            transaction.add(R.id.container, fragmentMainNo);
//        }

        //fragment의 변경사항 반영
//        transaction.commit();
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