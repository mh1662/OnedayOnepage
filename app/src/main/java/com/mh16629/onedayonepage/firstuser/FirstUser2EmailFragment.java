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

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private EditText editTextUserEmail;

    public FirstUser2EmailFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstUser2EmailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstUser2EmailFragment newInstance(String param1, String param2) {
        FirstUser2EmailFragment fragment = new FirstUser2EmailFragment();
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
}