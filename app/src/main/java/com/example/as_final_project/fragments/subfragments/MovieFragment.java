package com.example.as_final_project.fragments.subfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.as_final_project.R;
import com.example.as_final_project.adapters.MovieCardAdapter;
import com.example.as_final_project.adapters.ImageSliderAdapter;
import com.example.as_final_project.adapters.MovieItemLineAdapter;
import com.example.as_final_project.config.ServerConfig;
import com.example.as_final_project.entities.Actor;
import com.example.as_final_project.entities.Movie;
import com.example.as_final_project.entities.MovieStills;
import com.example.as_final_project.entities.NetTask;
import com.example.as_final_project.fragments.BaseFragment;
import com.example.as_final_project.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends BaseFragment implements NetTask.NetListener{
    private RecyclerView ry_1;
    private RecyclerView ry_2;
    private RecyclerView ry_3;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sub_fragment_film, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewPager viewPager = getActivity().findViewById(R.id.sub_fg_film_viewpager);
        viewPager.setAdapter(new ImageSliderAdapter(getImageList()));
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        ry_1 = getActivity().findViewById(R.id.sub_fg_film_ry_hot);
        ry_2 = getActivity().findViewById(R.id.sub_fg_film_ry_current_hot);
        ry_3 = getActivity().findViewById(R.id.sub_fg_film_ry_videolist);
        swipeRefreshLayout = getActivity().findViewById(R.id.sub_fg_film_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ToastUtil.showToast(getActivity(), "数据已刷新");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        NetTask netTask = new NetTask(ServerConfig.getUrl("movie"), NetTask.GET, this);
        netTask.execute();
    }

    @Override
    public void onNetSuccess(String jsonData, String message) {
        MovieCardAdapter movieCardAdapter = new MovieCardAdapter(getMovieList(jsonData),
                R.layout.item_film_movie_vertical_card, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        ry_1.setLayoutManager(gridLayoutManager);
        ry_1.setAdapter(movieCardAdapter);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 3);
        ry_2.setLayoutManager(gridLayoutManager1);
        MovieCardAdapter movieCardAdapter1 = new MovieCardAdapter(getMovieList(jsonData),
                R.layout.item_film_movie_vertical_card, getContext());
        ry_2.setAdapter(movieCardAdapter1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ry_3.setLayoutManager(linearLayoutManager);
        MovieItemLineAdapter movieItemLineAdapter = new MovieItemLineAdapter(getMovieListPro(),
                R.layout.item_film_movie_horizontal_card, getContext());
        ry_3.setAdapter(movieItemLineAdapter);
        swipeRefreshLayout.setRefreshing(false);

        ry_1.setHasFixedSize(true);
        ry_2.setHasFixedSize(true);
        ry_3.setHasFixedSize(true);
        ry_1.setNestedScrollingEnabled(false);
        ry_2.setNestedScrollingEnabled(false);
        ry_3.setNestedScrollingEnabled(false);
    }

    @Override
    public void onNetError(int errorCode, String errorMessage) {
        ToastUtil.showToast(getContext(), errorMessage);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onNetJSON(String originalJSON) {

    }

    /**
     * 获取电影列表
     * @param jsonData
     * @return
     */
    private List<Movie> getMovieList(String jsonData) {
        List<Movie> movieList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                Movie movie = new Movie();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                movie.setMovieId(jsonObject.getInt("movie_id"));
                movie.setMovieName(jsonObject.getString("movie_name"));
                movie.setReleaseDate(jsonObject.getString("movie_release_date"));
                movie.setReleaseArea(jsonObject.getString("movie_release_area"));
                movie.setLength(jsonObject.getString("movie_length"));
                movie.setScore(jsonObject.getString("movie_score"));
                JSONArray jsonActorList = jsonObject.getJSONArray("movie_actor_list");
                movie.setActorList(getActorList(jsonActorList));
                movie.setMovieStillsList(getMovieStills(jsonObject));
                movie.setChannel(jsonObject.getString("movie_channel"));
                movie.setMovieIntro(jsonObject.getString("movie_intro"));
                movie.setRatingPeopleNum(jsonObject.getString("movie_rating_num"));
                movie.setMoviePosterUrl(ServerConfig.getUrl(jsonObject.getString("movie_poster_url")));
                movieList.add(movie);
            }
            List<String> urlList = new ArrayList<>();
            urlList.add("http://5b0988e595225.cdn.sohucs.com/images/20181203/7e9c82bbb916472a8e90ae07d8cf7ef5.jpeg");
            urlList.add("http://i05.cztv.com/cztv/vms/2018/04/28/999bee371d154c679ad5f5c84171e873/8_640_400.jpg");
            urlList.add("http://d.ifengimg.com/w600/p0.ifengimg.com/pmop/2018/1114/330D39160FD770B3BB26B18E3CD4D0FB780A9BBB_size270_w641_h950.jpeg");
            urlList.add("http://www.losking.com/upload/month_1807/201807070935471616.jpg");
            urlList.add("http://5b0988e595225.cdn.sohucs.com/q_70,c_zoom,w_640/images/20180415/758ebcc63ddd414d97f5c010016b0b3b.jpeg");

            List<String> nameList = new ArrayList<>();
            nameList.add("海王");
            nameList.add("复仇者联盟3:无限战争");
            nameList.add("蜘蛛侠:平行宇宙");
            nameList.add("我不是药神");
            nameList.add("头号玩家");

            for (int i = 0; i < urlList.size(); i++) {
                Movie movie = new Movie();
                movie.setMovieName(nameList.get(i));
                movie.setMoviePosterUrl(urlList.get(i));
                movieList.add(movie);
            }

        } catch (JSONException e) {

        }
        return movieList;
    }

    public List<Movie> getMovieListPro() {
        List<Movie> movieList = new ArrayList<>();

        List<String> urlList = new ArrayList<>();
        urlList.add("http://5b0988e595225.cdn.sohucs.com/images/20181203/7e9c82bbb916472a8e90ae07d8cf7ef5.jpeg");
        urlList.add("http://5b0988e595225.cdn.sohucs.com/q_70,c_zoom,w_640/images/20180415/758ebcc63ddd414d97f5c010016b0b3b.jpeg");
        urlList.add("http://i05.cztv.com/cztv/vms/2018/04/28/999bee371d154c679ad5f5c84171e873/8_640_400.jpg");
        urlList.add("http://www.losking.com/upload/month_1807/201807070935471616.jpg");
        urlList.add("http://d.ifengimg.com/w600/p0.ifengimg.com/pmop/2018/1114/330D39160FD770B3BB26B18E3CD4D0FB780A9BBB_size270_w641_h950.jpeg");

        List<String> nameList = new ArrayList<>();
        nameList.add("海王");
        nameList.add("头号玩家");
        nameList.add("复仇者联盟3:无限战争");
        nameList.add("我不是药神");
        nameList.add("蜘蛛侠:平行宇宙");

        List<String> dateList = new ArrayList<>();
        dateList.add("2018/12/7");
        dateList.add("2018/3/30");
        dateList.add("2018/4/27");
        dateList.add("2018/7/5");
        dateList.add("2018/12/21");

        List<String> areaList = new ArrayList<>();
        areaList.add("中国大陆");
        areaList.add("中国大陆");
        areaList.add("美国");
        areaList.add("中国大陆");
        areaList.add("中国大陆");

        for (int i = 0; i < urlList.size(); i++) {
            Movie movie = new Movie();
            movie.setMovieName(nameList.get(i));
            movie.setMoviePosterUrl(urlList.get(i));
            movie.setReleaseDate(dateList.get(i));
            movie.setReleaseArea(areaList.get(i));
            movieList.add(movie);
        }
        return movieList;
    }
    /**
     * 获取演职员
     * @param jsonArray
     * @return
     */
    private List<Actor> getActorList(JSONArray jsonArray) {
        List<Actor> actorList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Actor actor = new Actor();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                actor.setImageUrl(ServerConfig.getUrl(jsonObject.getString("actor_img_url")));
                actor.setActorName(jsonObject.getString("actor_name"));
                actor.setRoleName(jsonObject.getString("role_name"));
                actorList.add(actor);
            }
        } catch (JSONException e) { }
        return actorList;
    }

    /**
     * 获取视频剧照
     * @param jsonObject
     * @return List<MovieStills>
     */
    private List<MovieStills> getMovieStills(JSONObject jsonObject) {
        List<MovieStills> movieStillsList = new ArrayList<>();
        try {
            // 获取预告片
            JSONObject trailerJsonObject = jsonObject.getJSONObject("trailer");
            MovieStills trailer = new MovieStills();
            trailer.setImageUrl(ServerConfig.getUrl(trailerJsonObject.getString("trailer_cover_url")));
            trailer.setTrailerId(trailerJsonObject.getInt("trailer_id"));
            trailer.setTrailerUrl(ServerConfig.getUrl(trailerJsonObject.getString("trailer_video_url")));
            movieStillsList.add(trailer);
            // 获取剧照
            JSONArray jsonArray = jsonObject.getJSONArray("movie_stills_url");
            for (int i = 0; i < jsonArray.length(); i++) {
                MovieStills movieStills = new MovieStills();
                movieStills.setImageUrl(ServerConfig.getUrl(jsonArray.getString(i)));
                movieStillsList.add(movieStills);
            }
        } catch (JSONException e) {}
        return movieStillsList;
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
