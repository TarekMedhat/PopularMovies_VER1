package com.tarek.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
    private Movie mMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        int position = intent.getIntExtra(getString(R.string.index),-1);
        mMovie = JsonUtil.getMovie(position);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView releaseYearTV = findViewById(R.id.releaseYearTextView);
        TextView ratingTV = findViewById(R.id.ratingTextView);
        ImageView posterIV = findViewById(R.id.posterImageView);
        TextView overviewTV = findViewById(R.id.overviewTextView);
        toolbar.setTitle(mMovie.getTitle());
        Picasso.with(this).load(mMovie.getPosterURL()).into(posterIV);
        releaseYearTV.setText(mMovie.getReleaseDate());
        ratingTV.setText(mMovie.getVoteAverage()+"/10");
        overviewTV.setText(mMovie.getOverview());
    }
}
