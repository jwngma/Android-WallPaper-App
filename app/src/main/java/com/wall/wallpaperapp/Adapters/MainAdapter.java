package com.wall.wallpaperapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wall.wallpaperapp.Activities.DetailsActivity;
import com.wall.wallpaperapp.Model.Main_model;
import com.wall.wallpaperapp.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private static final String TAG = "MainAdapter";

    private List<Main_model> main_modelList;
    private Context context;

    public MainAdapter(List<Main_model> main_modelList, Context context) {
        this.main_modelList = main_modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_items_layout,viewGroup,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {

        final Main_model currentItem=main_modelList.get(i);

        final String image_url=currentItem.getmImageurls();
        final String creator_name=currentItem.getmCreators();
        final String likes=currentItem.getmLikes();

        mainViewHolder.crestor_name.setText(creator_name);
        mainViewHolder.likes.setText(likes);
        Picasso.get().load(image_url).fit().into(mainViewHolder.Image);
        mainViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: maheee "+image_url);
                Log.d(TAG, "onClick: creator"+creator_name);
                Intent inte= new Intent(context, DetailsActivity.class);
                inte.putExtra("image_link",image_url);
                inte.putExtra("creator_link",creator_name);
                inte.putExtra("like_link",likes);
                context.startActivity(inte);
            }
        });


    }

    @Override
    public int getItemCount() {
        return main_modelList.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        private ImageView Image;
        private TextView crestor_name, likes;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            Image=itemView.findViewById(R.id.main_image);
            crestor_name=itemView.findViewById(R.id.creator_name);
            likes=itemView.findViewById(R.id.likes);
        }
    }
}
