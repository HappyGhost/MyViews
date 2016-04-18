package com.mysource.myview.model;

/**
 * Created by tung.hoang on 2/18/2016.
 */
public class SimpleItemSearch extends ISearchModel {

    String content;

    @Override
    public String getStringForSearching() {
        return content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
