package com.example.as_final_project.managers;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.as_final_project.adapters.FragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabPagerManager {
    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;
    private List<Fragment> fragmentList;
    private String[] titles;

    public void makeNewAdapter(FragmentManager fragmentManager, TabLayout tabLayout, ViewPager viewPager) {
        this.fragmentManager = fragmentManager;
        this.tabLayout = tabLayout;
        this.viewPager = viewPager;
        this.fragmentList = new ArrayList<>();
        viewPager.setOffscreenPageLimit(2);
    }

    public void addFragment(Fragment... fragments) {
        for (Fragment fragment : fragments) {
            fragmentList.add(fragment);
        }
    }

    public void addTab(String... titles) {
        this.titles = titles;
    }

    public void build() {
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(fragmentManager);
        fragmentViewPagerAdapter.setAdapterBasicList(fragmentList, titles);
        viewPager.setAdapter(fragmentViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
