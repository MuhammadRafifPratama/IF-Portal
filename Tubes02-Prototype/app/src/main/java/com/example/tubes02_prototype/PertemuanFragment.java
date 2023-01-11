package com.example.tubes02_prototype;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes02_prototype.databinding.FragmentPertemuanBinding;

import org.json.JSONException;

import java.util.ArrayList;

public class PertemuanFragment extends Fragment implements IPertemuan {
    private FragmentPertemuanBinding pertemuanBinding;
    private PertemuanAdapter pertemuanAdapter;
    private MainPresenter presenter;
    private FragmentManager fragmentManager;

    public PertemuanFragment(){}

    public static PertemuanFragment newInstance(MainPresenter presenter, FragmentManager fragmentManager, PertemuanAdapter pertemuanAdapter){
        PertemuanFragment fragment = new PertemuanFragment();
        fragment.presenter = presenter;
        fragment.fragmentManager = fragmentManager;
        fragment.pertemuanAdapter = pertemuanAdapter;
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.pertemuanBinding = FragmentPertemuanBinding.inflate(inflater);
        this.pertemuanBinding.lvPertemuan.setAdapter(pertemuanAdapter);

        this.pertemuanAdapter = new PertemuanAdapter(this.presenter, this.fragmentManager);

        try {
            this.presenter.getPertemuan();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pertemuanBinding.btnAdd.setOnClickListener(this::onClick);

        return pertemuanBinding.getRoot();
    }

    public void onClick(View view) {
        Bundle result = new Bundle();
        if(view.getId() == pertemuanBinding.btnAdd.getId()) {
            result.putString("page", "TambahPertemuan");
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }

    @Override
    public void updateListPertemuan(ArrayList<Pertemuan> pertemuans) {
        pertemuanAdapter.setPertemuan(pertemuans);
        pertemuanAdapter.notifyDataSetChanged();
    }
}
