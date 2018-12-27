package com.example.as_final_project.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.as_final_project.R;
import com.example.as_final_project.config.BasicConfig;

import java.util.List;

public class ActivityUtil {

    public static final int FRAGMENT_HOMEPAGE = 0;
    public static final int FRAGMENT_VIDEO_AUDIO = 1;
    public static final int FRAGMENT_IDEA = 2;
    public static final int FRAGMENT_USER = 3;

    public static List<Fragment> fragmentList;

    public static ActivityUtil getInstance() {
        return new ActivityUtil();
    }

    public static void startActivityWithParcelable(Context context, Class targetClass, Parcelable parcelable) {
        Intent intent = new Intent(context, targetClass);
        intent.putExtra(BasicConfig.INTENT_DATA_NAME, parcelable);
        context.startActivity(intent);
    }

    public static void replaceFragment(int containerId, int fragmentIndex, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragmentList.get(fragmentIndex));
        transaction.commit();
    }

    /**
     * 设置为沉浸式状态栏
     */
    public void setStatusBar (Activity activity) {
        boolean useThemeStatusBarColor = false;
        boolean useStatusBarColor = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemeStatusBarColor) {
                activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.colorAccent));//设置状态栏背景色
            } else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(activity, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
