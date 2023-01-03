package com.example.tubes02_prototype;

import android.content.Context;

import com.google.gson.Gson;

public class VolleyMain {
    String BASE_URL;
    Context context;
    Gson gson;
    IMainActivity ui;

    public VolleyMain(Context context, IMainActivity ui){
        this.context = context;
        this.ui = ui;
        this.gson = new Gson();
        this.BASE_URL = "https://ifportal.labftis.net/api/v1/announcements/";

    }

    public void callVolley(String token) {

    }


}
