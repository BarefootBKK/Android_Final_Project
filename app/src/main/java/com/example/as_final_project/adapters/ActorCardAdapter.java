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
import com.example.as_final_project.entities.Actor;
import com.example.as_final_project.utils.MyStringUtil;
import com.example.as_final_project.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorCardAdapter extends RecyclerView.Adapter<ActorCardAdapter.ViewHolder> {
    List<Actor> actorList;
    private int rowLayout;
    private Context mContext;

    public ActorCardAdapter(List<Actor> actorList, int rowLayout, Context mContext) {
        this.actorList = actorList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ActorCardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Actor actor = actorList.get(i);
        Picasso.get().load(actor.getImageUrl()).into(viewHolder.actorImage);
        viewHolder.actorName.setText(MyStringUtil.getSubString(actor.getActorName(), 6));
        viewHolder.roleName.setText(MyStringUtil.getSubString(actor.getRoleName(), 6));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, "演员: " + actor.getActorName() +
                        "\n角色: " + actor.getRoleName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return actorList == null ? 0 : actorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView actorImage;
        public TextView actorName;
        public TextView roleName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_film_actor_cardView);
            actorImage = itemView.findViewById(R.id.item_film_actor_image);
            actorName = itemView.findViewById(R.id.item_film_actor_name);
            roleName = itemView.findViewById(R.id.item_film_actor_role);
        }
    }
}
