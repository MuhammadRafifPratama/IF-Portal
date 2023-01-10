package com.example.tubes02_prototype;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

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

    public void callVolleyPengumuman(String token) {
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL+"announcements";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("debug", response);
                try {
                    processPengumumansResult(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + token);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void callVolleyTags(String token) {
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL+"tags";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("debug", response);
                processTagsResult(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Bearer " + token);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void processTagsResult(String json) {
        TagsAPI result = gson.fromJson(json, TagsAPI.class);
        Tag[] tags = result.getTagList();

    }

    public void processPengumumansResult(String json) throws JSONException {
        ListPengumumanResult result = gson.fromJson(json, ListPengumumanResult.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        Type type = new TypeToken<List<Pengumuman>>(){}.getType();
        List<Pengumuman> pengumumans = gson.fromJson(String.valueOf(jsonArray), type);
        Log.d("debug", String.valueOf(pengumumans.size()));
        this.ui.updatePengumumanList(pengumumans);
    }


}
