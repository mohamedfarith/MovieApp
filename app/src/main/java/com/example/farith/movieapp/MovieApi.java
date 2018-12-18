package com.example.farith.movieapp;

import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.ModelClasses.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
 String BASE_URL = " https://api.themoviedb.org/3/discover/";

String pathUrl = "movie?api_key=0e12101a22c608993caa890e9dabea92";
 @GET(pathUrl)
 Call<Movies> getMovieDetails(@Query("page") int page);

}
