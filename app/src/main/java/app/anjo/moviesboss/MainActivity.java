package app.anjo.moviesboss;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private ProgressDialog pDialog,pDialog2;

    private static final String TAG_TITLE = "Title";
    private static final String TAG_RELEASED = "Released";
    private static final String TAG_RUNTIME = "Runtime";
    private static final String TAG_GENRE = "Genre";
    private static final String TAG_DIRECTOR = "Director";
    private static final String TAG_PLOT = "Plot";
    private static final String TAG_ACTORS = "Actors";
    private static final String TAG_AWARDS = "Awards";
    private static final String TAG_POSTER = "Poster";

    public static String poster_final, iserror;
    ImageView iv;
    public  String url_global,foto_global;

    JSONArray movies = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> moviesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pelicula = getIntent().getStringExtra("nombre_peli");
        pelicula = pelicula.replace(" ","+");

        String url = "http://www.omdbapi.com/?t="+pelicula+"&y=&plot=short&r=json";
        url_global=url;


        moviesList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String title = ((TextView) view.findViewById(R.id.title))
                        .getText().toString();
                String released = ((TextView) view.findViewById(R.id.released))
                        .getText().toString();
                String runtime = ((TextView) view.findViewById(R.id.runtime))
                        .getText().toString();
                String genre = ((TextView) view.findViewById(R.id.genre))
                        .getText().toString();
                String director = ((TextView) view.findViewById(R.id.director))
                        .getText().toString();
                String plot = ((TextView) view.findViewById(R.id.plot))
                        .getText().toString();
                String actors = ((TextView) view.findViewById(R.id.actors))
                        .getText().toString();
                String awards = ((TextView) view.findViewById(R.id.awards))
                        .getText().toString();




            }
        });

        // Calling async task to get json
        new GetMovie().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetMovie extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url_global, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try{
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node

                    String error = jsonObj.getString("Response");
                    iserror=error;

                    String title = jsonObj.getString(TAG_TITLE);
                    String released = jsonObj.getString(TAG_RELEASED);
                    String runtime = jsonObj.getString(TAG_RUNTIME);
                    String genre = jsonObj.getString(TAG_GENRE);
                    String director = jsonObj.getString(TAG_DIRECTOR);
                    String actors = jsonObj.getString(TAG_ACTORS);
                    String awards = jsonObj.getString(TAG_AWARDS);
                    String plot = jsonObj.getString(TAG_PLOT);
                    String foto = jsonObj.getString(TAG_POSTER);

                    foto_global=foto;

                    HashMap<String, String> movie = new HashMap<String, String>();

                    movie.put(TAG_TITLE,"Title: "+ title);
                    movie.put(TAG_RELEASED,"Released: "+ released);
                    movie.put(TAG_RUNTIME,"Runtime: "+ runtime);
                    movie.put(TAG_GENRE,"Genre: "+genre);
                    movie.put(TAG_DIRECTOR,"Director: "+director);
                    movie.put(TAG_PLOT,"Plot: "+plot);
                    movie.put(TAG_ACTORS,"Actors: "+actors);
                    movie.put(TAG_AWARDS,"Awards: "+awards);


                    // adding movie
                    moviesList.add(movie);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();


            if(iserror.compareTo("False")==0){

                getApplicationContext().startActivity(new Intent(getApplicationContext(), Start.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, moviesList,
                    R.layout.list_item, new String[] {TAG_TITLE,
                    TAG_RELEASED,TAG_RUNTIME,TAG_GENRE,TAG_DIRECTOR,TAG_ACTORS,TAG_AWARDS,TAG_PLOT }, new int[] {R.id.title, R.id.released, R.id.runtime, R.id.genre, R.id.director, R.id.actors, R.id.awards, R.id.plot});

            setListAdapter(adapter);

            iv = (ImageView) findViewById(R.id.poster1);
            Picasso.with(getApplicationContext()).load(foto_global).into(iv);

        }

    }

}