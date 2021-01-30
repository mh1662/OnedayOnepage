package com.mh16629.onedayonepage.aladdin;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AladdinOpenAPI {

    private static final String TTBKEY = "ttbmh55341513001";

    private static final String BASE_URL_BOOK_SEARCH = "http://www.aladdin.co.kr/ttb/api/ItemSearch.aspx?";
    private static final String BASE_URL_ITEM_SELECT = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?";

    /**
     * 알라딘 책 검색 URL을 반환한다.
     *
     *      ttbkey : TTBKEY
     *      Query : 검색어
     *      QueryType : 검색어 종류 제목+저자
     *      MaxResults : 검색결과 한 페이지당 최대 출력 개수
     *      start : 검색결과 시작페이지
     *      SearchTarget : 검색 대상 Mall - 도서
     *      output : 출력방법
     *
     * @param searchWord
     * @return
     * @throws Exception
     */
    public static String GetSearchBookUrl(String searchWord) throws Exception {
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ttbkey", TTBKEY);
        hm.put("Query", URLEncoder.encode(searchWord, "UTF-8"));
        hm.put("QueryType", "Keyword");
        hm.put("MaxResults", "100");
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
        return BASE_URL_BOOK_SEARCH + sb.toString();
    }

    /**
     * 알라딘 아이템 조회 URL을 반환한다.
     *
     *      ttbkey : TTBKEY
     *      itemIdType : 조회용 파라미터(ISBN, ISBN13, ItemId)
     *      ItemId : 상품을 구분짓는 유일한 값
     *      Cover : 표지크기
     *      Output : 출력방법
     *
     * @param itemId
     * @return
     * @throws Exception
     */
    public static String GetSelectItemUrl(String itemId) throws Exception {
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("ttbkey", TTBKEY);
        hm.put("itemIdType", "ItemId");
        hm.put("ItemId", URLEncoder.encode(itemId, "UTF-8"));
        hm.put("Cover", "Big");
        hm.put("Output", "XML");

        StringBuffer sb = new StringBuffer();
        Iterator<String> iter = hm.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String val = hm.get(key);
            sb.append(key).append("=").append(val).append("&");
        }
        return BASE_URL_ITEM_SELECT + sb.toString();
    }
}
