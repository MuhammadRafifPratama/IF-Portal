package com.example.tubes02_prototype;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubes02_prototype.databinding.FragmentPengumumanBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PengumumanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PengumumanFragment extends Fragment {
    FragmentPengumumanBinding binding;
    PengumumanAdapter pengumumanAdapter;
    MainPresenter presenter;
    MainActivity mainActivity;
    FragmentManager fragmentManager;
    public PengumumanFragment() {}

    public static Fragment newInstance(MainPresenter presenter, FragmentManager fragmentManager){
        PengumumanFragment fragment = new PengumumanFragment();
        fragment.presenter = presenter;
        fragment.fragmentManager = fragmentManager;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPengumumanBinding.inflate(inflater);
        pengumumanAdapter = new PengumumanAdapter(this.presenter, this.fragmentManager);
        binding.listPengumuman.setAdapter(pengumumanAdapter);
        if(presenter.user.role.equals("student")){
            binding.fab.hide();
        }else {
            binding.fab.setOnClickListener(this::onClick);
        }

        binding.btnSearch.setOnClickListener(this::onClick);

        return binding.getRoot();

    }

    public void onClick(View view){

    }
}