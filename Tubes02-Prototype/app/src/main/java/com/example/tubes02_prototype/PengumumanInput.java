package com.example.tubes02_prototype;

import java.util.ArrayList;
import java.util.List;

public class PengumumanInput {
    String title;
    String content;
    List<String> tags;

    public PengumumanInput(String title, String content, List<String> tagsList) {
        this.title = title;
        this.content = content;
        this.tags = new ArrayList<>();
        this.tags.addAll(tagsList);
    }

//    //public void addTag(Tags tag) {
//        this.tags.add(tag);
//    }
}
