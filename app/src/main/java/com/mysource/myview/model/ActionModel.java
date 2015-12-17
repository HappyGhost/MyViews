package com.mysource.myview.model;

/**
 * Created by Tung.Hoang on 12-12-15.
 */
public class ActionModel implements IModel{

    String actionText;
    int actionResource;

    public String getActionText() {
        return actionText;
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }

    public int getActionResource() {
        return actionResource;
    }

    public void setActionResource(int actionResource) {
        this.actionResource = actionResource;
    }
}
