package com.example.as_final_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.as_final_project.R;
import com.example.as_final_project.fragments.HomePageFragment;
import com.example.as_final_project.fragments.IdeaFragment;
import com.example.as_final_project.fragments.ProfileFragment;
import com.example.as_final_project.fragments.VideoAudioFragment;
import com.example.as_final_project.fragments.subfragments.BookFragment;
import com.example.as_final_project.managers.MyFragmentManager;
import com.example.as_final_project.managers.TabPagerManager;
import com.example.as_final_project.utils.ActivityUtil;
import com.example.as_final_project.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    private MyFragmentManager myFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        ToastUtil.initToast(this);
        CircleImageView circleImageView = findViewById(R.id.a_main_circleView);
        circleImageView.bringToFront();
        initFragments();

        FloatingActionButton floatingActionButton = findViewById(R.id.a_main_float_bn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PublishPostActivity.class));
            }
        });
    }

    private void initFragments() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new VideoAudioFragment());
        fragmentList.add(new IdeaFragment());
        fragmentList.add(new ProfileFragment());
        ActivityUtil.fragmentList = fragmentList;
    }
}
