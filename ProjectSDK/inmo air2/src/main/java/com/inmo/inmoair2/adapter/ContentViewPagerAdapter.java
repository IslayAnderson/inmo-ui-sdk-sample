package com.inmo.inmoair2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.inmo.inmoair2.ContentFragment;
import com.inmo.inmoair2.MainBean;

import java.util.List;


public class ContentViewPagerAdapter extends SmartFragmentStatePagerAdapter {
    private static final String TAG = "ContentViewPagerAdapter";

    private List<MainBean> dataBeans;

    public ContentViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ContentFragment.newInstance(dataBeans.get(position).getName());
    }

    @Override
    public int getCount() {
        return dataBeans == null ? 0 : dataBeans.size();
    }

    public void setData(List<MainBean> dataBeans) {
        this.dataBeans = dataBeans;
    }
}
