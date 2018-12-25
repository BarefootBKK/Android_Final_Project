package com.example.as_final_project.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.as_final_project.R;
import com.example.as_final_project.utils.ToastUtil;

import cn.bmob.v3.Bmob;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {
    public int ifLogin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ToastUtil.initToast(this);
        CircleImageView circleImageView = findViewById(R.id.a_main_circleView);
        circleImageView.bringToFront();


        Bmob.initialize(this, "a4ab2dc8579e82ecd0f69ae63d679b40");
        if(ifLogin == 0){
            // 强制登录
            Intent intent =new Intent(this,login.class);
            //启动
            startActivity(intent);
            ifLogin = 1;
        }


    }


}
