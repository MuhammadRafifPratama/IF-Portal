package com.example.tubes02_prototype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.example.tubes02_prototype.databinding.ListPertemuanBinding;

import java.util.ArrayList;
import java.util.List;

public class PertemuanAdapter extends BaseAdapter {
    protected ArrayList<Pertemuan> listPertemuan;
    protected MainPresenter presenter;
    protected FragmentManager fragmentManager;
    protected ListPertemuanBinding listPertemuanBinding;

    public PertemuanAdapter(MainPresenter presenter, FragmentManager fragmentManager){
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
        this.listPertemuan = new ArrayList<>();
    }

    public void setPertemuan(List<Pertemuan> listPertemuan){
        this.listPertemuan = (ArrayList<Pertemuan>) listPertemuan;
    }

    @Override
    public int getCount() {
        return listPertemuan.size();
    }

    @Override
    public Pertemuan getItem(int i) {
        return listPertemuan.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            listPertemuanBinding = ListPertemuanBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
            view = listPertemuanBinding.getRoot();
            viewHolder = new ViewHolder(listPertemuanBinding);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(getItem(i));
        return view;
    }

    private class ViewHolder{
        ListPertemuanBinding listPertemuanBinding;
        Pertemuan pertemuan;

        public ViewHolder(ListPertemuanBinding listPertemuanBinding){
            this.listPertemuanBinding = listPertemuanBinding;
        }

        public void updateView(Pertemuan pertemuan){
            this.pertemuan = pertemuan;
            this.listPertemuanBinding.tvTitlePertemuan.setText(this.pertemuan.getTitle());
            this.listPertemuanBinding.tvDatePertemuan.setText(this.pertemuan.getStart_date());
        }
    }
}
