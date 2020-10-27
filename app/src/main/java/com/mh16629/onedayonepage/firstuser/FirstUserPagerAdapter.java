package com.mh16629.onedayonepage.firstuser;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FirstUserPagerAdapter extends FragmentStatePagerAdapter {

    private static String TAG = "FirstUserPagerAdapter";

    int mNumOfTabs;

    // fragment level
    public final static int INDEX_FRAGMENT1_NAME = 0;
    public final static int INDEX_FRAGMENT2_EMAIL = 1;
    public final static int INDEX_FRAGMENT3_PHOTO = 2;
    public final static int INDEX_FRAGMENT4_COMPLETE = 3;
    public final static int INDEX_FRAGMENT5_CONGRATE = 4;

    public FirstUserPagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case INDEX_FRAGMENT1_NAME:
                Log.d(TAG, "ViewPagerAdapter - position[" + position + "]  set FirstUser1NameFragment");
                return new FirstUser1NameFragment();
            case INDEX_FRAGMENT2_EMAIL:
                Log.d(TAG, "ViewPagerAdapter - position[" + position + "]  set FirstUser2EmailFragment");
                return new FirstUser2EmailFragment();
            case INDEX_FRAGMENT3_PHOTO:
                Log.d(TAG, "ViewPagerAdapter - position[" + position + "]  set FirstUser3PhotoFragment");
                return new FirstUser3PhotoFragment();
            case INDEX_FRAGMENT4_COMPLETE:
                Log.d(TAG, "ViewPagerAdapter - position[" + position + "]  set FirstUser4CompleteFragment");
                return new FirstUser4CompleteFragment();
            case INDEX_FRAGMENT5_CONGRATE:
                Log.d(TAG, "ViewPagerAdapter - position[" + position + "]  set FirstUser5CongrateFragment");
                return new FirstUser5CongrateFragment();
            default:
                Log.d(TAG, "ViewPagerAdapter - position[" + position + "]  set null");
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
