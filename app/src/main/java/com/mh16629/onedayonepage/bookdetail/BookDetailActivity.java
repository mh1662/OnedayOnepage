package com.mh16629.onedayonepage.bookdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.addnote.AddNoteActivity;
import com.mh16629.onedayonepage.bookedit.BookEditActivity;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_book_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.home:
                    finish();
                    break;
                case R.id.toolbar_book_share:
                    Toast toastMessage_share = Toast.makeText(this, "공유기능!", Toast.LENGTH_SHORT);
                    toastMessage_share.show();
                    break;
                case R.id.toolbar_book_note:
                    Intent intentAddNote = new Intent(getApplicationContext(), AddNoteActivity.class);
                    startActivity(intentAddNote);
                    break;
                case R.id.toolbar_book_edit:
                    Intent intentEdit = new Intent(getApplicationContext(), BookEditActivity.class);
                    startActivity(intentEdit);
                    break;
                case R.id.toolbar_book_delete:
                    Toast toastMessage_delete = Toast.makeText(this, "삭제합니다", Toast.LENGTH_SHORT);
                    toastMessage_delete.show();
                    break;
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Toolbar toolbar = findViewById(R.id.book_detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
//        actionBar.setHomeAsUpIndicator(R.drawable.button_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

    }

}
