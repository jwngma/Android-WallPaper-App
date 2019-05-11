package com.wall.wallpaperapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wall.wallpaperapp.Adapters.MainAdapter;
import com.wall.wallpaperapp.Model.Main_model;
import com.wall.wallpaperapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView main_recy;
    private MainAdapter adapter;
    private RequestQueue requestQueue;
    List<Main_model> main_modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: creaatedd");
        initRecyclerview();
    }

    private void initRecyclerview() {

        main_recy = findViewById(R.id.main_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        main_recy.setLayoutManager(layoutManager);
        main_recy.setHasFixedSize(true);
        main_modelList= new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson() {
      //  String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        String url="https://pixabay.com/api/?key=12233795-8e6920f550522c7b7b89432dc&q=moon&image_type=photo&pretty=true&per_page=200";

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("hits");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject hit=jsonArray.getJSONObject(i);
                                String creators_name=hit.getString("user");
                                Log.d(TAG, "onResponse: user_name"+creators_name);
                                String image_url=hit.getString("webformatURL");
                                String likecount=hit.getString("likes");
                                main_modelList.add(new Main_model(image_url,creators_name,likecount));
                            }
                            adapter= new MainAdapter(main_modelList,MainActivity.this);
                            main_recy.setAdapter(adapter);
                        } catch (JSONException e) {;
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);




    }
}
