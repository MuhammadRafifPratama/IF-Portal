package com.example.tubes02_prototype;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubes02_prototype.databinding.FragmentPengumumanBaruFormBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PengumumanBaruFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PengumumanBaruFormFragment extends Fragment {
    MainPresenter presenter;
    FragmentPengumumanBaruFormBinding binding;

    public PengumumanBaruFormFragment() {}

    public static Fragment newInstance(MainPresenter presenter) {
        PengumumanBaruFormFragment fragment = new PengumumanBaruFormFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}