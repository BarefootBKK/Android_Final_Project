package com.example.as_final_project.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as_final_project.R;
import com.example.as_final_project.entities.Movie;
import com.squareup.picasso.Picasso;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class recyclerViewadapter extends RecyclerView.Adapter { 
    private List<Movie> lists;
    private Context context; 
    public recyclerViewadapter(List<Movie> lists, Context context) { 
        this.lists = lists; this.context = context;
    }
    class myholder extends RecyclerView.ViewHolder{
        private TextView tv1,tv2;
        private ImageView poster;
        public myholder(View itemView) { 
            super(itemView); 
            tv1= (TextView) itemView.findViewById(R.id.tv1); 
            tv2= (TextView) itemView.findViewById(R.id.tv2);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    } 
    @Override 
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //创建新的View,被LayoutManager所调用
        myholder holder =new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false)); 
        return holder; 
    } 
    @Override 
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) { //将数据与界面进行绑定
        //Log.d("TAG", "onBindViewHolder: "+lists.get(position).getAutor());
        ((myholder)holder).tv1.setText(" " + lists.get(position).getMovieName());
        ((myholder)holder).tv2.setText("   " + lists.get(position).getReleaseInfo());
        //((myholder)holder).poster.setImageBitmap(getURLimage(lists.get(position).getMoviePosterUrl()));
        Picasso.get()
                .load(lists.get(position).getMoviePosterUrl())
                .into(((myholder)holder).poster);
    } 
    
    @Override 
    public int getItemCount() { //返回数据的数量
        return lists.size(); 
    }

}
