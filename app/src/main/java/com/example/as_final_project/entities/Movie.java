package com.example.as_final_project.entities;

import java.util.List;

public class Movie {
    private int movieId;
    private String movieName;
    private String releaseInfo;
    private String score;
    private String ratingPeopleNum;
    private String channel;
    private String movieIntro;
    private String moviePosterUrl;
    private List<Actor> actorList;
    private List<Trailer> trailerList;

    public Movie() {
    }

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

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
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

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }

    public int getActorCount() {
        return actorList == null ? 0 :actorList.size();
    }

    public int getTrailerCount() {
        return trailerList == null ? 0 : trailerList.size();
    }
}
