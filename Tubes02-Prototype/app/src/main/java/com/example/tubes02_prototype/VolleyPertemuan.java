package com.example.tubes02_prototype;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class VolleyPertemuan {
    public static final String BASE_URL = "https://ifportal.labftis.net/api/v1/appointments/";
    private MainPresenter presenter;
    private Context context;
    private Gson gson;

    public VolleyPertemuan(MainPresenter presenter, Context context) {
        this.presenter = presenter;
        this.gson = new Gson();
        this.context = context;
    }

    public void execute() throws JSONException {
        this.callVolley();
    }

    public void callVolley() throws JSONException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String start_date = dateFormat.format(date);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, +6);

        Date date2 = cal.getTime();
        String end_date = dateFormat.format(date2);

        JSONObject json = new JSONObject();
        json.put("start_date", start_date);
        json.put("end_date", end_date);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(json);

        String url = BASE_URL + "start-date/" + start_date + "/end-date/" + end_date;
        RequestQueue rq = Volley.newRequestQueue(this.context);
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, jsonArray, response -> {
            Log.d("debugPertemuan", response.toString());
            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = new JSONObject();
                try {
                    obj = (JSONObject) response.get(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    presenter.addListPertemuan(obj.getString("id"),
                            obj.getString("title"),
                            obj.getString("start_datetime"),
                            obj.getString("end_datetime"),
                            obj.getString("description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error ->
                Log.d("debugPertemuan", error.toString())) {
        };
        rq.add(req);
    }
}
