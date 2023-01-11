package com.example.tubes02_prototype;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
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
    PengumumanDetail pengumumanDetail;

    public PengumumanDetailFragment() {}

    public static PengumumanDetailFragment newInstance(MainPresenter presenter, FragmentManager fragmentManager) {
        PengumumanDetailFragment fragment = new PengumumanDetailFragment();
        fragment.presenter = presenter;
        fragment.fragmentManager = fragmentManager;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPengumumanDetailBinding.inflate(inflater);
        if(!presenter.user.role.equals("admin")){
            binding.btnHapus.setVisibility(View.INVISIBLE);
        }
        this.pengumuman = presenter.passingData();
        presenter.loadDetailPengumuman(this.pengumuman.id);

        binding.btnBack.setOnClickListener(this::onCLick);

        binding.btnHapus.setOnClickListener(this::onCLick);

        return binding.getRoot();

    }

    public void setDetail(PengumumanDetail pengumumanDetail){
        Log.d("debug22", pengumumanDetail.title);
        String title = pengumumanDetail.getTitle();
        Log.d("debug23", title);
        String content = pengumumanDetail.getContent();
        String tag = pengumumanDetail.getTags();
//        binding.tvTitle.setText(title);
//        binding.tvPengumuman.setText(content);
//        binding.tvTags.setText(tag);
    }

    public void onCLick(View view){
        if(view == binding.btnBack){
            FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.fragments_container, PengumumanFragment.newInstance(presenter, fragmentManager));
            ft.commit();
        }
    }
}