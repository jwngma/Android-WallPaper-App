package com.wall.wallpaperapp.Adapters;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tapadoo.alerter.Alerter;
import com.wall.wallpaperapp.Activities.DetailsActivity;
import com.wall.wallpaperapp.Activities.HomeActivity;
import com.wall.wallpaperapp.Model.SlideShowModel;
import com.wall.wallpaperapp.R;

import java.io.IOException;
import java.util.List;

public class SlideShowAdapter  extends PagerAdapter {

    private Context context;
    private List<SlideShowModel> slideShowModelList;
    public SlideShowAdapter(Context context, List<SlideShowModel> slideShowModelList) {
        this.context = context;
        this.slideShowModelList = slideShowModelList;
    }



    @Override
    public int getCount() {
        return slideShowModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.slideshow_item_layout,container,false);

        TextView slide_description=view.findViewById(R.id.slide_show_image_tag);
        TextView slide_image_like=view.findViewById(R.id.slide_show_image_like);
        TextView setWallpaperr=view.findViewById(R.id.setWallpaperr);
        final ImageView slide_image=view.findViewById(R.id.slide_show_image);

        slide_description.setText(slideShowModelList.get(position).getTags());
        slide_image_like.setText(slideShowModelList.get(position).getLikes());
        Picasso.get().load(slideShowModelList.get(position).getWebformatURL()).
                placeholder(R.drawable.wall_paper_logo).fit().centerInside().into(slide_image, new Callback() {
            @Override
            public void onSuccess() {

            }
            @Override
            public void onError(Exception e) {
                Picasso.get().load(slideShowModelList.get(position).getLargeImageURL()).into(slide_image);
            }
        });

        setWallpaperr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallPaperMethod(position);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }


    private void setWallPaperMethod(int position) {
        Picasso.get().load(slideShowModelList.get(position).getWebformatURL()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                try {
                    wallpaperManager.setBitmap(bitmap);
                    Alerter.create((Activity) context)
                            .setTitle("Thanks You!!!")
                            .setText(" Wallpaper Has been Set" )
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
                Toast.makeText(context, "Failed to load"+e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
