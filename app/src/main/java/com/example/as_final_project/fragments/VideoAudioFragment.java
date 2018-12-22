package com.example.as_final_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.as_final_project.R;

public class VideoAudioFragment extends BaseFragment implements TabLayout.OnTabSelectedListener{
    private TabLayout mTab;
    private final int VA_CONTAINER_ID = R.id.fg_va_fragment_container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_audio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setToolbar(R.id.fg_va_toolbar);
        initTabLayout();
        super.onActivityCreated(savedInstanceState);
    }

    private void initTabLayout() {
        mTab = getActivity().findViewById(R.id.fg_va_tabLayout);
        mTab.addTab(mTab.newTab().setText("电影"), true);
        mTab.addTab(mTab.newTab().setText("书籍"));
        mTab.addTab(mTab.newTab().setText("音乐"));
        mTab.addOnTabSelectedListener(this);
        replaceFragment(VA_CONTAINER_ID, new MovieFragment());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                replaceFragment(VA_CONTAINER_ID, new MovieFragment());
                break;
            case 1:
                replaceFragment(VA_CONTAINER_ID, new BookFragment());
                break;
            case 2:
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
