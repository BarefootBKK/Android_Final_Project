package com.example.as_final_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.as_final_project.R;
import com.example.as_final_project.activities.MainActivity;

public class HomepageFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    private TabLayout mTab;
    private final int HP_CONTAINER_ID = R.id.fg_hp_fragment_container;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setToolbar(R.id.fg_hp_toolbar);
        initTabLayout();
        super.onActivityCreated(savedInstanceState);
    }
    private void initTabLayout() {
        mTab = getActivity().findViewById(R.id.fg_hp_tabLayout);
        mTab.addTab(mTab.newTab().setText("关注"), true);
        mTab.addTab(mTab.newTab().setText("推荐"));
        mTab.addTab(mTab.newTab().setText("热榜"));
        mTab.addOnTabSelectedListener(this);
        replaceFragment(HP_CONTAINER_ID, new MovieFragment());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                replaceFragment(HP_CONTAINER_ID, new FollowFragment());
                break;
            case 1:
                replaceFragment(HP_CONTAINER_ID, new RecommendFragment());
                break;
            case 2:
                replaceFragment(HP_CONTAINER_ID, new ListFragment());
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

}
