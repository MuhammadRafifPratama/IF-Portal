package com.example.tubes02_prototype;

public class TagsAPI {
    com.example.tubes02_prototype.Tag[] tagList;

    public TagsAPI(com.example.tubes02_prototype.Tag[] tagList) {
        this.tagList = tagList;
    }

    public com.example.tubes02_prototype.Tag getTag(int i) {
        return tagList[i];
    }

    public Tag[] getTagList() {
        return tagList;
    }
}
