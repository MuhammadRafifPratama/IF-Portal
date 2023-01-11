package com.example.tubes02_prototype;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    protected List<Pengumuman> pengumumans;
    protected IMainActivity ui;
    protected VolleyMain volleyMain;
    protected ArrayList<Pertemuan> pertemuans;
    LoginUser user;

    public MainPresenter(IMainActivity ui, LoginUser user, VolleyMain volleyMain){
        this.ui = ui;
        this.user = user;
        this.volleyMain = volleyMain;
        this.pengumumans = new ArrayList<>();
        this.pertemuans = new ArrayList<>();
    }

    public void loadDataPengumuman() {
        volleyMain.callVolleyPengumuman(user.token);
    }

    public void getPertemuan() throws JSONException {
        this.ui.getPertemuan();
    }

    public void loadDataFRS() {
        volleyMain.callVolleyFRS(user.token);

    public ArrayList<Object> loadDataFRS() {
        ArrayList<Object> data = volleyMain.callVolleyFRS(user.token);
        Log.d("debug001", "loadDataFRS: " + data.size());

        return data;

    }


    public void addListPertemuan(String id, String title, String start_datetime, String end_datetime, String description) {
        pertemuans.add(new Pertemuan(id, title, start_datetime, end_datetime, description));
        this.ui.updatePertemuan(pertemuans);
    }
}


