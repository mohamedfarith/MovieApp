package com.example.farith.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.ArrayList;

public class MovieDescription extends AppCompatActivity {
    TextView movieTitle;
    TextView movieDescription;
    TextView txtRating;
    SimpleDraweeView backDropImage;
    private static final String TAG  = MovieDescription.class.getSimpleName();
    ArrayList<MovieDetails> movieDetailsArrayList;
    String backdropPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_movie_description);
        movieTitle = findViewById(R.id.movie_title);
        movieDescription = findViewById(R.id.txt_description);
        txtRating = findViewById(R.id.rating);
        backDropImage = findViewById(R.id.back_drop_image);
        Intent intent = getIntent();
        MovieDetails movieDetails  = (MovieDetails) intent.getSerializableExtra("movieDetails");
        Log.d(TAG, "onCreate: the bundle value is "+movieDetails.getBackDropPath());
         backdropPath = "https://image.tmdb.org/t/p/w500"+movieDetails.getBackDropPath();
        loadImageUri();

        movieTitle.setText(movieDetails.getTitle());
        movieDescription.setText(movieDetails.getOverview());
        txtRating.setText(movieDetails.getVoteverage());
        }
        public void loadImageUri(){
            Uri backdropImageUri = Uri.parse(backdropPath);
            backDropImage.setImageURI(backdropImageUri);
        }
}
