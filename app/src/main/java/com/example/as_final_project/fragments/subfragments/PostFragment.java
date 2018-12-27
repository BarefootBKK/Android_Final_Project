package com.example.as_final_project.fragments.subfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.as_final_project.R;
import com.example.as_final_project.adapters.PostAdapter;
import com.example.as_final_project.config.ServerConfig;
import com.example.as_final_project.entities.NetTask;
import com.example.as_final_project.entities.Post;
import com.example.as_final_project.fragments.BaseFragment;
import com.example.as_final_project.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends BaseFragment implements NetTask.NetListener{
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sub_fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = getActivity().findViewById(R.id.sub_fg_post_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        NetTask netTask = new NetTask(ServerConfig.getUrl("post"), NetTask.GET, this);
        netTask.execute();
    }

    @Override
    public void onNetSuccess(String jsonData, String message) {
        PostAdapter postAdapter = new PostAdapter(getPostList(jsonData), R.layout.item_homepage_post, getContext());
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public void onNetError(int errorCode, String errorMessage) {
        ToastUtil.showToast(getActivity(), errorMessage);
    }

    @Override
    public void onNetJSON(String originalJSON) {

    }

    private List<Post> getPostList(String jsonData) {
        List<Post> postList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Post post = new Post();
                post.setPostId(jsonObject.getInt("post_id"));
                post.setPostTitle(jsonObject.getString("post_title"));
                post.setPostContent(jsonObject.getString("post_content"));
                post.setBloggerEmail(jsonObject.getString("user_email"));
                post.setPostCommentNum("0");
                post.setPostLikeNum("0");
                post.setPostImageUrl(ServerConfig.getUrl(jsonObject.getString("post_url")));
                post.setBloggerImageUrl(ServerConfig.getUrl(jsonObject.getString("user_head_img_url")));
                post.setBloggerNickname(jsonObject.getString("user_nickname"));
                post.setPostDate(jsonObject.getString("post_time"));
                postList.add(post);
            }

        } catch (JSONException e) {
            ToastUtil.showToast(getActivity(), "解析JSON出错！");
        }
        return postList;
    }
}
