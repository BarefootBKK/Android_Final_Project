package com.example.as_final_project.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.as_final_project.R;

public class NavigationFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setMenu(R.menu.navigation);
        BottomNavigationView navigationView = getActivity().findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 设置导航栏图标和字体样式
        navigationView.setItemIconTintList(null);
        ColorStateList colorStateList = getResources().getColorStateList(R.color.bm_text);
        navigationView.setItemTextColor(colorStateList);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 导航栏按钮监听器
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_movie:
                    return true;
                case R.id.navigation_idea:
                    return true;
                case R.id.navigation_user:
                    return true;
                default:
                    return false;
            }
        }
    };
}
