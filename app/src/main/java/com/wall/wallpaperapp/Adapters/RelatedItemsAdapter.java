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

import com.android.volley.Response;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wall.wallpaperapp.Activities.DetailsActivity;
import com.wall.wallpaperapp.Model.RelatedItemsModel;
import com.wall.wallpaperapp.R;



import java.util.List;

public class RelatedItemsAdapter  extends RecyclerView.Adapter<RelatedItemsAdapter.RelatedItemsViewHolder>{
    private static final String TAG = "RelatedItemsAdapter";
    private Context context;
    private List<RelatedItemsModel> relatedItemsModelList;

    public RelatedItemsAdapter(Context context, List<RelatedItemsModel> relatedItemsModelList) {
        this.context = context;
        this.relatedItemsModelList = relatedItemsModelList;
    }

    @NonNull
    @Override
    public RelatedItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_items_layout, viewGroup, false);
        return new RelatedItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RelatedItemsViewHolder relatedItemsViewHolder, int i) {
        final RelatedItemsModel currentItem=relatedItemsModelList.get(i);

        final String image_url=currentItem.getImage_link();
        final String creator_name=currentItem.getCreator_name();
        final String likes=currentItem.getLikes();

        relatedItemsViewHolder.crestor_name.setText("Creator's Name:"+creator_name);
        relatedItemsViewHolder.likes.setText("Likes:"+ likes);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).fit().centerInside().into(relatedItemsViewHolder.Image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

                Picasso.get().load(image_url).into(relatedItemsViewHolder.Image);

            }
        });

        relatedItemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        return relatedItemsModelList.size();
    }

    public  static  class RelatedItemsViewHolder extends RecyclerView.ViewHolder{
        private ImageView Image;
        private TextView crestor_name, likes;

        public RelatedItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.main_image);
            crestor_name=itemView.findViewById(R.id.creator_name);
            likes=itemView.findViewById(R.id.likes);
        }
    }
}
