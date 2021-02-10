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
                /**
                 *  TODO: 알라딘측 제공 데이터의 author가 SelectItem, SearchBook의 사양차이가 있음.
                 *  SearchBook에서 깔끔하게 나오지 않으니 BookSearchActivity의 저자 레이아웃을 삭제하는건 어떨까?
                 */
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
                currentBook.setCoverURL(tempValue);
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

