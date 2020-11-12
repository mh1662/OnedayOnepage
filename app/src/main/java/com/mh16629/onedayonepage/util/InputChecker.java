package com.mh16629.onedayonepage.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isNullorEmpty(String str) {
//        if (str == null) {
//            return true;
//        } else {
//            return str.isEmpty();
//        }
//        boolean result;
//        result = ;
        return (str == null) || str.isEmpty();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

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
    public static boolean isPassValid (String passInput) {
        String expression = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(passInput);
        return matcher.matches();
    }


}
