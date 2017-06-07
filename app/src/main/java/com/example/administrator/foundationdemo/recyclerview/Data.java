package com.example.administrator.foundationdemo.recyclerview;

/**
 * Created by "FLY" on 2017/4/13.
 */
public class Data {

    private int viewType;

    private String name;

    private String logo;

    private String notificationText;

    private String notificationImg;

    public Data(){

    }

    public Data(int viewType, String name, String logo) {
        this.viewType = viewType;
        this.name = name;
        this.logo = logo;
    }

    public Data(int viewType, String name, String logo, String notificationText, String notificationImg) {
        this.viewType = viewType;
        this.name = name;
        this.logo = logo;
        this.notificationText = notificationText;
        this.notificationImg = notificationImg;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getNotificationImg() {
        return notificationImg;
    }

    public void setNotificationImg(String notificationImg) {
        this.notificationImg = notificationImg;
    }
}
