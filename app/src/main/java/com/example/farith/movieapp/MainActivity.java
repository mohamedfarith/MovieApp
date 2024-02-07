package com.example.farith.movieapp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farith.movieapp.Adapters.MovieListAdapter;
import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.ModelClasses.Movies;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineExperiments;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        ImagePipelineConfig pipeline = ImagePipelineConfig.newBuilder(this)
                .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                .build();
        Fresco.initialize(MainActivity.this,pipeline);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint_layout);
        movieListView = findViewById(R.id.movie_list_view);
        progressBar = findViewById(R.id.progress_bar);
        loadMorePrgressbar = findViewById(R.id.load_more_progressbar);
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<Movies> call = movieApi.getMovieDetails(Constants.API_KEY, number, "revenue.desc");
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                if (response.body() != null) {
                    final String page = response.body().getPage();
                    if (!page.equals("")) {
                        Log.d(TAG, "into movies obj: " + page);
                        addMovieDetails(response.body());
                        progressBar.setVisibility(View.INVISIBLE);
                        loadMorePrgressbar.setVisibility(View.GONE);
                    }
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
    public void addMovieDetails(Movies movies) {
        int size = movies.getResults().size();
        ArrayList<MovieDetails> movieDetails = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Log.d(TAG, "fetchMovieDetails: ");
            movieDetails.add(movies.getResults().get(i));
            Log.d(TAG, "fetchMovieDetails: " + movieDetailsArrayList.size());
            Log.d(TAG, "fetchMovieDetails: " + movies.getResults().get(i).getBackDropPath());
        }
        movieDetailsArrayList.addAll(movieDetails);
        movieListAdapter.notifyItemRangeChanged(movieDetailsArrayList.size(), 20);
        pageNumber++;
    }

}
