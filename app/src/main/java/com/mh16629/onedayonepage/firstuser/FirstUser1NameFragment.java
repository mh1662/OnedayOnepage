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

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private EditText editTextUserName;

    public FirstUser1NameFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstUser1NameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstUser1NameFragment newInstance(String param1, String param2) {
        FirstUser1NameFragment fragment = new FirstUser1NameFragment();
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
}