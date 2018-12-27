package com.example.as_final_project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.config.BasicConfig;
import com.example.as_final_project.config.ServerConfig;
import com.example.as_final_project.entities.NetTask;
import com.example.as_final_project.utils.ToastUtil;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class PublishPostActivity extends BaseActivity implements View.OnClickListener{
    private TextView cancelImageView;
    private TextView publishImageView;
    private CardView addImageCardView;
    private EditText titleEdiText;
    private EditText contentEdiText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        initData();
    }

    private void initData() {
        cancelImageView = findViewById(R.id.a_publish_cancel);
        publishImageView = findViewById(R.id.a_publish_send);
        addImageCardView = findViewById(R.id.a_publish_cv_add_img);
        titleEdiText = findViewById(R.id.a_publish_edit_title);
        contentEdiText = findViewById(R.id.a_publish_edit_content);

        cancelImageView.setOnClickListener(this);
        publishImageView.setOnClickListener(this);
        addImageCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_publish_cancel:
                this.finish();
                break;
            case R.id.a_publish_send:
                publishPost();
                break;
            case R.id.a_publish_cv_add_img:
                break;
            default:
                break;
        }
    }

    private void publishPost() {
        String postTitle = titleEdiText.getText().toString();
        String postContent = contentEdiText.getText().toString();
        if (!postTitle.isEmpty() && !postContent.isEmpty()) {
            NetTask netTask = new NetTask(ServerConfig.getUrl("post/add"), NetTask.POST, new NetTask.NetListener() {
                @Override
                public void onNetSuccess(String jsonData, String message) {
                    PublishPostActivity.this.finish();
                    ToastUtil.showToast(PublishPostActivity.this, "发布成功！");
                }

                @Override
                public void onNetError(int errorCode, String errorMessage) {
                    ToastUtil.showToast(PublishPostActivity.this, errorMessage);
                }

                @Override
                public void onNetJSON(String originalJSON) {

                }
            });
            netTask.execute(getRequestBody(postTitle, postContent));
        }
    }

    private RequestBody getRequestBody(String postTitle, String postContent) {
        RequestBody requestBody = new FormBody.Builder()
                .add("post_title", postTitle)
                .add("post_content", postContent)
                .add("user_email", BasicConfig.user.getUserEmail())
                .build();
        return requestBody;
    }
}
