package com.example.tubes02_prototype;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public interface IMainActivity {
    void updatePengumumanList(List<Pengumuman> pengumumans, String cursor);
    void updateTagList(List<Tags> tags);
    void setDetailPengumuman(PengumumanDetail pengumuman);
    //void updateTagListForm(List<Tags> tags);
    void responNewPengumuman(PengumumanDetail pengumumanDetail);
    void getPertemuan() throws JSONException;
    void updatePertemuan(ArrayList<Pertemuan> pertemuans);
}
