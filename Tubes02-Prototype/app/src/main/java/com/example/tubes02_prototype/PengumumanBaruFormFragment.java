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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tubes02_prototype.databinding.FragmentPengumumanBaruFormBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PengumumanBaruFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PengumumanBaruFormFragment extends Fragment {
    MainPresenter presenter;
    FragmentPengumumanBaruFormBinding binding;
    FragmentManager fragmentManager;
    TagAdapter tagAdapter;

    public PengumumanBaruFormFragment() {}

    public static PengumumanBaruFormFragment newInstance(MainPresenter presenter, FragmentManager fragmentManager, TagAdapter tagAdapter) {
        PengumumanBaruFormFragment fragment = new PengumumanBaruFormFragment();
        fragment.presenter = presenter;
        fragment.fragmentManager = fragmentManager;
        fragment.tagAdapter = tagAdapter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentPengumumanBaruFormBinding.inflate(inflater);

        String[] tagTitle = new String[this.tagAdapter.tags.size()];
        for(int i=0; i<this.tagAdapter.tags.size(); i++) {
            tagTitle[i] = this.tagAdapter.tags.get(i).getTag();
        }
        ArrayAdapter<Tags> adapter = new ArrayAdapter(this.getContext(), R.layout.role_dropdown_item, tagTitle);
        binding.etTag.setAdapter(adapter);

        binding.btnBack.setOnClickListener(this::onClick);
        binding.btnPost.setOnClickListener(this::onClick);

        return binding.getRoot();
    }



    public void onClick(View view) {
        if(view == binding.btnBack) {
            FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.fragments_container, PengumumanFragment.newInstance(presenter, fragmentManager));
            ft.commit();
        }else if(view == binding.btnPost){
            String title = binding.etTitle.getText().toString();
            String content = binding.etContent.getText().toString();
            String tagTitle = binding.etTag.getText().toString();
            Log.d("debug form", tagTitle);
            if(!title.equals("") && !content.equals("") && !tagTitle.equals("")){
                List<String> tagsList = new ArrayList<>();
                tagsList.addAll(this.tagAdapter.getTagsByName(tagTitle));
                PengumumanInput input = new PengumumanInput(title, content, tagsList);
                presenter.newPengumuman(input);
                FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.fragments_container, PengumumanFragment.newInstance(presenter, fragmentManager));
                ft.commit();
            }
        }
    }

//    public void onResponesAPI(PengumumanDetail pengumumanDetail){
//        if(!pengumumanDetail.title.equals("")){
//            Toast toast = Toast.makeText(this.getContext(), "berhasil", Toast.LENGTH_SHORT);
//            toast.show();
//            FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.fragments_container, PengumumanFragment.newInstance(presenter, fragmentManager));
//            ft.commit();
//        }
//    }

}