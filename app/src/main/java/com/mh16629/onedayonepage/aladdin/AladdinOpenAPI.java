package com.mh16629.onedayonepage.aladdin;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AladdinOpenAPI {

    private static final String BASE_URL = "http://www.aladdin.co.kr/ttb/api/ItemSearch.aspx?";

    public static String GetUrl(String searchWord) throws Exception {
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ttbkey", "ttbmh55341513001");
        hm.put("Query", URLEncoder.encode(searchWord, "UTF-8"));
        hm.put("QueryType", "Title");
        hm.put("MaxResults", "10");
        hm.put("start", "1");
        hm.put("SearchTarget", "Book");
        hm.put("output", "xml");

        StringBuffer sb = new StringBuffer();
        Iterator<String> iter = hm.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String val = hm.get(key);
            sb.append(key).append("=").append(val).append("&");
        }
        return BASE_URL + sb.toString();
    }
}
