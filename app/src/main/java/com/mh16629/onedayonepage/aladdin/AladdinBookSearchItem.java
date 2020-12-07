package com.mh16629.onedayonepage.aladdin;

public class AladdinBookSearchItem {
    private int itemId;
    private String title = "";
    private String link = "";
    private String author = "";
    private String pubDate = "";
    private String description = "";
    private String imgRrlStr = "";
    private String publisher = "";

    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public String getImgRrlStr() {
        return imgRrlStr;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgRrlStr(String imgRrlStr) {
        this.imgRrlStr = imgRrlStr;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
