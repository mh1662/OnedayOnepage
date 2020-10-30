package com.mh16629.onedayonepage.firstuser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.util.RoundImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT2_EMAIL_ABLE;
import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT3_PHOTO_ABLE;

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

        //버튼 클릭 리스너 설정
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
                .start(getContext(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK && data !=null) {
                mFirstUserPhotoUri = result.getUri();
                Log.d(TAG, "onActivityResult: imageUrl is" + mFirstUserPhotoUri.toString());

                //선택된 사진 표시
                mFirstUserPhoto.setImageURI(mFirstUserPhotoUri);

                //FirstUserActivity의 bottomLayout 변경
                ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT3_PHOTO_ABLE);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public Uri getFirstUserPhotoUri(){
        return mFirstUserPhotoUri;
    }
}