package com.mh16629.onedayonepage.booksearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.aladdin.AladdinBookSearchItem;
import com.mh16629.onedayonepage.aladdin.AladdinOpenAPI;
import com.mh16629.onedayonepage.aladdin.AladdinOpenAPISearchBookHandler;
import com.mh16629.onedayonepage.booknew.BookNewActivity;
import com.mh16629.onedayonepage.databinding.ActivityBookSearchBinding;
import com.mh16629.onedayonepage.util.InputChecker;

import java.util.ArrayList;

public class BookSearchActivity extends AppCompatActivity {

    private static final String TAG = "BookSearchActivity";

    public static Context mContext;
    private ActivityBookSearchBinding mBinding;
    private BookSearchResultListViewAdapter adapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_book, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.toolbar_book_null:
                Intent intentNewBook = new Intent(getApplicationContext(), BookNewActivity.class);
                startActivity(intentNewBook);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        mContext = this;
        mBinding = ActivityBookSearchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        adapter = new BookSearchResultListViewAdapter();
        mBinding.bookSearchResultListView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.book_search_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setHomeAsUpIndicator(R.drawable.icon_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

        mBinding.bookSearchView.setOnQueryTextListener(searchListener);
        mBinding.bookSearchResultListView.setOnItemClickListener(listviewClickListener);

    }

    /**
     * 검색바 액션이벤트
     */
    SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        //검색 버튼을 눌렀을 경우
        @Override
        public boolean onQueryTextSubmit(String query) {

            if (!InputChecker.isNullorEmpty(query)){
                try {
                    String url = AladdinOpenAPI.GetSearchBookUrl(query);
                    AladdinOpenAPISearchBookHandler api = new AladdinOpenAPISearchBookHandler();
                    api.searchBook(url);
                    for(AladdinBookSearchItem item : api.Books) {
                        Log.d(TAG, "알라딘 open api test: "+item.getTitle()+":"+item.getLink());
                    }
                }catch (Exception ex) {
                    Log.w(TAG, "onQueryTextSubmit Exception: " + ex);
                }
            }
            return false;
        }

        //텍스트가 바뀔 때마다 호출
        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }

    };

    /*
    ListView Click Event
     */
    ListView.OnItemClickListener listviewClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            //FIXME: BookNewActivity 구현 후 하단 토스트 메세지 삭제
            Toast.makeText(BookSearchActivity.this, adapter.getItem(position).getItemId(), Toast.LENGTH_SHORT).show();

            Intent intentNewBook = new Intent(getApplicationContext(), BookNewActivity.class);
            intentNewBook.putExtra("itemId", adapter.getItem(position).getItemId());
            startActivity(intentNewBook);
        }
    };


    /**
     *
     * @param list
     */
    public void setSearchResult(final ArrayList<AladdinBookSearchItem> list) {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBinding.bookSearchLayoutResultZero.setVisibility(View.GONE);
                mBinding.bookSearchLayoutResultOk.setVisibility(View.VISIBLE);

                adapter.listViewClear();
                adapter.addItem(list);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void setSearchResultZero() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBinding.bookSearchLayoutResultZero.setVisibility(View.VISIBLE);
                mBinding.bookSearchLayoutResultOk.setVisibility(View.GONE);
            }
        });
    }

}