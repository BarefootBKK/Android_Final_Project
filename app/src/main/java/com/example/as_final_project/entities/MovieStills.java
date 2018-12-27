package com.example.as_final_project.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 电影剧照（视频剧照-预告片和图片剧照）
 */
public class MovieStills implements Parcelable{
    private String imageUrl;
    private int trailerId;
    private String trailerUrl;

    public MovieStills() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(trailerId);
        dest.writeString(imageUrl);
        dest.writeString(trailerUrl);
    }

    public static final Parcelable.Creator<MovieStills> CREATOR = new Parcelable.Creator<MovieStills>() {
        @Override
        public MovieStills createFromParcel(Parcel source) {
            MovieStills movieStills = new MovieStills();
            movieStills.trailerId = source.readInt();
            movieStills.imageUrl = source.readString();
            movieStills.trailerUrl = source.readString();
            return movieStills;
        }

        @Override
        public MovieStills[] newArray(int size) {
            return new MovieStills[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(int trailerId) {
        this.trailerId = trailerId;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}
