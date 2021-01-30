package com.mh16629.onedayonepage.aladdin;

import android.os.AsyncTask;

import com.mh16629.onedayonepage.booknew.BookNewActivity;

import org.xml.sax.SAXException;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AladdinItemSelectTask  extends AsyncTask<String, Void, ArrayList<AladdinItemSelectItem>> {

    @Override
    protected ArrayList<AladdinItemSelectItem> doInBackground(String... strings) {
        ArrayList<AladdinItemSelectItem> list = new ArrayList<AladdinItemSelectItem>();

        try {
            URL url = new URL(strings[0]);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlR = saxParser.getXMLReader();

            AladdinOpenAPISelectItemHandler dataHandler = new AladdinOpenAPISelectItemHandler();

            xmlR.setContentHandler(dataHandler);
            xmlR.parse(new InputSource(url.openStream()));

            //getResult
            list = dataHandler.getItem();

            //TODO: 비동기 검색결과를 BookNewActivity에 표시
            if (list.size() == 0) {
                ((BookNewActivity)BookNewActivity.mContext).setResultZero();
            } else {
                ((BookNewActivity)BookNewActivity.mContext).setSelectItemResult(list);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
