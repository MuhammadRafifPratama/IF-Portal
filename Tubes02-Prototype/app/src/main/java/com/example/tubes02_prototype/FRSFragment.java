package com.example.tubes02_prototype;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubes02_prototype.databinding.FragmentPengumumanBinding;
import com.example.tubes02_prototype.databinding.FrsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FRSFragment extends Fragment {
    FrsBinding binding;
    FRSAdapter frsAdapter;
    MainPresenter presenter;
    FragmentManager fragmentManager;

    String BASE_URL = "https://ifportal.labftis.net/api/v1/";

    private ListView listView;

    public FRSFragment() {}

    public static Fragment newInstance(MainPresenter presenter, FragmentManager fragmentManager, FRSAdapter frsAdapter) {
        FRSFragment frsFragment = new FRSFragment();
        frsFragment.presenter = presenter;
        frsFragment.fragmentManager = fragmentManager;
        frsFragment.frsAdapter = frsAdapter;

        return frsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FrsBinding.inflate(inflater);
        this.binding.lvSemester.setAdapter(frsAdapter);

        Log.d("role", presenter.user.role);

        String token = presenter.user.token;
        FRSAdapter frsAdapter = new FRSAdapter(this.presenter, this.fragmentManager);

        presenter.loadDataFRS();

        this.listView = this.binding.lvSemester;
        this.binding.lvSemester.setAdapter(frsAdapter);

//        this.binding.lvSemester.setOnItemClickListener(this::onClick);

        API();

        return binding.getRoot();
    }

    public void API() {
        Log.d("debug", "AWE");
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = BASE_URL + "students/email/aguero@sergio.com";
//        String url = BASE_URL + "academic-years";
        ArrayList<Object> data = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("debug", response.toString());

//                if (response != null) {
//                    for (int i=0; i < response.length(); i++){
//                        try {
//                            data.add(response.get(i));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                for(int i=0; i < data.size(); i++) {
////                    Log.d("debug0011", "data" + data.get(i));
//                }

                try {
                    int initialYear = response.getInt("initial_year");
                    Log.d("debug", "onResponse: " + initialYear);
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
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + presenter.user.token);
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void onClick(View view) {
        if (view==this.binding.lvSemester) {
//            DetailSemesterFragment detailSemesterFragment = new DetailSemesterFragment();
//            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//
//            transaction.replace(this.fragmen)
        }
    }

    public void addSemester(int initialYear) {
        int ganjilgenap = initialYear % 10;
        initialYear/=10;
        int temp = 2023 - initialYear;

        ArrayList<Semester> tempArrayListSemester = new ArrayList<>();

        for (int i = 0; i < temp; i++) {
            String tempSemester = "";
            if (ganjilgenap%2==0) {
                tempSemester = "Semester Genap " + initialYear+i + "-" + initialYear+i+1;
            }else {
                tempSemester = "Semester Ganjil " + initialYear+i + "-" + initialYear+i+1;
            }
            ganjilgenap++;
            Semester semester = new Semester(tempSemester);
            tempArrayListSemester.add(semester);
        }

        updateList(tempArrayListSemester);
    }

    public void updateList(ArrayList<Semester> semesters) {
        this.frsAdapter.setListSemester(semesters);
    }


}
