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

public class ItemLineAdapter extends RecyclerView.Adapter<ItemLineAdapter.ViewHolder>
        implements View.OnClickListener{

    private List<Movie> movieList;
    private int rowLayout;
    private Context mContext;

    public ItemLineAdapter(List<Movie> movieList, int rowLayout, Context mContext) {
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie movie = movieList.get(i);
        viewHolder.name.setText(movie.getMovieName());
        viewHolder.score.setText(movie.getScore());
        viewHolder.extraInfo.setText(movie.getReleaseInfo());
        Picasso.get().load(movie.getMoviePosterUrl()).into(viewHolder.imgPoster);
        viewHolder.imgPoster.setOnClickListener(this);
        viewHolder.imgCollect.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_ry_sl_img_poster:
                ToastUtil.showToast(mContext, "poster");
                break;
            case R.id.item_ry_sl_img_collect:
                ToastUtil.showToast(mContext, "collect");
                break;
            default:
                break;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView imgPoster;
        public ImageView imgCollect;
        public TextView name;
        public TextView score;
        public TextView extraInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_ry_sl_cardView);
            imgPoster = itemView.findViewById(R.id.item_ry_sl_img_poster);
            imgCollect = itemView.findViewById(R.id.item_ry_sl_img_collect);
            name = itemView.findViewById(R.id.item_ry_sl_text_name);
            score = itemView.findViewById(R.id.item_ry_sl_text_score);
            extraInfo = itemView.findViewById(R.id.item_ry_sl_text_info);
        }
    }
}
