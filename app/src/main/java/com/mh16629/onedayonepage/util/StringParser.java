package com.mh16629.onedayonepage.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringParser {

    public static final int DATEFORMAT_0 = 0;
    public static final int DATEFORMAT_1 = 1;

    /**
     * Date를 정해진 형식의 String으로 변환해 반환합니다.
     *      dateFormat
     *          0 : yyyy년 MM월
     *          1 : yyyy년 MM월 dd일
     *
     * @param date 날짜
     * @param dateFormat 반환할 날짜 형식
     * @return
     */
    public static String dateStringToString(Date date, int dateFormat) {

        DateFormat format = null;

        switch (dateFormat) {
            case DATEFORMAT_0:
                format = new SimpleDateFormat("yyyy년 MM월");
                break;
            case DATEFORMAT_1:
                format = new SimpleDateFormat("yyyy년 MM월 dd일");
                break;
        }
        
        return format.format(date);
    }

}
