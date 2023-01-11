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

import com.example.tubes02_prototype.databinding.FragmentPengumumanBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PengumumanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PengumumanFragment extends Fragment {
    FragmentPengumumanBinding binding;
    PengumumanAdapter pengumumanAdapter;
    TagAdapter tagAdapter;
    MainPresenter presenter;
    MainActivity mainActivity;
    FragmentManager fragmentManager;
    public PengumumanFragment() {}

    public static PengumumanFragment newInstance(MainPresenter presenter, FragmentManager fragmentManager){
        PengumumanFragment fragment = new PengumumanFragment();
        fragment.presenter = presenter;
        fragment.fragmentManager = fragmentManager;
        //fragment.pengumumanAdapter = pengumumanAdapter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPengumumanBinding.inflate(inflater);
        this.pengumumanAdapter = new PengumumanAdapter(this.presenter, this.fragmentManager);
        this.tagAdapter = new TagAdapter(this.presenter, this.fragmentManager);
        binding.listPengumuman.setAdapter(pengumumanAdapter);
        Log.d("role1", presenter.user.role);
        if(!presenter.user.role.equals("admin")){
            binding.btnNew.setVisibility(View.INVISIBLE);

        }else {
            binding.btnNew.setOnClickListener(this::onClick);
        }

        String token = presenter.user.token;
        binding.listPengumuman.setAdapter(pengumumanAdapter);
        presenter.loadDataPengumuman();
        presenter.loadDataTag();

        binding.btnSearch.setOnClickListener(this::onClick);
        binding.btnNew.setOnClickListener(this::onClick);
        binding.btnRefresh.setOnClickListener(this::onClick);
        binding.btnNext.setOnClickListener(this::onClick);

        return binding.getRoot();

    }

    public void setList(List<Pengumuman> pengmumumans, String cursor) {
        this.pengumumanAdapter.update(pengmumumans);
        this.pengumumanAdapter.updatePagination(cursor);
    }

    public void setTagList(List<Tags> tags) {
        Log.d("debug", tags.get(5).getTag());
        this.tagAdapter.update(tags);

        String[] tagTitle = new String[this.tagAdapter.tags.size()];
        for(int i=0; i<this.tagAdapter.tags.size(); i++) {
            tagTitle[i] = this.tagAdapter.tags.get(i).getTag();
        }
        ArrayAdapter<Tags> adapter = new ArrayAdapter(this.getContext(), R.layout.role_dropdown_item, tagTitle);
        binding.etTag.setAdapter(adapter);
    }

    public void onClick(View view){
        if(view == binding.btnNew) {
            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.fragments_container, PengumumanBaruFormFragment.newInstance(this.presenter, this.fragmentManager, this.tagAdapter));
            ft.commit();
        }else  if(view == binding.btnSearch) {
            String title = binding.etTag.getText().toString();
            if(!title.equals("")) {
                Tags tags = tagAdapter.getTagsByTitle(title);
                filterTitle(tags);
            }
        }else if(view == binding.btnRefresh) {
            presenter.loadDataPengumuman();
        }else if(view == binding.btnNext) {
            nextPage();
        }
    }

    public void nextPage() {
        presenter.loadPaginationPengumuman(this.pengumumanAdapter.next);

    }

    public void filterTitle(Tags tags){
        presenter.loadFilterPengumuman(tags);
    }

}