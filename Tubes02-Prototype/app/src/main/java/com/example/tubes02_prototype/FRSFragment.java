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

import com.example.tubes02_prototype.databinding.FragmentPengumumanBinding;
import com.example.tubes02_prototype.databinding.FrsBinding;

import java.util.ArrayList;
import java.util.List;

public class FRSFragment extends Fragment {
    FrsBinding binding;
    FRSAdapter frsAdapter;
    MainPresenter presenter;
    FragmentManager fragmentManager;

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

        ArrayList<Object> data = presenter.loadDataFRS();

        this.listView = this.binding.lvSemester;
        this.binding.lvSemester.setAdapter(frsAdapter);

//        this.binding.lvSemester.setOnItemClickListener(this::onClick);

        Log.d("debug001", "onCreateView: " + data.size());

        changeObject(data);

        return binding.getRoot();
    }

    private void onClick(View view) {
        if (view==this.binding.lvSemester) {
//            DetailSemesterFragment detailSemesterFragment = new DetailSemesterFragment();
//            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//
//            transaction.replace(this.fragmen)
        }
    }

    public void changeObject(ArrayList<Object> data) {
        for (int i = 0; i < data.size(); i++) {
            String temp = data.get(i).toString();
            Log.d("debug001", "changeObject: " + data.get(i));
            Log.d("debug001", "data " + temp);
        }

    }

    public void updateList(ArrayList<FRS> frsList) {
        this.frsAdapter.setListFRS(frsList);
    }


}
