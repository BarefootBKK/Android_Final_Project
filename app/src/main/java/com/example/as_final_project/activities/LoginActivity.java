package com.example.as_final_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.config.BasicConfig;
import com.example.as_final_project.config.ServerConfig;
import com.example.as_final_project.entities.NetTask;
import com.example.as_final_project.entities.User;
import com.example.as_final_project.managers.QQLoginManager;
import com.example.as_final_project.utils.ActivityUtil;
import com.example.as_final_project.utils.ToastUtil;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity implements
        QQLoginManager.QQLoginListener, View.OnClickListener {
    private ImageView loginImageView;
    private TextView loadHintTextView;
    private Button registerButton;
    private Button loginButton;
    private QQLoginManager qqLoginManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        qqLoginManager = new QQLoginManager("1107995901", this, this);
        loginImageView = findViewById(R.id.a_login_img_qq);
        loadHintTextView = findViewById(R.id.a_login_text_loading_hint);
        registerButton = findViewById(R.id.a_login_bn_register);
        loginButton = findViewById(R.id.a_login_bn_login);
        loginImageView.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        qqLoginManager.registerResultListener(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_login_img_qq:
                qqLoginManager.launchQQLogin(this);
                showLoadingHint(true);
                break;
            case R.id.a_login_bn_login:
                login();
                break;
            case R.id.a_login_bn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onQQLoginSuccess(JSONObject jsonObject) {
        ToastUtil.showToast(this, "登陆成功");
        showLoadingHint(false);
        this.finish();
    }

    @Override
    public void onQQLoginCancel() {
        ToastUtil.showToast(this, "登陆取消");
        showLoadingHint(false);
    }

    @Override
    public void onQQLoginError(UiError uiError) {
        ToastUtil.showToast(this, "登陆失败");
        showLoadingHint(false);
    }

    private void showLoadingHint(boolean isShow) {
        if (isShow) {
            loadHintTextView.setText("正在发起第三方授权，请稍候...");
        } else {
            loadHintTextView.setText("");
        }
    }

    private void login() {
        String username = ((EditText) findViewById(R.id.a_login_edit_account))
                .getText().toString();
        String password = ((EditText) findViewById(R.id.a_login_edit_password))
                .getText().toString();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        NetTask netTask = new NetTask(ServerConfig.getUrl("login"), NetTask.POST, new NetTask.NetListener() {
            @Override
            public void onNetSuccess(String jsonData, String message) {
                BasicConfig.user = getUser(jsonData);
                ToastUtil.showToast(LoginActivity.this, "登录成功");
                LoginActivity.this.finish();
            }

            @Override
            public void onNetError(int errorCode, String errorMessage) {
                ToastUtil.showToast(LoginActivity.this, errorMessage);
            }

            @Override
            public void onNetJSON(String originalJSON) {

            }
        });
        netTask.execute(requestBody);
    }

    private User getUser(String jsonData) {
        User user = new User();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            user.setUserEmail(jsonObject.getString("email"));
            user.setUserNickname(jsonObject.getString("email"));
            user.setUserSignature(jsonObject.getString("signature"));
            user.setUserHeadImageUrl(jsonObject.getString("head_img_url"));
        } catch (JSONException e) {}
        return user;
    }
}
