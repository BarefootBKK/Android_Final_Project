package com.example.as_final_project.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Actor implements Parcelable{
    private String actorName;
    private String roleName;
    private String imageUrl;

    public Actor() {
    }

    public Actor(String actorName, String roleName, String imageUrl) {
        this.actorName = actorName;
        this.roleName = roleName;
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(actorName);
        dest.writeString(roleName);
    }

    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel source) {
            Actor actor = new Actor();
            actor.imageUrl = source.readString();
            actor.actorName = source.readString();
            actor.roleName = source.readString();
            return actor;
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
