package com.tarek.popularmovies;

import java.util.ArrayList;

public class Movie {

    private String posterPath;
    private int voteAverage;
    private String overview;
    private String title;
    private String releaseDate;

    public Movie(String posterPath, int voteAverage, String overview, String title, String releaseDate) {
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getPosterURL(){
        final String IMG_BASE_URL = "http://image.tmdb.org/t/p/";
        final String IMG_SIZE = "w185";
        String posterURL = IMG_BASE_URL + IMG_SIZE + posterPath;
        return posterURL;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        String releaseYear = releaseDate.substring(0, Math.min(releaseDate.length(), 4));
        return releaseYear;
    }
}
