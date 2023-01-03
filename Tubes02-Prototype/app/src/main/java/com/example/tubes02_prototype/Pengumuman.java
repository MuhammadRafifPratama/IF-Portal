package com.example.tubes02_prototype;

import android.nfc.Tag;

import java.util.List;

public class Pengumuman {
    String id;
    String title;
    List<Tag> tags;
    String konten;

    public Pengumuman(String id, String title, List<Tag> tags, String konten){
        this.title = title;
        this.tags = tags;
        this.konten = konten;
        this.id = id;
    }

    public String getTags() {
        String tag = "";
        for(int i=0; i<tags.size(); i++){
            if(tag.equals("")){
                tag+= tags.get(i);
            }else{
                tag+= ", "+tags.get(i);
            }
        }
        return tag;

    }
}
