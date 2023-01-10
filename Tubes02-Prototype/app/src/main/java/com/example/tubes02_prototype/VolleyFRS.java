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
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleyFRS {
    String BASE_URL;
    Context context;
    Gson gson;
    IFRSActivity ui;

    public VolleyFRS(Context context, IFRSActivity ui) {
        this.context = context;
        this.ui = ui;
        this.BASE_URL = "https://ifportal.labftis.net/api/v1/courses/";
        this.gson = new Gson();
    }

    public void execute(String token) throws JSONException {
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXJfaWQiOiI2ZTY2ODZmMC0yOTZlLTRjNzItOGE0NS1hNmFjMWVkNDhlNDQiLCJyb2xlIjoiYWRtaW4ifSwiaWF0IjoxNjczMzM1NjI1fQ.ysqprah_1Krq-TGguGNq3LoxQPn1Pk_rdkNFNnWH-3I";
        callVolley(token);
    }

    public void callVolley(String token) {
        Log.d("debug", "AWE");
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        String url = "https://ifportal.labftis.net/api/v1/courses/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("debug", response.toString());
                ArrayList<Object> data = new ArrayList<>();

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
                    Log.d("debug0011", "data" + data.get(i));
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

    public void changeJSONtoString() {

    }

    public interface IFRSActivity {
        void getKeyLogin(String token);
    }
}
