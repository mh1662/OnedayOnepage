package com.mh16629.onedayonepage.aladdin;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class AladdinOpenAPISearchBookHandler extends DefaultHandler {

    private static final String TAG = "AladdinOpenAPISearchBookHandler";

    public ArrayList<AladdinBookSearchItem> Books;

    private AladdinBookSearchItem currentBook;
    private boolean inItemElement = false;
    private String tempValue;

    private static final String authorExceptTag = "지음";
    private static final String descriptionExceptTagStart = "<img";
    private static final String descriptionExceptTagEnd = "<br/>";

    //FIXME: 날짜 포맷 변경
//    private static final SimpleDateFormat pubdateParseFormat = new SimpleDateFormat("E, dd MMM ");
//    private static final SimpleDateFormat pubdateTransFormat = new SimpleDateFormat("");

    public AladdinOpenAPISearchBookHandler() { Books = new ArrayList<>(); }

    public void startElement(String namespace, String localName, String qName, Attributes attributes) {
        if (localName.equals("item")) {
            currentBook = new AladdinBookSearchItem();

            //parse itemId
            int length = attributes.getLength();
            for (int i=0; i<length; i++) {
                currentBook.setItemId(attributes.getValue(i));
            }

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

    public void characters(char[] ch, int start, int length) throws SAXException{
        tempValue = tempValue + new String(ch,start,length);
    }

    public void endElement(String namespaceURI, String localName, String qName) {
        if (inItemElement) {
            if (localName.equals("item")) {
                Books.add(currentBook);
                currentBook = null;
                inItemElement = false;

            } else if (localName.equals("title")) {
                currentBook.setTitle(tempValue);
            } else if (localName.equals("link")) {
                currentBook.setLink(tempValue);
            } else if (localName.equals("author")) {
                //MEMO: ~~"지음" 이라는 문자열 삭제할까? 말까?
                if (tempValue.contains(authorExceptTag)) {
                    String subAuthor = null;
                    subAuthor = tempValue.substring(tempValue.lastIndexOf(authorExceptTag) + 2);
                    currentBook.setAuthor(subAuthor);
                } else {
                    currentBook.setAuthor(tempValue);
                }
            } else if (localName.equals("pubDate")) {
                SimpleDateFormat transFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
                Date pubDate = null;
                try {
                    pubDate = transFormat.parse(tempValue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                currentBook.setPubDate(pubDate);
            } else if (localName.equals("description")) {
                if (tempValue.contains(descriptionExceptTagStart)) {
                    String subDescription = null;
                    subDescription = tempValue.substring(tempValue.lastIndexOf(descriptionExceptTagEnd) + 6);
                    currentBook.setDescription(subDescription);
                } else {
                    currentBook.setDescription(tempValue);
                }
            } else if (localName.equals("cover")) {
                currentBook.setImgUrlStr(tempValue);
            } else if (localName.equals("publisher")) {
                currentBook.setPublisher(tempValue);
            }
        }
    }

    public static void searchBook(String xmlUrl) throws Exception {
        new AladdinBookSearchTask().execute(xmlUrl);
    }

    public ArrayList<AladdinBookSearchItem> getBooks(){ return (ArrayList<AladdinBookSearchItem>) this.Books; }
}

