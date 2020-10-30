package com.mh16629.onedayonepage.firstuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mh16629.onedayonepage.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstUser5CongrateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstUser5CongrateFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_first_user5_congrate, container, false);
    }
}