package com.example.tubes02_prototype;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

public class TagAdapter extends BaseAdapter {
    MainPresenter presenter;
    FragmentManager fragmentManager;
    MainActivity activity;
    Tag[] tags;

    public TagAdapter(MainPresenter presenter, FragmentManager fragmentManager, MainActivity activity) {
        this.activity = activity;
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
    }

    public void update(Tag[] tags) {
        this.tags = tags;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return tags.length;
    }

    @Override
    public Object getItem(int i) {
        return tags[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
