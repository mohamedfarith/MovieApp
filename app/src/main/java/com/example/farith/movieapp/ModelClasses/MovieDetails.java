package com.example.farith.movieapp.ModelClasses;

import java.io.Serializable;
import java.util.List;

public class MovieDetails implements Serializable {
    String vote_count;
    String id;
    String video;
    String vote_average;
    String title;
    String popularity;
    String poster_path;
    String original_language;
    String original_title;
    List<String> genreId;
    String backdrop_path;
    String adult;
    String overview;
    String release_date;

    public MovieDetails(String vote_count, String id, String video, String vote_average, String title, String popularity, String poster_path, String original_language, String original_title, List<String> genreId, String backDropPath, String adult, String overview,String release_date) {
        this.vote_count = vote_count;
        this.id = id;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genreId = genreId;
        this.backdrop_path = backDropPath;
        this.adult = adult;
        this.overview = overview;
        this.release_date=release_date;
    }

    public String getVoteCount() {
        return vote_count;
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
        return original_language;
    }

    public String getOriginalTitle() {
        return original_title;
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

    public void setVoteCount(String vote_count) {
        this.vote_count = vote_count;
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

    public void setOriginalLanguage(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
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

