package com.example.tubes02_prototype;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    protected List<Pengumuman> pengumumans;
    protected IMainActivity ui;
    protected VolleyMain volleyMain;
    LoginUser user;

    public MainPresenter(IMainActivity ui, LoginUser user, VolleyMain volleyMain){
        this.ui = ui;
        this.user = user;
        this.volleyMain = volleyMain;
        this.pengumumans = new ArrayList<>();

    }

    public void loadDataPengumuman() {
        volleyMain.callVolleyPengumuman(user.token);
    }


}


