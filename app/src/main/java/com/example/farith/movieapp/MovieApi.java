package com.example.farith.movieapp;

import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.ModelClasses.Movies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
 String BASE_URL = " https://api.themoviedb.org/";

 @GET("3/discover/movie?api_key=0e12101a22c608993caa890e9dabea92")
    Call<Movies> getMovieDetails();
}
