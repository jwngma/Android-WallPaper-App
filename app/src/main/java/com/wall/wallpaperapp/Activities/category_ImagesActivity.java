package com.wall.wallpaperapp.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.wall.wallpaperapp.Adapters.ImagecategoryAdapter;
import com.wall.wallpaperapp.Model.ImageCategoryModel;
import com.wall.wallpaperapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class category_ImagesActivity extends AppCompatActivity {
    private static final String TAG = "category_ImagesActivity";

    private RecyclerView category_recyclerview;
    private LinearLayoutManager linearLayoutManager;
    private RequestQueue requestQueue;
    private String category_link,category_name,category_type_image;

    List<ImageCategoryModel> imageCategoryModelList;
    ImagecategoryAdapter imagecategoryAdapter;
    private ProgressDialog progressDialog;

    //collapsing
    private ImageView collapse_image;
    private Toolbar mtoolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__images);
        Log.d(TAG, "onCreate: category_image_recyclerview is started");

        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading. . .");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        getIncomingIntent();
        initToolbar();
        initRecyclerview();
    }

    private void initToolbar() {
        mtoolbar=findViewById(R.id.category_activity_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle(category_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapse_image=findViewById(R.id.catergory_type_image);
        Picasso.get().load(category_type_image).into(collapse_image);

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("category_link")&getIntent().hasExtra("category_name")) {
            category_link = getIntent().getStringExtra("category_link");
            category_name=getIntent().getStringExtra("category_name");
            category_type_image=getIntent().getStringExtra("category_type_image");
        }
    }

    private void initRecyclerview() {
        category_recyclerview = findViewById(R.id.category_image_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        category_recyclerview.setHasFixedSize(true);
        category_recyclerview.setLayoutManager(linearLayoutManager);
        requestQueue = Volley.newRequestQueue(this);
        imageCategoryModelList = new ArrayList<>();
        parseJason();
    }

    private void parseJason() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, category_link, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String image_url = hit.getString("webformatURL");
                                Log.d(TAG, "onResponse: image_url" + image_url);
                                String creators_name = hit.getString("user");
                                Log.d(TAG, "onResponse: user_name" + creators_name);
                                String likecount = hit.getString("likes");
                                Log.d(TAG, "onResponse: like_count" + likecount);
                                String tags=hit.getString("tags");
                                int all_word=tags.indexOf(tags);
                                String word = tags.substring(0, all_word);

                                imageCategoryModelList.add(new ImageCategoryModel(image_url, likecount, creators_name,word));

                            }
                            imagecategoryAdapter= new ImagecategoryAdapter(category_ImagesActivity.this,imageCategoryModelList);
                            category_recyclerview.setAdapter(imagecategoryAdapter);
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
