package com.example.as_final_project.entities;

public class Idea {
    private int id;
    private String context;
    private String title;
    private int zan_num;
    private int comment_num;
    private String idea_image_url;

    public Idea(int id, String title, String context, int zan_num, int comment_num, String idea_image_url ){
        this.id = id;
        this.title = title;
        this.context = context;
        this.zan_num = zan_num;
        this.comment_num = comment_num;
        this.idea_image_url = idea_image_url;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setContext(String context){
        this.context = context;
    }
    public String getContext(){
        return context;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setZan_num(int num){
        zan_num = num;
    }
    public int getZan_num(){
        return zan_num;
    }

    public void setComment_num(int num){
        comment_num = num;
    }
    public int getComment_num(){
        return comment_num;
    }

    public void setIdea_image_url(String url){
        idea_image_url = url;
    }
    public String getIdea_image_url(){
        return idea_image_url;
    }
}
