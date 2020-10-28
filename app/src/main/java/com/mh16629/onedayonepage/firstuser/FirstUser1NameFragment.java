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

import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT1_NAME_ABLE;
import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT1_NAME_DISABLE;

public class FirstUser1NameFragment extends Fragment {

    private EditText editTextUserName;

    public FirstUser1NameFragment() {}

    public static FirstUser1NameFragment newInstance() {
        return new FirstUser1NameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_user1_name, container, false);

        //화면 표시시 이름 입력 확인
        editTextUserName = (EditText) v.findViewById(R.id.first_user_name);

        editTextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 2) {
                    ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT1_NAME_ABLE);
                } else {
                    ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT1_NAME_DISABLE);
                }
            }
        });

        return v;
    }

    public String getFirstUserName(){
        EditText et = (EditText)getView().findViewById(R.id.first_user_name);
        return et.getText().toString();
    }
}