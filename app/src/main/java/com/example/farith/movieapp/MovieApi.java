package com.example.farith.movieapp;

import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.ModelClasses.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("discover/movie")
    Call<Movies> getMovieDetails(@Query("api_key") String apiKey, @Query("page") int page, @Query("sort_by") String sortBy);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getDetails(@Path("movie_id") String movieId, @Query("api_key")String apiKey);


}
