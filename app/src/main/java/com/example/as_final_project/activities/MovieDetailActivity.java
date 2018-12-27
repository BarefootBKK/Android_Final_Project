package com.example.as_final_project.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.adapters.ActorCardAdapter;
import com.example.as_final_project.adapters.StillsCardAdapter;
import com.example.as_final_project.config.BasicConfig;
import com.example.as_final_project.entities.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends BaseActivity {

    private TextView toolBarTitleTextView;
    private CardView posterCardView;
    private ImageView posterImageView;
    private TextView movieNameTextView;
    private TextView channelTextView;
    private TextView releaseDateTextView;
    private TextView lengthTextView;
    private TextView scoreTextView;
    private TextView introTextView;
    private RecyclerView actorRecyclerView;
    private RecyclerView stillsRecyclerView;
    private RecyclerView commentRecyclerView;
    private Movie movie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        setActivityToolbar(R.id.a_film_detail_toolbar, true, false);
        loadControl();
        loadData();
        initRecyclerView();
    }

    private void loadControl() {
        toolBarTitleTextView = findViewById(R.id.a_film_detail_toolbar_title);
        posterCardView = findViewById(R.id.a_film_detail_cv_poster);
        posterImageView = findViewById(R.id.a_film_detail_img_poster);
        movieNameTextView = findViewById(R.id.a_film_detail_text_movie_name);
        channelTextView = findViewById(R.id.a_film_detail_text_channel);
        releaseDateTextView = findViewById(R.id.a_film_detail_text_release_date);
        lengthTextView = findViewById(R.id.a_film_detail_text_length);
        scoreTextView = findViewById(R.id.a_film_detail_text_score);
        introTextView = findViewById(R.id.a_film_detail_text_intro_content);
        actorRecyclerView = findViewById(R.id.a_film_detail_ry_actor_list);
        stillsRecyclerView = findViewById(R.id.a_film_detail_ry_stills);
        commentRecyclerView = findViewById(R.id.a_film_detail_ry_comment);

        movie = getIntent().getParcelableExtra(BasicConfig.INTENT_DATA_NAME);
    }

    private void loadData() {
        toolBarTitleTextView.setText(movie.getMovieName());
        Picasso.get().load(movie.getMoviePosterUrl()).into(posterImageView);
        movieNameTextView.setText(movie.getMovieName());
        channelTextView.setText(movie.getChannel());
        releaseDateTextView.setText(movie.getReleaseDate() + "  (" + movie.getReleaseArea() + ")");
        lengthTextView.setText(movie.getLength() + " 分钟");
        scoreTextView.setText(movie.getScore());
        introTextView.setText(movie.getMovieIntro());
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ActorCardAdapter actorCardAdapter = new ActorCardAdapter(movie.getActorList(),
                R.layout.item_film_actor_card, this);
        actorRecyclerView.setLayoutManager(linearLayoutManager);
        actorRecyclerView.setAdapter(actorCardAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        StillsCardAdapter stillsCardAdapter = new StillsCardAdapter(movie.getMovieStillsList(),
                R.layout.item_film_stills_card, this);
        stillsRecyclerView.setLayoutManager(linearLayoutManager1);
        stillsRecyclerView.setAdapter(stillsCardAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
    }
}
