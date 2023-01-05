package com.example.tubes02_prototype;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyLogin {
    String BASE_URL;
    Context context;
    Gson gson;
    ILoginActivity ui;

    public VolleyLogin(Context context, ILoginActivity ui) {
        this.context = context;
        this.ui = ui;
        this.BASE_URL = "http://ifportal.labftis.net/api/v1/authenticate";
        this.gson = new Gson();
    }

    public void execute(String email, String password, String role) throws JSONException {
        InputLogin user = new InputLogin(email, password, role);
        Log.d("debug", email);
        String stringJSON = gson.toJson(user);
        JSONObject json = new JSONObject(stringJSON);
        callVolley(json);
    }

    public void callVolley(JSONObject json) {
        RequestQueue requestQueue = Volley.newRequestQueue(this.context);
        Log.d("debug", json.toString());
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, this.BASE_URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("debug", response.toString());
                String json = response.toString();
                Log.d("debug", json);
                processResult(json);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug", "error");
            }
        });
        requestQueue.add(jsonObject);
    }

    public void processResult(String json) {
        LoginResult result = gson.fromJson(json, LoginResult.class);
        Log.d("debug2", result.getToken());
        this.ui.getKeyLogin(result.getToken());
    }

    public interface ILoginActivity {
        void getKeyLogin(String token);
    }
}
