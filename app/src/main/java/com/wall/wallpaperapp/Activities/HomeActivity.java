package com.wall.wallpaperapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;
import com.wall.wallpaperapp.Adapters.Category_Adapter;
import com.wall.wallpaperapp.Fragments.SlideshowFragment;
import com.wall.wallpaperapp.Model.Category_Model;
import com.wall.wallpaperapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private RecyclerView category_recyclerview;
    private GridLayoutManager gridLayoutManager;
    private DrawerLayout drawer;
    private RelativeLayout rel_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rel_alpha=findViewById(R.id.rel_alpha);
        initToolbar();
        initNavigation();
        initcategoryRecyclerview();


    }



    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("WallPaper App");
    }

    private void initNavigation() {
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initcategoryRecyclerview() {
        /*https://pixabay.com/get/e83cb6062cf0043ed1584d05fb1d4794e174e1d210b40c4090f5c778a4ecb3bfdb_640.jpg
         https://pixabay.com/get/e835b3072cf41c22d2524518b74d4494e074e5dc1cac104490f6c07aa6eabdba_1280.jpg
         "https://pixabay.com/get/e834b80d2ef2033ed1584d05fb1d4794e174e1d210b40c4090f5c778a4ecbdbbd9_1280.jpg"

         */


        List<Category_Model>category_modelList= new ArrayList<>();
        category_modelList.add(new Category_Model("https://pixabay.com/get/eb3db70b2ef0063ed1584d05fb1d4794e174e1d210b40c4090f5c778afedb7bed0_640.jpg","Animals"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e835b3072cf41c22d2524518b74d4494e074e5dc1cac104490f6c071a7eeb5b9_640.jpg","Architecture"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e835b60d2bf2073ed1584d05fb1d4794e174e1d210b40c4090f5c778afedb6bbda_640.jpg","Backgrounds"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e836b00621f1003ed1584d05fb1d4794e174e1d210b40c4090f5c778afedb1b9de_1280.jpg","Beauty"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e837b1062ef0013ed1584d05fb1d4794e174e1d210b40c4090f5c778afedb0b9d1_640.jpg","Business"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e131b8072af11c22d2524518b74d4494e074e5dc1cac104490f6c071a7e8b0bf_1280.jpg","Computer"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e034b90b2cfd1c22d2524518b74d4494e074e5dc1cac104490f6c071a7e8bdb9_640.jpg","Education"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/eb3db60728f5003ed1584d05fb1d4794e174e1d210b40c4090f5c778afedb3b8df_640.jpg","Emotions"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ea34b20b20f6093ed1584d05fb1d4794e174e1d210b40c4090f5c778afedb3bbd8_640.jpg","Health"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/eb30b90820f21c22d2524518b74d4494e074e5dc1cac104490f6c071a7ebb3be_1280.jpg","Industry"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/eb33b6062cfd1c22d2524518b74d4494e074e5dc1cac104490f6c071a7ebbcba_640.jpg","Statue"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/eb31b9092df6093ed1584d05fb1d4794e174e1d210b40c4090f5c778afedb2bddc_1280.jpg","angels"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ec32b0062bfc1c22d2524518b74d4494e074e5dc1cac104490f6c071a7e4b3bd_640.jpg","Craft"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e031b70828f21c22d2524518b74d4494e074e5dc1cac104490f6c071a7e4bdb1_1280.jpg","Music"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e835b60d20f6003ed1584d05fb1d4794e174e1d210b40c4090f5c778afecb5badd_1280.jpg","Nature"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/eb37b10f2dfc1c22d2524518b74d4494e074e5dc1cac104490f6c071a6edb3b1_640.jpg","Landscapes"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e83db90c2ef1093ed1584d05fb1d4794e174e1d210b40c4090f5c778afecb4b8da_1280.jpg","WildLife"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ea35b70b2ef7023ed1584d05fb1d4794e174e1d210b40c4090f5c778afecb4bade_1280.jpg","People"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ea36b90b2bfc073ed1584d05fb1d4794e174e1d210b40c4090f5c778afecb4bcd1_640.jpg","Places"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ea30b50f20fc023ed1584d05fb1d4794e174e1d210b40c4090f5c778afecb4b0d1_1280.jpg","Monuments"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ef34b40b29f11c22d2524518b74d4494e074e5dc1cac104490f6c071a6efb7ba_640.jpg","Religion"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e834b10f21e90021d85a5854e74e4495e070ebd004b0144493f1c978a5e9b1_640.jpg","Science"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e837b10628f4093ed1584d05fb1d4794e174e1d210b40c4090f5c778afecb7b1d8_1280.jpg","Technology"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ef30b40c2df71c22d2524518b74d4494e074e5dc1cac104490f6c071a6eeb0bb_1280.jpg","Sports"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ea3db00d2bf71c22d2524518b74d4494e074e5dc1cac104490f6c071a6eeb2be_1280.jpg","Transportation"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/eb36b90b2ffc1c22d2524518b74d4494e074e5dc1cac104490f6c071a7e4b4b1_640.jpg","Traffic"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/e837b2092dfc003ed1584d05fb1d4794e174e1d210b40c4090f5c778afedbdbfd1_1280.jpg","Travel"));
        category_modelList.add(new Category_Model("https://pixabay.com/get/ef3cb10f2bf01c22d2524518b74d4494e074e5dc1cac104490f6c071a7e5b0bd_1280.jpg","Vacation"));

        Category_Adapter category_adapter= new Category_Adapter(this,category_modelList);
        category_recyclerview=findViewById(R.id.category_recyclerview);
        gridLayoutManager=new GridLayoutManager(this,2);
        category_recyclerview.setHasFixedSize(true);
        category_recyclerview.setLayoutManager(gridLayoutManager);
        category_recyclerview.setAdapter(category_adapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                final AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("About us");
                builder.setMessage("This Application is developed with the help of pixabay.com, all the Images in this application belongs to pixabay.com");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
                builder.show();
                break;
        }
        return true;


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:

                Intent home_intent= new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(home_intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                closeDrawer();
                break;
            case R.id.nav_trending:

                Alerter.create(HomeActivity.this)
                        .setTitle("Thanks For the Click!!!")
                        .setText( item.getTitle()+" Has been clicked" )
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .enableSwipeToDismiss()
                        .setDuration(500)
                        .show();
                closeDrawer();
                break;
            case R.id.nav_slideshow:
                setFragment(new SlideshowFragment());
                rel_alpha.setAlpha(0);
                closeDrawer();
                break;
            case R.id.nav_share:
                Intent shareintent= new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                String shareSub=" Your Sub Here";
                String shareBody=" Your sms will appear here";
                shareintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                shareintent.putExtra(Intent.EXTRA_TEXT,shareBody);
                shareintent.putExtra(Intent.EXTRA_EMAIL,shareBody);
                startActivity(Intent.createChooser(shareintent,"Share Using"));
                closeDrawer();
                break;
            case R.id.nav_rating:
                Intent intent=new Intent(HomeActivity.this,RatingActivity.class);
                startActivity(intent);
                closeDrawer();
                break;
        }
        return true;
    }

    private void closeDrawer() {

        drawer.closeDrawer(GravityCompat.START);
    }
    private void openDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }

    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }
}
