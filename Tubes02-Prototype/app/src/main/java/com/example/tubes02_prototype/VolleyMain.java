package com.example.tubes02_prototype;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Authenticator;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

public class VolleyMain {
    String BASE_URL;
    Context context;
    Gson gson;
    IMainActivity ui;

    public VolleyMain(Context context, IMainActivity ui){
        this.context = context;
        this.ui = ui;
        this.gson = new Gson();
        this.BASE_URL = "https://ifportal.labftis.net/api/v1/";

    }

    public void callVolley(String token) {

    }

    public void callVolleyTags() {
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL+"tags";
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("debug", response.toString());
                String json = response.toString();
                Log.d("debug", json);
                processTagsResult(json);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, {
            @Override
                public Map<String,String> getHeader() {
                return ;
        }
        });
        requestQueue.add(jsonObject);
    }

    public void processTagsResult(String json) {

    }


}
