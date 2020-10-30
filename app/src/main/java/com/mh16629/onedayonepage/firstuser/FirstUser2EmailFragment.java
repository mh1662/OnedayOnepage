package com.mh16629.onedayonepage.firstuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mh16629.onedayonepage.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT2_EMAIL_ABLE;
import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT2_EMAIL_DISABLE;

public class FirstUser2EmailFragment extends Fragment {

    private EditText editTextUserEmail;

    public FirstUser2EmailFragment() {}

    public static FirstUser2EmailFragment newInstance() {
        return new FirstUser2EmailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_first_user2_email, container, false);

        //화면 표시시 이메일 입력 확인
        editTextUserEmail = (EditText) v.findViewById(R.id.first_user_email);
        editTextUserEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isEmailValid(editable.toString())) {
                    ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT2_EMAIL_ABLE);
                } else {
                    ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT2_EMAIL_DISABLE);
                }
            }
        });

        return v;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getFirstUserEmail(){
        EditText et = (EditText)getView().findViewById(R.id.first_user_email);
        return et.getText().toString();
    }
}