package com.example.farith.movieapp.ModelClasses;

import java.util.List;

public class Movies {
    String page;
    String totalResults;
    String totalPages;
    List<MovieDetails> results;

    public Movies(String page, String totalResults, String totalPages, List<MovieDetails> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieDetails> getResults() {
        return results;
    }

    public void setResults(List<MovieDetails> results) {
        this.results = results;
    }
}

