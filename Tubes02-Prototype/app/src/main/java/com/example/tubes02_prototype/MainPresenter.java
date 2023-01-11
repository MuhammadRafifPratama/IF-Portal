package com.example.tubes02_prototype;

import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {
    protected List<Pengumuman> pengumumans;
    protected IMainActivity ui;
    protected VolleyMain volleyMain;
    protected Pengumuman pengumuman;
    ArrayList<Pertemuan> pertemuans;
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

    public void loadDataTag() {
        volleyMain.callVolleyTags(user.token);

    }

    public void loadDetailPengumuman(String id) {
        volleyMain.callVolleyPengumumanDetail(user.token, id);
    }

    public Pengumuman passingData() {
        return this.pengumuman;
    }

    public void setPengumuman(Pengumuman pengumuman){
        this.pengumuman = pengumuman;
    }

    public void newPengumuman(PengumumanInput input)  {
        try {
            volleyMain.callVolleyNewPengumuman(user.token, input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadPaginationPengumuman(String cursor) {
        volleyMain.callVolleyPengumumanPagination(user.token, cursor);
    }

    public void loadFilterPengumuman(Tags tags) {
        Filter filter = new Filter(tags);
        try {
            volleyMain.callVolleyPengumumanFilter(user.token, filter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getPertemuan() throws JSONException {
        this.ui.getPertemuan();
    }

    public void loadDataFRS() {
        volleyMain.callVolleyFRS(user.token);
    }


    public void addListPertemuan(String id, String title, String start_datetime, String end_datetime, String description) {
        pertemuans.add(new Pertemuan(id, title, start_datetime, end_datetime, description));
        this.ui.updatePertemuan(pertemuans);
    }


}


