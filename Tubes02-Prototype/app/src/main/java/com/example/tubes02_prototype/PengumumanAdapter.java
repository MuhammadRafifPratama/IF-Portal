package com.example.tubes02_prototype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes02_prototype.databinding.ListPengumumanStringBinding;

import java.util.ArrayList;

public class PengumumanAdapter extends BaseAdapter {
    protected ArrayList<Pengumuman> listPengumuman;
    protected MainPresenter presenter;
    protected FragmentManager fragmentManager;

    public PengumumanAdapter(MainPresenter presenter, FragmentManager fragmentManager){
        this.presenter = presenter;
        this.listPengumuman = new ArrayList<>();
        this.fragmentManager = fragmentManager;
    }

    public void update(ArrayList<Pengumuman> pengumumans) {
        this.listPengumuman = pengumumans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listPengumuman.size();
    }

    @Override
    public Object getItem(int i) {
        return listPengumuman.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListPengumumanStringBinding binding = ListPengumumanStringBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        View itemView =  binding.getRoot();
        return itemView;
    }

    private class ViewHolder {
        ListPengumumanStringBinding binding;
        MainPresenter presenter;
        MainActivity activity;
        int curr;

        public ViewHolder(ListPengumumanStringBinding binding, MainActivity activity, MainPresenter presenter){
            this.binding = binding;
            this.activity = activity;
            this.presenter = presenter;
        }

        public void updateView(int i) {
            this.curr = i;
            Pengumuman data = listPengumuman.get(i);
            String tag = data.getTags();

            binding.tvTitle.setText(data.title);
            binding.tvTags.setText(tag);

            binding.tvTitle.setOnClickListener(this::onClick);
        }

        public void onClick(View view){
            if(view == binding.tvTitle){
                FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.fragments_container, PengumumanDetailFragment.newInstance(presenter, fragmentManager, listPengumuman.get(curr)));
                ft.commit();
            }

        }
    }
}
