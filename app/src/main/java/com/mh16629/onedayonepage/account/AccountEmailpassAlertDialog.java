package com.mh16629.onedayonepage.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.DialogAccountUsernameUpdateBinding;

public class AccountEmailpassAlertDialog extends Dialog implements
        View.OnClickListener {

    private static final String TAG = "AccountEmailpassAlertDialog";

    private Context mContext;

    public AccountEmailpassAlertDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_account_emailpass_alert);

        Button accountEmailpassNo = (Button) findViewById(R.id.account_emailpass_alert_yes);
        accountEmailpassNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account_emailpass_alert_yes:
                dismiss();
                break;
        }
    }
}
