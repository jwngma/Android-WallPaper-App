package com.wall.wallpaperapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wall.wallpaperapp.Activities.DetailsActivity;
import com.wall.wallpaperapp.Model.ImageCategoryModel;
import com.wall.wallpaperapp.R;

import java.util.List;

public class ImagecategoryAdapter extends RecyclerView.Adapter<ImagecategoryAdapter.ImagesCategoryViewHolder> {
    private static final String TAG = "ImagecategoryAdapter";

    private Context context;
    private List<ImageCategoryModel> imageCategoryModelList;

    public ImagecategoryAdapter(Context context, List<ImageCategoryModel> imageCategoryModelList) {
        this.context = context;
        this.imageCategoryModelList = imageCategoryModelList;
    }

    @NonNull
    @Override
    public ImagesCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_items_layout, viewGroup, false);
        return new ImagesCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImagesCategoryViewHolder imagesCategoryViewHolder, final int i) {
        final ImageCategoryModel currentItem=imageCategoryModelList.get(i);

        final String image_url=currentItem.getImage_link();
        final String creator_name=currentItem.getCreator_name();
        final String likes=currentItem.getLikes();
        final String tags=currentItem.getTags();
        String url1="https://pixabay.com/api/?key=12233795-8e6920f550522c7b7b89432dc&q=";
        String url2="&image_type=photo&per_page=150&orientation=vertical";
        final String related_link=url1+tags+url2;

        imagesCategoryViewHolder.crestor_name.setText("Creator's Name:"+creator_name);
        imagesCategoryViewHolder.likes.setText("Likes:"+ likes);
        Picasso.get().load(image_url).placeholder(R.drawable.wall_paper_logo).networkPolicy(NetworkPolicy.OFFLINE).fit()
                .centerInside().into(imagesCategoryViewHolder.Image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

                Picasso.get().load(image_url).into(imagesCategoryViewHolder.Image);

            }
        });

        imagesCategoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: maheee "+image_url);
                Log.d(TAG, "onClick: creator"+creator_name);
                Intent inte= new Intent(context, DetailsActivity.class);
                inte.putExtra("image_link",image_url);
                inte.putExtra("creator_link",creator_name);
                inte.putExtra("like_link",likes);
                inte.putExtra("tags",related_link);
                context.startActivity(inte);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageCategoryModelList.size();
    }

    public static class ImagesCategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView Image;
        private TextView crestor_name, likes;

        public ImagesCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.main_image);
            crestor_name=itemView.findViewById(R.id.creator_name);
            likes=itemView.findViewById(R.id.likes);
        }
    }
}
