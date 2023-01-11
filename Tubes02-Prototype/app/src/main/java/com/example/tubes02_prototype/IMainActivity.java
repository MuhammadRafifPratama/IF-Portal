package com.example.tubes02_prototype;

import java.util.List;

public interface IMainActivity {
    void updatePengumumanList(List<Pengumuman> pengumumans, String cursor);
    void updateTagList(List<Tags> tags);
    void setDetailPengumuman(PengumumanDetail pengumuman);
    //void updateTagListForm(List<Tags> tags);
    void responNewPengumuman(PengumumanDetail pengumumanDetail);
}
