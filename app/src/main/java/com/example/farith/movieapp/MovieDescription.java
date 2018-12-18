package com.example.farith.movieapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class MovieDescription extends AppCompatActivity {
    TextView movieTitle;
    TextView movieDescription;
    TextView txtRating;
    SimpleDraweeView backDropImage;
    private static final String TAG = MovieDescription.class.getSimpleName();
    String backdropPath;
    FloatingActionButton favIconButton;
    Drawable drawable;
    Boolean isRed = false;
    SQLiteDatabase database;
    FavouriteIconDb favouriteIconDb;
    MovieDetails movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_movie_description);
        initializingViews();
        Intent intent = getIntent();
        movieDetails = (MovieDetails) intent.getSerializableExtra("movieDetails");
        Log.d(TAG, "onCreate: the bundle value is " + movieDetails.getBackDropPath());
        backdropPath = "https://image.tmdb.org/t/p/w500" + movieDetails.getBackDropPath();
        loadImageUri();
        favouriteIconDb = new FavouriteIconDb(this);
        getFavIconStatus();
        movieTitle.setText(movieDetails.getTitle());
        movieDescription.setText(movieDetails.getOverview());
        txtRating.setText(movieDetails.getVoteverage());
        favIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchColor();
            }
        });
    }

    public void initializingViews() {
        movieTitle = findViewById(R.id.movie_title);
        movieDescription = findViewById(R.id.txt_description);
        txtRating = findViewById(R.id.rating);
        backDropImage = findViewById(R.id.back_drop_image);
        favIconButton = findViewById(R.id.favourite_action_button);
    }

    //parsing and setting the backdropImage
    public void loadImageUri() {
        Uri backdropImageUri = Uri.parse(backdropPath);
        backDropImage.setImageURI(backdropImageUri);
    }

    //Getting the contents from the db and if is empty inserting the content with moviename and status
    public void getFavIconStatus() {
        Log.d(TAG, "getFavIconStatus: into the method");
        String favIconStatus = favouriteIconDb.readValues(database, movieDetails.getTitle());
        if (!TextUtils.isEmpty(favIconStatus)) {
            if (favIconStatus.equals("true")) {
                isRed = true;
                setRedColor();
            } else {
                setBlackColor();
            }
        } else {
            favouriteIconDb.insertData(database, movieDetails.getTitle(), "false");
            setBlackColor();
        }
    }

    //This method is used inside the on Click of the floating button to enable switching of colours
    public void switchColor() {
        if (isRed) {
            favIconButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            isRed = false;
        } else {
            favIconButton.setImageResource(R.drawable.ic_favorite_red_24dp);
            isRed = true;
        }
    }

    //setting black color
    public void setBlackColor() {
        favIconButton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }

    //setting redColor
    public void setRedColor() {
        favIconButton.setImageResource(R.drawable.ic_favorite_red_24dp);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //updating the db when the activity is paused
        favouriteIconDb.update(database, String.valueOf(isRed), movieDetails.getTitle());
    }
}
