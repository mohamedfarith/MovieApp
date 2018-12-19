package com.example.farith.movieapp.ModelClasses;

import java.io.Serializable;
import java.util.List;

public class MovieDetails implements Serializable {
    String voteCount;
    String id;
    String video;
    String vote_average;
    String title;
    String popularity;
    String poster_path;
    String originalLanguage;
    String originalTitle;
    List<String> genreId;
    String backdrop_path;
    String adult;
    String overview;
    String release_date;

    public MovieDetails(String voteCount, String id, String video, String vote_average, String title, String popularity, String poster_path, String originalLanguage, String originalTitle, List<String> genreId, String backDropPath, String adult, String overview,String release_date) {
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreId = genreId;
        this.backdrop_path = backDropPath;
        this.adult = adult;
        this.overview = overview;
        this.release_date=release_date;
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
        return vote_average;
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
        return backdrop_path;
    }

    public String getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }
    public String getReleaseDate() {
        return release_date;
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

    public void setVoteverage(String vote_average) {
        this.vote_average = vote_average;
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

    public void setBackDropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }
}

