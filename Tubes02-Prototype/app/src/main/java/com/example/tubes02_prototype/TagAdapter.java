package com.example.tubes02_prototype;

import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class TagAdapter extends BaseAdapter {
    MainPresenter presenter;
    FragmentManager fragmentManager;
    List<Tags> tags;

    public TagAdapter(MainPresenter presenter, FragmentManager fragmentManager) {
        this.presenter = presenter;
        this.fragmentManager = fragmentManager;
        this.tags = new ArrayList<>();
    }

    public void update(List<Tags> tags) {
        this.tags.addAll(tags);
        Log.d("debug", this.tags.get(0).getTag());
        notifyDataSetChanged();
    }

    public List<String> getTagsByName(String tag) {
        List<String> curr = new ArrayList<>();
        for(int i=0; i<tags.size(); i++) {
            if(tags.get(i).tag.equals(tag)) {
                curr.add(tags.get(i).id);
            }
        }
        return curr;
    }

    public Tags getTagsByTitle(String tag) {
        Tags curr = null;
        for(int i=0; i<tags.size(); i++) {
            if(tags.get(i).tag.equals(tag)) {
                curr = tags.get(i);
            }
        }
        return curr;
    }


    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public Object getItem(int i) {
        return tags.get(i);
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
