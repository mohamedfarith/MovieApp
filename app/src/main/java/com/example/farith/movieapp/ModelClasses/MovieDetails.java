package com.example.farith.movieapp.ModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieDetails implements Serializable {
    @SerializedName("vote_count")
    @Expose
    String vote_count;
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("video")
    @Expose
    String video;
    @SerializedName("vote_average")
    @Expose
    String vote_average;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("popularity")
    @Expose
    String popularity;
    @SerializedName("poster_path")
    @Expose
    String poster_path;
    @SerializedName("original_language")
    @Expose
    String original_language;
    @SerializedName("original_title")
    @Expose
    String original_title;
    @SerializedName("genreId")
    @Expose
    List<String> genreId;
    @SerializedName("backdrop_path")
    @Expose
    String backdrop_path;
    @SerializedName("adult")
    @Expose
    String adult;
    @SerializedName("overview")
    @Expose
    String overview;
    @SerializedName("release_date")
    @Expose
    String release_date;
    @SerializedName("spoken_languages")
    @Expose
    List<SpokenLanguages> spokenLanguages;

    public List<SpokenLanguages> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguages> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
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

    public static class SpokenLanguages {
        @SerializedName("name")
        @Expose
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

