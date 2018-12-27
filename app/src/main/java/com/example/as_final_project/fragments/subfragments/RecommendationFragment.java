package com.example.as_final_project.fragments.subfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.as_final_project.R;
import com.example.as_final_project.adapters.PostAdapter;
import com.example.as_final_project.entities.NetTask;
import com.example.as_final_project.entities.Post;
import com.example.as_final_project.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RecommendationFragment extends BaseFragment implements NetTask.NetListener{
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
        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Post post = new Post();
            post.setPostTitle("这是一个新帖子");
            post.setPostContent("这是一个新帖子的内容，你成功啦！");
            post.setPostLikeNum("20");
            post.setPostCommentNum("89");
            post.setPostImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545741611325&di=b3edee3939686ad7fdb54bc01418b59d&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fent%2Fcrawl%2F92%2Fw550h342%2F20181130%2FU50m-hphsupx6160332.jpg");
            postList.add(post);
        }
        PostAdapter postAdapter = new PostAdapter(postList, R.layout.item_homepage_post, getContext());
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public void onNetSuccess(String jsonData, String message) {

    }

    @Override
    public void onNetError(int errorCode, String errorMessage) {

    }

    @Override
    public void onNetJSON(String originalJSON) {

    }
}
