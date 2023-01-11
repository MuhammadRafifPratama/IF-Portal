package com.example.tubes02_prototype;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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
        String url = BASE_URL+"announcements?limit=4";
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

    public void callVolleyPengumumanPagination(String token, String cursor) {
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL+"announcements?limit=4&cursor="+cursor;
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

    public void callVolleyPengumumanFilter(String token, Filter filter) throws JSONException {
        String stringJSON = gson.toJson(filter);
        JSONObject jsonObject = new JSONObject(stringJSON);
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL+"announcements?limit=4";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("debug", response.toString());
                try {
                    processPengumumansResult(response.toString());
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

    public void callVolleyPengumumanDetail(String token, String id) {
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL+"announcements/"+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("debug", response);
                processDetailPengumuman(response);
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
                try {
                    processTagsResult(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    public void callVolleyNewPengumuman(String token, PengumumanInput input) throws JSONException {
        String stringJSON = gson.toJson(input);
        JSONObject jsonObject = new JSONObject(stringJSON);
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL+"announcements";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("debug", response.toString());
                processDetailPengumuman(response.toString());
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

    public void processTagsResult(String json) throws JSONException{
//        TagsAPI result = gson.fromJson(json, TagsAPI.class);
        Log.d("debug", json);
        Type type = new TypeToken<List<Tags>>(){}.getType();
        List<Tags> tags = gson.fromJson(json, type);
        Log.d("debug", String.valueOf(tags.size()));
        this.ui.updateTagList(tags);
    }

    public void processPengumumansResult(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        Type type1 = new TypeToken<String>(){}.getType();
        String cursor = (String) jsonObject.getJSONObject("metadata").get("next");
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        Type type = new TypeToken<List<Pengumuman>>(){}.getType();
        List<Pengumuman> pengumumans = gson.fromJson(String.valueOf(jsonArray), type);
        Log.d("debug cursor", cursor);

        Log.d("debug", String.valueOf(pengumumans.size()));
        this.ui.updatePengumumanList(pengumumans, cursor);
    }

    public void processDetailPengumuman(String json) {
        PengumumanDetail result = gson.fromJson(json, PengumumanDetail.class);
        Log.d("debug", result.id);
        this.ui.setDetailPengumuman(result);
    }

    public void processNewPengumuman(String json) {
        PengumumanDetail result = gson.fromJson(json, PengumumanDetail.class);
        this.ui.setDetailPengumuman(result);
    }

    public void callVolleyFRS(String token) {
        Log.d("debug", "AWE");
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = BASE_URL + "courses";
        ArrayList<Object> data = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("debug", response.toString());

                if (response != null) {
                    for (int i=0; i < response.length(); i++){
                        try {
                            data.add(response.get(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for(int i=0; i < data.size(); i++) {
//                    Log.d("debug0011", "data" + data.get(i));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

}
