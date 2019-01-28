package com.example.farith.movieapp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "MainActivity";
    ConstraintLayout constraintLayout;
    ArrayList<String> title = new ArrayList<>();
    Movies movies;
    MovieDetails movieDetails;
    ProgressBar progressBar, loadMorePrgressbar;
    RecyclerView movieListView;
    Context context;
    ArrayList<MovieDetails> movieDetailsArrayList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    int pastVisibleItems, visibleItemCount, totalItemConut = 0;
    int viewThreshold = 20;
    Boolean isScrolling = false;
    int pageNumber = 1;
    MovieListAdapter movieListAdapter;
    FloatingActionButton btnScrollToTop;
    FloatingActionButton btnScrollToBottom;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint_layout);
        movieListView = findViewById(R.id.movie_list_view);
        progressBar = findViewById(R.id.progress_bar);
        loadMorePrgressbar = findViewById(R.id.load_more_progressbar);
        btnScrollToTop = findViewById(R.id.btn_scroll_to_top);
        btnScrollToTop.setOnClickListener(this);
        btnScrollToBottom = findViewById(R.id.btn_scroll_to_bottom);
        btnScrollToBottom.setOnClickListener(this);
        loadMorePrgressbar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        progressBar.setVisibility(View.VISIBLE);
        displayElements();


    }

    private void displayElements() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            movieListView.setLayoutManager(linearLayoutManager);
            movieListAdapter = new MovieListAdapter(context, movieDetailsArrayList);
            movieListView.setAdapter(movieListAdapter);
            getDataFromApi(pageNumber);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Snackbar.make(constraintLayout, "No internet Connection", Snackbar.LENGTH_INDEFINITE).setAction("Try Again", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayElements();
                }
            }).show();
        }
    }

    //check network connection
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //making network call to the movie db api
    public void getDataFromApi(int number) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MovieApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<Movies> call = movieApi.getMovieDetails(number);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                movies = response.body();
                final String page = movies.getPage();
                if (!page.equals("")) {
                    Log.d(TAG, "into movies obj: " + page);
                    fetchMovieDetails();
                    progressBar.setVisibility(View.INVISIBLE);
                    loadMorePrgressbar.setVisibility(View.GONE);
                }

                movieListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        isScrolling = true;
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        visibleItemCount = linearLayoutManager.getChildCount();
                        Log.d(TAG, "onScrolled: child count is " + visibleItemCount);
                        totalItemConut = linearLayoutManager.getItemCount();
                        Log.d(TAG, "onScrolled: total item count is " + totalItemConut);
                        pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
                        Log.d(TAG, "onScrolled: first visible item position " + pastVisibleItems);
                        if (dy > 0) {
                            if (isScrolling) {
                                if (visibleItemCount + pastVisibleItems == totalItemConut) {
                                    loadMorePrgressbar.setVisibility(View.VISIBLE);
                                    getDataFromApi(pageNumber);
                                    isScrolling = false;
                                }
                            }
                        }

                    }

                });
            }


            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Snackbar.make(constraintLayout, "Something went Wrong", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    //getting the values of the response from api
    public void fetchMovieDetails() {
        int size = movies.getResults().size();
        for (int i = 0; i < size; i++) {
            Log.d(TAG, "fetchMovieDetails: ");
            movieDetails = movies.getResults().get(i);
            movieDetailsArrayList.add(movieDetails);
            Log.d(TAG, "fetchMovieDetails: " + movieDetailsArrayList.size());
            Log.d(TAG, "fetchMovieDetails: " + movieDetails.getBackDropPath());
        }
        movieListAdapter.notifyItemRangeChanged(movieDetailsArrayList.size(), 20);
        pageNumber++;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroll_to_top:
                movieListView.smoothScrollToPosition(0);
                break;
            case R.id.btn_scroll_to_bottom:
                movieListView.smoothScrollToPosition(movieDetailsArrayList.size()-1);
        }
    }
}
