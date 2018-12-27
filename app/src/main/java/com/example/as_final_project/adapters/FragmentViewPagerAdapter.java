package com.example.as_final_project.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> fragmentList;

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /**
     * 设置fragmentList和titles
     * @param fragmentList
     * @param titles
     */
    public void setAdapterBasicList(List<Fragment> fragmentList, String... titles) {
        this.fragmentList = fragmentList;
        this.titles = titles;
    }
}
