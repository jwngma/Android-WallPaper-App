package com.wall.wallpaperapp.Model;

public class Detail_ViewPagerModel {

    private String image;
    private String title;
    private String likes  ;

    public Detail_ViewPagerModel() {
    }

    public Detail_ViewPagerModel(String image, String title, String likes) {
        this.image = image;
        this.title = title;
        this.likes = likes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
