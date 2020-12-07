package com.mh16629.onedayonepage.aladdin;

import android.util.Log;

import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class AladdinOpenAPIHandler extends DefaultHandler {

    private static final String TAG = "AladdinOpenAPIHandler";

    public ArrayList<AladdinBookSearchItem> Items;
    private AladdinBookSearchItem currentItem;
    private boolean inItemElement = false;
    private String tempValue;

    public AladdinOpenAPIHandler() {
        Items = new ArrayList<>();
    }

    public void startElement(String namespace, String localName, String qName, Attributes atts) {
        if (localName.equals("item")) {
            currentItem = new AladdinBookSearchItem();
            inItemElement = true;
        } else if (localName.equals("title")) {
            tempValue = "";
        } else if (localName.equals("link")) {
            tempValue = "";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException{
        tempValue = tempValue + new String(ch,start,length);
    }

    public void endElement(String namespaceURI, String localName, String qName) {
        if (inItemElement) {
            if (localName.equals("item")) {
                Items.add(currentItem);
                currentItem = null;
                inItemElement = false;
            } else if (localName.equals("title")) {
                currentItem.setTitle(tempValue);
            } else if (localName.equals("link")) {
                currentItem.setLink(tempValue);
            }
        }
    }

    public static void parseXml(String xmlUrl) throws Exception {
        ArrayList<AladdinBookSearchItem> list = new ArrayList<AladdinBookSearchItem>();
        new AladdinBookSearchTask().execute(xmlUrl);
    }

    public ArrayList<AladdinBookSearchItem> getResult(){ return (ArrayList<AladdinBookSearchItem>) this.Items; }

}

