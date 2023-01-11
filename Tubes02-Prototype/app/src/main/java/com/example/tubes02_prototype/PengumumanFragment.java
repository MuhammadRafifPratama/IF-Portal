package com.example.tubes02_prototype;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
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

    public static Fragment newInstance(MainPresenter presenter, FragmentManager fragmentManager, PengumumanAdapter pengumumanAdapter){
        PengumumanFragment fragment = new PengumumanFragment();
        fragment.presenter = presenter;
        fragment.fragmentManager = fragmentManager;
        fragment.pengumumanAdapter = pengumumanAdapter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPengumumanBinding.inflate(inflater);
//        binding.listPengumuman.setAdapter(pengumumanAdapter);
//        Log.d("role1", presenter.user.role);
//        if(presenter.user.role.equals("student")){
//            binding.btnNew.invalidate();
//        }else {
//            binding.btnNew.setOnClickListener(this::onClick);
//        }
//
//        String token = presenter.user.token;
//        PengumumanAdapter pengumumanAdapter = new PengumumanAdapter(this.presenter, this.fragmentManager);
//        binding.listPengumuman.setAdapter(pengumumanAdapter);
//        presenter.loadDataPengumuman();
//
//
//        binding.btnSearch.setOnClickListener(this::onClick);

        return null;

    }

    public void setList() {
        this.pengumumanAdapter.update(this.presenter.pengumumans);
    }

    public void onClick(View view){
//        if(view == binding.btnNew) {
//            DialogFragment dialogFragment = new DialogFragment();
//            dialogFragment.show(this.fragmentManager, "PengumumanDetailFragment");
//        }
    }
}