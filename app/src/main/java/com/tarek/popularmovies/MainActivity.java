package com.tarek.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PostersAdapter.ListItemClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private final String API_URL_BASE = "https://api.themoviedb.org/3/movie/";
    private String sortBy;
    private final String POPULAR_MOVIES = "popular";
    private final String TOP_RATED_MOVIES = "top_rated";

    private RequestQueue mRequestQueue;
    //TODO Kindly, add your own api_key.
    private final String API_KEY = "";
    private Movie[] mMovies;
    private boolean sortByPopular = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       fetchingData();

//
    }

    private void fetchingData() {
        mRequestQueue = Volley.newRequestQueue(this);
        if(sortByPopular){sortBy=POPULAR_MOVIES;}
        else {sortBy=TOP_RATED_MOVIES;}
        String moviesAPI = API_URL_BASE + sortBy + "?api_key=" + API_KEY;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, moviesAPI, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("TAGG","a222sd");

                            mMovies = JsonUtil.parseJson(response);
                            setRecyclerView();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this, "Check your network!", Toast.LENGTH_LONG).show();
                    }
                });
        mRequestQueue.add(jsObjRequest);
    }

    public void setRecyclerView(){
        mRecyclerView = findViewById(R.id.recycler_view);
                mAdapter = new PostersAdapter(this,mMovies,this);
                mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poster_screen_menu, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) item.getActionView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
       R.array.spinner_list_item_array,R.layout.spinner_row);
        spinner.setAdapter(adapter); // set the adapter to provide layout of rows and content
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Popular")){sortByPopular=true;}
                else if (adapterView.getItemAtPosition(i).toString().equals("Top rated")){sortByPopular=false;}
                fetchingData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }); // set the listener, to perform actions based on item selection
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this,MovieDetails.class);
        intent.putExtra(getString(R.string.index),clickedItemIndex);
        startActivity(intent);

        Toast.makeText(this, "asdas", Toast.LENGTH_SHORT).show();
    }
}
