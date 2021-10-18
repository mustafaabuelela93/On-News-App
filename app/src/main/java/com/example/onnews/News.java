package com.example.onnews;

import org.json.JSONObject;

public class News {

    private String mSectionId;


    /** web title of the selected news item */
    private String mWebTitle;

    /** date of news item */
    private String mDate;

    /** Website URL of the news article */
    private String mUrl;

    private String mAuthor;

    private String mThumbnail;


    public News(String sectionId, String webTitle, String url, String date, String author, String thumbnail) {
        mWebTitle = webTitle;
        mDate = date;
        mUrl = url;
        mSectionId = sectionId;
        mAuthor = author;
        mThumbnail = thumbnail;

    }


    /**
     * Returns the title of the news item.
     */
    public String getWebTitle() {
        return mWebTitle;
    }

    /**
     * Returns the date of the news article.
     */
    public String getDate() {
        return mDate;
    }

    /**
     * Returns the website URL to find more information about the news article.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Returns the name of the author of the article.
     */
    public String getSectionId() {
        return mSectionId;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getThumbnail() {
        return mThumbnail;
    }
}
