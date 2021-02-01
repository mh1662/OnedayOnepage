package com.mh16629.onedayonepage.aladdin;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AladdinOpenAPISelectItemHandler extends DefaultHandler {

    private static final String TAG = "AladdinOpenAPISelectItemHandler";

    public ArrayList<AladdinItemSelectItem> Item;

    private AladdinItemSelectItem currentItem;
    private boolean inItemElement = false;
    private String tempValue;

    //FIXME: 날짜 포맷 변경
//    private static final SimpleDateFormat pubdateParseFormat = new SimpleDateFormat("E, dd MMM ");
//    private static final SimpleDateFormat pubdateTransFormat = new SimpleDateFormat("");

    public AladdinOpenAPISelectItemHandler() {
        Item = new ArrayList<>();
    }

    public void startElement(String namespace, String localName, String qName, Attributes attributes) {
        if (localName.equals("item")) {
            currentItem = new AladdinItemSelectItem();
            inItemElement = true;
        } else if (localName.equals("title")) {
            tempValue = "";
        } else if (localName.equals("link")) {
            tempValue = "";
        } else if (localName.equals("author")) {
            tempValue = "";
        }else if (localName.equals("pubDate")) {
            tempValue = "";
        }else if (localName.equals("description")) {
            tempValue = "";
        }else if (localName.equals("cover")) {
            tempValue = "";
        }else if (localName.equals("publisher")) {
            tempValue = "";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tempValue = tempValue + new String(ch,start,length);
    }

    public void endElement(String namespaceURI, String localName, String qName) {
        if (inItemElement) {
            if (localName.equals("item")) {
                Item.add(currentItem);
                currentItem = null;
                inItemElement = false;
            } else if (localName.equals("title")) {
                currentItem.setTitle(tempValue);
            } else if (localName.equals("link")) {
                currentItem.setLink(tempValue);
            } else if (localName.equals("author")) {
                currentItem.setAuthor(tempValue);
            } else if (localName.equals("pubDate")) {
                SimpleDateFormat transFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
                Date pubDate = null;
                try {
                    pubDate = transFormat.parse(tempValue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                currentItem.setPubDate(pubDate);

            } else if (localName.equals("description")) {
                currentItem.setDescription(tempValue);
            } else if (localName.equals("cover")) {
                currentItem.setCover(tempValue);
            } else if (localName.equals("publisher")) {
                currentItem.setPublisher(tempValue);
            }
        }
    }

    public static void selectItem(String xmlUrl) throws Exception {
        new AladdinItemSelectTask().execute(xmlUrl);
    }

    public ArrayList<AladdinItemSelectItem> getItem(){ return (ArrayList<AladdinItemSelectItem>) this.Item; }
}
