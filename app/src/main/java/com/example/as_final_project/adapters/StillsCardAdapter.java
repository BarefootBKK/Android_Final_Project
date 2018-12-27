package com.example.as_final_project.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.activities.VideoTrailerActivity;
import com.example.as_final_project.entities.MovieStills;
import com.example.as_final_project.utils.ActivityUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StillsCardAdapter extends RecyclerView.Adapter<StillsCardAdapter.ViewHolder> {

    List<MovieStills> movieStillsList;
    private int rowLayout;
    private Context mContext;

    public StillsCardAdapter(List<MovieStills> movieStillsList, int rowLayout, Context mContext) {
        this.movieStillsList = movieStillsList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new StillsCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MovieStills movieStills = movieStillsList.get(i);
        if (i == 0) {
            viewHolder.constraintLayout.setVisibility(View.VISIBLE);
            viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtil.startActivityWithParcelable(mContext, VideoTrailerActivity.class, movieStills);
                }
            });
        }
        Picasso.get().load(movieStills.getImageUrl()).into(viewHolder.stillsImage);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieStillsList == null ? 0 : movieStillsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView stillsImage;
        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_film_stills_cardView);
            stillsImage = itemView.findViewById(R.id.item_film_stills_image);
            constraintLayout = itemView.findViewById(R.id.item_film_stills_cover);
        }
    }
}
