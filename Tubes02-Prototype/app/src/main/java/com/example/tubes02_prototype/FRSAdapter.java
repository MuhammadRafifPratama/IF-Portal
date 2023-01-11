package com.example.tubes02_prototype;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.example.tubes02_prototype.databinding.ListFrsBinding;

import java.util.ArrayList;

public class FRSAdapter extends BaseAdapter {
    protected ArrayList<Semester> listSemester;
    MainPresenter presenter;
    FragmentManager fragmentManager;
    private Activity activity;

    ListFrsBinding binding;


    public FRSAdapter(MainPresenter presenter, FragmentManager fragmentManager) {
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
        this.listSemester = new ArrayList<>();
    }

    public void setListSemester(ArrayList<Semester> semester) {
        this.listSemester = semester;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.listSemester.size();
    }

    @Override
    public Object getItem(int i) {
        return listSemester.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        binding = ListFrsBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        View views = binding.getRoot();
        ViewHolder viewHolder = new ViewHolder(binding, this.presenter);


        return views;
    }

    private class ViewHolder {
        ListFrsBinding binding;
        MainPresenter presenter;

        int curr;

        public ViewHolder(ListFrsBinding binding, MainPresenter presenter) {
            this.binding = binding;
            this.presenter = presenter;
        }

        public void updateView(int i) {

        }
    }
}
