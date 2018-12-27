package com.example.as_final_project.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable{
    private int postId;
    private String postTitle;
    private String postContent;
    private String postImageUrl;
    private String postCommentNum;
    private String postLikeNum;
    private String postDate;
    private String bloggerEmail;
    private String bloggerNickname;
    private String bloggerImageUrl;

    public Post() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(postId);
        dest.writeString(postTitle);
        dest.writeString(postContent);
        dest.writeString(postImageUrl);
        dest.writeString(postCommentNum);
        dest.writeString(postLikeNum);
        dest.writeString(postDate);
        dest.writeString(bloggerEmail);
        dest.writeString(bloggerNickname);
        dest.writeString(bloggerImageUrl);
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            Post post = new Post();
            post.postId = source.readInt();
            post.postTitle = source.readString();
            post.postContent = source.readString();
            post.postImageUrl = source.readString();
            post.postCommentNum = source.readString();
            post.postLikeNum = source.readString();
            post.postDate = source.readString();
            post.bloggerEmail = source.readString();
            post.bloggerNickname = source.readString();
            post.bloggerImageUrl = source.readString();
            return post;
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getPostCommentNum() {
        return postCommentNum;
    }

    public void setPostCommentNum(String postCommentNum) {
        this.postCommentNum = postCommentNum;
    }

    public String getPostLikeNum() {
        return postLikeNum;
    }

    public void setPostLikeNum(String postLikeNum) {
        this.postLikeNum = postLikeNum;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getBloggerEmail() {
        return bloggerEmail;
    }

    public void setBloggerEmail(String bloggerEmail) {
        this.bloggerEmail = bloggerEmail;
    }

    public String getBloggerNickname() {
        return bloggerNickname;
    }

    public void setBloggerNickname(String bloggerNickname) {
        this.bloggerNickname = bloggerNickname;
    }

    public String getBloggerImageUrl() {
        return bloggerImageUrl;
    }

    public void setBloggerImageUrl(String bloggerImageUrl) {
        this.bloggerImageUrl = bloggerImageUrl;
    }
}
