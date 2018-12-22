package com.example.as_final_project.entities;

/**
 * 预告片
 */
public class Trailer {
    private String trailerName;          // 预告片名字
    private String trailerPosterUrl;    // 预告片封面海报url
    private String trailerVideoUrl;     // 预告片视频utl

    public Trailer() {

    }

    public Trailer(String trailerName, String trailerPosterUrl, String trailerVideoUrl) {
        this.trailerName = trailerName;
        this.trailerPosterUrl = trailerPosterUrl;
        this.trailerVideoUrl = trailerVideoUrl;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public void setTrailerName(String trailerName) {
        this.trailerName = trailerName;
    }

    public String getTrailerPosterUrl() {
        return trailerPosterUrl;
    }

    public void setTrailerPosterUrl(String trailerPosterUrl) {
        this.trailerPosterUrl = trailerPosterUrl;
    }

    public String getTrailerVideoUrl() {
        return trailerVideoUrl;
    }

    public void setTrailerVideoUrl(String trailerVideoUrl) {
        this.trailerVideoUrl = trailerVideoUrl;
    }
}
