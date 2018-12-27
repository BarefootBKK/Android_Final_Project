package com.example.as_final_project.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.activities.PostDetailActivity;
import com.example.as_final_project.entities.NetTask;
import com.example.as_final_project.entities.Post;
import com.example.as_final_project.utils.ActivityUtil;
import com.example.as_final_project.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postList;
    private int rowLayout;
    private Context mContext;

    public PostAdapter(List<Post> postList, int rowLayout, Context mContext) {
        this.postList = postList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Post post = postList.get(i);
        Log.d("测试", "onBindViewHolder: " + post.getBloggerNickname());
        viewHolder.posTitle.setText(post.getPostTitle());
        viewHolder.postContent.setText(post.getPostContent());
        viewHolder.postLikeNum.setText(post.getPostLikeNum());
        viewHolder.postCommentNum.setText(post.getPostCommentNum());
        if (post.getPostImageUrl().length() > 0) {
            Picasso.get().load(post.getPostImageUrl()).into(viewHolder.postImage);
        } else {
//            viewHolder.postImageCardView.setVisibility(View.INVISIBLE);
        }
        viewHolder.postLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetTask netTask = new NetTask("http://baidu.com", NetTask.GET, new NetTask.NetListener() {
                    @Override
                    public void onNetSuccess(String jsonData, String message) {
                        viewHolder.postLikeImage.setImageResource(R.drawable.ic_like);
                        viewHolder.postLikeNum.setText("1");
                    }

                    @Override
                    public void onNetError(int errorCode, String errorMessage) {
                        viewHolder.postLikeImage.setImageResource(R.drawable.ic_like);
                        viewHolder.postLikeNum.setText("1");
                    }

                    @Override
                    public void onNetJSON(String originalJSON) {

                    }
                });
                netTask.execute();
            }
        });
        viewHolder.postCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivityWithParcelable(mContext, PostDetailActivity.class, post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView posTitle;
        public TextView postContent;
        public TextView postLikeNum;
        public TextView postCommentNum;
        public ImageView postImage;
        public ImageView postLikeImage;
        public ImageView postCommentImage;
        public CardView postCardView;
        public CardView postImageCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            posTitle = itemView.findViewById(R.id.item_hp_post_text_title);
            postContent = itemView.findViewById(R.id.item_hp_post_text_content);
            postLikeNum = itemView.findViewById(R.id.item_hp_post_text_like_num);
            postCommentNum = itemView.findViewById(R.id.item_hp_post_text_comment_num);
            postImage = itemView.findViewById(R.id.item_hp_post_img_imageView);
            postLikeImage = itemView.findViewById(R.id.item_hp_post_img_like);
            postCommentImage = itemView.findViewById(R.id.item_hp_post_img_comment);
            postCardView = itemView.findViewById(R.id.item_hp_post_cv_cardView);
            postImageCardView = itemView.findViewById(R.id.item_hp_post_cv_image_card);
        }
    }
}
