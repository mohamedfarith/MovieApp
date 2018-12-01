package com.example.farith.movieapp.ModelClasses;

import java.util.List;

public class MovieDetails {
    String voteCount;
    String id;
    String video;
    String voteverage;
    String title;
    String popularity;
    String poster_path;
    String originalLanguage;
    String originalTitle;
    List<String> genreId;
    String backDropPath;
    String adult;
    String overView;
    String releaseDate;

    public MovieDetails(String voteCount, String id, String video, String voteverage, String title, String popularity, String poster_path, String originalLanguage, String originalTitle, List<String> genreId, String backDropPath, String adult, String overView,String releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteverage = voteverage;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreId = genreId;
        this.backDropPath = backDropPath;
        this.adult = adult;
        this.overView = overView;
        this.releaseDate=releaseDate;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getId() {
        return id;
    }

    public String getVideo() {
        return video;
    }

    public String getVoteverage() {
        return voteverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<String> getGenreId() {
        return genreId;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public String getAdult() {
        return adult;
    }

    public String getOverView() {
        return overView;
    }
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setVoteverage(String voteverage) {
        this.voteverage = voteverage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setGenreId(List<String> genreId) {
        this.genreId = genreId;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}

