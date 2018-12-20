package com.example.as_final_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.as_final_project.activities.MainActivity;

public class BaseFragment extends Fragment implements View.OnClickListener {

    protected int menuId;
    protected int toolbarId;
    private boolean isSetToolbar = false;
    private boolean isSetMenu = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // 初始化ToolBar
        if (isSetToolbar) {
            this.initToolBar();
        }
        // 初始化Click监听器
        this.initClickListener();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        // 加载图标
        inflater.inflate(menuId, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /**
     * 初始化Click监听器
     */
    private void initClickListener() {

    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {
        // 重载布局
        setHasOptionsMenu(true);
        // 获取Activity
        MainActivity mainActivity = (MainActivity) getActivity();
        // 获取toolbar
        Toolbar toolbar = mainActivity.findViewById(toolbarId);
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (isSetMenu) {
            // 设置toolbar菜单
            toolbar.inflateMenu(menuId);
        }
    }

    /**
     * 设置菜单
     * @param menuId
     */
    public void setMenu(int menuId) {
        this.menuId = menuId;
        this.isSetMenu = true;
    }

    /**
     * 设置Toolbar
     * @param toolbarId
     */
    public void setToolbar(int toolbarId) {
        this.toolbarId = toolbarId;
        this.isSetToolbar = true;
    }
}
