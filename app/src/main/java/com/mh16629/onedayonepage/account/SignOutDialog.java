package com.mh16629.onedayonepage.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import androidx.annotation.NonNull;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.util.FirebaseOdOpAuth;

public class SignOutDialog extends Dialog implements
        View.OnClickListener{

    private static final String TAG = "AnonymousAlertDialog";

    private Context mContext;

    public SignOutDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_signout);

        Button accountSignOutNo = (Button) findViewById(R.id.account_signOut_no);
        Button accountSignOutYes = (Button) findViewById(R.id.account_signOut_yes);
        accountSignOutNo.setOnClickListener(this);
        accountSignOutYes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account_signOut_no:
                dismiss();
                break;
            case R.id.account_signOut_yes:
                FirebaseOdOpAuth mAuth = new FirebaseOdOpAuth(mContext);
                mAuth.signOut();
                ((AccountActivity) mContext).finish();
                break;
        }
    }
}
