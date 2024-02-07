package com.example.farith.movieapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.R;

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
