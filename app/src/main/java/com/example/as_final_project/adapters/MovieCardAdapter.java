package com.example.as_final_project.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.entities.Movie;
import com.example.as_final_project.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieCardAdapter extends RecyclerView.Adapter<MovieCardAdapter.ViewHolder> {

    private List<Movie> movieList;
    private int rowLayout;
    private Context mContext;

    public MovieCardAdapter(List<Movie> movieList, int rowLayout, Context mContext) {
        this.movieList = movieList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Movie movie = movieList.get(position);
        viewHolder.movieName.setText(movie.getMovieName());
        Picasso.get().load(movie.getMoviePosterUrl()).into(viewHolder.moviePoster);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, movie.getMovieName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView moviePoster;
        public TextView movieName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_film_cardView);
            moviePoster = itemView.findViewById(R.id.item_film_image);
            movieName = itemView.findViewById(R.id.item_film_name);
        }
    }
}
