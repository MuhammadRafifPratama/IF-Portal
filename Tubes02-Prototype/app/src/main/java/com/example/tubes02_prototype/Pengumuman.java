package com.example.tubes02_prototype;

import android.nfc.Tag;

import java.util.List;

public class Pengumuman {
    String id;
    String title;
    String updated_at;
    String created_at;
    Author author;
    Tag[] tags;

    String author_id;

    public Pengumuman(String id, String title, String author_id, String created_at, String updated_at, Author author, Tag[] tags){
        this.title = title;
        this.tags = tags;
        this.id = id;
        this.author_id = author_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.author = author;

    }

    public String getTags() {
        String tag = "";
        for(int i=0; i<tags.length; i++){
            if(tag.equals("")){
                tag+= tags[i];
            }else{
                tag+= ", "+tags[i];
            }
        }
        return tag;

    }
}
