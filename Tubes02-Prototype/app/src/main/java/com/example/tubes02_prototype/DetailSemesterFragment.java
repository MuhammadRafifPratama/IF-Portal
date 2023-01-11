package com.example.tubes02_prototype;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes02_prototype.databinding.DetailSemesterBinding;
import com.example.tubes02_prototype.databinding.TambahMatkulBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailSemesterFragment extends Fragment {
    DetailSemesterBinding binding;
    DetailSemesterAdapter adapter;
    MainPresenter presenter;
    FragmentManager fragmentManager;

    String BASE_URL = "https://ifportal.labftis.net/api/v1/";

    ListView lvMatkul;

    public DetailSemesterFragment() {}

    public static Fragment newInstance(MainPresenter presenter, FragmentManager fragmentManager, DetailSemesterAdapter adapter) {
        DetailSemesterFragment detailSemesterFragment = new DetailSemesterFragment();
        detailSemesterFragment.presenter = presenter;
        detailSemesterFragment.fragmentManager = fragmentManager;
        detailSemesterFragment.adapter = adapter;

        return detailSemesterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DetailSemesterBinding.inflate(inflater);
        this.binding.lvMatkul.setAdapter(adapter);

        Log.d("role", presenter.user.role);

        String token = presenter.user.token;
        TambahMatkulAdapter adapter = new TambahMatkulAdapter(this.presenter, this.fragmentManager);

        this.lvMatkul = this.binding.lvMatkul;

        this.binding.lvMatkul.setAdapter(adapter);

        API();

        return binding.getRoot();
    }

    public void API() {
        Log.d("debug", "AWE");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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
                headers.put("Authorization", "Bearer " + presenter.user.token);
                return headers;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    private void onClick(View view) {

    }

    public void addMatkul() {

    }

    public void updateList(ArrayList<FRS> matakuliah) {
        this.adapter.setListMataKuliah(matakuliah);
    }
}
