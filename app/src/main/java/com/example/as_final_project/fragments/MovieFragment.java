package com.example.as_final_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.as_final_project.R;
import com.example.as_final_project.adapters.ItemLineAdapter;
import com.example.as_final_project.adapters.MovieCardAdapter;
import com.example.as_final_project.adapters.ViewPagerAdapter;
import com.example.as_final_project.entities.Movie;
import com.example.as_final_project.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends BaseFragment {
    private RecyclerView ry_1;
    private RecyclerView ry_2;
    private RecyclerView ry_3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sub_fragment_film, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ViewPager viewPager = getActivity().findViewById(R.id.sub_fg_film_viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getImageList()));
        initRecyclerView();
        super.onActivityCreated(savedInstanceState);
    }

    public void initRecyclerView() {
        ry_1 = getActivity().findViewById(R.id.sub_fg_film_ry_hot);
        ry_2 = getActivity().findViewById(R.id.sub_fg_film_ry_current_hot);
        ry_3 = getActivity().findViewById(R.id.sub_fg_film_ry_videolist);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        GridLayoutManager gridLayoutManager_2 = new GridLayoutManager(getContext(), 3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        MovieCardAdapter movieCardAdapter = new MovieCardAdapter(getMovieList(), R.layout.item_film_movie_card, getContext());
        MovieCardAdapter movieCardAdapter_2 = new MovieCardAdapter(getMovieList(), R.layout.item_film_movie_card, getContext());
        ItemLineAdapter itemLineAdapter = new ItemLineAdapter(getMovieList(), R.layout.item_film_movie_horizontal_card, getContext());

        loadRecyclerViewItem(ry_1, gridLayoutManager, movieCardAdapter);
        loadRecyclerViewItem(ry_2, gridLayoutManager_2, movieCardAdapter_2);
        loadRecyclerViewItem(ry_3, linearLayoutManager, itemLineAdapter);
    }

    private void loadRecyclerViewItem(RecyclerView recyclerView, LayoutManager layoutManager, Adapter adapter) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    private List<Movie> getMovieList() {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Movie movie = new Movie();
            movie.setMovieName("毒液");
            movie.setScore("89");
            movie.setReleaseInfo("2018/11 中国大陆 112分钟");
            movie.setMoviePosterUrl("http://vgfame.top:8000/static/images/coach/venon.jpg");
            movieList.add(movie);
        }
        return movieList;
    }

    /**
     * 图片轮播
     * @return
     */
    private List<View> getImageList() {
        LayoutInflater inflater = getLayoutInflater();
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.get().load("http://vgfame.top:8000/static/images/coach/venon.jpg").into(imageView);
            CardView cardView = new CardView(getContext());
            cardView.setRadius(10);
            cardView.setCardElevation(1);
            cardView.addView(imageView);
            final int temp = i + 1;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(getContext(), "Click：" + temp);
                }
            });
            viewList.add(cardView);
        }
        return viewList;
    }
}
