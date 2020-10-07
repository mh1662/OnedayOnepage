package com.mh16629.onedayonepage.account;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.mh16629.onedayonepage.databinding.DialogAnonymousAlertBinding;

public class UserDeleteDialog extends Dialog implements
        View.OnClickListener  {

    private static final String TAG = "UserDeleteDialog";

    private DialogAnonymousAlertBinding mBinding;

    public UserDeleteDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onClick(View view) {

    }
}
