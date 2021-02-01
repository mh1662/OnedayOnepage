package com.mh16629.onedayonepage.booknew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.aladdin.AladdinItemSelectItem;
import com.mh16629.onedayonepage.aladdin.AladdinOpenAPI;
import com.mh16629.onedayonepage.aladdin.AladdinOpenAPISelectItemHandler;
import com.mh16629.onedayonepage.databinding.ActivityBookNewBinding;
import com.mh16629.onedayonepage.firebase.FirebaseAccessStorage;
import com.mh16629.onedayonepage.util.StringParser;

import java.util.ArrayList;

public class BookNewActivity extends AppCompatActivity implements
        View.OnClickListener{

    private static final String TAG = "BookNewActivity";

    public static Context mContext;
    private ActivityBookNewBinding mBinding;
    private FirebaseAccessStorage mFireStore;

    private final String EXTRA_ITEMID = "ItemId";

    AladdinItemSelectItem currentItem;

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

        mContext = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_new);
        mFireStore = FirebaseAccessStorage.getInstance();

        Toolbar toolbar = findViewById(R.id.book_new_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setHomeAsUpIndicator(R.drawable.icon_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

        // 현재 Item 초기화
        currentItem = new AladdinItemSelectItem();

        mBinding.bookNewBottomButtonAdd.setOnClickListener(this);

        // BookSearchActivity 의 ItemId를 받아온다.
        Intent bookSearchIntent = getIntent();
        String itemId = bookSearchIntent.getStringExtra(EXTRA_ITEMID);

//        mBinding.bookNewInputTitle.setText(itemId);

        // Aladdin 제품 검색
        try {
            String url = AladdinOpenAPI.GetSelectItemUrl(itemId);
            AladdinOpenAPISelectItemHandler apiHandler = new AladdinOpenAPISelectItemHandler();
            apiHandler.selectItem(url);
            for(AladdinItemSelectItem item : apiHandler.Item) {
                Log.d(TAG, "Aladdin open api - Select Item : "+item.getTitle()+":"+item.getLink());
            }
        }catch (Exception ex) {
            Log.w(TAG, "onQueryTextSubmit Exception: " + ex);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book_new_bottom_button_add: {
                mFireStore.createNewBookTest();
            }
        }
    }

    /**
     * AladdinItemSelectTask
     *      return 결과 화면 표시
     */
    public void setSelectItemResult(final ArrayList<AladdinItemSelectItem> list) {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (list.size() == 0) {
                    setResultZero();
                } else {
                    // AladdinItemSelectTask 완료 후 로컬변수 currentItem에 보존
                    currentItem = list.get(0);

                    mBinding.bookNewInputTitle.setText(currentItem.getTitle());
                    mBinding.bookNewInputAuthor.setText(currentItem.getAuthor());
                    mBinding.bookNewInputPublisher.setText(currentItem.getPublisher());
                    //FIXME: 출판일을 Util.StringParser 작성 후 수정
                    mBinding.bookNewInputYm.setText(StringParser.dateStringToString(currentItem.getPubDate(), StringParser.DATEFORMAT_1));


                }

            }
        });
    }

    /**
     * AladdinItemSelectTask
     *      return 0 결과 화면 표시
     */
    public void setResultZero() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BookNewActivity.mContext, "책을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
