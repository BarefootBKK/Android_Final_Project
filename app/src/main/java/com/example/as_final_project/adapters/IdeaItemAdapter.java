package com.example.as_final_project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.entities.Idea;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IdeaItemAdapter extends RecyclerView.Adapter {
    private List<Idea> lists;
    private Context context;

    public IdeaItemAdapter(List<Idea> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    class myholder extends RecyclerView.ViewHolder {
        private TextView idea_title, idea_context,idea_num;
        private ImageView idea_image;

        public myholder(View itemView) {
            super(itemView);
            idea_title = (TextView) itemView.findViewById(R.id.idea_title);
            idea_context = (TextView) itemView.findViewById(R.id.idea_context);
            idea_num = (TextView)itemView.findViewById(R.id.idea_num);
            idea_image = (ImageView) itemView.findViewById(R.id.idea_image);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //创建新的View,被LayoutManager所调用
        IdeaItemAdapter.myholder holder = new IdeaItemAdapter.myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_idea_card, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) { //将数据与界面进行绑定
        //Log.d("TAG", "onBindViewHolder: "+lists.get(position).getAutor());
        ((IdeaItemAdapter.myholder) holder).idea_title.setText(lists.get(position).getTitle());
        ((IdeaItemAdapter.myholder) holder).idea_context.setText(lists.get(position).getContext());
        ((IdeaItemAdapter.myholder) holder).idea_num.setText(lists.get(position).getZan_num() + "赞同  ·  " + lists.get(position).getComment_num() + "评论");
        //((myholder)holder).idea_image.setImageBitmap(getURLimage(lists.get(position).getIdeaidea_imageUrl()));
        Picasso.get()
                .load(lists.get(position).getIdea_image_url())
                .into(((IdeaItemAdapter.myholder) holder).idea_image);
    }

    @Override
    public int getItemCount() { //返回数据的数量
        return lists.size();
    }
}

