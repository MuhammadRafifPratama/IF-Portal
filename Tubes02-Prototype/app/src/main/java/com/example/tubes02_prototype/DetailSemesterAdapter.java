package com.example.tubes02_prototype;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.example.tubes02_prototype.databinding.ListIsiFrsBinding;

import java.util.ArrayList;

public class DetailSemesterAdapter extends BaseAdapter {
    protected ArrayList<FRS> listMataKuliah;
    MainPresenter presenter;
    FragmentManager fragmentManager;
    Activity activity;

    ListIsiFrsBinding binding;

    public DetailSemesterAdapter(MainPresenter presenter, FragmentManager fragmentManager) {
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
        this.listMataKuliah = new ArrayList<>();
    }

    public void setListMataKuliah(ArrayList<FRS> mataKuliah) {
        this.listMataKuliah = mataKuliah;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.listMataKuliah.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listMataKuliah.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        binding = ListIsiFrsBinding.inflate(LayoutInflater.from(view.getContext()));
        View views = binding.getRoot();

        ViewHolder viewHolder = new ViewHolder(binding, this.presenter);

        return views;
    }

    private class ViewHolder {
        ListIsiFrsBinding binding;
        MainPresenter presenter;

        int curr;

        public ViewHolder(ListIsiFrsBinding binding, MainPresenter presenter) {
            this.binding = binding;
            this.presenter = presenter;
        }

        public void updateView(int i) {

        }
    }
}
