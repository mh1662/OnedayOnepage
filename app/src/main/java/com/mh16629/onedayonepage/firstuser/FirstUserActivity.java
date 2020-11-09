package com.mh16629.onedayonepage.firstuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.main.MainActivity;
import com.mh16629.onedayonepage.util.FirebaseOdOpAuth;

import static com.mh16629.onedayonepage.firstuser.FirstUserPagerAdapter.*;

public class FirstUserActivity extends AppCompatActivity {

    private static String TAG = "FirstUserActivity";
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    FirstUserViewPager viewPager;
    FirstUserPagerAdapter pagerAdapter;
    FragmentManager mFragmentManager;

    LinearLayout bottomButtonLayout1;
    LinearLayout bottomButtonLayout2;
    Button bottomButtonNext;
    Button bottomButtonYes;
    Button bottomButtonNo;
    ImageView imageViewlogoDog;

    private String mFirstUserName;
    private String mFirstUserEmail;
    private String mFirstUserPassword;
    private Uri mFirstUserPhotoUri;

    // bottom layout level(viewpager 기반)
    public final static int BOTTOM_LAYOUT1_NAME_DISABLE = 0;
    public final static int BOTTOM_LAYOUT1_NAME_ABLE = 1;
    public final static int BOTTOM_LAYOUT2_EMAIL_DISABLE = 2;
    public final static int BOTTOM_LAYOUT2_EMAIL_ABLE = 3;
    public final static int BOTTOM_LAYOUT3_PHOTO_SKIP = 4;
    public final static int BOTTOM_LAYOUT3_PHOTO_ABLE = 5;
    public final static int BOTTOM_LAYOUT4_COMPLETE = 6;
    public final static int BOTTOM_LAYOUT5_CONGRATE = 7;
    public final static int BOTTOM_LAYOUT21_PASSWORD_DISABLE = 8;
    public final static int BOTTOM_LAYOUT21_PASSWORD_ABLE = 9;

    //레이아웃 페이지 수
    public final static int BOTTOM_LAYOUT_COUNT = 6;

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == INDEX_FRAGMENT1_NAME) {
            super.onBackPressed();
        } else if (viewPager.getCurrentItem() == INDEX_FRAGMENT5_CONGRATE) {
            ((FirstUserActivity) mContext).finish();
            Intent mainPageIntent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(mainPageIntent);
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() -1);
        }
    }

    /**
     * 툴바 뒤로가기 버튼 이벤트
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (viewPager.getCurrentItem() == 0) {
                    super.onBackPressed();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
                return super.onOptionsItemSelected(item);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_user);
        mContext = this;

        Toolbar toolbar = findViewById(R.id.first_user_toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(moveBackPageListener);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.icon_back);

        bottomButtonLayout1 = (LinearLayout) findViewById(R.id.first_user_bottom_button_layout1);
        bottomButtonLayout2 = (LinearLayout) findViewById(R.id.first_user_bottom_button_layout2);
        bottomButtonNext = (Button) findViewById(R.id.first_user_bottom_button_next);
        bottomButtonYes = (Button) findViewById(R.id.first_user_bottom_button_yes);
        bottomButtonNo = (Button) findViewById(R.id.first_user_bottom_button_no);
        imageViewlogoDog = (ImageView) findViewById(R.id.first_user_viewpager_logo_dog);

        viewPager = (FirstUserViewPager) findViewById(R.id.first_user_viewpager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new FirstUserPagerAdapter(getSupportFragmentManager(), BOTTOM_LAYOUT_COUNT);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(INDEX_FRAGMENT1_NAME);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.executePendingTransactions();

        bottomButtonNext.setOnClickListener(moveNextPageListener);
        bottomButtonYes.setOnClickListener(moveCompletePageListener);
        bottomButtonNo.setOnClickListener(moveBackPageListener);

        //bottom layout 초기설정
        setbottomLayout(BOTTOM_LAYOUT1_NAME_DISABLE);
    }

    /**
     * 레이아웃 레벨별 버튼 레이아웃 변경
     */
    public void setbottomLayout(int layoutLevel){

        switch (layoutLevel){
            case BOTTOM_LAYOUT1_NAME_DISABLE:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);

                imageViewlogoDog.setVisibility(View.VISIBLE);
                bottomButtonNext.setTag(INDEX_FRAGMENT1_NAME);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_next));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.inactive_shape));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.inactive_font));
                bottomButtonNext.setEnabled(false);

                break;

            case BOTTOM_LAYOUT1_NAME_ABLE:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonNext.setTag(INDEX_FRAGMENT1_NAME);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_next));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.point));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.white));

                bottomButtonNext.setEnabled(true);

                break;

            case BOTTOM_LAYOUT2_EMAIL_DISABLE:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonNext.setTag(INDEX_FRAGMENT2_EMAIL);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_next));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.inactive_shape));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.inactive_font));
                bottomButtonNext.setEnabled(false);

                break;

            case BOTTOM_LAYOUT2_EMAIL_ABLE:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonNext.setTag(INDEX_FRAGMENT2_EMAIL);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_next));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.point));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.white));
                bottomButtonNext.setEnabled(true);

                break;

            case BOTTOM_LAYOUT3_PHOTO_SKIP:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonNext.setTag(INDEX_FRAGMENT3_PHOTO);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_skip));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.point));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.white));
                bottomButtonNext.setEnabled(true);

                break;

            case BOTTOM_LAYOUT3_PHOTO_ABLE:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonNext.setTag(INDEX_FRAGMENT3_PHOTO);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_next));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.point));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.white));
                bottomButtonNext.setEnabled(true);

                break;

            case BOTTOM_LAYOUT4_COMPLETE:
                bottomButtonLayout1.setVisibility(View.GONE);
                bottomButtonLayout2.setVisibility(View.VISIBLE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonYes.setTag(INDEX_FRAGMENT4_COMPLETE);
                bottomButtonNo.setTag(INDEX_FRAGMENT4_COMPLETE);

                break;

            case BOTTOM_LAYOUT5_CONGRATE:
                bottomButtonLayout1.setVisibility(View.GONE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.INVISIBLE);

                break;

            case BOTTOM_LAYOUT21_PASSWORD_DISABLE:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonNext.setTag(INDEX_FRAGMENT21_PASSWORD);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_next));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.inactive_shape));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.inactive_font));
                bottomButtonNext.setEnabled(false);

                break;

            case BOTTOM_LAYOUT21_PASSWORD_ABLE:
                bottomButtonLayout1.setVisibility(View.VISIBLE);
                bottomButtonLayout2.setVisibility(View.GONE);
                imageViewlogoDog.setVisibility(View.VISIBLE);

                bottomButtonNext.setTag(INDEX_FRAGMENT21_PASSWORD);
                bottomButtonNext.setText(getResources().getString(R.string.first_user_bottom_button_next));
                bottomButtonNext.setBackgroundColor(getResources().getColor(R.color.point));
                bottomButtonNext.setTextColor(getResources().getColor(R.color.white));
                bottomButtonNext.setEnabled(true);

                break;
        }
    }

    /**
     * Fragment전환시 바텀 레이아웃 변경(low to high)
     * @param fragmentNo 전환 전 프레그먼트 인덱스
     */
    private void setNextFragmentLayout (int fragmentNo){
        switch (fragmentNo) {
            case INDEX_FRAGMENT1_NAME:
                setbottomLayout(BOTTOM_LAYOUT2_EMAIL_DISABLE);
                break;
            case INDEX_FRAGMENT2_EMAIL:
                setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_DISABLE);
                break;
            case INDEX_FRAGMENT21_PASSWORD:
                setbottomLayout(BOTTOM_LAYOUT3_PHOTO_SKIP);
                break;
            case INDEX_FRAGMENT3_PHOTO:
                setbottomLayout(BOTTOM_LAYOUT4_COMPLETE);
                break;
            case INDEX_FRAGMENT4_COMPLETE:
                setbottomLayout(BOTTOM_LAYOUT5_CONGRATE);
                break;
        }
    }

    /**
     * Fragment전환시 바텀 레이아웃 변경(high to low)
     * @param fragmentNo 전환 전 프레그먼트 인덱스
     */
    private void setBackFragmentLayout(int fragmentNo) {
        switch (fragmentNo) {
            case INDEX_FRAGMENT4_COMPLETE:
                setbottomLayout(BOTTOM_LAYOUT3_PHOTO_SKIP);
                break;
            case INDEX_FRAGMENT3_PHOTO:
                setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_ABLE);
                break;
            case INDEX_FRAGMENT21_PASSWORD:
                setbottomLayout(BOTTOM_LAYOUT2_EMAIL_ABLE);
                break;
            case INDEX_FRAGMENT2_EMAIL:
                setbottomLayout(BOTTOM_LAYOUT1_NAME_ABLE);
                break;
        }
    }

    /**
     * onClickListener
     *  bottom layout1 "계속하기"버튼, "나중에 할래요"버튼(bottomButtonNext)
     */
    View.OnClickListener moveNextPageListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int tag = viewPager.getCurrentItem();
            if (tag < BOTTOM_LAYOUT_COUNT) {
                //viewPager의 메모리 누수정책에 의해 각 fragment가 삭제되기 전 화면 이동시마다 로컬변수로 저장
                getFragmentData(tag);
                setNextFragmentLayout(tag);
                viewPager.setCurrentItem(tag + 1);
            }
        }
    };

    /**
     * onClickListener
     *  bottom layout2 "맞아요!"버튼(bottomButtonYes)
     */
    View.OnClickListener moveCompletePageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int tag = viewPager.getCurrentItem();
            if (tag == INDEX_FRAGMENT4_COMPLETE) {
                //유저 생성, 프로필 설정
                Log.d(TAG, "moveCompletePageListener auth start");
                FirebaseOdOpAuth mAuth = new FirebaseOdOpAuth(mContext);
                mAuth.createFirstUser(mFirstUserEmail, mFirstUserPassword, mFirstUserName, mFirstUserPhotoUri);
                Log.d(TAG, "moveCompletePageListener auth end");

                //FirstUser5CongrateFragment로 이동
                setNextFragmentLayout(tag);
                viewPager.setCurrentItem(tag + 1);
            }
        }
    };

    /**
     * onClickListener
     *  bottom layout2 "아니에요"버튼(bottomButtonNo)
     *  toolbar android.R.id.home
     */
    View.OnClickListener moveBackPageListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            int tag = viewPager.getCurrentItem();
            if (tag > INDEX_FRAGMENT1_NAME) {
                //INDEX_FRAGMENT1_NAME 이외 : 전 프레그먼트로 전환
                setBackFragmentLayout(tag);
                viewPager.setCurrentItem(tag - 1);
            }

        }
    };

    @SuppressLint("ResourceType")
    private void getFragmentData(int fragmentNo) {
        Fragment currentFragment = pagerAdapter.getRegisteredFragment(viewPager.getCurrentItem());
        if(currentFragment != null) {
            switch (fragmentNo) {
                case INDEX_FRAGMENT1_NAME:
                    mFirstUserName = ((FirstUser1NameFragment)currentFragment).getFirstUserName();
                    break;
                case INDEX_FRAGMENT2_EMAIL:
                    mFirstUserEmail = ((FirstUser2EmailFragment)currentFragment).getFirstUserEmail();
                    break;
                case INDEX_FRAGMENT21_PASSWORD:
                    mFirstUserPassword = ((FirstUser21PasswordFragment)currentFragment).getFirstUserPassword();
                    break;
                case INDEX_FRAGMENT3_PHOTO:
                    mFirstUserPhotoUri = ((FirstUser3PhotoFragment)currentFragment).getFirstUserPhotoUri();
                    break;
                case INDEX_FRAGMENT4_COMPLETE:
//                    ((FirstUser4CompleteFragment)currentFragment).setFragmentSrc();
                    break;
            }
        } else {
            Log.d(TAG, "get current fragment failed");
        }
    }

    public String getFirstUserName() { return mFirstUserName; }
    public String getFirstUserEmail() { return mFirstUserEmail; }
    public Uri getFirstUserPhotoUri() { return mFirstUserPhotoUri; }

}