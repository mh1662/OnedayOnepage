package com.mh16629.onedayonepage.firstuser;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FirstUserPagerAdapter extends FragmentStatePagerAdapter {

    private static String TAG = "FirstUserPagerAdapter";

    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    int mNumOfTabs;

    // fragment level
    public final static int INDEX_FRAGMENT1_NAME = 0;
    public final static int INDEX_FRAGMENT2_EMAIL = 1;
    public final static int INDEX_FRAGMENT21_PASSWORD = 2;
    public final static int INDEX_FRAGMENT3_PHOTO = 3;
    public final static int INDEX_FRAGMENT4_COMPLETE = 4;
    public final static int INDEX_FRAGMENT5_CONGRATE = 5;

    public FirstUserPagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case INDEX_FRAGMENT1_NAME:
                return FirstUser1NameFragment.newInstance();
            case INDEX_FRAGMENT2_EMAIL:
                return FirstUser2EmailFragment.newInstance();
            case INDEX_FRAGMENT21_PASSWORD:
                return FirstUser21PasswordFragment.newInstance();
            case INDEX_FRAGMENT3_PHOTO:
                return FirstUser3PhotoFragment.newInstance();
            case INDEX_FRAGMENT4_COMPLETE:
                return FirstUser4CompleteFragment.newInstance();
            case INDEX_FRAGMENT5_CONGRATE:
                return FirstUser5CongrateFragment.newInstance();
            default:
                return null;
        }
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

//    @Override
//    public void notifyDataSetChanged() {
////        super.notifyDataSetChanged();
//        int key = 0;
//        for (int i = 0; i < registeredFragments.size(); i++) {
//            key = registeredFragments.keyAt(i);
//            Fragment frg = registeredFragments.get(key);
//            //refresh view
//        }
//        super.notifyDataSetChanged();
//    }

    @Override
    public int getItemPosition(@NonNull Object object) {
//        return super.getItemPosition(object);
        return POSITION_NONE;
    }
}
