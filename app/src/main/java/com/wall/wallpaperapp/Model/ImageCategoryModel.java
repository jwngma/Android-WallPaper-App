package com.wall.wallpaperapp.Model;

public class ImageCategoryModel {

    private String image_link;
    private String likes;
    private String creator_name;
    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ImageCategoryModel() {
    }

    public ImageCategoryModel(String image_link, String likes, String creator_name,String tags) {
        this.image_link = image_link;
        this.likes = likes;
        this.creator_name = creator_name;
        this.tags=tags;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }
}
