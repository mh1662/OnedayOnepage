package com.mh16629.onedayonepage.firstuser;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.util.RoundImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class FirstUser3PhotoFragment extends Fragment implements View.OnClickListener {

    private String TAG = "FirstUser3PhotoFragment";

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private RoundImageView mFirstUserPhoto;
    private ImageButton mFirstUserPhotoButton;
    private Uri mFirstUserPhotoUri;

    public FirstUser3PhotoFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstUser3PhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstUser3PhotoFragment newInstance(String param1, String param2) {
        FirstUser3PhotoFragment fragment = new FirstUser3PhotoFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_first_user3_photo, container, false);

        mFirstUserPhotoButton = (ImageButton) v.findViewById(R.id.first_user_photo_button);
        mFirstUserPhoto = (RoundImageView) v.findViewById(R.id.first_user_photo);

        mFirstUserPhotoButton.setOnClickListener(this);
        mFirstUserPhoto.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start((Activity) getView().getContext());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //FIXME: 사진 크롭 이벤트 Callback event 처리 안들어옴
    }
}