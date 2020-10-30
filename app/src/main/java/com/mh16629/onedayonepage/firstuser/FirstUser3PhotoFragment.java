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

    private RoundImageView mFirstUserPhoto;
    private ImageButton mFirstUserPhotoButton;
    private Uri mFirstUserPhotoUri;

    public FirstUser3PhotoFragment() {}

    public static FirstUser3PhotoFragment newInstance() {
        return new FirstUser3PhotoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        //TODO: mFirstUserPhotoUri세팅할것
    }

    public Uri getFirstUserPhotoUri(){
        return mFirstUserPhotoUri;
    }
}