package com.wall.wallpaperapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wall.wallpaperapp.Activities.category_ImagesActivity;
import com.wall.wallpaperapp.Adapters.ImagecategoryAdapter;
import com.wall.wallpaperapp.Adapters.SlideShowAdapter;
import com.wall.wallpaperapp.Model.ImageCategoryModel;
import com.wall.wallpaperapp.Model.SlideShowModel;
import com.wall.wallpaperapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    private static final String TAG = "SlideshowFragment";
    private RequestQueue requestQueue;
    private ViewPager slideViewpager;
    SlideShowAdapter slideShowAdapter;
    private List<SlideShowModel> slideShowModelList = new ArrayList<>();

    private ProgressDialog progressDialog;


    public SlideshowFragment() {

    }

    String image_link = "https://pixabay.com/api/?key=12233795-8e6920f550522c7b7b89432dc&q=logo&image_type=photo&pretty=true&per_page=200";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        progressDialog=new ProgressDialog(getContext());
        initSlideShowViewPager(view);

        return view;
    }

    private void initSlideShowViewPager(View view) {
        slideViewpager = view.findViewById(R.id.slideshow_viewpager);
        parseJason();

    }


    private void parseJason() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        requestQueue= Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, image_link, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String image_url = hit.getString("webformatURL");
                                String large_image=hit.getString("largeImageURL");
                                Log.d(TAG, "onResponse: image_url" + image_url);
                                String creators_name = hit.getString("user");
                                Log.d(TAG, "onResponse: user_name" + creators_name);
                                String likecount = hit.getString("likes");
                                Log.d(TAG, "onResponse: like_count" + likecount);
                                String tags = hit.getString("tags");
                                int all_word = tags.indexOf(tags);
                                String word = tags.substring(0, all_word);

                                slideShowModelList.add(new SlideShowModel(likecount,image_url,image_url,tags,image_url));

                            }
                            slideShowAdapter = new SlideShowAdapter(getContext(), slideShowModelList);
                            slideViewpager.setAdapter(slideShowAdapter);
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
