package com.tarek.popularmovies;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    private static final String RESULTS_KEY = "results";
    private static final String POSTER_PATH_KEY = "poster_path";
    private static final String VOTE_AVERAGE_KEY = "vote_average";
    private static final String TITLE_KEY = "title";
    private static final String OVERVIEW_KEY = "overview";
    private static final String RELEASE_DATE = "release_date";
    public static int postersToShow=10;
    public static Movie[] movies;

    public static Movie[] parseJson(JSONObject jsonData) throws JSONException {
        movies = new Movie[postersToShow];

        JSONArray jsonArray = jsonData.getJSONArray(RESULTS_KEY);
        for (int i=0;i<postersToShow;i++){
            JSONObject movieObject = jsonArray.getJSONObject(i);
            movies[i] = new Movie(movieObject.getString(POSTER_PATH_KEY),
                    movieObject.getInt(VOTE_AVERAGE_KEY),
                    movieObject.getString(OVERVIEW_KEY),
                    movieObject.getString(TITLE_KEY),
                    movieObject.getString(RELEASE_DATE));

        }
        return movies;
    }
    public static Movie getMovie(int position){
        return movies[position];
    }
}
