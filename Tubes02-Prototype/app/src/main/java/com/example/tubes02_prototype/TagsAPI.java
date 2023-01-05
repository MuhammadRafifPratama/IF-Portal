package com.example.tubes02_prototype;

import android.nfc.Tag;

import java.util.ArrayList;

public class TagsAPI {
    Tag[] tagList;

    public TagsAPI(Tag[] tagList) {
        this.tagList = tagList;
    }

    public Tag getTag(int i) {
        return tagList[i];
    }

}
