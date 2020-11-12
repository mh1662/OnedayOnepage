package com.mh16629.onedayonepage.booknew;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.ActivityBookNewBinding;
import com.mh16629.onedayonepage.firebase.FirebaseAccessStorage;

public class BookNewActivity extends AppCompatActivity implements
        View.OnClickListener{

    private static final String TAG = "BookNewActivity";

    private ActivityBookNewBinding mBinding;
    private FirebaseAccessStorage mFireStore;



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_new);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_new);
        mFireStore = FirebaseAccessStorage.getInstance();

        Toolbar toolbar = findViewById(R.id.book_new_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setHomeAsUpIndicator(R.drawable.icon_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요


        mBinding.bookNewBottomButtonAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book_new_bottom_button_add: {
                mFireStore.createNewBookTest();
            }
        }
    }
}
