package com.example.farith.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farith.movieapp.Adapters.MovieDescriptionAdapter;
import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDescription extends AppCompatActivity {
    Toolbar movieTitle;
    SimpleDraweeView backDropImage;
    private static final String TAG = MovieDescription.class.getSimpleName();
    String backdropPath;
    FloatingActionButton favIconButton;
    Drawable drawable;
    Boolean isRed = false;
    SQLiteDatabase database;
    FavouriteIconDb favouriteIconDb;
    MovieDetails movieDetails;
    RecyclerView descriptionRecyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_movie_description);
        initializingViews();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        Intent intent = getIntent();
        movieDetails = (MovieDetails) intent.getSerializableExtra("movieDetails");
        makeNetworkCall(movieDetails);


    }

    private void makeNetworkCall(final MovieDetails movieDetails) {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        movieApi.getDetails(movieDetails.getId(), Constants.API_KEY).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                progressBar.setVisibility(View.GONE);
                bindData(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void bindData(MovieDetails movieDetails) {
        Log.d(TAG, "onCreate: the bundle value is " + movieDetails.getBackDropPath());
        backdropPath = "https://image.tmdb.org/t/p/original" + movieDetails.getBackDropPath();
        loadImageUri();
        favouriteIconDb = new FavouriteIconDb(this);
        getFavIconStatus();
        movieTitle.setTitle(movieDetails.getTitle());
        setSupportActionBar(movieTitle);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        favIconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchColor();
            }
        });
        MovieDescriptionAdapter adapter = new MovieDescriptionAdapter(movieDetails);
        descriptionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        descriptionRecyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializingViews() {
        movieTitle = findViewById(R.id.movie_title);
        backDropImage = findViewById(R.id.back_drop_image);
        favIconButton = findViewById(R.id.favourite_action_button);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedtoolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedtoolbar);
        descriptionRecyclerView = findViewById(R.id.description_recycler_view);
        appBarLayout = findViewById(R.id.app_bar);
        progressBar = findViewById(R.id.progressBar);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i > 400) {
                    favIconButton.setVisibility(View.INVISIBLE);
                } else {
                    favIconButton.setVisibility(View.VISIBLE);
                    //     switchColor();
                }
            }
        });
    }

    //parsing and setting the backdropImage
    public void loadImageUri() {
        Uri backdropImageUri = Uri.parse(backdropPath);
        backDropImage.setImageURI(backdropImageUri);
        backDropImage.setColorFilter(R.color.collapsedColour, PorterDuff.Mode.DST_ATOP);
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
