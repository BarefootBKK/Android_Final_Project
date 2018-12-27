package com.example.as_final_project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.config.BasicConfig;
import com.example.as_final_project.entities.Post;
import com.squareup.picasso.Picasso;

public class PostDetailActivity extends BaseActivity {
    private TextView postTitle;
    private TextView postContent;
    private TextView postDate;
    private TextView postLikeNum;
    private TextView postCommentNum;
    private TextView bloggerNickname;
    private ImageView bloggerImage;
    private ImageView postImage;
    private ImageView postLikeImage;
    private RecyclerView commentRecyclerView;
    private Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setActivityToolbar(R.id.a_post_toolBar, true, false);
        loadControl();
        loadData();
    }

    private void loadControl() {
        post = getIntent().getParcelableExtra(BasicConfig.INTENT_DATA_NAME);
        postTitle = findViewById(R.id.a_post_title);
        postContent = findViewById(R.id.a_post_content);
        postImage = findViewById(R.id.a_post_p_image);
        postDate = findViewById(R.id.a_post_text_post_date);
        postLikeNum = findViewById(R.id.a_post_text_like_num);
        postCommentNum = findViewById(R.id.a_post_text_comment_num);
        bloggerImage = findViewById(R.id.a_post_user_image);
        postLikeImage = findViewById(R.id.a_post_img_like);
        bloggerNickname = findViewById(R.id.a_post_user_nickname);
        commentRecyclerView = findViewById(R.id.a_post_comment_ry);
    }

    private void loadData() {
        postTitle.setText(post.getPostTitle());
        postContent.setText(post.getPostContent());
        postDate.setText(post.getPostDate());
        postLikeNum.setText(post.getPostLikeNum());
        postCommentNum.setText("(" + post.getPostCommentNum() + ")");
        bloggerNickname.setText(post.getBloggerNickname());
        Picasso.get().load(post.getBloggerImageUrl()).into(bloggerImage);
        if (post.getPostImageUrl().length() > 0) {
            Picasso.get().load(post.getPostImageUrl()).into(postImage);
        } else {
            postImage.getLayoutParams().height = 0;
        }
        postLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLikeImage.setImageResource(R.drawable.ic_like);
            }
        });
        postLikeImage.setImageResource(R.drawable.ic_like);
        postLikeNum.setText("1");

        postLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postLikeImage.setImageResource(R.drawable.ic_like);
                postLikeNum.setText("1");
            }
        });
    }
}
