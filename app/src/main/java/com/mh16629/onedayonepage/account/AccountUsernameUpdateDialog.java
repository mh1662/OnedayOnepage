package com.mh16629.onedayonepage.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.databinding.ActivityLoginBinding;
import com.mh16629.onedayonepage.databinding.DialogAccountUsernameUpdateBinding;
import com.mh16629.onedayonepage.util.FirebaseOdOpAuth;

import org.w3c.dom.Text;

public class AccountUsernameUpdateDialog extends Dialog implements
        View.OnClickListener {

    private static final String TAG = "AccountUsernameUpdateDialog";

    private Context mContext;
    private FirebaseOdOpAuth mAuth;
    private DialogAccountUsernameUpdateBinding mBinding;

    public AccountUsernameUpdateDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_account_username_update);

        mAuth = new FirebaseOdOpAuth(mContext);

        mBinding = DialogAccountUsernameUpdateBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.accountUsernameUpdateNo.setOnClickListener(this);
        mBinding.accountUsernameUpdateYes.setOnClickListener(this);
        mBinding.accountUsernameUpdateInput.setText(mAuth.getUserName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account_username_update_no:
                dismiss();
                break;
            case R.id.account_username_update_yes:{
                String updateName = mBinding.accountUsernameUpdateInput.getText().toString();
                mAuth.updateProfile(updateName);

                ((AccountActivity)mContext).updateUserName(updateName);

                Toast toastMessage_share = Toast.makeText(mContext, "사용자 이름이 변경되었어요.", Toast.LENGTH_SHORT);
                toastMessage_share.show();

                dismiss();
                break;
            }
        }
    }
}
