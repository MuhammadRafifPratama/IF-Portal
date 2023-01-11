package com.example.tubes02_prototype;

import android.widget.TextView;

public class PengumumanDetail {
    String id;
    String title;
    String updated_at;
    String created_at;
    Author author;
    Tags[] tags;
    String content;


    public PengumumanDetail(String id, String title, String content, String created_at, String updated_at, Author author, Tags[] tags){
        this.title = title;
        this.tags = tags;
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.author = author;
        this.content = content;

    }

    public String getTags() {
        String tag = "";
        for(int i=0; i<tags.length; i++){
            Tags curr = tags[i];
            if(tag.equals("")){
                tag+= curr.getTag();
            }else{
                tag+= ", "+curr.getTag();
            }
        }
        return tag;

    }

    public String getTitle(){
        return this.title;
    }

    public String getContent() {
        return this.content;
    }
}
