package com.example.as_final_project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.as_final_project.R;
import com.example.as_final_project.config.ServerConfig;
import com.example.as_final_project.entities.NetTask;
import com.example.as_final_project.managers.RequestBodyManager;
import com.example.as_final_project.utils.ToastUtil;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity implements NetTask.NetListener{
    public static final String TAG = "测试";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setActivityToolbar(R.id.signUpToolbar, true, false);
        Button button_sign_up = findViewById(R.id.button_register);
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetTask netTask = new NetTask(ServerConfig.getUrl("register"),
                        NetTask.POST, RegisterActivity.this);
                netTask.execute(getRequestBody());
            }
        });
    }

    private RequestBody getRequestBody() {
        String username = ((EditText) findViewById(R.id.signUpEditUser))
                .getText().toString();
        String password = ((EditText) findViewById(R.id.signUpEditPassword))
                .getText().toString();
        String password_cfm = ((EditText) findViewById(R.id.signUpEditPasswordCfm))
                .getText().toString();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("password_cfm", password_cfm)
                .build();
        return requestBody;
    }

    @Override
    public void onNetSuccess(String jsonData, String message) {
        ToastUtil.showToast(this, "注册成功");
        this.finish();
    }

    @Override
    public void onNetError(int errorCode, String errorMessage) {
        ToastUtil.showToast(this, errorMessage);
    }

    @Override
    public void onNetJSON(String originalJSON) {
        Log.d("测试", "onNetJSON: " + originalJSON);
    }
}
