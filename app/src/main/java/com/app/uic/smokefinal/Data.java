package com.app.uic.smokefinal;

public class Data {


    private String coreid;
    private String data;
    private String event;
    private String location;
    private String published_at;

    public  Data(){

    }

    public Data(String coreid, String data, String event, String location, String published_at){
        this.coreid = coreid;
        this.data = data;
        this.event = event;
        this.location = location;
        this.published_at = published_at;

    }

    public String getCoreid() {
        return coreid;
    }

    public void setCoreid(String coreid) {
        this.coreid = coreid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }



}
