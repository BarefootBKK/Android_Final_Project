package com.example.as_final_project.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable{
    private int movieId;
    private String movieName;
    private String releaseDate;
    private String releaseArea;
    private String length;
    private String score;
    private String ratingPeopleNum;
    private String channel;
    private String movieIntro;
    private String moviePosterUrl;
    private List<Actor> actorList;
    private List<MovieStills> movieStillsList;
    private String releaseInfo;

    public Movie() {
    }

    public Movie(int id,String name,String release, String posterurl) {
        movieId = id;
        movieName = name;
        releaseInfo = release;
        moviePosterUrl = posterurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeString(movieName);
        dest.writeString(releaseDate);
        dest.writeString(releaseArea);
        dest.writeString(length);
        dest.writeString(score);
        dest.writeString(ratingPeopleNum);
        dest.writeString(channel);
        dest.writeString(movieIntro);
        dest.writeString(moviePosterUrl);
        dest.writeList(actorList);
        dest.writeList(movieStillsList);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            Movie movie = new Movie();
            movie.movieId = source.readInt();
            movie.movieName = source.readString();
            movie.releaseDate = source.readString();
            movie.releaseArea = source.readString();
            movie.length = source.readString();
            movie.score = source.readString();
            movie.ratingPeopleNum = source.readString();
            movie.channel = source.readString();
            movie.movieIntro = source.readString();
            movie.moviePosterUrl = source.readString();
            movie.actorList = new ArrayList<>();
            source.readList(movie.actorList, getClass().getClassLoader());
            movie.movieStillsList = new ArrayList<>();
            source.readList(movie.movieStillsList, getClass().getClassLoader());
            return movie;
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseArea() {
        return releaseArea;
    }

    public void setReleaseArea(String releaseArea) {
        this.releaseArea = releaseArea;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRatingPeopleNum() {
        return ratingPeopleNum;
    }

    public void setRatingPeopleNum(String ratingPeopleNum) {
        this.ratingPeopleNum = ratingPeopleNum;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMovieIntro() {
        return movieIntro;
    }

    public void setMovieIntro(String movieIntro) {
        this.movieIntro = movieIntro;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public void setMoviePosterUrl(String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }

    public List<MovieStills> getMovieStillsList() {
        return movieStillsList;
    }

    public void setMovieStillsList(List<MovieStills> movieStillsList) {
        this.movieStillsList = movieStillsList;
    }

    public int getActorCount() {
        return actorList == null ? 0 :actorList.size();
    }

    public int getMovieStillsCount() {
        return movieStillsList == null ? 0 : movieStillsList.size();
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
    }
}
