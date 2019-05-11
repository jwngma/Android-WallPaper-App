package com.wall.wallpaperapp.Model;

public class RelatedItemsModel {

    private String image_link;
    private String likes;
    private String creator_name;

    public RelatedItemsModel() {
    }

    public RelatedItemsModel(String image_link, String likes, String creator_name) {
        this.image_link = image_link;
        this.likes = likes;
        this.creator_name = creator_name;
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
