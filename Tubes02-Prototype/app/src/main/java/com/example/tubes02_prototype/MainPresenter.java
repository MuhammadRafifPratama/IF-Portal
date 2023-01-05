package com.example.tubes02_prototype;

import android.util.Log;

import java.util.ArrayList;

public class MainPresenter {
    protected ArrayList<Pengumuman> pengumumans;
    protected IMainActivity ui;
    LoginUser user;

    public MainPresenter(IMainActivity ui, LoginUser user){
        this.ui = ui;
        this.user = user;
        this.pengumumans = new ArrayList<>();
    }

    public void loadDataPengumuman(ArrayList<Pengumuman> pengumumans) {
        this.pengumumans.addAll(pengumumans);

    }


}


