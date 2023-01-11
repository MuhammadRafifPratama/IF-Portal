package com.example.tubes02_prototype;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public interface IMainActivity {
    void updatePengumumanList(List<Pengumuman> pengumumans);
    void updateTagList(Tag[] tags);
    void getPertemuan() throws JSONException;
    void updatePertemuan(ArrayList<Pertemuan> pertemuans);
}
