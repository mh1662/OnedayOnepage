package com.mh16629.onedayonepage.aladdin;

import java.util.Date;

public class AladdinItemSelectItem {

    private String title = "";
    private String link = "";
    private String author = "";
    private Date pubDate;
    private String description = "";
    private String coverURL = "";
    private String categoryId = "";
    private String categoryName = "";
    private String publisher= "";

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getPublisher() {
        return publisher;
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

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

