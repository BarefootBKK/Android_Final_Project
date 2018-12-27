package com.example.as_final_project.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String userEmail;
    private String userNickname;
    private String userSignature;
    private String userHeadImageUrl;

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userEmail);
        dest.writeString(userNickname);
        dest.writeString(userSignature);
        dest.writeString(userHeadImageUrl);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            User user = new User();
            user.userEmail = source.readString();
            user.userNickname = source.readString();
            user.userSignature = source.readString();
            user.userHeadImageUrl = source.readString();
            return null;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getUserHeadImageUrl() {
        return userHeadImageUrl;
    }

    public void setUserHeadImageUrl(String userHeadImageUrl) {
        this.userHeadImageUrl = userHeadImageUrl;
    }
}
