package com.example.tubes02_prototype;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubes02_prototype.databinding.FragmentPengumumanDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PengumumanDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PengumumanDetailFragment extends Fragment {

    Pengumuman pengumuman;
    MainPresenter presenter;
    FragmentManager fragmentManager;
    FragmentPengumumanDetailBinding binding;

    public PengumumanDetailFragment() {}

    public static Fragment newInstance(MainPresenter presenter, FragmentManager fragmentManager, Pengumuman pengumuman) {
        PengumumanDetailFragment fragment = new PengumumanDetailFragment();
        fragment.presenter = presenter;
        fragment.fragmentManager = fragmentManager;
        fragment.pengumuman = pengumuman;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPengumumanDetailBinding.inflate(inflater);
        binding.tvTitle.setText(pengumuman.title);
        binding.tvTags.setText(pengumuman.getTags());
        binding.tvPengumuman.setText(pengumuman.konten);

        binding.btnBack.setOnClickListener(this::onCLick);
        binding.btnEdit.setOnClickListener(this::onCLick);
        binding.btnHapus.setOnClickListener(this::onCLick);

        return binding.getRoot();

    }

    public void onCLick(View view){

    }
}