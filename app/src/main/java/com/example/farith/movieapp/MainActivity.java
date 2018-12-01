package com.example.farith.movieapp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.farith.movieapp.Adapters.MovieListAdapter;
import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.ModelClasses.Movies;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    ConstraintLayout constraintLayout;
    ArrayList<String> title = new ArrayList<>();
    Movies movies;
    MovieDetails movieDetails;
    ProgressBar progressBar;
    RecyclerView movieListView;
    Context context;
    ArrayList<String> imageURL = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint_layout);
        movieListView = findViewById(R.id.movie_list_view);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN );
        progressBar.setVisibility(View.VISIBLE);
        if (isNetworkAvailable()) {
            getDataFromApi();
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Snackbar.make(constraintLayout, "No internet Connection", Snackbar.LENGTH_LONG).show();
        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getDataFromApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MovieApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<Movies> call = movieApi.getMovieDetails();
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                movies = response.body();
                String page = movies.getPage();
                Log.d(TAG, "into movies obj: " + page);
                fetchMovieDetails();
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Snackbar.make(constraintLayout, "Something went Wrong", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    //getting the values of the response from api
    public void fetchMovieDetails() {
        for (int i = 0; i < movies.getResults().size(); i++) {
            Log.d(TAG, "fetchMovieDetails: ");
            movieDetails = movies.getResults().get(i);
            title.add(movieDetails.getTitle());
            imageURL.add("https://image.tmdb.org/t/p/w500"+movieDetails.getPosterPath());
            Log.d(TAG, "fetchMovieDetails: " + title.get(i));
            Log.d(TAG, "fetchMovieDetails: "+imageURL.get(i));
        }
        progressBar.setVisibility(View.INVISIBLE);
        movieListView.setLayoutManager(new LinearLayoutManager(this));
        movieListView.setAdapter(new MovieListAdapter(title,imageURL));
    }
}
