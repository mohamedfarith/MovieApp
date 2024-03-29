package com.example.farith.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farith.movieapp.ModelClasses.MovieDetails;
import com.example.farith.movieapp.MovieDescription;
import com.example.farith.movieapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    ArrayList<MovieDetails> movieDetailsArrayList;
    Context context;
    HashMap<String, MovieDetails> hashMap = new HashMap<>();
    private static final String TAG = MovieListAdapter.class.getSimpleName();

    public MovieListAdapter(Context context, ArrayList<MovieDetails> movieDetails) {
        this.movieDetailsArrayList = movieDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_recycler_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieListAdapter.ViewHolder viewHolder, int i) {
        String releaseDate = movieDetailsArrayList.get(viewHolder.getAdapterPosition()).getReleaseDate();
        releaseDate = getReleaseYear(releaseDate);
        Log.d(TAG, "onBindViewHolder: releasedate is "+releaseDate);
        viewHolder.movieName.setText(movieDetailsArrayList.get(viewHolder.getAdapterPosition()).getOriginalTitle()+" ("+releaseDate+")");
        String uriParse ="https://image.tmdb.org/t/p/original"+ movieDetailsArrayList.get(viewHolder.getAdapterPosition()).getBackDropPath();
        Uri imageUri = Uri.parse(uriParse);
        viewHolder.movieImage.setImageURI(imageUri);
        viewHolder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovieDescription.class);
                Log.d(TAG, "onClick: "+movieDetailsArrayList.get(viewHolder.getAdapterPosition()).toString());
                intent.putExtra("movieDetails",movieDetailsArrayList.get(viewHolder.getAdapterPosition()));
                v.getContext().startActivity(intent);
            }
        });

        //Load more data when the adapter reaches the bottom of the list
        if(viewHolder.getAdapterPosition()==movieDetailsArrayList.size()-1){
            Log.d(TAG, "onBindViewHolder: bottom reached");

        }
    }

    private String getReleaseYear(String releaseDate) {
        String[] year = releaseDate.split("-");
        Log.d(TAG, "getReleaseYear: ");
        return year[0];
    }

    @Override
    public int getItemCount() {
        return movieDetailsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        SimpleDraweeView movieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movie_name);
            movieImage = itemView.findViewById(R.id.movie_img);

        }
    }
}
