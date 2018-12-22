package com.example.as_final_project.activities;

import android.os.Bundle;

import com.example.as_final_project.R;
import com.example.as_final_project.utils.ToastUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtil.initToast(this);
        CircleImageView circleImageView = findViewById(R.id.a_main_circleView);
        circleImageView.bringToFront();
    }

}
