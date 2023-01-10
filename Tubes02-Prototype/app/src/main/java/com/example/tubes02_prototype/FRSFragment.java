package com.example.tubes02_prototype;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tubes02_prototype.databinding.FragmentPengumumanBinding;
import com.example.tubes02_prototype.databinding.FrsBinding;

public class FRSFragment extends Fragment {
    FrsBinding binding;
    FRSAdapter frsAdapter;
    MainPresenter presenter;
    FragmentManager fragmentManager;

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
        this.binding.expandableListView.setAdapter(frsAdapter);

        Log.d("role", presenter.user.role);

        String token = presenter.user.token;
        FRSAdapter frsAdapter = new FRSAdapter(this.presenter, this.fragmentManager);
        this.binding.expandableListView.setAdapter(frsAdapter);
        presenter.loadDataFRS();

        return binding.getRoot();
    }

}
