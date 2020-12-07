package com.mh16629.onedayonepage.aladdin;

import android.os.AsyncTask;

import com.mh16629.onedayonepage.booksearch.BookSearchActivity;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AladdinBookSearchTask extends AsyncTask<String, Void, ArrayList<AladdinBookSearchItem>> {

    @Override
    protected ArrayList<AladdinBookSearchItem> doInBackground(String... strings) {
        ArrayList<AladdinBookSearchItem> list = new ArrayList<AladdinBookSearchItem>();

        try {
            URL url = new URL(strings[0]);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlR = saxParser.getXMLReader();

            AladdinOpenAPIHandler dataHandler = new AladdinOpenAPIHandler();

            xmlR.setContentHandler(dataHandler);
            xmlR.parse(new InputSource(url.openStream()));

            //getResult
            list = dataHandler.getResult();
//            dataHandler.setResult(list);

            //비동기 검색결과를 BookSearchActivity에 표시
            if (list.size() == 0) {
                ((BookSearchActivity)BookSearchActivity.mContext).setSearchResultZero();
            } else {
                ((BookSearchActivity)BookSearchActivity.mContext).setSearchResult(list);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
