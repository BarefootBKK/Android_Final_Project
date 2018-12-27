package com.example.as_final_project.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {

    private String userImageUrl;
    private String userNickname;
    private String commentDate;
    private String commentContent;
    private String commentLikeNum;

    public Comment() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[0];
        }
    };

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentLikeNum() {
        return commentLikeNum;
    }

    public void setCommentLikeNum(String commentLikeNum) {
        this.commentLikeNum = commentLikeNum;
    }

    public static Creator<Comment> getCREATOR() {
        return CREATOR;
    }
}
