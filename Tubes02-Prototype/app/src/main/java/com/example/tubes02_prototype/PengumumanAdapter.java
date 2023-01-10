package com.example.tubes02_prototype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes02_prototype.databinding.ListPengumumanStringBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PengumumanAdapter extends BaseAdapter {
    protected ArrayList<Pengumuman> listPengumuman;
    protected MainPresenter presenter;
    protected FragmentManager fragmentManager;

    public PengumumanAdapter(MainPresenter presenter, FragmentManager fragmentManager){
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
        this.listPengumuman = new ArrayList<>();
    }

    public void update(List<Pengumuman> pengumumans) {
        this.listPengumuman.addAll(pengumumans);
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
        ViewHolder viewHolder = new ViewHolder(binding, this.presenter);
        viewHolder.updateView(i);
        return itemView;
    }

    private class ViewHolder {
        ListPengumumanStringBinding binding;
        MainPresenter presenter;

        int curr;

        public ViewHolder(ListPengumumanStringBinding binding, MainPresenter presenter){
            this.binding = binding;

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
