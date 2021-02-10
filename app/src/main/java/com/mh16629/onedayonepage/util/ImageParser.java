package com.mh16629.onedayonepage.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageParser {

    /**
     * URL문자열로부터 비트맵 이미지를 받아온다.
     * @param url
     * @return
     */
    public static Bitmap getBitmap(String url) {
        URL imgURL = null;
        HttpURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try {
            imgURL = new URL(url);
            connection = (HttpURLConnection) imgURL.openConnection();
            connection.setDoInput(true);
            connection.connect();

            is = connection.getInputStream();
            retBitmap = BitmapFactory.decodeStream(is);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }

}
