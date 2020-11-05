package com.mh16629.onedayonepage.firstuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.util.RoundImageView;

public class FirstUser4CompleteFragment extends Fragment {

    TextView mFirstUserCompleteName;
    TextView mFirstUserCompleteEmail;
    RoundImageView mFirstUserCompletePhoto;

    public FirstUser4CompleteFragment() {
    }

    public static FirstUser4CompleteFragment newInstance() {
        return new FirstUser4CompleteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_user4_complete, container, false);

        //화면 초기 설정
        mFirstUserCompleteName = v.findViewById(R.id.first_user_complete_name);
        mFirstUserCompleteEmail = v.findViewById(R.id.first_user_complete_email);
        mFirstUserCompletePhoto = v.findViewById(R.id.first_user_complete_photo);
        setFragment4Src();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setFragment4Src();
    }

    public void setFragment4Src() {
        FirstUserActivity firstUserActivity = (FirstUserActivity)getActivity();
        assert firstUserActivity != null;
        mFirstUserCompleteName.setText(firstUserActivity.getFirstUserName());
        mFirstUserCompleteEmail.setText(firstUserActivity.getFirstUserEmail());
        mFirstUserCompletePhoto.setImageURI(firstUserActivity.getFirstUserPhotoUri());
    }
}