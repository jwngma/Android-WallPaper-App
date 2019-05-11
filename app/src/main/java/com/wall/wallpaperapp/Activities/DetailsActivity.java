package com.wall.wallpaperapp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tapadoo.alerter.Alerter;
import com.wall.wallpaperapp.Adapters.DetailsViewPager;
import com.wall.wallpaperapp.Adapters.ImagecategoryAdapter;
import com.wall.wallpaperapp.Adapters.RelatedItemsAdapter;
import com.wall.wallpaperapp.Model.Detail_ViewPagerModel;
import com.wall.wallpaperapp.Model.ImageCategoryModel;
import com.wall.wallpaperapp.Model.RelatedItemsModel;
import com.wall.wallpaperapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";

    private ImageView details_image;
    private TextView creator_name;
    private String detail_image_link, creator_name_link, like_text_link,tags_link;
    //setWallPaper
    private TextView setWallPaper;
    public ProgressDialog progressDialog;
    private Toolbar mtoolbar;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Log.d(TAG, "onCreate: details created");

        mtoolbar=findViewById(R.id.detail_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog=new ProgressDialog(this);
        getIncomingIntent();
        initDetailsImage();

    }
    private void getIncomingIntent() {
        if (getIntent().hasExtra("image_link") && getIntent().hasExtra("creator_link")&& getIntent().hasExtra("like_link")){
            detail_image_link=getIntent().getStringExtra("image_link");
            creator_name_link=getIntent().getStringExtra("creator_link");
            like_text_link=getIntent().getStringExtra("like_link");
            tags_link=getIntent().getStringExtra("tags");
        }
    }

    private void initDetailsImage() {

        details_image = findViewById(R.id.detailed_image);
        creator_name = findViewById(R.id.details_creator_name);
        setWallPaper=findViewById(R.id.setWallPaper);

        Picasso.get().load(detail_image_link).into(details_image);
       creator_name.setText(creator_name_link);
        setWallPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Setting wallpepr clicked");
                setWallPaperMethod();
            }
        });

    }

    private void setWallPaperMethod() {
        progressDialog.show();
       Picasso.get().load(detail_image_link).into(new Target() {
           @Override
           public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
               progressDialog.dismiss();
               WallpaperManager wallpaperManager = WallpaperManager.getInstance(DetailsActivity.this);
               try {
                   wallpaperManager.setBitmap(bitmap);
                   Alerter.create(DetailsActivity.this )
                           .setTitle("Thanks You!!!")
                           .setText(" Your Wallpaper Has been Set" )
                           .setBackgroundColorRes(R.color.colorPrimary)
                           .enableSwipeToDismiss()
                           .setDuration(500)
                           .show();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onBitmapFailed(Exception e, Drawable errorDrawable) {
               progressDialog.dismiss();
               Toast.makeText(DetailsActivity.this, "Failed to load"+e.getMessage(), Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onPrepareLoad(Drawable placeHolderDrawable) {

           }
       });
    }

}
