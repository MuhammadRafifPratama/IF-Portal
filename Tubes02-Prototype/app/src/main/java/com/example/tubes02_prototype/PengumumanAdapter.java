package com.example.tubes02_prototype;

import android.util.Log;
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
     ArrayList<Pengumuman> listPengumuman;
    protected MainPresenter presenter;
    protected FragmentManager fragmentManager;
    String next;

    public PengumumanAdapter(MainPresenter presenter, FragmentManager fragmentManager){
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
        this.listPengumuman = new ArrayList<>();
    }

    public void update(List<Pengumuman> pengumumans) {
        if (this.listPengumuman.size()==0){
            this.listPengumuman.addAll(pengumumans);
        }else{
            while (listPengumuman.size()>0){
                listPengumuman.remove(0);
            }
            this.listPengumuman.addAll(pengumumans);
        }
        Log.d("debug", listPengumuman.get(0).title);
        notifyDataSetChanged();
    }

    public String getId(String title) {
        String id = "";
        for(int i=0; i<this.listPengumuman.size(); i++) {
            if(this.listPengumuman.get(i).title.equals(title)) {
                id = listPengumuman.get(i).id;
            }
        }
        return id;
    }

    public void updatePagination(String cursor){
        this.next = cursor;
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
            Log.d("debug", data.title);
            String tag = data.getTags();

            binding.tvTitle.setText(data.title);
            binding.tvTags.setText(tag);

            binding.ivDetail.setOnClickListener(this::onClick);
        }

        public void onClick(View view){
            if(view == binding.ivDetail){
                presenter.setPengumuman(listPengumuman.get(curr));
                FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.fragments_container, PengumumanDetailFragment.newInstance(presenter, fragmentManager));
                ft.commit();
            }

        }
    }
}
