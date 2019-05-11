package com.wall.wallpaperapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wall.wallpaperapp.Activities.category_ImagesActivity;
import com.wall.wallpaperapp.Model.Category_Model;
import com.wall.wallpaperapp.R;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.CategoryViewHolder> {


    private Context context;
    private List<Category_Model> categoryModelList;

    public Category_Adapter(Context context, List<Category_Model> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }



    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_layout, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder categoryViewHolder, int i) {

        Category_Model currentItem=categoryModelList.get(i);
        final String category_name=currentItem.getCategory_name();
        final String category_image=currentItem.getCategory_image();



        final String url1="https://pixabay.com/api/?key=12233795-8e6920f550522c7b7b89432dc&q=";
        final String url2="&image_type=photo&per_page=150&orientation=vertical";
        final String final_string=url1+category_name+url2;


        Picasso.get().load(category_image).placeholder(R.drawable.wall_paper_logo).networkPolicy(NetworkPolicy.OFFLINE).fit()
                .centerInside().into(categoryViewHolder.category_image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

                Picasso.get().load(category_image).into(categoryViewHolder.category_image);

            }
        });

        categoryViewHolder.category_name.setText(currentItem.getCategory_name());

        categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent= new Intent(context, category_ImagesActivity.class);
                detailsIntent.putExtra("category_link",final_string);
                detailsIntent.putExtra("category_name",category_name);
                detailsIntent.putExtra("url1",url1);
                detailsIntent.putExtra("url2",url2);
                detailsIntent.putExtra("category_type_image",category_image);

                context.startActivity(detailsIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView category_image;
        private TextView category_name;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);
        }
    }
}
