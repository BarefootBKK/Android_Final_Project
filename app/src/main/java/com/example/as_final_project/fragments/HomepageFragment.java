package com.example.as_final_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.as_final_project.R;
import com.example.as_final_project.adapters.PostAdapter;
import com.example.as_final_project.entities.Post;
import com.example.as_final_project.fragments.subfragments.BookFragment;
import com.example.as_final_project.fragments.subfragments.PostFragment;
import com.example.as_final_project.fragments.subfragments.RecommendationFragment;
import com.example.as_final_project.managers.TabPagerManager;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment {

    private final int CONTAINER_ID = R.id.fg_hp_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initTabLayout();
        super.onActivityCreated(savedInstanceState);
    }

    private void initTabLayout() {
//        TabPagerManager tabPagerManager = new TabPagerManager();
//        tabPagerManager.makeNewAdapter(getActivity().getSupportFragmentManager(),
//                (TabLayout) getActivity().findViewById(R.id.fg_hp_tabLayout),
//                (ViewPager) getActivity().findViewById(R.id.fg_hp_viewPager));
//        tabPagerManager.addTab("推荐", "关注");
//        tabPagerManager.addFragment(new PostFragment(), new BookFragment());
//        tabPagerManager.build();
        TabLayout mTab = getActivity().findViewById(R.id.fg_hp_tabLayout);
        mTab.addTab(mTab.newTab().setText("推荐"));
        mTab.addTab(mTab.newTab().setText("关注"));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        replaceFragment(CONTAINER_ID, new PostFragment());
                        break;
                    case 1:
                        replaceFragment(CONTAINER_ID, new BookFragment());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
