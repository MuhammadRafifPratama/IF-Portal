package com.example.tubes02_prototype;

public class Tags {
    protected String tag;
    protected String id;

    public Tags(String id, String tag){
        this.tag = tag;
        this.id = id;
    }

    public String getTag(){
        return this.tag;
    }

    public String getTag_id() {
        return this.id;
    }
}
