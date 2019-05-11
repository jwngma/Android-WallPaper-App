package com.wall.wallpaperapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wall.wallpaperapp.Model.Detail_ViewPagerModel;
import com.wall.wallpaperapp.R;

import java.util.List;

public class DetailsViewPager extends PagerAdapter {

    private List<Detail_ViewPagerModel> detailModelList;

    public DetailsViewPager(List<Detail_ViewPagerModel> detailModelList) {
        this.detailModelList = detailModelList;
    }

    @Override
    public int getCount() {
        return detailModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpager_item_layout, container, false);
        final ImageView sliderImage = view.findViewById(R.id.viewpager_image);
        TextView name = view.findViewById(R.id.viewpager_name);
        TextView like = view.findViewById(R.id.viewpager_like);

        Picasso.get().load(detailModelList.get(position).getImage()).placeholder(R.drawable.piki_icon).fit().centerInside().into(sliderImage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(detailModelList.get(position).getImage()).into(sliderImage);
            }
        });
        name.setText(detailModelList.get(position).getTitle());
        like.setText(detailModelList.get(position).getLikes());
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
