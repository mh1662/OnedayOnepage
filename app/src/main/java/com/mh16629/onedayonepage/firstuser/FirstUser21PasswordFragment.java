package com.mh16629.onedayonepage.firstuser;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mh16629.onedayonepage.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT21_PASSWORD_ABLE;
import static com.mh16629.onedayonepage.firstuser.FirstUserActivity.BOTTOM_LAYOUT21_PASSWORD_DISABLE;

public class FirstUser21PasswordFragment extends Fragment {

    private EditText editTextUserPassword;
    private EditText editTextUserPasswordCheck;

    private TextView textViewPasswordErrorMessage;
    private TextView textViewPasswordCheckErrorMessage;

    private ImageView imageViewPasswordMark;
    private ImageView imageViewPasswordCheckMark;

    //메세지
    private static final int passwordStatus0Empty = 0;
    private static final int passwordStatus1CannotUse = 1;
    private static final int passwordStatus2Currect = 2;

    private static final int passwordCheckStatus0Empty = 0;
    private static final int passwordCheckStatus1Disconnected = 1;
    private static final int passwordCheckStatus2Connected = 2;
    private static final int passwordCheckStatus3AfterPassword = 3;

    public FirstUser21PasswordFragment() {}

    public static FirstUser21PasswordFragment newInstance() {
        return new FirstUser21PasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_user21_password, container, false);

        editTextUserPassword = (EditText) v.findViewById(R.id.first_user_password);
        editTextUserPasswordCheck = (EditText) v.findViewById(R.id.first_user_password_check);
        textViewPasswordErrorMessage = (TextView) v.findViewById(R.id.first_user_password_error_message);
        textViewPasswordCheckErrorMessage = (TextView) v.findViewById(R.id.first_user_password_check_error_message);
        imageViewPasswordMark = (ImageView) v.findViewById(R.id.first_user_password_checkmark);
        imageViewPasswordCheckMark = (ImageView) v.findViewById(R.id.first_user_passwordcheck_checkmark);

        editTextUserPassword.addTextChangedListener(passwordListener);
        editTextUserPasswordCheck.addTextChangedListener(passwordCheckListener);

        //화면 초기화
        initFirstUser21();

        return v;
    }

    /**
     * 화면 초기화
     */
    private void initFirstUser21() {
        editTextUserPassword.getText().clear();
        setPasswordStatus(passwordStatus0Empty);

        editTextUserPasswordCheck.getText().clear();
        setPasswordCheckStatus(passwordCheckStatus0Empty);
    }

    /**
     * editTextUserPassword의 입력결과에 따라 관련 레이아웃 스타일을 변경
     * @param status 해당 스테이터스 코드
     */
    @SuppressLint("ResourceAsColor")
    private void setPasswordStatus(int status) {
        switch (status){
            case passwordStatus0Empty:
                textViewPasswordErrorMessage.setVisibility(View.INVISIBLE);
                textViewPasswordErrorMessage.setText(R.string.first_user_layout21_password_message0);
                textViewPasswordErrorMessage.setTextColor(R.color.point);
                imageViewPasswordMark.setVisibility(View.INVISIBLE);
                break;
            case passwordStatus1CannotUse:
                textViewPasswordErrorMessage.setVisibility(View.VISIBLE);
                textViewPasswordErrorMessage.setText(R.string.first_user_layout21_password_message1);
                textViewPasswordErrorMessage.setTextColor(R.color.point);
                imageViewPasswordMark.setVisibility(View.INVISIBLE);
                break;
            case passwordStatus2Currect:
                textViewPasswordErrorMessage.setVisibility(View.VISIBLE);
                textViewPasswordErrorMessage.setText(R.string.first_user_layout21_password_message2);
                textViewPasswordErrorMessage.setTextColor(R.color.okay);
                imageViewPasswordMark.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * editTextUserPasswordCheck의 입력결과에 따라 관련 레이아웃 스타일을 변경
     * @param status 해당 스테이터스 코드
     */
    @SuppressLint("ResourceAsColor")
    private void setPasswordCheckStatus(int status) {
        switch (status) {
            case passwordCheckStatus0Empty:
                textViewPasswordCheckErrorMessage.setVisibility(View.INVISIBLE);
                textViewPasswordCheckErrorMessage.setText(R.string.first_user_layout21_passwordcheck_message0);
                textViewPasswordCheckErrorMessage.setTextColor(R.color.point);
                imageViewPasswordCheckMark.setVisibility(View.INVISIBLE);
                break;
            case passwordCheckStatus1Disconnected:
                textViewPasswordCheckErrorMessage.setVisibility(View.VISIBLE);
                textViewPasswordCheckErrorMessage.setText(R.string.first_user_layout21_passwordcheck_message1);
                textViewPasswordCheckErrorMessage.setTextColor(R.color.point);
                imageViewPasswordCheckMark.setVisibility(View.INVISIBLE);
                break;
            case passwordCheckStatus2Connected:
                textViewPasswordCheckErrorMessage.setVisibility(View.VISIBLE);
                textViewPasswordCheckErrorMessage.setText(R.string.first_user_layout21_passwordcheck_message2);
                textViewPasswordCheckErrorMessage.setTextColor(R.color.okay);
                imageViewPasswordCheckMark.setVisibility(View.VISIBLE);
                break;
            case passwordCheckStatus3AfterPassword:
                textViewPasswordCheckErrorMessage.setVisibility(View.VISIBLE);
                textViewPasswordCheckErrorMessage.setText(R.string.first_user_layout21_passwordcheck_message3);
                textViewPasswordCheckErrorMessage.setTextColor(R.color.point);
                imageViewPasswordCheckMark.setVisibility(View.INVISIBLE);
                break;

        }
    }

    /**
     * editTextUserPassword 문자열 변경 리스너
     */
    TextWatcher passwordListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            String passwordCheck = editTextUserPasswordCheck.getText().toString();
            if (isPassValid(editable.toString())) {
                //패스워드 규약O
                setPasswordStatus(passwordStatus2Currect);
                setPasswordCheckStatus(passwordCheckStatus0Empty);
                editTextUserPasswordCheck.setText("");
                ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_DISABLE);
            } else {
                if ("".equals(passwordCheck)){
                    //패스워드 규약X 패스워드 체크 입력X
                    setPasswordStatus(passwordStatus1CannotUse);
                    setPasswordCheckStatus(passwordCheckStatus0Empty);
                    ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_DISABLE);
                } else {
                    //패스워드 규약X 패스워드 체크 입력O
                    setPasswordStatus(passwordStatus1CannotUse);
                    setPasswordCheckStatus(passwordCheckStatus0Empty);
                    editTextUserPasswordCheck.setText("");
                    ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_DISABLE);
                }
            }
        }
    };

    /**
     * editTextUserPasswordCheck 문자열 변경 리스너
     */
    TextWatcher passwordCheckListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            String password = editTextUserPassword.getText().toString();
            if ("".equals(password)) {
                //패스워드 미입력
                setPasswordStatus(passwordStatus2Currect);
                setPasswordCheckStatus(passwordCheckStatus0Empty);
                editTextUserPasswordCheck.setText("");
                ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_DISABLE);
            } else {
                if (isPassValid(password)) {
                    if (password.equals(editable.toString())) {
                        //패스워드 일치
                        setPasswordStatus(passwordStatus2Currect);
                        setPasswordCheckStatus(passwordCheckStatus2Connected);
                        ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_ABLE);
                    } else {
                        //패스워드 불일치
                        setPasswordStatus(passwordStatus2Currect);
                        setPasswordCheckStatus(passwordCheckStatus1Disconnected);
                        ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_DISABLE);
                    }

                } else {
                    //패스워드 규약X
                    setPasswordStatus(passwordStatus1CannotUse);
                    setPasswordCheckStatus(passwordCheckStatus3AfterPassword);
                    ((FirstUserActivity)FirstUserActivity.mContext).setbottomLayout(BOTTOM_LAYOUT21_PASSWORD_DISABLE);
                }
            }
        }
    };

    /**
     * 패스워드 형식 체크
     *  ^               문자열 시작
     *  (?=.*[0-9])     숫자 포함
     *  (?=.*[a-z])     영문자 포함
     *  (?=\S+$)        공백 없음
     *  .{6,}           6글자 이상
     *  $               문자열 끝
     * @param passInput
     * @return
     */
    public boolean isPassValid (String passInput) {
        String expression = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(passInput);
        return matcher.matches();
    }

    public String getFirstUserPassword() {
        EditText et = (EditText)getView().findViewById(R.id.first_user_password);
        return et.getText().toString();
    }
}