package com.mh16629.onedayonepage.firstuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.util.RoundImageView;

public class FirstUser5CongrateFragment extends Fragment {

    TextView mFirstUserCompleteName;
    RoundImageView mFirstUserCompletePhoto;

    public FirstUser5CongrateFragment() {}

    public static FirstUser5CongrateFragment newInstance() {
        return new FirstUser5CongrateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_user5_congrate, container, false);

        //화면 초기 설정
        mFirstUserCompletePhoto = v.findViewById(R.id.first_user_congrate_photo);
        mFirstUserCompleteName = v.findViewById(R.id.first_user_congrate_name);
        setFragment5Src();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setFragment5Src();
    }

    public void setFragment5Src() {
        FirstUserActivity firstUserActivity = (FirstUserActivity)getActivity();
        assert firstUserActivity != null;
        mFirstUserCompleteName.setText(firstUserActivity.getFirstUserName());
        mFirstUserCompletePhoto.setImageURI(firstUserActivity.getFirstUserPhotoUri());
    }
}