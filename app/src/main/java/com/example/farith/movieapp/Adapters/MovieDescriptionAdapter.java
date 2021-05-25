package com.example.farith.movieapp.Adapters;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class MovieDescriptionAdapter extends RecyclerView.Adapter<DescriptionViewHolder> {
    TextView ratingCount;
    TextView spokenLanguage;
    TextView movieDescription;
    TextView txtRating;
    MovieDetails movieDetails;

    public MovieDescriptionAdapter(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
    }

    @NonNull
    @Override
    public DescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.description_recyclerview, viewGroup, false);
        initiateViews(view);
        return new DescriptionViewHolder(view);
    }

    private void initiateViews(View view) {
        movieDescription = view.findViewById(R.id.txt_description);
        txtRating = view.findViewById(R.id.rating);
        ratingCount = view.findViewById(R.id.rating_count);
        spokenLanguage = view.findViewById(R.id.spoken_language);

    }

    @Override
    public void onBindViewHolder(@NonNull DescriptionViewHolder descriptionViewHolder, int i) {
        movieDescription.setText(movieDetails.getOverview());
        txtRating.setText(movieDetails.getVoteverage());
        ratingCount.setText(movieDetails.getVoteCount());
        String value = "";
        for (MovieDetails.SpokenLanguages language : movieDetails.getSpokenLanguages()) {
            value = value + " " + language.getName();
        }
        spokenLanguage.setText(value);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

class DescriptionViewHolder extends RecyclerView.ViewHolder {
    public DescriptionViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
