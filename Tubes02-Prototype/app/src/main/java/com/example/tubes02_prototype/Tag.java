package com.example.tubes02_prototype;

public class Tag {
    protected String tag;
    protected String tag_id;

    public Tag(String tag, String tag_id){
        this.tag = tag;
        this.tag_id = tag_id;
    }

    public String getTag(){
        return this.tag;
    }

    public String getTag_id() {
        return this.tag_id;
    }
}
