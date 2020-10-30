package com.mh16629.onedayonepage.firstuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mh16629.onedayonepage.R;

public class FirstUser4CompleteFragment extends Fragment {

    private TextView mFirstUserCompleteName;
    private TextView mFirstUserCompleteEmail;
    private ImageButton mFirstUserCompletePhoto;

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
        //TODO: mFirstUserCompletePhoto 설정할 것
        mFirstUserCompleteName = v.findViewById(R.id.first_user_complete_name);
        mFirstUserCompleteEmail = v.findViewById(R.id.first_user_complete_email);
//        mFirstUserCompletePhoto = v.findViewById(R.id.first_user_complete_photo);

        FirstUserActivity firstUserActivity = (FirstUserActivity)getActivity();
        assert firstUserActivity != null;
        mFirstUserCompleteName.setText(firstUserActivity.getmFirstUserName());
        mFirstUserCompleteEmail.setText(firstUserActivity.getmFirstUserEmail());
//        mFirstUserCompletePhoto.

        return v;
    }
}