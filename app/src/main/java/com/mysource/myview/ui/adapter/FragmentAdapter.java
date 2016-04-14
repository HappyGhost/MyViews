package com.mysource.myview.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mysource.myview.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Forex Exchange screen with two fragments
 * Created by cuong.huynh on 10/9/2015.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    protected List<BaseFragment> fragments;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void addFragments(BaseFragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    public void clear() {
        fragments.clear();
    }

    public void remove(int position){

    }
}
